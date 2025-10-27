package test.java.cs.vsu.hatuncev_k_v.Models;

import cs.vsu.hatuncev_k_v.Models.SceneEntity;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SceneEntityPropertiesTest {
    static { System.setProperty("java.awt.headless", "true"); }

    // Минимальная заглушка
    static class DummyEntity extends SceneEntity {
        public DummyEntity(double x, double y, double w, double h, double speed) {
            super(x, y, w, h, speed);
        }
        @Override public void draw(Graphics2D g) { /* no-op */ }
    }

    @Test
    void ctorAssignsProperties() {
        DummyEntity e = new DummyEntity(10.5, 20.0, 30.0, 40.0, 2.5);

        assertEquals(10.5, e.getOriginX(), 1e-9);
        assertEquals(20.0, e.getOriginY(), 1e-9);
        assertEquals(30.0, e.getWidth(),   1e-9);
        assertEquals(40.0, e.getHeight(),  1e-9);
        assertEquals(2.5,  e.getAnimationSpeed(), 1e-9);
    }
}

