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
    void rasterize_3x3_centerPixelIsExactlyCenter() {
        int w = 3, h = 3;
        // Круг a=b=1, центр — белый непрозрачный, край — прозрачный
        PixelSampler s = new RadialEllipseSampler(1.0, 1.0,
                new Color(255,255,255,255),
                new Color(255,255,255,  0));

        int[] buf = Raster.rasterize(w, h, s);
        assertEquals(w*h, buf.length);

        // Центровка: cx=1.5, cy=1.5; пиксель (1,1) → (x=0,y=0) → центр
        int centerIndex = 1 + 1*w; // row-major
        assertEquals(0xFFFFFFFF, buf[centerIndex]);

        // Угол (0,0): (x=-1, y=-1) → t = sqrt(1+1)=1.414... > 1 → прозрачный
        assertEquals(0x00000000, buf[0]);
    }

    @Test
    void rasterize_2x2_allFourNearCenter_haveSameT() {
        int w = 2, h = 2;
        PixelSampler s = new RadialEllipseSampler(1.0, 1.0,
                new Color(0,0,0,255),   // чёрный, A=255
                new Color(0,0,0,  0));  // прозрачный

        int[] buf = Raster.rasterize(w, h, s);
        assertEquals(w*h, buf.length);

        // Для w=h=2: cx=1.0,cy=1.0.
        // Любой пиксель имеет локальные |x|=|y|=0.5 → t = sqrt(0.5^2+0.5^2)=~0.70710678
        // A = round(255*(1 - t)) ≈ round(255*0.292893) ≈ round(74.73) = 75 (0x4B).
        int expectedA = (int)Math.round(255 * (1 - Math.sqrt(0.5*0.5 + 0.5*0.5)));
        int expected = (expectedA << 24);

        for (int px : buf) {
            assertEquals(expected, px, "Все 4 пикселя должны иметь одинаковую альфу");
        }
    }

    @Test
    void toImage_preservesPixels() {
        int w = 2, h = 1;
        int[] argb = new int[] { 0xFF112233, 0x80123456 };
        BufferedImage img = Raster.toImage(argb, w, h);

        assertEquals(w, img.getWidth());
        assertEquals(h, img.getHeight());
        assertEquals(0xFF112233, img.getRGB(0,0));
        assertEquals(0x80123456, img.getRGB(1,0));
    }
}
