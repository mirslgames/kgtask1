package cs.vsu.hatuncev_k_v.Primitives;

import java.awt.*;

public class TextLabel implements Drawable {
    public enum Anchor { LEFT, CENTER, RIGHT }

    private final String text;
    private final double x, y;
    private final Font font;
    private final Color color;
    private final Anchor anchor;

    public TextLabel(String text, double x, double y, Font font, Color color, Anchor anchor) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = font;
        this.color = color;
        this.anchor = anchor;
    }

    @Override
    public void draw(Graphics2D g) {
        Font localFont = g.getFont();
        Color localColor = g.getColor();

        g.setFont(font);
        g.setColor(color);

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int ascent = fm.getAscent();

        double drawX = x;
        if (anchor == Anchor.CENTER) drawX = x - textWidth / 2.0;
        else if (anchor == Anchor.RIGHT) drawX = x - textWidth;

        g.drawString(text, (float) drawX, (float) (y + ascent / 2.0));

        g.setFont(localFont);
        g.setColor(localColor);
    }
}

