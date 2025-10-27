package cs.vsu.hatuncev_k_v.EllipseRaster;

public interface PixelSampler {
    int argbAt(double x, double y); //Локальные координаты (0,0) — центр, X вправо, Y вниз.
}
