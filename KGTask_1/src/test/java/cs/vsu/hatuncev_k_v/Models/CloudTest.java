package test.java.cs.vsu.hatuncev_k_v.Models;


import cs.vsu.hatuncev_k_v.Models.Cloud;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CloudTest {
    static { System.setProperty("java.awt.headless", "true"); }

    @Test
    void drawCloudDoesNotThrow() {
        BufferedImage img = new BufferedImage(600, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            Cloud cloud = new Cloud(120, 90, 160, 60);
            assertDoesNotThrow(() -> cloud.draw(g));
        } finally {
            g.dispose();
        }
    }
}

