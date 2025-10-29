package test.java.cs.vsu.hatuncev_k_v.Primitives;

import cs.vsu.hatuncev_k_v.DrawStyle;
import cs.vsu.hatuncev_k_v.Primitives.Polyline;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PolylineTest {

    @Test
    void drawPolylineDoesNotThrow() {
        BufferedImage img = new BufferedImage(200, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            Polyline pl = new Polyline(
                    new double[]{20, 60, 120, 170},
                    new double[]{30, 20, 40, 35},
                    DrawStyle.stroke(Color.BLUE, 1.2f)
            );
            assertDoesNotThrow(() -> pl.draw(g));
        } finally {
            g.dispose();
        }
    }
}
