

class Vector {
    public double x = 0.;
    public double y = 0.;
    public final static String className = "Vector";
    Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    Vector() {}
    final double length() {
        return Math.sqrt(x*x + y*y);
    }

}
