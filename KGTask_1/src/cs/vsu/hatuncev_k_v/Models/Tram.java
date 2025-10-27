package cs.vsu.hatuncev_k_v.Models;

import cs.vsu.hatuncev_k_v.DrawStyle;
import cs.vsu.hatuncev_k_v.Primitives.*;

import java.awt.*;
import java.awt.geom.Arc2D;

import java.awt.*;
import java.awt.geom.Arc2D;

public class Tram extends SceneEntity {
    private Color bodyColor   = new Color(204, 0, 0);
    private Color windowColor = new Color(200, 230, 255);
    private boolean headlightsOn = true;
    private boolean night = false;
    private String routeText = "№ 5";

    public Tram(double originX, double originY, double width, double height) {
        super(originX, originY, width, height);
    }
    public Tram(double originX, double originY, double width, double height, double animationSpeed) {
        super(originX, originY, width, height, animationSpeed);
    }

    public void setBodyColor(Color c) { this.bodyColor = c; }
    public void setWindowColor(Color c) { this.windowColor = c; }
    public void setHeadlightsOn(boolean on) { this.headlightsOn = on; }
    public void setNight(boolean night) { this.night = night; }
    public void setRouteText(String txt) { this.routeText = txt; }

    public boolean isHeadlightsOn() { return this.headlightsOn; }
    public void toggleHeadlights() { setHeadlightsOn(!this.headlightsOn); }

    @Override
    public void onToggleDayNight(boolean night) { setNight(night); }

    @Override
    public void onToggleHeadlights() { toggleHeadlights(); }


    @Override
    public void draw(Graphics2D g) {
        drawShadow(g);
        drawCarBody(g);
        drawWindows(g);
        drawDoor(g);
        drawWheels(g);
        drawPantograph(g);
        drawHeadlight(g);
        drawRouteText(g);
    }

    private void drawShadow(Graphics2D g) {
        new EllipseShape(originX + width * 0.22, originY + height - 4, width * 0.56, 8,
                DrawStyle.fill(new Color(0, 0, 0, 60))).draw(g);
    }

    private void drawCarBody(Graphics2D g) {
        new RectShape(originX, originY, width, height, 18,
                DrawStyle.strokeAndFill(new Color(30, 30, 30), 2f, bodyColor)).draw(g);
    }

    private void drawWindows(Graphics2D g) {
        int count = 3;
        double pad = 14, gap = 12;
        double w = 40, h = 24;
        double left = originX + pad;
        double top  = originY + 18;

        for (int i = 0; i < count; i++) {
            double xx = left + i * (w + gap);
            new RectShape(xx, top, w, h,
                    DrawStyle.strokeAndFill(Color.DARK_GRAY, 1.4f, windowColor)).draw(g);
        }
    }

    private void drawDoor(Graphics2D g) {
        double dw = 42, pad = 12;
        new RectShape(originX + width - dw - pad, originY + pad, dw, height - 2 * pad,
                DrawStyle.strokeAndFill(Color.DARK_GRAY, 1.6f, new Color(230, 230, 240))).draw(g);
    }

    private void drawWheels(Graphics2D g) {
        double diameter = 26;
        double top = originY + height - diameter + 20;
        new EllipseShape(originX + width * 0.12, top, diameter, diameter, DrawStyle.fill(Color.BLACK)).draw(g);
        new EllipseShape(originX + width - width * 0.12 - diameter, top, diameter, diameter, DrawStyle.fill(Color.BLACK)).draw(g);
    }

    private void drawPantograph(Graphics2D g) {
        double baseX = originX + width / 2.0;
        double baseY = originY - 2;
        new LineSegment(baseX - 10, originY, baseX, baseY - 18, DrawStyle.stroke(Color.BLACK, 1.6f)).draw(g);
        new LineSegment(baseX + 10, originY, baseX, baseY - 18, DrawStyle.stroke(Color.BLACK, 1.6f)).draw(g);
        new LineSegment(baseX, baseY - 18, baseX + 38, baseY - 36, DrawStyle.stroke(Color.BLACK, 1.6f)).draw(g);
    }

    private void drawHeadlight(Graphics2D g) {
        if (!night || !headlightsOn) return;

        double ax = originX + width - 45;
        double ay = originY + height / 2.0 - 28;
        double aw = 110;
        double ah = 96;
        double start = -20;
        double extent = 50;

        new ArcShape(ax, ay, aw, ah, start, extent, Arc2D.PIE,
                DrawStyle.fill(new Color(255, 255, 180, 90)))
                .draw(g);
    }

    private void drawRouteText(Graphics2D g) {
        new TextLabel(routeText, originX + width / 2.0, originY + 60,
                new Font("SansSerif", Font.BOLD, 20), Color.WHITE, TextLabel.Anchor.CENTER).draw(g);
    }
}


