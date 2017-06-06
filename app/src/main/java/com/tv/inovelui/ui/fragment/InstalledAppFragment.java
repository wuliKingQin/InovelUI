package com.tv.inovelui.ui.fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.text.TextUtils;

import com.tv.framework.utils.ToastUtil;
import com.tv.inovelui.model.Category;
import com.tv.inovelui.model.IModel;
import com.tv.inovelui.model.InstalledApp;
import com.tv.inovelui.presenter.InstalledAppPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：已安装过的app碎片
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/11- 17:17
 */

public class InstalledAppFragment extends AbsBaseRowsFragment{

    @Override
    public int getNumRows() {
        return 2;
    }

    @Override
    protected void getCategories(List<Category> categories) {
        categories.clear();
        registerAppInstallReceiver();
        loadInstalledApps(categories);
    }

    @Override
    protected Presenter getModelPresenter() {
        return new InstalledAppPresenter(getActivity());
    }

    /**
     * 获取已经安装的app
     */
    private void loadInstalledApps(List<Category> categories) {
        InstalledApp app;
        List<IModel> models = new ArrayList<>();
        final List<PackageInfo> packageInfos = getActivity().getPackageManager().getInstalledPackages(0);
        for (PackageInfo packageInfo : packageInfos) {
//            //过滤掉系统app
//            if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0) {
//                continue;
//            }
            if (packageInfo.applicationInfo.loadIcon(getActivity().getPackageManager()) == null) {
                continue;
            }
            if(TextUtils.equals(packageInfo.packageName,getActivity().getPackageName())) {
                continue;
            }
            app = getInstalledApp(packageInfo.packageName, packageInfo.applicationInfo);
            models.add(app);
        }
        categories.add(new Category(models));
    }

    /**
     * 根据包信息获取app
     * @param packageInfo 包信息
     * @return 获取安装包信息
     */
    private InstalledApp getInstalledApp(String packageName,ApplicationInfo packageInfo) {
        final InstalledApp app = new InstalledApp();
        app.setTitle(packageInfo.loadLabel(getActivity().getPackageManager()).toString());
        app.setPackageName(packageName);
        app.setAppIcon(packageInfo.loadIcon(getActivity().getPackageManager()));
        return app;
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        openApk((InstalledApp) item);
    }

    /**
     * 安装apk
     * @param app app信息
     */
    private void openApk(InstalledApp app) {
        try {
            PackageManager packageManager = getActivity().getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage(app.getPackageName());
            startActivity(intent);
        } catch (Exception e) {
            ToastUtil.toast(getActivity(),"打开失败");
        }
    }

    /**
     * 卸载app
     * @param packageName 包名
     */
    private void uninstallApk(String packageName) {
        final Uri uri = Uri.parse("package:" + packageName);
        final Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        startActivity(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mAppInstallReceiver);
    }

    /**
     * 删除孩子数据
     * @param packageName 包名
     */
    private void removeChildData(String packageName) {
        final List<InstalledApp> category = getChildData(0);
        if(category != null) {
            InstalledApp installedApp = null;
            for(InstalledApp app:category) {
                if(TextUtils.equals(app.getPackageName(), packageName)) {
                    installedApp = app;
                    break;
                }
            }
            if(installedApp != null) {
                removerChildData(0, installedApp);
            }
        }
    }

    /**
     * 注册广播接收器
     */
    private void registerAppInstallReceiver() {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_ADDED );
        filter.addDataScheme("package");
        getActivity().registerReceiver(mAppInstallReceiver,filter);
    }

    /**
     * app安装和卸载广播接收器
     */
    private final BroadcastReceiver mAppInstallReceiver = new  BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            final String packageName = intent.getData().getSchemeSpecificPart();
            if(Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
                removeChildData(packageName);
            } else if(Intent.ACTION_PACKAGE_ADDED.equals(action)) {
                try {
                    final ApplicationInfo applicationInfo = getActivity().getPackageManager().getApplicationInfo(packageName,0);
                    final InstalledApp installedApp = getInstalledApp(packageName, applicationInfo);
                    putChildData(0, installedApp);
                } catch (PackageManager.NameNotFoundException e) {
                }
            }
        }
    };
}
