package cs.vsu.hatuncev_k_v.Models;

import cs.vsu.hatuncev_k_v.DrawStyle;
import cs.vsu.hatuncev_k_v.Primitives.RectShape;
import cs.vsu.hatuncev_k_v.Primitives.EllipseShape;

import java.awt.*;

public class Sky extends SceneEntity {
    private Color skyColor = new Color(135, 206, 250);
    private boolean night = false;
    private boolean showSunMoon = true;

    private final java.util.Random rnd = new java.util.Random();
    private int starsCount = 120;
    private int[] starX, starY, starR;

    public Sky(double originX, double originY, double width, double height) {
        super(originX, originY, width, height);
        initStars();
    }

    private void initStars() {
        starX = new int[starsCount];
        starY = new int[starsCount];
        starR = new int[starsCount];
        for (int i = 0; i < starsCount; i++) {
            starX[i] = rnd.nextInt((int) Math.max(1, width));

            starY[i] = rnd.nextInt((int) Math.max(1, (int)(height * 0.66)));
            starR[i] = 1 + rnd.nextInt(3);
        }
    }

    public void setSkyColor(Color c) { this.skyColor = c; }
    public void setNight(boolean night) { this.night = night; }
    public void setShowSunMoon(boolean on) { this.showSunMoon = on; }

    @Override
    public void onToggleDayNight(boolean night) {
        this.night = night;
        this.skyColor = night ? new java.awt.Color(20, 26, 51) : new java.awt.Color(135, 206, 250);
    }

    @Override
    public void draw(Graphics2D g) {
        new RectShape(originX, originY, width, height, DrawStyle.fill(skyColor)).draw(g);

        if (night) drawStars(g);

        if (!showSunMoon) return;
        if (night) {
            new EllipseShape(originX + width - 120, originY + 20, 70, 70,
                    DrawStyle.fill(new Color(240, 240, 255))).draw(g);
            new EllipseShape(originX + width - 105, originY + 25, 70, 70,
                    DrawStyle.fill(skyColor)).draw(g);
        } else {
            new EllipseShape(originX + width - 120, originY + 20, 80, 80,
                    DrawStyle.strokeAndFill(new Color(255, 200, 0), 2f, new Color(255, 215, 0))).draw(g);
        }
    }

    private void drawStars(Graphics2D g) {
        g.setColor(new Color(255, 255, 255, 230));
        for (int i = 0; i < starsCount; i++) {
            g.fillOval((int)(originX + starX[i]), (int)(originY + starY[i]), starR[i], starR[i]);
        }
    }


}


