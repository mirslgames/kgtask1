package cs.vsu.hatuncev_k_v.Primitives;

import cs.vsu.hatuncev_k_v.DrawStyle;

import java.awt.*;
import java.awt.geom.Arc2D;

public class ArcShape implements Drawable {
    private final double x, y, w, h;
    private final double startAngleDeg, extentDeg;
    private final int type; // Arc2D.OPEN (просто дуга), Arc2D.CHORD (дуга с хордами), Arc2D.PIE (сектор/пирог)
    private final DrawStyle style;

    public ArcShape(double x, double y, double w, double h,
                    double startAngleDeg, double extentDeg, int type, DrawStyle style) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.startAngleDeg = startAngleDeg;
        this.extentDeg = extentDeg;
        this.type = type;
        this.style = style;
    }

    @Override
    public void draw(Graphics2D g) {
        Arc2D arc = new Arc2D.Double(x, y, w, h, startAngleDeg, extentDeg, type);

        Color color = g.getColor();
        Stroke stroke = g.getStroke();

        // Для PIE заливка даст «сектор», для OPEN/CHORD — область дуги
        if (style.getFillColor() != null) {
            g.setColor(style.getFillColor());
            g.fill(arc);
        }
        if (style.getStrokeColor() != null) {
            if (style.getStroke() != null) g.setStroke(style.getStroke());
            g.setColor(style.getStrokeColor());
            g.draw(arc);
        }

        g.setColor(color);
        g.setStroke(stroke);
    }
}

