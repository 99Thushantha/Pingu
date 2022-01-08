package com.pingu.android;

public class ExampleItem {

    private int grideIcon;
    private String title;

    public ExampleItem(int grideIcon, String title) {
        this.grideIcon = grideIcon;
        this.title = title;
    }

    public int getGrideIcon() {
        return grideIcon;
    }

    public void setGrideIcon(int grideIcon) {
        this.grideIcon = grideIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
