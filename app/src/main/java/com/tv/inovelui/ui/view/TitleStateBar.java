package com.tv.inovelui.ui.view;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.tv.inovelui.R;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 功能描述：状态栏，包含WIFI状态，蓝牙状态和usb状态
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/17- 9:39
 */

public class TitleStateBar extends FrameLayout {

    //usb状态
    private ImageView mUsbStateTv;
    //wifi连接状态
    private ImageView mWifiStateIv;
    //蓝牙连接状态
    private ImageView mBluetoothStateTv;
    //用来检查wifi状态
    private Timer mCheckWifiStateTimer = new Timer("wifiStateTimer");

    public TitleStateBar(Context context) {
        this(context,null);
    }

    public TitleStateBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleStateBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_state_bar,this);
        mUsbStateTv = (ImageView) findViewById(R.id.iv_usb);
        mWifiStateIv = (ImageView) findViewById(R.id.iv_wifi);
        mBluetoothStateTv = (ImageView) findViewById(R.id.iv_ble);

        updateWifiState();
        updateBleState();
        updateUsbState();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        final IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        getContext().registerReceiver(mIntentReceiver,filter);

        mCheckWifiStateTimer.schedule(mCheckWifiStateTask,0, 1000);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().unregisterReceiver(mIntentReceiver);
        if(mCheckWifiStateTimer != null) {
            mCheckWifiStateTimer.cancel();
            mCheckWifiStateTask.cancel();
        }
        mCheckWifiStateTask = null;
        mCheckWifiStateTimer = null;
    }

    //用于监听wifi，蓝牙和usb的状态改变
    private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if(WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {
                updateWifiState();
            } else if(BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                updateBleState();
            } else if(UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action) ||
                    UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                updateUsbState();
            }
        }
    };

    /**
     * 更新wifi状态
     */
    private void updateWifiState() {
        final WifiManager wifiMgr = (WifiManager)getContext().getSystemService(Context.WIFI_SERVICE);
        if(wifiMgr != null) {
            if(wifiMgr.isWifiEnabled()) {
                final WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
                if(wifiInfo != null) {
                    final int rssi = wifiInfo.getRssi();
                    if(rssi >= -50) {
                        mWifiStateIv.setBackgroundResource(R.mipmap.ic_wifi_3);
                    } else if(rssi < -50 && rssi >= -70) {
                        mWifiStateIv.setBackgroundResource(R.mipmap.ic_wifi_2);
                    } else if(rssi < -70 && rssi >= -126){
                        mWifiStateIv.setBackgroundResource(R.mipmap.ic_wifi_1);
                    } else {
                        mWifiStateIv.setBackgroundResource(R.mipmap.ic_wifi_off);
                    }
                } else {
                    mWifiStateIv.setBackgroundResource(R.mipmap.ic_wifi_off);
                }
            } else {
                mWifiStateIv.setBackgroundResource(R.mipmap.ic_wifi_off);
            }
        } else {
            mWifiStateIv.setBackgroundResource(R.mipmap.ic_wifi_off);
        }
    }

    /**
     * 更新蓝牙状态
     */
    private void updateBleState() {
        final BluetoothManager bluetoothMgr = (BluetoothManager) getContext().getSystemService(Context.BLUETOOTH_SERVICE);
        if(bluetoothMgr != null) {
            final BluetoothAdapter adapter = bluetoothMgr.getAdapter();
            if(adapter != null && adapter.enable()) {
                mBluetoothStateTv.setBackgroundResource(R.mipmap.ic_bluetooth_on);
            } else {
                mBluetoothStateTv.setBackgroundResource(R.mipmap.ic_bluetooth_off);
            }
        } else {
            mBluetoothStateTv.setBackgroundResource(R.mipmap.ic_bluetooth_off);
        }
    }

    /**
     * 检查USB状态
     */
    private void updateUsbState() {
        final UsbManager usbMgr = (UsbManager)getContext().getSystemService(Context.USB_SERVICE);
        if(usbMgr != null) {
            final HashMap<String,UsbDevice> usbDeivces =  usbMgr.getDeviceList();
            if(usbDeivces != null) {
                final int size = usbDeivces.size();
                if(size == 1) {
                    mUsbStateTv.setBackgroundResource(R.mipmap.ic_usb_1);
                } else if(size == 2) {
                    mUsbStateTv.setBackgroundResource(R.mipmap.ic_usb_2);
                } else {
                    mUsbStateTv.setVisibility(GONE);
                }
            } else {
                mUsbStateTv.setVisibility(GONE);
            }
        } else {
            mUsbStateTv.setVisibility(GONE);
        }
    }

    //处理wifi状态
    private final Handler mWifiStateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 200) {
                updateWifiState();
                updateUsbState();
            }
        }
    };

    //wifi状态运行任务
    private TimerTask mCheckWifiStateTask = new TimerTask() {
        @Override
        public void run() {
            mWifiStateHandler.obtainMessage(200).sendToTarget();
        }
    };
}
