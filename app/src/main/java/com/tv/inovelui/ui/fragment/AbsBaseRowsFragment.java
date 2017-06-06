package com.tv.inovelui.ui.fragment;

import android.os.Bundle;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import com.tv.inovelui.model.Category;
import com.tv.inovelui.model.CategoryRow;
import com.tv.inovelui.model.IModel;
import com.tv.inovelui.ui.view.TvRowsFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：抽象行数据碎片
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/19- 10:59
 */

public abstract class AbsBaseRowsFragment extends TvRowsFragment
        implements OnItemViewClickedListener {

    //判断是否请求数据成功
    protected boolean isRequestSuccess = false;
    //数据适配器
    private ArrayObjectAdapter mAdapter;
    //保存容器列表
    private List<Category> categories = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAdapter();
        createRowsData();
    }

    /**
     * 创建适配器
     */
    private void createAdapter() {
        final ListRowPresenter presenter = getListRowPresenter();
        if (presenter != null) {
            presenter.setNumRows(getNumRows());
            mAdapter = new ArrayObjectAdapter(presenter);
        }
        final PresenterSelector presenterSelector = getPresenterSelector();
        if (presenterSelector != null) {
            mAdapter = new ArrayObjectAdapter(presenterSelector);
        }
        setAdapter(mAdapter);
        setOnItemViewClickedListener(this);
    }

    /**
     * 创建rows数据
     */
    protected void createRowsData() {
        Row model;
        getCategories(categories);
        for (Category category : categories) {
            model = getCategoryListRow(category);
            if (model != null) {
                mAdapter.add(model);
            }
        }
        notifyDataReady();
    }

    /**
     * 获取分类行
     * @param position 位置
     * @return 实例
     */
    public CategoryRow getCategoryRow(int position) {
        return (CategoryRow)getAdapter().get(position);
    }

    /**
     * 获取孩子适配器
     * @param position 位置
     * @return ArrayObjectAdapter实例
     */
    private ArrayObjectAdapter getChildAdapter(int position) {
        return (ArrayObjectAdapter)getCategoryRow(position).getAdapter();
    }

    /**
     * 添加孩子数据到列表
     * @param index 行索引
     * @param ob 添加的数据
     */
    public void putChildData(int index, Object ob) {
        final ArrayObjectAdapter adapter = getChildAdapter(index);
        if(adapter != null) {
            adapter.add(ob);
        }
    }

    /**
     * 移除孩子数据
     * @param index 行索引
     * @param ob 数据
     */
    public void removerChildData(int index, Object ob) {
        final ArrayObjectAdapter adapter = getChildAdapter(index);
        if(adapter != null) {
            getCategoryRow(index).getCategory().getData().remove(ob);
            adapter.remove(ob);
        }
    }

    /**
     * 获取孩子数据
     * @param index 索引
     * @return 数据集
     */
    public <T extends IModel> List<T> getChildData(int index) {
        return getCategoryRow(index).getCategory().getData();
    }

    /**
     * 向适配器里推送数据
     * @param model 模型数据
     */
    public void putRowData(Object model) {
        mAdapter.add(model);
    }

    /**
     * 按位置插入数据
     * @param index 位置索引
     * @param model 数据模型
     */
    public void putRowData(int index, Object model) {
        mAdapter.add(index, model);
    }

    /**
     * 移除适配器里的数据
     * @param model 模型
     */
    public void removeRowData(Object model) {
        mAdapter.remove(model);
    }

    /**
     * 替换行数据
     * @param position 位置
     * @param model 数据
     */
    public void replaceRowData(int position, Object model) {
        mAdapter.replace(position,model);
    }

    /**
     * 添加多个数据
     * @param index 索引
     * @param models 数据集
     */
    public void putRowsData(int index, List<Object> models) {
        mAdapter.addAll(index, models);
    }

    /**
     * 清除数据
     */
    public void clear() {
        mAdapter.clear();
    }

    /**
     * 获取索引，根据模型实例
     * @param model 模型
     * @return 获取索引
     */
    public int indexOf(Object model) {
        return mAdapter.indexOf(model);
    }

    /**
     * 获取模型数据实例
     * @param index 索引
     * @return 模型实例
     */
    public Object getModel(int index) {
        return mAdapter.get(index);
    }

    /**
     * 获取分类模型
     * @return 模型实例
     */
    private Row getCategoryListRow(Category<?> category) {
        final Presenter presenter = getModelPresenter();
        final ArrayObjectAdapter adapter = new ArrayObjectAdapter(presenter);
        for (IModel model : category.getData()) {
            adapter.add(model);
        }
        final HeaderItem headerItem = new HeaderItem(category.getName());
        return new CategoryRow(headerItem, adapter, category);
    }

    /**
     * 通知数据已经准备好
     */
    public void notifyDataReady() {
        getMainFragmentAdapter().getFragmentHost().notifyDataReady(getMainFragmentAdapter());
    }

    /**
     * 获取列表行数Presenter实例
     * @return ListRowPresenter实例
     */
    protected ListRowPresenter getListRowPresenter() {
        return new ListRowPresenter();
    }

    /**
     * 分类数据在该方法中填充
     * @return 返回分类数据，比如从网络请求回来
     */
    protected abstract void getCategories(List<Category> categories);

    /**
     * 获取对应的模型控制器
     * @return 控制器实例
     */
    protected abstract Presenter getModelPresenter();

    /**
     * 获取行数据
     * @return 大于零的数据
     */
    public int getNumRows() {
        return 1;
    }
}
