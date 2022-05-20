public class Projectile {
  private double x, y;
  public Projectile(double init_x, double init_y) {
    x = init_x; y = init_y;
  }
  public Projectile() {
    x = 0; y = 0;
  }
  public double currentX() {
    return x;
  }
  public double currentY() {
    return y;
  }
}
