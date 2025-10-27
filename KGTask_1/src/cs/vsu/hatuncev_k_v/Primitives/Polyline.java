package cs.vsu.hatuncev_k_v.Primitives;

import cs.vsu.hatuncev_k_v.DrawStyle;

import java.awt.*;
import java.awt.geom.Path2D;

public class Polyline implements Drawable {
    private final double[] xs, ys;
    private final DrawStyle style;

    public Polyline(double[] xs, double[] ys, DrawStyle style) {
        if (xs == null || ys == null || xs.length != ys.length || xs.length < 2)
            throw new IllegalArgumentException("Точки ломанной линии заданы неверно");
        this.xs = xs;
        this.ys = ys;
        this.style = style;
    }

    @Override
    public void draw(Graphics2D g) {
        if (style.getStrokeColor() == null) return;

        Path2D path = new Path2D.Double();
        path.moveTo(xs[0], ys[0]);
        for (int i = 1; i < xs.length; i++) path.lineTo(xs[i], ys[i]);

        Stroke stroke = g.getStroke();
        Color color = g.getColor();

        if (style.getStroke() != null) g.setStroke(style.getStroke());
        g.setColor(style.getStrokeColor());
        g.draw(path);

        g.setStroke(stroke);
        g.setColor(color);
    }
}

