package cs.vsu.hatuncev_k_v.Primitives;
import cs.vsu.hatuncev_k_v.DrawStyle;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class RectShape implements Drawable {
    private double x, y, w, h;

    private double arcW, arcH;
    private final DrawStyle style;

    //Обычный прямоугольник без скругления
    public RectShape(double x, double y, double w, double h, DrawStyle style) {
        this(x, y, w, h, 0, 0, style);
    }
    //Прямоугольник со скруглением по радиусу
    public RectShape(double x, double y, double w, double h, double cornerRadius, DrawStyle style) {
        this(x, y, w, h, 2 * cornerRadius, 2 * cornerRadius, style);
    }

    //Прямоугольник со скруглением, можно отдельно задать горизонтальный и вертикальный диаметры дуг
    public RectShape(double x, double y, double w, double h, double arcW, double arcH, DrawStyle style) {
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.arcW = Math.max(0, arcW);
        this.arcH = Math.max(0, arcH);
        this.style = style;
    }

    @Override
    public void draw(Graphics2D g) {
        Shape shape = (arcW > 0 || arcH > 0)
                ? new RoundRectangle2D.Double(x, y, w, h, arcW, arcH)
                : new Rectangle2D.Double(x, y, w, h);

        Color color = g.getColor();
        Stroke stroke = g.getStroke();

        if (style.getFillColor() != null) {
            g.setColor(style.getFillColor());
            g.fill(shape);
        }
        if (style.getStrokeColor() != null) {
            if (style.getStroke() != null) g.setStroke(style.getStroke());
            g.setColor(style.getStrokeColor());
            g.draw(shape);
        }

        g.setColor(color);
        g.setStroke(stroke);
    }

    // Изменить у прямоугольника скругление
    public RectShape withCornerRadius(double r) {
        this.arcW = this.arcH = 2 * r;
        return this;
    }
    public RectShape withArc(double arcW, double arcH) {
        this.arcW = Math.max(0, arcW);
        this.arcH = Math.max(0, arcH);
        return this;
    }
}

