package cs.vsu.hatuncev_k_v.EllipseRaster;

import java.awt.Color;

public class RadialEllipseSampler implements PixelSampler {

    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    private final double semiAxisX;
    private final double semiAxisY;
    private final Color centerColor;
    private final Color edgeColor;

    public RadialEllipseSampler(double semiAxisX, double semiAxisY,
                                Color centerColor, Color edgeColor) {
        this.semiAxisX = Math.max(1.0, semiAxisX);
        this.semiAxisY = Math.max(1.0, semiAxisY);
        this.centerColor = centerColor;
        this.edgeColor = edgeColor;
    }

    @Override
    public Color colorAt(double localX, double localY) {
        double t = Math.sqrt(
                (localX * localX) / (semiAxisX * semiAxisX) +
                        (localY * localY) / (semiAxisY * semiAxisY)
        ); // Нормированное расстояние до границы эллипса
        if (t > 1.0) {
            return TRANSPARENT;
        }
        return lerpColor(centerColor, edgeColor, clamp01(t));
    }

    public static Color lerpColor(Color c0, Color c1, double t) {
        t = clamp01(t);
        int r = (int) Math.round((c0.getRed()     + (c1.getRed()     - c0.getRed())     * t));
        int g = (int) Math.round((c0.getGreen()   + (c1.getGreen()   - c0.getGreen())   * t));
        int b = (int) Math.round((c0.getBlue()    + (c1.getBlue()    - c0.getBlue())    * t));
        int a = (int) Math.round((c0.getAlpha() + (c1.getAlpha() - c0.getAlpha()) * t));
        return new Color(r, g, b, a);
    }

    public static double clamp01(double v) {
        return (v < 0) ? 0 : (v > 1 ? 1 : v);
    }
}
