package HW18;

public class CommentsAuthor {
    private int id;
    private String name;
    private String profilePicturePath;

    public CommentsAuthor(int id, String name, String profilePicturePath) {
        this.id = id;
        this.name = name;
        this.profilePicturePath = profilePicturePath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    @Override
    public String toString() {
        return "\n\t\tCommentsAuthor{" +
                "\n\t\t\tid=" + id +
                ",\n\t\t\tname='" + name + '\'' +
                ",\n\t\t\tprofilePicturePath='" + profilePicturePath + '\'' +
                "\n\t\t}";
    }
}