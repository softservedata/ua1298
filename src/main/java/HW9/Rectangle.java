package HW9;

public class Rectangle {
    public static final String RECTANGLE_NOT_VALID = "Rectangle not Valid";
    private final String TEMPLATE_ERROR_MESSAGE = RECTANGLE_NOT_VALID + ", width=%4.1f, height=%4.1f";

    private double width;
    private double height;
    private final double angle = 90.0;

    public Rectangle() {
        this.width = 0.0;
        this.height = 0.0;
    }

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(String.format(TEMPLATE_ERROR_MESSAGE, width, height));
        }
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getAngle() {
        return angle;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive.");
        }
        this.width = width;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive.");
        }
        this.height = height;
    }

    public boolean isValidRectangle() {
        return width > 0 && height > 0;
    }

    public double getPerimeter() {
        if (!isValidRectangle()) {
            throw new IllegalArgumentException(String.format(TEMPLATE_ERROR_MESSAGE, width, height));
        }
        return 2 * (width + height);
    }

    public double getArea() {
        if (!isValidRectangle()) {
            throw new IllegalArgumentException(String.format(TEMPLATE_ERROR_MESSAGE, width, height));
        }
        return width * height;
    }

    public double getDiagonal() {
        if (!isValidRectangle()) {
            throw new IllegalArgumentException(String.format(TEMPLATE_ERROR_MESSAGE, width, height));
        }
        return Math.sqrt((width * width) + (height * height));
    }
}