package com.softserve.edu08rest;

import java.util.List;

public class GreencityAllHabits {
    private List<GreencityHabitPage> page;
    private int totalElements;
    private int currentPage;
    private int totalPages;

    public GreencityAllHabits(List<GreencityHabitPage> page, int totalElements, int currentPage, int totalPages) {
        this.page = page;
        this.totalElements = totalElements;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<GreencityHabitPage> getPage() {
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
        return "\nGreencityAllHabits{" +
                "\n\tpage=" + page +
                ", \n\ttotalElements=" + totalElements +
                ", \n\tcurrentPage=" + currentPage +
                ", \n\ttotalPages=" + totalPages +
                "\n}";
    }
}
