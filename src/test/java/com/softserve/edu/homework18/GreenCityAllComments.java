package com.softserve.edu.homework18;

import java.util.List;

public class GreenCityAllComments {
    private List<GreenCityComment> page;
    private int totalElements;
    private int currentPage;
    private int totalPages;

    public GreenCityAllComments(List<GreenCityComment> page, int totalElements,
                                int currentPage, int totalPages) {
        this.page = page;
        this.totalElements = totalElements;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<GreenCityComment> getPage() {
        return page;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public String toString() {
        return "\ngreenCityAllComments{" +
                "\n\tpage=" + page +
                ", \n\ttotalElements=" + totalElements +
                ", \n\tcurrentPage=" + currentPage +
                ", \n\ttotalPages=" + totalPages +
                '}';
    }

}
