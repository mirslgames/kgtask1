package test.java.cs.vsu.hatuncev_k_v.Models;


import cs.vsu.hatuncev_k_v.Models.Sky;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SkyTest {
    static { System.setProperty("java.awt.headless", "true"); }

    @Test
    void drawSkyDoesNotThrow() {
        BufferedImage img = new BufferedImage(1000, 700, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            Sky sky = new Sky(0, 0, 960, 600);
            assertDoesNotThrow(() -> sky.draw(g));
        } finally {
            g.dispose();
        }
    }
}

