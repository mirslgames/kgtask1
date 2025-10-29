package test.java.cs.vsu.hatuncev_k_v.Primitives;

import cs.vsu.hatuncev_k_v.DrawStyle;
import cs.vsu.hatuncev_k_v.Primitives.ArcShape;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ArcShapeTest {

    @Test
    void drawArcDoesNotThrow() {
        BufferedImage img = new BufferedImage(200, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            ArcShape arc = new ArcShape(20, 20, 120, 80, 0, 180, Arc2D.CHORD,
                    DrawStyle.strokeAndFill(Color.DARK_GRAY, 1.5f, Color.CYAN));
            assertDoesNotThrow(() -> arc.draw(g));
        } finally {
            g.dispose();
        }
    }
}

