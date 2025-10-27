package cs.vsu.hatuncev_k_v.EllipseRaster;

import java.awt.image.BufferedImage;

public class Raster {
    public static int[] rasterize(int w, int h, PixelSampler sampler) {
        w = Math.max(1, w); h = Math.max(1, h);
        int[] argb = new int[w*h];
        double cx = w/2.0, cy = h/2.0;

        int k = 0;
        for (int y = 0; y < h; y++) {
            double ly = (y + 0.5) - cy;
            for (int x = 0; x < w; x++) {
                double lx = (x + 0.5) - cx;
                argb[k++] = sampler.argbAt(lx, ly);
            }
        }
        return argb;
    }

    public static BufferedImage toImage(int[] argb, int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        img.setRGB(0, 0, w, h, argb, 0, w);
        return img;
    }
}
