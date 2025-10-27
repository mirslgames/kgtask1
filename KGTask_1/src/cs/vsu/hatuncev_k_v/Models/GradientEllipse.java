package cs.vsu.hatuncev_k_v.Models;

import cs.vsu.hatuncev_k_v.EllipseRaster.PixelSampler;
import cs.vsu.hatuncev_k_v.EllipseRaster.RadialEllipseSampler;
import cs.vsu.hatuncev_k_v.EllipseRaster.Raster;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GradientEllipse extends SceneEntity {
    private Color centerColor;
    private Color edgeColor;

    public GradientEllipse(double originX, double originY, double widthPixels, double heightPixels,
                           Color centerColor, Color edgeColor) {
        super(originX, originY, widthPixels, heightPixels);
        this.centerColor = centerColor;
        this.edgeColor   = edgeColor;
    }

    @Override
    public void draw(Graphics2D g) {
        final int imageWidth  = Math.max(1, (int) Math.round(getWidth()));
        final int imageHeight = Math.max(1, (int) Math.round(getHeight()));

        final double semiAxisX = imageWidth  / 2.0;
        final double semiAxisY = imageHeight / 2.0;

        PixelSampler sampler = new RadialEllipseSampler(semiAxisX, semiAxisY, centerColor, edgeColor);

        BufferedImage image = Raster.rasterizeToImage(imageWidth, imageHeight, sampler);

        final int drawX = (int) Math.round(getOriginX());
        final int drawY = (int) Math.round(getOriginY());
        g.drawImage(image, drawX, drawY, null);
    }
}
