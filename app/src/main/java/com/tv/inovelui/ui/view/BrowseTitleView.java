package com.tv.inovelui.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.TitleViewAdapter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tv.inovelui.R;

/**
 * 功能描述：主页标题视图
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/20- 10:31
 */

public class BrowseTitleView extends RelativeLayout
        implements TitleViewAdapter.Provider,TitleBuilder {

    //标题显示区
    private TextView mTitleView;
    //搜索视图
    private View mSearchOrbView;
    //标题左边图标
    private ImageView mTitleIconIv;
    //标题上面的状态栏容器
    private LinearLayout mTitleStateLl;
    //搜索旁的视图容器，为后面动态添加时用
    private LinearLayout mSearchStateLl;

    public BrowseTitleView(Context context) {
        this(context, null);
    }

    public BrowseTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BrowseTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.fragment_custom_title,this);
        mTitleIconIv = (ImageView) findViewById(R.id.iv_title_icon);
        mTitleStateLl = (LinearLayout) findViewById(R.id.ll_title_state);
        mSearchStateLl = (LinearLayout) findViewById(R.id.ll_search_state);
        mTitleView = (TextView) findViewById(R.id.tv_title);
        mSearchOrbView = findViewById(R.id.search_orb);

        mSearchOrbView.setVisibility(GONE);
    }

    /**
     * 标题视图适配器
     */
    private final TitleViewAdapter mTitleViewAdapter = new TitleViewAdapter() {
        @Override
        public View getSearchAffordanceView() {
            return mSearchOrbView;
        }

        @Override
        public void setTitle(CharSequence titleText) {
            BrowseTitleView.this.setTitle(titleText);
        }

        @Override
        public void setBadgeDrawable(Drawable drawable) {
            BrowseTitleView.this.setBadgeDrawable(drawable);
        }

        @Override
        public void setOnSearchClickedListener(OnClickListener listener) {
            mSearchOrbView.setOnClickListener(listener);
        }

        @Override
        public void updateComponentsVisibility(int flags) {
//            int visibility = (flags & SEARCH_VIEW_VISIBLE) == SEARCH_VIEW_VISIBLE
//                    ? View.VISIBLE : View.INVISIBLE;
//            mSearchOrbView.setVisibility(visibility);
//            mSearchStateLl.setVisibility(visibility);
        }
    };

    /**
     * 设置标题信息
     * @param title 标题信息
     */
    public void setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            mTitleView.setText(title);
            mTitleView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置标题左边图标
     * @param drawable 图标
     */
    public void setBadgeDrawable(Drawable drawable) {
        if (drawable != null) {
            mTitleView.setVisibility(View.GONE);
            mTitleIconIv.setVisibility(View.VISIBLE);
            mTitleIconIv.setBackground(drawable);
        } else {
            mTitleView.setVisibility(View.VISIBLE);
            mTitleIconIv.setVisibility(View.GONE);
        }
    }

    @Override
    public void addSearchBadgeView(View view) {
        if(mSearchStateLl != null) {
            mSearchStateLl.addView(view);
        }
    }

    @Override
    public void removeSearchBadgeView(View view) {
        if(mSearchStateLl != null) {
            mSearchStateLl.removeView(view);
        }
    }

    @Override
    public void addTitleBadgeView(View view) {
        if(mTitleStateLl != null) {
            mTitleStateLl.addView(view);
        }
    }

    @Override
    public void removeTitleBadgeView(View view) {
        if(mTitleStateLl != null) {
            mTitleStateLl.removeView(view);
        }
    }

    @Override
    public void setSearchBadgeBackgroundColor(int color) {
        mSearchOrbView.setBackgroundColor(color);
    }

    @Override
    public TitleViewAdapter getTitleViewAdapter() {
        return mTitleViewAdapter;
    }
}
