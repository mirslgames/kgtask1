package cs.vsu.hatuncev_k_v.EllipseRaster;

import java.awt.*;

public interface PixelSampler {
    Color colorAt(double localX, double localY);
}
