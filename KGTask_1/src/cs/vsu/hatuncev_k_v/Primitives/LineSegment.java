package cs.vsu.hatuncev_k_v.Primitives;

import cs.vsu.hatuncev_k_v.DrawStyle;

import java.awt.*;
import java.awt.geom.Line2D;

public class LineSegment implements Drawable {
    private double x1, y1, x2, y2;
    private DrawStyle style;

    public LineSegment(double x1, double y1, double x2, double y2, DrawStyle style) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.style = style;
    }

    @Override
    public void draw(Graphics2D g) {
        if (style.getStrokeColor() == null) return;
        Stroke stroke = g.getStroke();
        Color color = g.getColor();

        if (style.getStroke() != null) g.setStroke(style.getStroke());
        g.setColor(style.getStrokeColor());
        g.draw(new Line2D.Double(x1, y1, x2, y2));

        g.setStroke(stroke);
        g.setColor(color);
    }
}

