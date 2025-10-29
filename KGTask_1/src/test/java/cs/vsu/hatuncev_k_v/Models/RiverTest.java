package test.java.cs.vsu.hatuncev_k_v.Models;


import cs.vsu.hatuncev_k_v.Models.River;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RiverTest {

    @Test
    void drawRiverDoesNotThrow() {
        BufferedImage img = new BufferedImage(1000, 700, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            River river = new River(0, 400, 960, 200);
            assertDoesNotThrow(() -> river.draw(g));
        } finally {
            g.dispose();
        }
    }
}

