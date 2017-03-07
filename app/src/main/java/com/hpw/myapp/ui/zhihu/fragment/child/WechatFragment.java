package com.hpw.myapp.ui.zhihu.fragment.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hpw.mvpframe.base.CoreBaseFragment;
import com.hpw.mvpframe.widget.recyclerview.BaseQuickAdapter;
import com.hpw.mvpframe.widget.recyclerview.BaseViewHolder;
import com.hpw.mvpframe.widget.recyclerview.CoreRecyclerView;
import com.hpw.myapp.R;
import com.hpw.myapp.ui.zhihu.activity.WechatDetailsActivity;
import com.hpw.myapp.ui.zhihu.contract.ZhihuContract;
import com.hpw.myapp.ui.zhihu.model.wechatmodel.WXItemBean;
import com.hpw.myapp.ui.zhihu.model.wechatmodel.WechatModel;
import com.hpw.myapp.ui.zhihu.presenter.wechatpresenter.WechatPresenter;

import java.util.List;

/**
 * Created by hpw on 16/11/6.
 */

public class WechatFragment extends CoreBaseFragment<WechatPresenter, WechatModel> implements ZhihuContract.WechatView {
    CoreRecyclerView coreRecyclerView;
    private static int pageNum = 20;

    @Override
    public int getLayoutId() {
        return 0;
    }
    static final String Tag = "ShowLocationListAct:";

    @Override
    public View getLayoutView() {
        coreRecyclerView = new CoreRecyclerView(mContext).init(new BaseQuickAdapter<WXItemBean, BaseViewHolder>(R.layout.item_weichat) {
            @Override
            protected void convert(BaseViewHolder helper, WXItemBean item) {
                Glide.with(mContext).load(item.getPicUrl()).crossFade().into((ImageView) helper.getView(R.id.iv_wechat_item_image));
                helper.setText(R.id.tv_wechat_item_title, item.getTitle())
                        .setText(R.id.tv_wechat_item_from, item.getDescription())
                        .setText(R.id.tv_wechat_item_time, item.getCtime())
                        .setOnClickListener(R.id.ll_click, v -> {
                            WechatDetailsActivity.start(mContext, item.getTitle(), item.getUrl());
                        });
            }
        }).openLoadMore(pageNum, new CoreRecyclerView.addDataListener() {
                    @Override
                    public void addData(int page) {
        Log.e(Tag, "adddata ");
                        mPresenter.getWechatData(pageNum, page);
                    }
                }


//                Log.e(Tag, "adddata ");
        )
//                .openRefresh()
        ;
        return coreRecyclerView;
    }
;
//    private void adddata(int pageNum,int page) {
//        Log.e(Tag, "adddata ");
//
//        for (int i = 0; i < pageNum; i++) {
//            WXItemBean info=new WXItemBean(1488861410+"","title "+i,"描述 "+i,"url "+i,"pic "+i);
////            info.address="成都一环路"+i+"第"+page+"次加载";
////            info.name="龙泉 "+i+"第"+page+"次加载";
//
////            searchResultPoiList.add(info);
//        }
//    }
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showContent(List<WXItemBean> mList) {
        coreRecyclerView.getAdapter().addData(mList);
    }

    @Override
    public void showError(String msg) {
        showToast(msg + " api key失效,自己去http://www.tianapi.com/#wxnew申请key");
    }
}
