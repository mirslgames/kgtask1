package test.java.cs.vsu.hatuncev_k_v.EllipseRaster;

import cs.vsu.hatuncev_k_v.EllipseRaster.PixelSampler;
import cs.vsu.hatuncev_k_v.EllipseRaster.RadialEllipseSampler;
import cs.vsu.hatuncev_k_v.EllipseRaster.Raster;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class RasterTest {

    @Test
    void toImage_preservesPixels_andSize() {
        Color c0 = new Color(0x11, 0x22, 0x33, 0xFF);
        Color c1 = new Color(0x12, 0x34, 0x56, 0x80);
        Color[][] grid = new Color[][]{{c0, c1}};

        BufferedImage img = Raster.toImage(grid);

        assertEquals(2, img.getWidth());
        assertEquals(1, img.getHeight());
        assertEquals(c0.getRGB(), img.getRGB(0, 0));
        assertEquals(c1.getRGB(), img.getRGB(1, 0));
    }

    @Test
    void rasterizeToImage_returnsRequestedSize() {
        int w = 7, h = 5;

        RadialEllipseSampler sampler = new RadialEllipseSampler(
                w / 2.0, h / 2.0,
                new Color(0, 0, 0, 255),
                new Color(0, 0, 0, 0)
        );

        BufferedImage img = Raster.rasterizeToImage(w, h, sampler);

        assertEquals(w, img.getWidth());
        assertEquals(h, img.getHeight());
    }

}
