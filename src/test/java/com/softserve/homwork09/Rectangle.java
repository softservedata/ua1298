package com.softserve.homwork09;

public class Rectangle {
    private double width;
    private double height;
    private final double angle = 90.0;  // Кут між сторонами завжди 90 градусів

    public Rectangle() {
        this.width = 1.0;  // Початкове значення ширини
        this.height = 1.0;  // Початкове значення висоти
    }

    public Rectangle(double width, double height) {
        setWidth(width);  // Використовуємо сеттери для встановлення значень
        setHeight(height);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive");
        }
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.height = height;
    }

    public double getAngle() {
        return angle;
    }

    public double calculateArea() {
        return width * height;
    }

    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    public double getDiagonal() {
        return Math.sqrt(width * width + height * height);
    }
}