package test.java.cs.vsu.hatuncev_k_v.Primitives;

import cs.vsu.hatuncev_k_v.Primitives.TextLabel;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TextLabelTest {
    static { System.setProperty("java.awt.headless", "true"); }

    @Test
    void drawTextLabelDoesNotThrow() {
        BufferedImage img = new BufferedImage(300, 120, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            TextLabel lbl = new TextLabel("Test", 150, 60,
                    new Font("SansSerif", Font.PLAIN, 14),
                    Color.BLACK,
                    TextLabel.Anchor.CENTER);
            assertDoesNotThrow(() -> lbl.draw(g));
        } finally {
            g.dispose();
        }
    }
}

