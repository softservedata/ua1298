package com.softserve.homwork09.hometask1;

public class Rectangle {

    private double width;
    private double height;
    private double angle;

    public Rectangle(){
        this.height = 1.0;
        this.width = 2.0;
        this.angle = 90.0;
    }

    public Rectangle(double width, double height){
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }

        this.width = width;
        this.height = height;
        this.angle = 90.0;
    }

    public double getWidth(){
        return width;
    }

    public void setWidth(double width){
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive");
    }
        this.width = width;
    }

    public double getHeight(){
        return height;
    }

    public void setHeight(double height){
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.height = height;
    }

    public double getAngle(){
        return angle;
    }


    public boolean isItRectangle(){
        if (width <= 0 || height <= 0 || angle != 90.0) {
            throw new IllegalArgumentException("Invalid rectangle:" +
                    " width and height must be positive, and angle must be 90 degrees");
        }

        return (width > 0) && (height > 0) && (angle == 90.0);
    }

    public double getArea(){
        return width * height;
    }

    public double getPerimeter(){
        return 2 * (width + height);
    }

    public double getDiagonal() {
        return Math.sqrt(width * width + height * height);
    }


}
