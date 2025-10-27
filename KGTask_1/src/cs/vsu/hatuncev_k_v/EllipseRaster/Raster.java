package cs.vsu.hatuncev_k_v.EllipseRaster;

import java.awt.Color;
import java.awt.image.BufferedImage;


public class Raster {

    public static Color[][] rasterizeToColorGrid(int width, int height, PixelSampler sampler) {
        Color[][] pixels = new Color[height][width];

        double centerX = width / 2.0;
        double centerY = height / 2.0;

        for (int y = 0; y < height; y++) {
            double localY = (y + 0.5) - centerY;
            for (int x = 0; x < width; x++) {
                double localX = (x + 0.5) - centerX;
                pixels[y][x] = sampler.colorAt(localX, localY);
            }
        }
        return pixels;
    }

    public static BufferedImage toImage(Color[][] pixels) {
        int height = pixels.length;
        int width = (height > 0) ? pixels[0].length : 0;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            Color[] row = pixels[y];
            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, row[x].getRGB());
            }
        }
        return image;
    }

    public static BufferedImage rasterizeToImage(int width, int height, PixelSampler sampler) {
        return toImage(rasterizeToColorGrid(width, height, sampler));
    }
}
