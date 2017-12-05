package com.xsf.realstuff.launcher.ui.module.main.theme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.xsf.framework.util.ItemDecoration;
import com.xsf.framework.util.LogUtils;
import com.xsf.framework.util.RecyclerViewUtil;
import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.RealStuffApplication;
import com.xsf.realstuff.launcher.common.AbstractLazyFragment;
import com.xsf.realstuff.launcher.common.BaseFragment;
import com.xsf.realstuff.launcher.common.Constants;
import com.xsf.realstuff.launcher.data.network.response.Result;
import com.xsf.realstuff.launcher.presenter.ICommonMvpPresenter;
import com.xsf.realstuff.launcher.presenter.Impl.CommonPresenterImpl;
import com.xsf.realstuff.launcher.ui.adapter.CommonAdapter;
import com.xsf.realstuff.launcher.ui.module.detail.DetailActivity;
import com.xsf.realstuff.launcher.ui.module.main.theme.view.ICommonView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class CommonFragment extends AbstractLazyFragment implements ICommonView {

    public static final String BEAN = "bean";
    private static final String THEME_ID = "theme_id";
    ICommonMvpPresenter<ICommonView> mCommonPresenter;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private String mTheme;
    private int pageCount = 1;
    private List<Result> mList = new ArrayList<>();
    private CommonAdapter mCommonAdapter = new CommonAdapter();
    private Unbinder mUnbinder;

    public static BaseFragment newInstance(String themeId) {
        Bundle bundle = new Bundle();
        bundle.putString(THEME_ID, themeId);
        BaseFragment fragment = new CommonFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTheme = getArguments().getString(THEME_ID);
        mCommonPresenter = new CommonPresenterImpl<>(RealStuffApplication.getDadaManager(), new CompositeDisposable());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        //getActivityComponent().inject(this);
        mCommonPresenter.attachView(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.addItemDecoration(new ItemDecoration(getActivity(), ItemDecoration.VERTICAL_LIST));
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mCommonAdapter);
        RecyclerViewUtil.setHeader(getActivity(), mRefreshLayout);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageCount = 1;
                        mList.clear();
                        mCommonPresenter.loadData(mTheme + "/" + Constants.PAGECOUNT + "/" + pageCount);
                    }
                }, 0);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                LogUtils.v("onLoadmore");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCommonPresenter.loadData(mTheme + "/" + Constants.PAGECOUNT + "/" + pageCount);
                    }
                }, 500);
            }

            @Override
            public void onFinishLoadMore() {
                if (Constants.ERROR) {
                    Constants.ERROR = false;
                } else {
                    pageCount++;
                }
                LogUtils.v("onFinishLoadMore");
                super.onFinishLoadMore();
            }

        });

        mCommonAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Result resultbean = mList.get(position);
                intent.putExtra(BEAN, resultbean);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCommonPresenter.onDetchView();
    }

    @Override
    public void loadData() {
        mRefreshLayout.startRefresh();
    }


    @Override
    public void showError() {
        Constants.ERROR = true;
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void showList(List<Result> resultList) {
        int startPosition = mList.size();
        mList.addAll(resultList);
        RecyclerViewUtil.loadMoreSetting(mRefreshLayout, mList);
        mCommonAdapter.setData(mList);
        mCommonAdapter.notifyData(startPosition, mList.size());
    }
}
