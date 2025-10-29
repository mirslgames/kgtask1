package test.java.cs.vsu.hatuncev_k_v.Primitives;

import cs.vsu.hatuncev_k_v.DrawStyle;
import cs.vsu.hatuncev_k_v.Primitives.EllipseShape;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EllipseShapeTest {

    @Test
    void drawEllipseDoesNotThrow() {
        BufferedImage img = new BufferedImage(200, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            EllipseShape e = new EllipseShape(50, 30, 80, 60, DrawStyle.fill(Color.ORANGE));
            assertDoesNotThrow(() -> e.draw(g));
        } finally {
            g.dispose();
        }
    }
}
