package com.mobaijun.model;

/**
 * software：IntelliJ IDEA 2022.1
 * class name: ZhInfo
 * class description： 知乎详情对象
 *
 * @author MoBaiJun 2022/9/8 8:54
 */
public class ZhInfo {

    /**
     * 标题
     */
    private String title;

    /**
     * 地址
     */
    private String url;

    /**
     * 热度
     */
    private String detailText;

    /**
     * 摘抄
     */
    private String excerpt;

    /**
     * 略缩图
     */
    private String thumbnail;

    /**
     * 格式化 markdown 文本
     *
     * @return 字符串
     */
    public String formatMarkdown() {
        String img = String.format("<img src=\"%s\" width = \"829\" height = \"600\" alt=\"%s\" align=center/>", thumbnail, detailText);
        return String.format("%s<br>[%s](%s)<br>[%s] |", img, title, url, excerpt);
    }

    public ZhInfo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "ZhInfo{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", detailText='" + detailText + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
