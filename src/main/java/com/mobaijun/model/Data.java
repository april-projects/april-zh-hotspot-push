package com.mobaijun.model;

import java.util.List;

/**
 * software：IntelliJ IDEA 2022.1
 * class name: Data
 * class description：数据模型
 *
 * @author MoBaiJun 2022/9/7 17:20
 */
public class Data {
    private String type;
    private String id;
    private int trend;
    private boolean debut;
    private String styleType;
    private String cardId;
    private Target target;
    private String attachedInfo;
    private String detailText;
    private List<Children> children;

    public Data() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTrend() {
        return trend;
    }

    public void setTrend(int trend) {
        this.trend = trend;
    }

    public boolean isDebut() {
        return debut;
    }

    public void setDebut(boolean debut) {
        this.debut = debut;
    }

    public String getStyleType() {
        return styleType;
    }

    public void setStyleType(String styleType) {
        this.styleType = styleType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public String getAttachedInfo() {
        return attachedInfo;
    }

    public void setAttachedInfo(String attachedInfo) {
        this.attachedInfo = attachedInfo;
    }

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Data{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", trend=" + trend +
                ", debut=" + debut +
                ", styleType='" + styleType + '\'' +
                ", cardId='" + cardId + '\'' +
                ", target=" + target +
                ", attachedInfo='" + attachedInfo + '\'' +
                ", detailText='" + detailText + '\'' +
                ", children=" + children +
                '}';
    }
}
