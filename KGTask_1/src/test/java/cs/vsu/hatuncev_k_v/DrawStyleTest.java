package test.java.cs.vsu.hatuncev_k_v;

import cs.vsu.hatuncev_k_v.DrawStyle;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Arc2D;

import static org.junit.jupiter.api.Assertions.*;

class DrawStyleTest {

    @Test
    void stroke() {
        DrawStyle ds = DrawStyle.stroke(Color.BLACK, 2f);
        assertNotNull(ds.getStrokeColor());
        assertNull(ds.getFillColor());
        assertTrue(ds.getStroke() instanceof BasicStroke);
        assertEquals(2f, ((BasicStroke) ds.getStroke()).getLineWidth(), 1e-6);
    }

    @Test
    void fill() {
        DrawStyle ds = DrawStyle.fill(Color.RED);
        assertNull(ds.getStrokeColor());
        assertEquals(Color.RED, ds.getFillColor());
        assertNull(ds.getStroke());
    }

    @Test
    void strokeAndFill() {
        DrawStyle ds = DrawStyle.strokeAndFill(Color.DARK_GRAY, 1.5f, Color.YELLOW);
        assertEquals(Color.DARK_GRAY, ds.getStrokeColor());
        assertEquals(Color.YELLOW, ds.getFillColor());
        assertTrue(ds.getStroke() instanceof BasicStroke);
        assertEquals(1.5f, ((BasicStroke) ds.getStroke()).getLineWidth(), 1e-6);
    }

    @Test
    void dashed() {
        float[] dash = {4f, 2f};
        DrawStyle ds = DrawStyle.dashed(Color.BLUE, 1.2f, dash);
        assertEquals(Color.BLUE, ds.getStrokeColor());
        assertNull(ds.getFillColor());
        assertTrue(ds.getStroke() instanceof BasicStroke);
        assertEquals(1.2f, ((BasicStroke) ds.getStroke()).getLineWidth(), 1e-6);
    }
}

