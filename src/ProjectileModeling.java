import java.lang.Math;
public class ProjectileModeling {
    public static final double g = 9.81;
    public static double horizontalVelocity = 25;
    public static double verticalVelocity = 16;
    public ProjectileModeling() {}
    public static void setHorizontalVelocity(double pixelsPerSecond) {
        horizontalVelocity = pixelsPerSecond;
    }
    public static void setVerticalVelocity(double pixelsPerSecond) {
        verticalVelocity = pixelsPerSecond;
    }
    public boolean checkInputRadians(double radians, double xInit, double xFinal) {
        double max_x_distance = (xFinal - xInit);
        double predicted_x_distance = (Math.sin(2*radians)*Math.pow(horizontalVelocity, 2))/g;
        if(predicted_x_distance == max_x_distance) {
            return true;
        }else {
            return false;
        }
    }
    public boolean checkInputDegrees(double degrees, double xInit, double xFinal) {
        double radians = convertToDegrees(degrees);
        double max_x_distance = (xFinal - xInit);
        double predicted_x_distance = (Math.sin(2*radians)*Math.pow(horizontalVelocity, 2))/g;
        if(predicted_x_distance == max_x_distance) {
            return true;
        }else {
            return false;
        }
    }
    private double convertToDegrees(double radians) {
        return radians * (180/Math.PI);
    }
    public double calcXPos(double currentXPixel, double time) {
        return currentXPixel + (horizontalVelocity*time);
    }
    public double calcYPos(double currentYPixel, double time) {
        return currentYPixel + (verticalVelocity*time) - (g*time*time);
    }
    public String xParametric(double velocity, double angle) {
        double coeff = velocity*Math.cos(angle);
        return "x="+coeff+"t";
    }
    public String yParametric(double velocity,double angle){
        double coeff= velocity*Math.sin(angle);
        return "y="+ coeff+"t-"+g+"t^2";
    }
}