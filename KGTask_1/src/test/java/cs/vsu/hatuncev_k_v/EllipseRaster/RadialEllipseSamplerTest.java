package test.java.cs.vsu.hatuncev_k_v.EllipseRaster;

import cs.vsu.hatuncev_k_v.EllipseRaster.RadialEllipseSampler;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class RadialEllipseSamplerTest {


    @Test
    void colorAt_onBoundary_returnsEdgeColor() {
        var center = new Color(10, 20, 30, 200);
        var edge = new Color(40, 50, 60, 80);
        var sampler = new RadialEllipseSampler(10.0, 20.0, center, edge);

        Color c = sampler.colorAt(10.0, 0.0);
        assertEquals(edge.getRGB(), c.getRGB());
    }

    @Test
    void colorAt_outside_returnsTransparent() {
        var sampler = new RadialEllipseSampler(
                10.0, 20.0,
                new Color(0, 0, 0, 255),
                new Color(0, 0, 0, 0)
        );

        Color c = sampler.colorAt(10.0001, 0.0);
        assertEquals(new Color(0, 0, 0, 0).getRGB(), c.getRGB());
        assertEquals(0, c.getAlpha());
    }

    @Test
    void clamp_belowZero_returnsZero() {
        double clamped = RadialEllipseSampler.clamp01(-1.23);
        assertEquals(0.0, clamped, 1e-12);
    }

    @Test
    void clamp_aboveOne_returnsOne() {
        double clamped = RadialEllipseSampler.clamp01(2.7);
        assertEquals(1.0, clamped, 1e-12);
    }

    @Test
    void clamp_insideRange_returnsSameValue() {
        double v = 0.42;
        double clamped = RadialEllipseSampler.clamp01(v);
        assertEquals(v, clamped, 1e-12);
    }

    @Test
    void lerp_t0_returnsFirstColor() {
        Color c0 = new Color(1, 2, 3, 4);
        Color c1 = new Color(200, 150, 100, 50);
        Color out = RadialEllipseSampler.lerpColor(c0, c1, 0.0);
        assertEquals(c0.getRGB(), out.getRGB());
    }

    @Test
    void lerp_t1_returnsSecondColor() {
        Color c0 = new Color(1, 2, 3, 4);
        Color c1 = new Color(200, 150, 100, 50);
        Color out = RadialEllipseSampler.lerpColor(c0, c1, 1.0);
        assertEquals(c1.getRGB(), out.getRGB());
    }

    @Test
    void lerp_tHalf_mixesChannelsWithRounding() {
        Color c0 = new Color(255, 0, 0, 255);
        Color c1 = new Color(0, 0, 255, 0);

        Color mid = RadialEllipseSampler.lerpColor(c0, c1, 0.5);

        assertEquals(128, mid.getAlpha());
        assertEquals(128, mid.getRed());
        assertEquals(0, mid.getGreen());
        assertEquals(128, mid.getBlue());

        int expectedArgb = (128 << 24) | (128 << 16) | (0) | 128;
        assertEquals(expectedArgb, mid.getRGB());
    }
}
