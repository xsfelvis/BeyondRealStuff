package com.xsf.realstuff.launcher.ui.module.main.homepage;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.xsf.framework.util.LogUtils;
import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.RealStuffApplication;
import com.xsf.realstuff.launcher.common.AbstractLazyFragment;
import com.xsf.realstuff.launcher.common.Constants;
import com.xsf.realstuff.launcher.data.network.response.Result;
import com.xsf.realstuff.launcher.presenter.IHomePagePresenter;
import com.xsf.realstuff.launcher.presenter.Impl.HomePagePresenterImpl;
import com.xsf.realstuff.launcher.ui.adapter.HomePageAdapter;
import com.xsf.realstuff.launcher.ui.module.detail.DetailActivity;
import com.xsf.realstuff.launcher.ui.module.main.homepage.view.IHomePageView;
import com.xsf.realstuff.launcher.ui.module.picdetail.PicDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * 首页fragment
 */
public class HomePageFragment extends AbstractLazyFragment implements IHomePageView {

    public static final String TAG = "HomePageFragment";
    public static final int INITPAGECOUNT = 1;

    @BindView(R.id.rv_index)
    RecyclerView mRvIndex;
    @BindView(R.id.tv_top)
    TextView mStickyView;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout mRefreshLayout;

    private HomePageAdapter mAdapter = new HomePageAdapter();
    private int mPageCount = INITPAGECOUNT;
    private int mPosition;
    private List<Result> mList = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;

    IHomePagePresenter<IHomePageView> mIndexMvpPresenter;


    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }


    public HomePageFragment() {
        mIndexMvpPresenter = new HomePagePresenterImpl<>(RealStuffApplication.getDadaManager(), new CompositeDisposable());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        ButterKnife.bind(this, view);
        mIndexMvpPresenter.attachView(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // mAdapter = new IndexAdapter();
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRvIndex.setLayoutManager(mLinearLayoutManager);
        mRvIndex.setAdapter(mAdapter);
        mRvIndex.setItemAnimator(new DefaultItemAnimator());
        //吸顶布局滚动监听
        mRvIndex.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                View stickyInfoView = recyclerView.findChildViewUnder(
                        mStickyView.getMeasuredWidth() / 2, 5);

                if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                    mStickyView.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }

                View transInfoView = recyclerView.findChildViewUnder(
                        mStickyView.getMeasuredWidth() / 2, mStickyView.getMeasuredHeight() + 1);

                if (transInfoView != null && transInfoView.getTag() != null) {

                    int transViewStatus = (int) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - mStickyView.getMeasuredHeight();

                    if (transViewStatus == HomePageAdapter.HAS_STICKY_VIEW) {
                        if (transInfoView.getTop() > 0) {
                            mStickyView.setTranslationY(dealtY);
                        } else {
                            mStickyView.setTranslationY(0);
                        }
                    } else if (transViewStatus == HomePageAdapter.NONE_STICKY_VIEW) {
                        mStickyView.setTranslationY(0);
                    }
                }
            }
        });


        mAdapter.setOnItemClickListener(new HomePageAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                mPosition = position;
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("bean", mList.get(position));
                startActivity(intent);
            }

            @Override
            public void OnImageViewClick(View v, int position) {
                Intent intent = new Intent(getActivity(), PicDetailActivity.class);
                intent.putExtra("imgUrl", mList.get(position).getImages().get(0));
                startActivity(intent);
            }
        });


        ProgressLayout header = new ProgressLayout(getActivity());
        header.setColorSchemeResources(R.color.Blue, R.color.Orange, R.color.Yellow, R.color.Green);
        mRefreshLayout.setHeaderView(header);
        mRefreshLayout.setFloatRefresh(true);
        mRefreshLayout.setOverScrollRefreshShow(false);
        mRefreshLayout.setAutoLoadMore(true);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPageCount = 1;
                        mList.clear();
                        mIndexMvpPresenter.loadData("all/" + Constants.PAGECOUNT + "/" + mPageCount);
                    }
                }, 0);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                LogUtils.v("onLoadmore");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIndexMvpPresenter.loadData("all/" + Constants.PAGECOUNT + "/" + mPageCount);
                        LogUtils.v(mPageCount);

                    }
                }, 1000);
            }


            @Override
            public void onLoadmoreCanceled() {
                LogUtils.v("onLoadmoreCanceled");
                super.onLoadmoreCanceled();
            }

            @Override
            public void onFinishLoadMore() {
                if (Constants.ERROR) {
                    Constants.ERROR = false;
                } else {
                    mPageCount++;
                }
                LogUtils.v("onFinishLoadMore");
                super.onFinishLoadMore();
            }

        });
    }

    @Override
    public void loadData() {
        mRefreshLayout.startRefresh();
    }


    @Override
    public void showError() {
        LogUtils.v("error");
        Constants.ERROR = true;
        mRefreshLayout.finishRefreshing();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void showList(List<Result> resultList) {
        LogUtils.d(TAG,"showList");
        this.mList.addAll(resultList);
        mAdapter.notifyData(this.mList);
        mRefreshLayout.finishRefreshing();
        mRefreshLayout.finishLoadmore();
    }
}
