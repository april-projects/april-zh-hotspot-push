package com.mobaijun.model;

import java.util.List;

/**
 * software：IntelliJ IDEA 2022.1
 * class name: Target
 * class description：目标
 *
 * @author MoBaiJun 2022/9/7 17:21
 */
public class Target {
    private int id;
    private String title;
    private String url;
    private String type;
    private int created;
    private int answerCount;
    private int followerCount;
    private Author author;
    private List<Integer> boundTopicIds;
    private int commentCount;
    private boolean isFollowing;
    private String excerpt;

    public Target() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Integer> getBoundTopicIds() {
        return boundTopicIds;
    }

    public void setBoundTopicIds(List<Integer> boundTopicIds) {
        this.boundTopicIds = boundTopicIds;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    @Override
    public String toString() {
        return "Target{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", created=" + created +
                ", answerCount=" + answerCount +
                ", followerCount=" + followerCount +
                ", author=" + author +
                ", boundTopicIds=" + boundTopicIds +
                ", commentCount=" + commentCount +
                ", isFollowing=" + isFollowing +
                ", excerpt='" + excerpt + '\'' +
                '}';
    }
}
