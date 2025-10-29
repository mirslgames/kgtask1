package test.java.cs.vsu.hatuncev_k_v.Primitives;

import cs.vsu.hatuncev_k_v.DrawStyle;
import cs.vsu.hatuncev_k_v.Primitives.QuadBezier;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class QuadBezierTest {

    @Test
    void drawQuadBezierDoesNotThrow() {
        BufferedImage img = new BufferedImage(220, 160, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            QuadBezier qb = new QuadBezier(20, 120, 110, 20, 200, 120, DrawStyle.stroke(Color.MAGENTA, 1.4f));
            assertDoesNotThrow(() -> qb.draw(g));
        } finally {
            g.dispose();
        }
    }
}

