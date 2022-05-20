import java.lang.Math;

public class ProjectileModeling {
    private float gravity = 9.81f;
    private float horizontalVelocity = 25;
    private float verticalVelocity = 16;

    public ProjectileModeling(float gravity, float horizontalVelocity, float verticalVelocity) {
        this.gravity = gravity;
        this.horizontalVelocity = horizontalVelocity;
        this.verticalVelocity = verticalVelocity;
    }

    public ProjectileModeling(float horizontalVelocity, float verticalVelocity) {
        this.gravity = 9.81f;
        this.horizontalVelocity = horizontalVelocity;
        this.verticalVelocity = verticalVelocity;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getHorizontalVelocity() {
        return horizontalVelocity;
    }

    public void setHorizontalVelocity(float pixelsPerSecond) {
        this.horizontalVelocity = pixelsPerSecond;
    }

    public float getVerticalVelocity() {
        return verticalVelocity;
    }

    public void setVerticalVelocity(float pixelsPerSecond) {
        this.verticalVelocity = pixelsPerSecond;
    }

    public float getSpeed() {
        return (float) (Math.sqrt(horizontalVelocity * horizontalVelocity + verticalVelocity * verticalVelocity));
    }

    public float getAngle() {
        return (float) Math.atan2(verticalVelocity, horizontalVelocity);
    }

    public boolean checkInputRadians(double radians, double xInit, double xFinal) {
        double max_x_distance = (xFinal - xInit);
        double predicted_x_distance = (Math.sin(2 * radians) * Math.pow(horizontalVelocity, 2)) / gravity;
        if (predicted_x_distance == max_x_distance) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkInputDegrees(double degrees, double xInit, double xFinal) {
        double radians = convertToDegrees(degrees);
        double max_x_distance = (xFinal - xInit);
        double predicted_x_distance = (Math.sin(2 * radians) * Math.pow(horizontalVelocity, 2)) / gravity;
        if (predicted_x_distance == max_x_distance) {
            return true;
        } else {
            return false;
        }
    }

    private double convertToDegrees(double radians) {
        return radians * (180 / Math.PI);
    }

    public double calcXPos(double currentXPixel, double time) {
        return currentXPixel + (horizontalVelocity * time);
    }

    public double calcYPos(double currentYPixel, double time) {
        return currentYPixel + (verticalVelocity * time) - (gravity * time * time);
    }

    public String xParametric(double velocity, double angle) {
        double coeff = velocity * Math.cos(angle);
        return "x=" + coeff + "t";
    }

    public String yParametric(double velocity, double angle) {
        double coeff = velocity * Math.sin(angle);
        return "y=" + coeff + "t-" + gravity + "t^2";
    }
}