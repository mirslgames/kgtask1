package test.java.cs.vsu.hatuncev_k_v.EllipseRaster;

import cs.vsu.hatuncev_k_v.EllipseRaster.RadialEllipseSampler;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class RadialEllipseSamplerTest {

    @Test
    void centerReturnsCenterColor_alphaOnlyCase() {
        // Центр = чёрный, A=255; Край = чёрный, A=0 → удобно считать по альфе.
        var sampler = new RadialEllipseSampler(10.0, 20.0,
                new Color(0,0,0,255),
                new Color(0,0,0,  0));

        int argbCenter = sampler.argbAt(0, 0);
        // t = 0 → цвет центра: 0xFF000000
        assertEquals(0xFF000000, argbCenter);
    }

    @Test
    void edgeReturnsEdgeColor_alphaOnlyCase() {
        var sampler = new RadialEllipseSampler(10.0, 20.0,
                new Color(0,0,0,255),
                new Color(0,0,0,  0));

        // Точка (x=a, y=0) → t = sqrt((a/a)^2 + 0) = 1
        int argbEdge = sampler.argbAt(10.0, 0.0);
        assertEquals(0x00000000, argbEdge);
    }

    @Test
    void halfwayAlongMajorAxis_tIsHalf_alphaIsHalfRounded() {
        var sampler = new RadialEllipseSampler(10.0, 20.0,
                new Color(0,0,0,255),
                new Color(0,0,0,  0));

        // (x = a/2, y = 0) → t = 0.5
        int argbHalf = sampler.argbAt(5.0, 0.0);

        // Ручной расчёт:
        // A = round(255 + (0 - 255)*0.5) = round(127.5) = 128 (0x80)
        // RGB = 0
        int expected = (128 << 24);
        assertEquals(expected, argbHalf);
    }

    @Test
    void outsideReturnsTransparent() {
        var sampler = new RadialEllipseSampler(10.0, 20.0,
                new Color(0,0,0,255),
                new Color(0,0,0,  0));

        // Чуть правее границы по большой оси → t > 1
        int argb = sampler.argbAt(10.0001, 0.0);
        assertEquals(0x00000000, argb);
    }

    @Test
    void rgbMixingAtHalf() {
        // Центр: красный, полностью непрозрачный
        // Край:  синий, полностью прозрачный
        var sampler = new RadialEllipseSampler(8.0, 8.0,
                new Color(255,0,0,255),
                new Color(0,0,255,0));

        // Выберем t=0.5: (x=a/2, y=0)
        int argb = sampler.argbAt(4.0, 0.0);

        // Ручной расчёт по каналам (округление к ближайшему):
        // A: round(255 + (0   -255)*0.5) = 128
        // R: round(255 + (0   -255)*0.5) = 128
        // G: round(0   + (0    -0 )*0.5) =   0
        // B: round(0   + (255 - 0 )*0.5) = 128
        int expected = (128<<24) | (128<<16) | (0<<8) | 128; // 0x80800080
        assertEquals(expected, argb);
    }
}
