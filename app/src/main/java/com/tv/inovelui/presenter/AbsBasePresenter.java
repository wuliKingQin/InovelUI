package com.tv.inovelui.presenter;

import android.content.Context;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.ShadowOverlayContainer;
import android.support.v7.widget.CardView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tv.inovelui.R;
import com.tv.inovelui.model.IModel;

/**
 * 功能描述：抽象的基本视图填充器,该类如何选择不含参数的实例，则自动使用ImageCardView作为Item的布局
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/3/7- 16:45
 */

public abstract class AbsBasePresenter<M extends IModel> extends Presenter {

    //保存选中的颜色
    private int mSelectedBackgroundColor;
    //保存默认的颜色
    private int mDefaultBackgroundColor;
    //保存子布局资源ID
    private int mItemLayoutResId;
    //运行环境
    private Context mContext;

    public AbsBasePresenter(Context context) {
        this(context,R.style.DefaultCardTheme);
    }

    public AbsBasePresenter(Context context, int themeResId) {
        mContext = new ContextThemeWrapper(context,themeResId);
    }

    /**
     * 创建视图保持器
     * @param itemView 子布局视图
     * @return ViewHolder子类实例
     */
    protected abstract ViewHolder onCreateViewHolder(View itemView);

    /**
     * 为视图绑定数据
     * @param viewHolder ViewHolder子类实例
     * @param item bean实例
     */
    protected abstract void onBindViewData(ViewHolder viewHolder, M item);

    /**
     * 解除视图绑定的数据
     * @param viewHolder 视图
     */
    protected abstract void onUnBindViewData(ViewHolder viewHolder);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView;
        try {
            mDefaultBackgroundColor = getItemDefaultBackgroundColor(mContext);
            mSelectedBackgroundColor = getItemSelectedBackgroundColor(mContext);
            itemView = LayoutInflater.from(mContext).inflate(mItemLayoutResId,parent,false);
            final View finalItemView = itemView;
            itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    setItemSelectedBackgroundColor(finalItemView,hasFocus);
                }
            });
        } catch (Exception e) {
            itemView = new ImageCardView(mContext) {
                @Override
                public void setSelected(boolean selected) {
                    setItemSelectedBackgroundColor(this,selected);
                    super.setSelected(selected);
                }
            };
        }
        itemView.setFocusable(true);
        itemView.setFocusableInTouchMode(false);
        setItemSelectedBackgroundColor(itemView,false);
        return onCreateViewHolder(itemView);
    }

    /**
     * 设置item被选中的背景颜色
     * @param view item视图
     * @param selected 是否被选中
     */
    protected void setItemSelectedBackgroundColor(View view,boolean selected) {
        final int color = selected ? mSelectedBackgroundColor : mDefaultBackgroundColor;
        if(view instanceof ImageCardView) {
            view.findViewById(R.id.info_field).setBackgroundColor(color);
        } else if(view instanceof CardView){
            ((CardView)view).setCardBackgroundColor(color);
        } else {
            view.setBackgroundColor(color);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        onBindViewData(viewHolder,(M)item);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        if(viewHolder.view instanceof ImageCardView) {
            ((ImageCardView)viewHolder.view).setBadgeImage(null);
            ((ImageCardView)viewHolder.view).setMainImage(null);
        }
        onUnBindViewData(viewHolder);
    }

    /**
     * 获取item被选中的背景颜色，默认是黑色
     * @param context 运行环境
     * @return 颜色值
     */
    protected int getItemSelectedBackgroundColor(Context context) {
        return context.getResources().getColor(R.color.color5);
    }

    /**
     * 获取item默认下的背景颜色，默认是灰色
     * @param context 运行环境
     * @return 颜色值
     */
    protected int getItemDefaultBackgroundColor(Context context) {
        return context.getResources().getColor(R.color.color6);
    }

    public void setItemLayoutResId(int mItemLayoutResId) {
        this.mItemLayoutResId = mItemLayoutResId;
    }

    public Context getContext() {
        return mContext;
    }
}
