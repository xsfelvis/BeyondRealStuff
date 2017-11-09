package com.xsf.realstuff.launcher.ui.moudle.main.homepage;


import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.RealStuffApplication;
import com.xsf.realstuff.launcher.common.Constants;
import com.xsf.realstuff.launcher.data.network.response.Result;
import com.xsf.realstuff.launcher.presenter.IHomePagePresenter;
import com.xsf.realstuff.launcher.presenter.Impl.HomePagePresenterImpl;
import com.xsf.realstuff.launcher.ui.adapter.HomePageAdapter;
import com.xsf.realstuff.launcher.ui.base.AbstractLazyFragment;
import com.xsf.realstuff.launcher.ui.moudle.main.homepage.view.IHomePageView;
import com.xsf.realstuff.launcher.util.LogUtils;
import com.xsf.realstuff.launcher.util.RecyclerViewUtil;

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
    RecyclerView rvIndex;
    @BindView(R.id.tv_top)
    TextView stickyView;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;

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
        rvIndex.setLayoutManager(mLinearLayoutManager);
        rvIndex.setAdapter(mAdapter);
        rvIndex.setItemAnimator(new DefaultItemAnimator());
        //吸顶布局滚动监听
        rvIndex.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                View stickyInfoView = recyclerView.findChildViewUnder(
                        stickyView.getMeasuredWidth() / 2, 5);

                if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                    stickyView.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }

                View transInfoView = recyclerView.findChildViewUnder(
                        stickyView.getMeasuredWidth() / 2, stickyView.getMeasuredHeight() + 1);

                if (transInfoView != null && transInfoView.getTag() != null) {

                    int transViewStatus = (int) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - stickyView.getMeasuredHeight();

                    if (transViewStatus == HomePageAdapter.HAS_STICKY_VIEW) {
                        if (transInfoView.getTop() > 0) {
                            stickyView.setTranslationY(dealtY);
                        } else {
                            stickyView.setTranslationY(0);
                        }
                    } else if (transViewStatus == HomePageAdapter.NONE_STICKY_VIEW) {
                        stickyView.setTranslationY(0);
                    }
                }
            }
        });


        mAdapter.setOnItemClickListener(new HomePageAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                mPosition = position;
                /*Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("bean", mList.get(position));
                startActivity(intent);*/
            }

            @Override
            public void OnImageViewClick(View v, int position) {
                /*Intent intent = new Intent(getActivity(), ImageDetailActivity.class);
                intent.putExtra("imgUrl", mList.get(position).getImages().get(0));
                startActivity(intent);*/
            }
        });


        ProgressLayout header = new ProgressLayout(getActivity());
        header.setColorSchemeResources(R.color.Blue, R.color.Orange, R.color.Yellow, R.color.Green);
        refreshLayout.setHeaderView(header);
        refreshLayout.setFloatRefresh(true);
        refreshLayout.setOverScrollRefreshShow(false);
        refreshLayout.setAutoLoadMore(true);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
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
        refreshLayout.startRefresh();
    }

    @Override
    protected void refreshUI() {
        TypedValue bground_itemcolor = new TypedValue();
        TypedValue textcolor = new TypedValue();
        TypedValue toplinecolor = new TypedValue();
        TypedValue bottomlinecolor = new TypedValue();
        TypedValue topviewcolor = new TypedValue();
        TypedValue toptextcolor = new TypedValue();
        Resources.Theme theme = mActivity.getTheme();
        theme.resolveAttribute(R.attr.backgroundcolor_item, bground_itemcolor, true);
        theme.resolveAttribute(R.attr.textcolor, textcolor, true);
        theme.resolveAttribute(R.attr.topline, toplinecolor, true);
        theme.resolveAttribute(R.attr.bottomline, bottomlinecolor, true);
        theme.resolveAttribute(R.attr.topview, topviewcolor, true);
        theme.resolveAttribute(R.attr.toptextcolor, toptextcolor, true);
        Resources resources = getResources();
        stickyView.setBackgroundColor(resources.getColor(topviewcolor.resourceId));
        stickyView.setTextColor(resources.getColor(toptextcolor.resourceId));
        int childCount = rvIndex.getChildCount();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            LinearLayout ll = (LinearLayout) rvIndex.getChildAt(childIndex).findViewById(R.id.item_container);
            ll.setBackgroundColor(resources.getColor(bground_itemcolor.resourceId));
            TextView title = (TextView) ll.findViewById(R.id.tv_title);
            TextView tv_top = (TextView) ll.findViewById(R.id.tv_top);
            View view1 = ll.findViewById(R.id.topline);
            View view2 = rvIndex.getChildAt(childIndex).findViewById(R.id.bottomline);
            title.setTextColor(resources.getColor(textcolor.resourceId));
            tv_top.setTextColor(resources.getColor(toptextcolor.resourceId));
            tv_top.setBackgroundColor(resources.getColor(topviewcolor.resourceId));
            view1.setBackgroundColor(resources.getColor(toplinecolor.resourceId));
            view2.setBackgroundColor(resources.getColor(bottomlinecolor.resourceId));
        }
        RecyclerViewUtil.invalidateCacheItem(rvIndex);

    }

    @Override
    public void showError() {
        LogUtils.v("error");
        Constants.ERROR = true;
        refreshLayout.finishRefreshing();
        refreshLayout.finishLoadmore();
    }

    @Override
    public void showList(List<Result> resultList) {
        LogUtils.d(TAG,"showList");
        this.mList.addAll(resultList);
        mAdapter.notifyData(this.mList);
        refreshLayout.finishRefreshing();
        refreshLayout.finishLoadmore();
    }
}
