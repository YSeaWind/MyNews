package com.lenovo.mynews.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;
//Multi 多
public class MyJson implements MultiItemEntity , Serializable {
    private String reason;
    private Result result;
    private String refuresh;
    private int itemType; //加载的页面类型


    public MyJson() {
    }

    public MyJson(int itemType) {
        this.itemType = itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getRefuresh() {
        return refuresh;
    }

    public void setRefuresh(String refuresh) {
        this.refuresh = refuresh;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MyJson{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
