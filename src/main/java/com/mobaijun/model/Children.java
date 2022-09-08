package com.mobaijun.model;

/**
 * software：IntelliJ IDEA 2022.1
 * class name: Children
 * class description：子节点
 *
 * @author MoBaiJun 2022/9/7 17:21
 */
public class Children {
    private String type;
    private String thumbnail;

    public Children() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Children{" +
                "type='" + type + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
