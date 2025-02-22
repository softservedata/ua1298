package HW18;

import java.util.List;

public class Comments {
    private int id;
    private String createdDate;
    private String modifiedDate;
    private CommentsAuthor author;
    private Integer parentCommentId;
    private String text;
    private int replies;
    private int likes;
    private int dislikes;
    private boolean currentUserLiked;
    private boolean currentUserDisliked;
    private String status;
    private List<String> additionalImages;

    public Comments(int id, String createdDate, String modifiedDate,
                             CommentsAuthor author, Integer parentCommentId,
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

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public CommentsAuthor getAuthor() {
        return author;
    }

    public void setAuthor(CommentsAuthor author) {
        this.author = author;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public boolean isCurrentUserLiked() {
        return currentUserLiked;
    }

    public void setCurrentUserLiked(boolean currentUserLiked) {
        this.currentUserLiked = currentUserLiked;
    }

    public boolean isCurrentUserDisliked() {
        return currentUserDisliked;
    }

    public void setCurrentUserDisliked(boolean currentUserDisliked) {
        this.currentUserDisliked = currentUserDisliked;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getAdditionalImages() {
        return additionalImages;
    }

    public void setAdditionalImages(List<String> additionalImages) {
        this.additionalImages = additionalImages;
    }

    @Override
    public String toString() {
        return "\nComments{" +
                "\n\t\tid=" + id +
                ",\n\t\tcreatedDate='" + createdDate + '\'' +
                ",\n\t\tmodifiedDate='" + modifiedDate + '\'' +
                ",\n\t\tauthor=" + author +
                ",\n\t\tparentCommentId=" + parentCommentId +
                ",\n\t\ttext='" + text + '\'' +
                ",\n\t\treplies=" + replies +
                ",\n\t\tlikes=" + likes +
                ",\n\t\tdislikes=" + dislikes +
                ",\n\t\tcurrentUserLiked=" + currentUserLiked +
                ",\n\t\tcurrentUserDisliked=" + currentUserDisliked +
                ",\n\t\tstatus='" + status + '\'' +
                ",\n\t\tadditionalImages=" + additionalImages +
                "\n\t}";
    }
}