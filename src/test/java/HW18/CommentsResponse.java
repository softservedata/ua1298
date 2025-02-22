package HW18;

import java.util.List;

class CommentsResponse {
    private List<Comments> page;
    private int totalElements;
    private int currentPage;
    private int totalPages;

    public List<Comments> getPage() {
        return page; }

    public int getTotalElements() {
        return totalElements;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void printComments() {
        if (page != null && !page.isEmpty()) {
            for (Comments comment : page) {
                System.out.println(comment);
            }
        } else {
            System.out.println("No active comments found.");
        }
    }
}