package com.mobaijun.model;

import java.util.List;

/**
 * software：IntelliJ IDEA 2022.1
 * class name: ZhEntity
 * class description： 知乎实体
 *
 * @author MoBaiJun 2022/9/7 17:20
 */
public class ZhEntity {
    private List<Data> data;
    private Paging paging;
    private String freshText;
    private int displayNum;

    public ZhEntity() {
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public String getFreshText() {
        return freshText;
    }

    public void setFreshText(String freshText) {
        this.freshText = freshText;
    }

    public int getDisplayNum() {
        return displayNum;
    }

    public void setDisplayNum(int displayNum) {
        this.displayNum = displayNum;
    }

    @Override
    public String toString() {
        return "ZhEntity{" +
                "data=" + data +
                ", paging=" + paging +
                ", freshText='" + freshText + '\'' +
                ", displayNum=" + displayNum +
                '}';
    }
}
