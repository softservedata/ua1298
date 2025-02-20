package com.softserve.edu08rest;

public class GreencityHabitPage {
    private String defaultDuration;

    public GreencityHabitPage(String defaultDuration) {
        this.defaultDuration = defaultDuration;
    }

    public String getDefaultDuration() {
        return defaultDuration;
    }

    @Override
    public String toString() {
        return "\n\t\tGreencityHabitPage{" +
                "\n\t\t\tdefaultDuration='" + defaultDuration + '\'' +
                "\n\t\t}";
    }
}
