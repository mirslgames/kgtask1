package cs.vsu.hatuncev_k_v.EllipseRaster;

import java.awt.Color;

public class RadialEllipseSampler implements PixelSampler {
    private final double a, b;     // полуоси
    private final int argbCenter;  // цвет в центре (непрозрачный или как задашь)
    private final int argbEdge;    // цвет у границы (обычно с меньшей альфой)

    public RadialEllipseSampler(double a, double b, Color center, Color edge) {
        this.a = Math.max(1, a);
        this.b = Math.max(1, b);
        this.argbCenter = center.getRGB();
        this.argbEdge   = edge.getRGB();
    }

    @Override
    public int argbAt(double x, double y) {
        // нормированное расстояние до границы
        double t = Math.sqrt((x*x)/(a*a) + (y*y)/(b*b));
        if (t > 1.0) return 0; // снаружи — прозрачный

        double tt = (t < 0) ? 0 : (t > 1 ? 1 : t);
        return lerpARGB(argbCenter, argbEdge, tt);
    }

    private static int lerpARGB(int c0, int c1, double t) {
        int a0=(c0>>>24)&0xFF, r0=(c0>>>16)&0xFF, g0=(c0>>>8)&0xFF, b0=c0&0xFF;
        int a1=(c1>>>24)&0xFF, r1=(c1>>>16)&0xFF, g1=(c1>>>8)&0xFF, b1=c1&0xFF;

        int a = (int)Math.round(a0 + (a1 - a0)*t);
        int r = (int)Math.round(r0 + (r1 - r0)*t);
        int g = (int)Math.round(g0 + (g1 - g0)*t);
        int b = (int)Math.round(b0 + (b1 - b0)*t);

        return (a<<24) | (r<<16) | (g<<8) | b;
    }
}
