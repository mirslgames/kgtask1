package cs.vsu.hatuncev_k_v.Models;

import cs.vsu.hatuncev_k_v.DrawStyle;
import cs.vsu.hatuncev_k_v.Primitives.ArcShape;
import cs.vsu.hatuncev_k_v.Primitives.LineSegment;
import cs.vsu.hatuncev_k_v.Primitives.RectShape;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public class Bridge extends SceneEntity {
    private final Color bridgeColor = new Color(80, 80, 85);
    private final Color roadColor   = new Color(58, 58, 60);
    private final Color edgeColor   = new Color(120, 120, 125);
    private final Color railColor   = new Color(210, 210, 210);
    private final Color wireColor   = new Color(200, 200, 200);

    private Color openingColor = new Color(90, 160, 220);

    private final double wireOffset = 105;

    public Bridge(double originX, double originY, double width, double height) {
        super(originX, originY, width, height);
    }

    public void setOpeningColor(Color c) { this.openingColor = c; }
    @Override
    public void onToggleDayNight(boolean night) {
        setOpeningColor(night ? new java.awt.Color(35, 70, 120)
                : new java.awt.Color(90, 160, 220));
    }


    @Override
    public void draw(Graphics2D g) {
        drawDeck(g);
        drawGuardRails(g);
        drawRails(g);
        drawSubstructureWithArches(g);
        drawOverheadWire(g);
    }

    private void drawDeck(Graphics2D g) {
        new RectShape(originX, originY, width, height, DrawStyle.fill(roadColor)).draw(g);
        new LineSegment(originX, originY + 1, originX + width, originY + 1,
                DrawStyle.stroke(edgeColor, 2f)).draw(g);
        new LineSegment(originX, originY + height - 1, originX + width, originY + height - 1,
                DrawStyle.stroke(edgeColor, 2f)).draw(g);
    }

    private void drawGuardRails(Graphics2D g) {
        new LineSegment(originX, originY, originX + width, originY,
                DrawStyle.stroke(bridgeColor, 2f)).draw(g);
        new LineSegment(originX, originY + height, originX + width, originY + height,
                DrawStyle.stroke(bridgeColor, 2f)).draw(g);
    }

    private void drawRails(Graphics2D g) {
        double left  = originX + 10;
        double right = originX + width - 30;
        double yUpper = originY + height - 20;
        double yLower = originY + height - 10;

        drawTiesSkewed(g, left, right, yUpper, yLower);

        new LineSegment(left,  yUpper, right, yUpper, DrawStyle.stroke(railColor, 3f)).draw(g);
        new LineSegment(left,  yLower, right, yLower, DrawStyle.stroke(railColor, 3f)).draw(g);
    }

    private void drawTiesSkewed(Graphics2D g, double left, double right, double yUpper, double yLower) {
        final Color tieFill   = new Color(110, 95, 70);
        final Color tieStroke = new Color(60, 50, 40);

        final double angleRad = Math.toRadians(-12);
        final double tieThickness = 6;
        final double tieLength = (yLower - yUpper) + 12;
        final double midY = (yUpper + yLower) / 2.0;
        double step = 32;

        for (double x = left; x <= right; x += step) {
            Graphics2D gg = (Graphics2D) g.create();
            gg.rotate(angleRad, x, midY);
            new RectShape(x - tieThickness / 2.0, midY - tieLength / 2.0, tieThickness, tieLength,
                    DrawStyle.strokeAndFill(tieStroke, 1f, tieFill)).draw(gg);
            gg.dispose();
        }
    }

    private void drawSubstructureWithArches(Graphics2D g) {
        final double bandH   = 64;
        final double bandTop = originY + height;
        final double bandBot = bandTop + bandH;
        final Rectangle2D bandRect = new Rectangle2D.Double(originX, bandTop, width, bandH);

        new RectShape(bandRect.getX(), bandRect.getY(), bandRect.getWidth(), bandRect.getHeight(),
                DrawStyle.fill(new Color(70, 70, 75))).draw(g);

        Shape oldClip = g.getClip();
        g.setClip(bandRect);


        final int arches = 10;
        final double span  = width / (double) arches;
        final double archW = span - 18;
        final double archH = bandH + 24;
        final double startAngle = 0;
        final double extent     = 180;

        final double yArc = bandBot - archH + 6;

        for (int i = 0; i < arches; i++) {
            double ax = originX + i * span + 9;
            new ArcShape(ax, yArc, archW, archH, startAngle, extent, Arc2D.CHORD,
                    DrawStyle.strokeAndFill(new Color(60, 60, 65), 2f, openingColor)).draw(g);
        }

        g.setClip(oldClip);

        final double pierW = 10;
        final Color  pierColor = new Color(76, 76, 82);
        for (int i = 1; i < arches; i++) {
            double px = originX + i * span - pierW / 2.0;
            new RectShape(px, bandTop, pierW, bandH, DrawStyle.fill(pierColor)).draw(g);
        }

        new LineSegment(originX, bandBot, originX + width, bandBot,
                DrawStyle.stroke(new Color(55, 55, 60), 2f)).draw(g);
    }

    private void drawOverheadWire(Graphics2D g) {
        double yWire = originY - wireOffset;
        new LineSegment(originX, yWire, originX + width, yWire,
                DrawStyle.stroke(wireColor, 1.8f)).draw(g);
    }
}
