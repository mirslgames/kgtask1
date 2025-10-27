package test.java.cs.vsu.hatuncev_k_v.Models;

import cs.vsu.hatuncev_k_v.Models.Tram;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TramTest {
    static { System.setProperty("java.awt.headless", "true"); }

    @Test
    void drawTramDoesNotThrow() {
        BufferedImage img = new BufferedImage(1000, 700, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            Tram tram = new Tram(60, 290, 240, 84);
            assertDoesNotThrow(() -> tram.draw(g));
        } finally {
            g.dispose();
        }
    }
}

