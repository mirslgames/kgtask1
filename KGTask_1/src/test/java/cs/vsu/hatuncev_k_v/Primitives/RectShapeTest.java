package test.java.cs.vsu.hatuncev_k_v.Primitives;

import cs.vsu.hatuncev_k_v.DrawStyle;
import cs.vsu.hatuncev_k_v.Primitives.RectShape;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RectShapeTest {
    static { System.setProperty("java.awt.headless", "true"); }

    @Test
    void drawRectDoesNotThrow() {
        BufferedImage img = new BufferedImage(220, 160, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            RectShape r = new RectShape(40, 30, 120, 60, 12,
                    DrawStyle.strokeAndFill(Color.DARK_GRAY, 1.2f, new Color(230, 230, 240)));
            assertDoesNotThrow(() -> r.draw(g));
        } finally {
            g.dispose();
        }
    }
}

