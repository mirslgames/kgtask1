package test.java.cs.vsu.hatuncev_k_v.Models;

import cs.vsu.hatuncev_k_v.Models.Bridge;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BridgeTest {
    static { System.setProperty("java.awt.headless", "true"); }

    @Test
    void drawBridgeDoesNotThrow() {
        BufferedImage img = new BufferedImage(1000, 700, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            Bridge bridge = new Bridge(0, 360, 960, 40);
            assertDoesNotThrow(() -> bridge.draw(g));
        } finally {
            g.dispose();
        }
    }
}

