package test.java.cs.vsu.hatuncev_k_v.Primitives;


import cs.vsu.hatuncev_k_v.DrawStyle;
import cs.vsu.hatuncev_k_v.Primitives.LineSegment;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LineSegmentTest {
    static { System.setProperty("java.awt.headless", "true"); }

    @Test
    void drawLineDoesNotThrow() {
        BufferedImage img = new BufferedImage(200, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            LineSegment ln = new LineSegment(10, 10, 180, 120, DrawStyle.stroke(Color.BLACK, 2f));
            assertDoesNotThrow(() -> ln.draw(g));
        } finally {
            g.dispose();
        }
    }
}

