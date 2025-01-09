package com.softserve.hw;

public class Triangle {
    public static final String TRIANGLE_NOT_EXIST = "Triangle not Exist";
    private final String TEMPLATE_ERROR_MESSAGE = TRIANGLE_NOT_EXIST + ", a=%4.1f, b=%4.1f, c=%4.1f";

    public boolean isPositive(double a, double b, double c) {
        return (a > 0) && (b > 0) && (c > 0);
    }

    public boolean isValidTriangle(double a, double b, double c) {
        if (!isPositive(a, b, c)) {
            throw new IllegalArgumentException(String
                    .format(TEMPLATE_ERROR_MESSAGE, a, b, c));
        }
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    public double getPerimeter(double a, double b, double c) {
        return a + b + c;
    }

    public double getArea(double a, double b, double c) {
        if (!isValidTriangle(a, b, c)) {
            throw new IllegalArgumentException(String
                    .format(TEMPLATE_ERROR_MESSAGE, a, b, c));
        }
        double p2 = getPerimeter(a, b, c) / 2;
        return Math.sqrt(p2 * (p2 - a) * (p2 - b) * (p2 - c));
    }
}
