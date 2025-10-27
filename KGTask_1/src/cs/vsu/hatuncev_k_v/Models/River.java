package cs.vsu.hatuncev_k_v.Models;

import cs.vsu.hatuncev_k_v.DrawStyle;
import cs.vsu.hatuncev_k_v.Primitives.QuadBezier;
import cs.vsu.hatuncev_k_v.Primitives.RectShape;

import java.awt.*;

import java.awt.*;

public class River extends SceneEntity {
    private boolean night = false;
    private Color waterDay = new Color(90, 160, 220);
    private Color waterNight = new Color(35, 70, 120);

    public River(double originX, double originY, double width, double height) {
        super(originX, originY, width, height);
    }

    public void setNight(boolean night) { this.night = night; }
    @Override
    public void onToggleDayNight(boolean night) { setNight(night); }



    @Override
    public void draw(Graphics2D g) {
        new RectShape(originX, originY, width, height,
                DrawStyle.fill(night ? waterNight : waterDay)).draw(g);

        Color wave = new Color(255, 255, 255, night ? 110 : 70);
        for (double yy = originY + 16; yy < originY + height - 10; yy += 22) {
            for (double x0 = originX; x0 < originX + width; x0 += 80) {
                new QuadBezier(x0, yy, x0 + 40, yy + 8, x0 + 80, yy,
                        DrawStyle.stroke(wave, 1.2f)).draw(g);
            }
        }
    }
}


