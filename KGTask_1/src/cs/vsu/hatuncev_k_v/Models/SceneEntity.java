package cs.vsu.hatuncev_k_v.Models;

import cs.vsu.hatuncev_k_v.Movable;
import cs.vsu.hatuncev_k_v.Primitives.Drawable;

import java.awt.Graphics2D;

public abstract class SceneEntity implements Drawable, Movable {
    protected double originX;
    protected double originY;
    protected double width;
    protected double height;
    private double animationSpeed;

    public void onToggleDayNight(boolean night) {}
    public void onChangeSpeed(double dvx) { this.animationSpeed += dvx;}
    public void onPause(boolean paused) {}
    public void onToggleHeadlights() {}

    protected SceneEntity(double originX, double originY, double width, double height, double animationSpeed) {
        this.originX = originX;
        this.originY = originY;
        this.width = width;
        this.height = height;
        this.animationSpeed = animationSpeed;
    }

    protected SceneEntity(double originX, double originY, double width, double height) {
        this(originX, originY, width, height, 0);
    }

    public double getOriginX() { return originX; }
    public double getOriginY() { return originY; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public double getAnimationSpeed() { return animationSpeed; }
    public void setOriginX(double x) { this.originX = x; }
    public void setOriginY(double y) { this.originY = y; }
    public void setWidth(double w) { this.width = w; }
    public void setHeight(double h) { this.height = h; }
    public void setAnimationSpeed(double animationSpeed) { this.animationSpeed = animationSpeed; }


    @Override
    public abstract void draw(Graphics2D g);

    @Override
    public void move(double dx, double dy){
        this.originX += dx;
        this.originY += dy;
    }
    public void animateMove(double coefficientX, double coefficientY){
        move(getAnimationSpeed() * coefficientX, getAnimationSpeed() * coefficientY);
    }
}

