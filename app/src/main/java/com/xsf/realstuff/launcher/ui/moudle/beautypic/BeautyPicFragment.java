package com.xsf.realstuff.launcher.ui.moudle.beautypic;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.RealStuffApplication;
import com.xsf.realstuff.launcher.common.Constants;
import com.xsf.realstuff.launcher.common.AbstractLazyFragment;
import com.xsf.realstuff.launcher.common.BaseEnum;
import com.xsf.realstuff.launcher.data.network.response.Result;
import com.xsf.realstuff.launcher.presenter.Impl.BeautyPagePresenterImpl;
import com.xsf.realstuff.launcher.ui.adapter.BeautyPicAdapter;
import com.xsf.realstuff.launcher.ui.adapter.base.MultiItemTypeAdapter;
import com.xsf.realstuff.launcher.ui.moudle.beautypic.view.IBeautyView;
import com.xsf.realstuff.launcher.ui.moudle.picdetail.PicDetailActivity;
import com.xsf.framework.util.LogUtils;
import com.xsf.framework.util.RecyclerViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;


public class BeautyPicFragment extends AbstractLazyFragment implements IBeautyView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_mz)
    RecyclerView rvMz;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private BeautyPicAdapter mBeautyPicAdapter;
    private int page;
    private boolean isRefresh;
    private List<Result> list = new ArrayList<>();
    private Unbinder mUnbinder;

    BeautyPagePresenterImpl<IBeautyView> mBeatyMVPPresenter;

    public static BeautyPicFragment newInstance() {
        return new BeautyPicFragment();
    }

    public BeautyPicFragment() {
        mBeatyMVPPresenter = new BeautyPagePresenterImpl<>(RealStuffApplication.getDadaManager(), new CompositeDisposable());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mz, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mBeatyMVPPresenter.attachView(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar.setTitle(R.string.main2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvMz.setLayoutManager(staggeredGridLayoutManager);
        mBeautyPicAdapter = new BeautyPicAdapter(getActivity(), R.layout.item_mz, list);
        rvMz.setAdapter(mBeautyPicAdapter);

        RecyclerViewUtil.setHeader(getActivity(), refreshLayout);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        isRefresh = true;
                        mBeatyMVPPresenter.loadData(BaseEnum.fuli.getValue() + "/" + Constants.PAGECOUNT + "/" + page);
                    }
                }, 0);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                LogUtils.v("onLoadmore");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mBeatyMVPPresenter.loadData(BaseEnum.fuli.getValue() + "/" + Constants.PAGECOUNT + "/" + page);
                    }
                }, 500);
            }

            @Override
            public void onFinishLoadMore() {
                if (Constants.ERROR) {
                    Constants.ERROR = false;
                } else {
                    page++;
                }
                LogUtils.v("onFinishLoadMore");
                super.onFinishLoadMore();
            }

        });

        mBeautyPicAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(getActivity(), view,getString(R.string.translate_mz));
                    startActivity(new Intent(getActivity(), PicDetailActivity.class).putExtra("img_url",list.get(position).getUrl()), options.toBundle());
                } else {
                    startActivity(new Intent(getActivity(), PicDetailActivity.class).putExtra("img_url",list.get(position).getUrl()));
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    protected void refreshUI() {
        refreshToolbar(mToolbar);
    }

    @Override
    public void loadData() {
        refreshLayout.startRefresh();
    }

    @Override
    public void showError() {
        Constants.ERROR = true;
        refreshLayout.finishLoadmore();
    }

    @Override
    public void showView(List<Result> results) {
        if(isRefresh) {
            mBeautyPicAdapter.clearData();
            isRefresh=false;
        }
        RecyclerViewUtil.loadMoreSetting(refreshLayout,results);
        mBeautyPicAdapter.addData(results);

    }
}
