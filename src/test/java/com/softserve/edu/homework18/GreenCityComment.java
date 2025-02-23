package com.softserve.edu.homework18;

import java.util.List;

public class GreenCityComment {

    private int id;
    private String createdDate;
    private String modifiedDate;
    private GreenCityCommentsAuthor author;
    private Integer parentCommentId;
    private String text;
    private int replies;
    private int likes;
    private int dislikes;
    private boolean currentUserLiked;
    private boolean currentUserDisliked;
    private String status;
    private List<String> additionalImages;

    public GreenCityComment(int id, String createdDate, String modifiedDate,
                            GreenCityCommentsAuthor author, Integer parentCommentId,
                            String text, int replies, int likes, int dislikes,
                            boolean currentUserLiked, boolean currentUserDisliked, String status,
                            List<String> additionalImages) {
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

    public GreenCityCommentsAuthor getAuthor() {
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

    @Override
    public String toString() {
        return "\ngreenCityComment{" +
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
