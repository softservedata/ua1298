package com.softserve.edu08rest;

import java.util.List;

public class GreencityAllComments {
    private int id;
    private String createdDate;
    private String modifiedDate;
    private GreencityCommentsAuthor author;
    private Integer parentCommentId;
    private String text;
    private int replies;
    private int likes;
    private int dislikes;
    private boolean currentUserLiked;
    private boolean currentUserDisliked;
    private String status;
    private List<String> additionalImages;

    public GreencityAllComments(int id, String createdDate, String modifiedDate,
                                GreencityCommentsAuthor author, Integer parentCommentId,
                                String text, int replies, int likes, int dislikes,
                                boolean currentUserLiked, boolean currentUserDisliked,
                                String status, List<String> additionalImages) {
        this.id = id;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.author = author;
        this.parentCommentId = parentCommentId;
        this.text = text;
        this.replies = replies;
        this.likes = likes;
        this.dislikes = dislikes;
        this.currentUserLiked = currentUserLiked;
        this.currentUserDisliked = currentUserDisliked;
        this.status = status;
        this.additionalImages = additionalImages;
    }

    public int getId() {
        return id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public GreencityCommentsAuthor getAuthor() {
        return author;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public String getText() {
        return text;
    }

    public int getReplies() {
        return replies;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public boolean isCurrentUserLiked() {
        return currentUserLiked;
    }

    public boolean isCurrentUserDisliked() {
        return currentUserDisliked;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getAdditionalImages() {
        return additionalImages;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setAuthor(GreencityCommentsAuthor author) {
        this.author = author;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void setCurrentUserLiked(boolean currentUserLiked) {
        this.currentUserLiked = currentUserLiked;
    }

    public void setCurrentUserDisliked(boolean currentUserDisliked) {
        this.currentUserDisliked = currentUserDisliked;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAdditionalImages(List<String> additionalImages) {
        this.additionalImages = additionalImages;
    }

    @Override
    public String toString() {
        return "\nGreencityAllComments{" +
                "\tid=" + id +
                ", \n\tcreatedDate='" + createdDate + '\'' +
                ", \n\tmodifiedDate='" + modifiedDate + '\'' +
                ", \n\tauthor=" + author +
                ", \n\tparentCommentId=" + parentCommentId +
                ", \n\ttext='" + text + '\'' +
                ", \n\treplies=" + replies +
                ", \n\tlikes=" + likes +
                ", \n\tdislikes=" + dislikes +
                ", \n\tcurrentUserLiked=" + currentUserLiked +
                ", \n\tcurrentUserDisliked=" + currentUserDisliked +
                ", \n\tstatus='" + status + '\'' +
                ", \n\tadditionalImages=" + additionalImages +
                "\n}";
    }
}
