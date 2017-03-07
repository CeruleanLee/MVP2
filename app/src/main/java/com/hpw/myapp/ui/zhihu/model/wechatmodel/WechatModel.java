package com.hpw.myapp.ui.zhihu.model.wechatmodel;

import com.hpw.myapp.ui.zhihu.contract.ZhihuContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpw on 16/11/6.
 */
public class WechatModel implements ZhihuContract.WechatModel {
    @Override
    public List<WXItemBean> getWechatData(int num, int page) {
        List<WXItemBean> list=new ArrayList<WXItemBean>();
        for (int i = 0; i < num; i++) {
            WXItemBean info=new WXItemBean(1488861410+"","title "+i,"描述 "+i,"url "+i,"pic "+i);
//            info.address="成都一环路"+i+"第"+page+"次加载";
//            info.name="龙泉 "+i+"第"+page+"次加载";
           list.add(info);
        }
        return list;
    }



}
