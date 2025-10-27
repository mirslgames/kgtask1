package cs.vsu.hatuncev_k_v.Models;

import cs.vsu.hatuncev_k_v.DrawStyle;
import cs.vsu.hatuncev_k_v.Primitives.EllipseShape;

import java.awt.*;

public class Cloud extends SceneEntity {
    private boolean night = false;
    private Color colorDay = new Color(255, 255, 255, 230);
    private Color colorNight = new Color(220, 220, 235, 180);
    private double puff = 1.0;

    public Cloud(double originX, double originY, double width, double height) {
        super(originX, originY, width, height, 0);
    }

    public Cloud(double originX, double originY, double width, double height, double animationSpeed) {
        super(originX, originY, width, height, animationSpeed);
    }

    public Cloud(double originX, double originY, double width, double height, double animationSpeed, double puff) {
        this(originX, originY, width, height, animationSpeed);
        this.puff = Math.max(0.6, Math.min(1.6, puff));
    }

    public void setNight(boolean night) { this.night = night; }
    public void setPuff(double puff) { this.puff = Math.max(0.6, Math.min(1.6, puff)); }

    @Override
    public void draw(Graphics2D g) {
        Color c = night ? colorNight : colorDay;
        DrawStyle fill = DrawStyle.fill(c);

        double h = height * puff;

        new EllipseShape(originX, originY, width, h, fill).draw(g);
        new EllipseShape(originX + width * 0.28, originY - h * 0.35, width * 0.52, h, fill).draw(g);
        new EllipseShape(originX + width * 0.55, originY + h * 0.02, width * 0.46, h * 0.98, fill).draw(g);
    }
}
