package cs.vsu.hatuncev_k_v.Primitives;

import cs.vsu.hatuncev_k_v.DrawStyle;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EllipseShape implements Drawable {
    private double x, y, w, h;
    private final DrawStyle style;

    public EllipseShape(double x, double y, double w, double h, DrawStyle style) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.style = style;
    }

    @Override
    public void draw(Graphics2D g) {
        Shape e = new Ellipse2D.Double(x, y, w, h);

        Color color = g.getColor();
        Stroke stroke = g.getStroke();

        if (style.getFillColor() != null) {
            g.setColor(style.getFillColor());
            g.fill(e);
        }
        if (style.getStrokeColor() != null) {
            if (style.getStroke() != null) g.setStroke(style.getStroke());
            g.setColor(style.getStrokeColor());
            g.draw(e);
        }

        g.setColor(color);
        g.setStroke(stroke);
    }
}

