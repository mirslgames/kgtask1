package cs.vsu.hatuncev_k_v;

import java.awt.*;

public class DrawStyle {
    private final Color strokeColor;   //null = отсутствие
    private final Color fillColor;     //null = отсутствие
    private final Stroke stroke;

    public DrawStyle(Color strokeColor, Color fillColor, Stroke stroke) {
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.stroke = stroke;
    }

    public Color getStrokeColor() { return strokeColor; }
    public Color getFillColor() { return fillColor; }
    public Stroke getStroke() { return stroke; }

    public static DrawStyle stroke(Color color, float width) {
        return new DrawStyle(color, null, new BasicStroke(width));
    }
    public static DrawStyle fill(Color fill) {
        return new DrawStyle(null, fill, null);
    }
    public static DrawStyle strokeAndFill(Color stroke, float width, Color fill) {
        return new DrawStyle(stroke, fill, new BasicStroke(width));
    }

    public static DrawStyle dashed(Color color, float width, float[] dash) {
        return new DrawStyle(color, null,
                new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dash, 0f));
    }
}
