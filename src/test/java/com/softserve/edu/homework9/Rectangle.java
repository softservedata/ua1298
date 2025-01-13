package com.softserve.edu.homework9;

public class Rectangle {
    private double width;
    private double height;
    private final double angle = 90.0;

    public Rectangle() {
        this.width = 1.0;
        this.height = 1.0;
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if(width <= 0){
            throw new IllegalArgumentException("Must be positive.");
        }
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if(height <= 0){
            throw new IllegalArgumentException("Must be positive.");
        }
        this.height = height;
    }

    public double getAngle() {
        return angle;
    }

    public double calculateArea(){
        validateNum();
        return height * width;
    }

    public double calculatePerimeter(){
        validateNum();
        return (height + width) * 2;
    }
    public double getDiagonal(){
        validateNum();
        return Math.sqrt(height * height + width * width);
    }
  public void validateNum(){
        if(height <= 0 || width <= 0){
            throw new IllegalArgumentException("Must be positive.");
        }
  }
}
