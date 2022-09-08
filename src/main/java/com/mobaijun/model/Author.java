package com.mobaijun.model;

/**
 * software：IntelliJ IDEA 2022.1
 * class name: Author
 * class description：作者信息
 *
 * @author MoBaiJun 2022/9/7 17:21
 */
public class Author {
    private String type;
    private String userType;
    private String id;
    private String urlToken;
    private String url;
    private String name;
    private String headline;
    private String avatarUrl;

    public Author() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlToken() {
        return urlToken;
    }

    public void setUrlToken(String urlToken) {
        this.urlToken = urlToken;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "Author{" +
                "type='" + type + '\'' +
                ", userType='" + userType + '\'' +
                ", id='" + id + '\'' +
                ", urlToken='" + urlToken + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", headline='" + headline + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
