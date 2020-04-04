import java.util.List;

class Figure {
    double scale;
    List<Triangle> polygons;

    public Figure(List<Triangle> polygons, double scale) {
        this.polygons = polygons;
        this.scale = scale;
    }

    public void addFoV(double step) {
        if  (scale + 1 <= 180)
            scale += step;
    }

    public void subtractFoV(double step) {
        if (scale - 1 >= 0)
            scale -= step;
    }

    public void setFoV(double value) {
        scale = value;
    }

    public double getFoV() {
        return scale;
    }
}
