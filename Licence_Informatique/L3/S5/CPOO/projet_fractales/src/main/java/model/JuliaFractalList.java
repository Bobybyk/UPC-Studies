package model;

public enum JuliaFractalList {
    EJ1(0.3, 0.5, -1, 1, -1, 1, 0.002),
    EJ2(0.285, 0.01, -1, 1, -1, 1, 0.002),
    EJ3(-1.417022285618, 0.0099534, -1, 1, -1, 1, 0.002),
    EJ4(-0.038088, 0.9754633, -1, 1, -1, 1, 0.002),
    EJ5(0.285, 0.013, -1, 1, -1, 1, 0.002),
    EJ6(0.285, 0.01, -1, 1, -1, 1, 0.002),
    EJ7(-1.476, 0.0, -1, 1, -1, 1, 0.002),
    EJ8(-0.4, 0.6, -1, 1, -1, 1, 0.002),
    EJ9(-0.8, 0.156, -1, 1, -1, 1, 0.002);

    private double real, imaginary, x1, x2, y1, y2, gap;

    private JuliaFractalList(double real, double imaginary, double x1, double x2, double y1, double y2, double gap) {
        this.real = real;
        this.imaginary = imaginary;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.gap = gap;
    }

    public Fractal createAndgetEnumFractal() {
        return new FractalBuilder().setDefinitionDomain(new FractalDefinitionDomain(
            x1, x2, y1, y2)).setGap(gap).setComplexConstant(new Complex(real, imaginary)).buildJulia();
    }

    public double getReal() {
        return real;
    }
    public double getImaginary() {
        return imaginary;
    }
    public double getX1() {
        return x1;
    }
    public double getX2() {
        return x2;
    }
    public double getY1() {
        return y1;
    }
    public double getY2() {
        return y2;
    }
    public double getGap() {
        return gap;
    }
}
