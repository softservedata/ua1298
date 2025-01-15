package com.softserve.homwork09.hometask1;

/*
This TestRectangle class created for testing methods
calculateArea(), calculatePerimeter(), getDiagonal()
with invalid values of width and height
without checking values in Constructor, setHeight() and setWidth (fathers Rectangle class)
 */

public class TestRectangle extends Rectangle {
    public TestRectangle(double width, double height){
        super();
        this.setHeight(height);
        this.setWidth(width);
    }

    @Override
    public void setWidth(double width){
        this.width = width;
    }

    @Override
    public void setHeight(double height){
        this.height = height;
    }
}

