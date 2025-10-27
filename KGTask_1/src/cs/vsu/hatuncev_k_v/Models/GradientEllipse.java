package cs.vsu.hatuncev_k_v.Models;

import cs.vsu.hatuncev_k_v.Models.SceneEntity;
import cs.vsu.hatuncev_k_v.EllipseRaster.PixelSampler;
import cs.vsu.hatuncev_k_v.EllipseRaster.RadialEllipseSampler;
import cs.vsu.hatuncev_k_v.EllipseRaster.Raster;

import java.awt.*;

public class GradientEllipse extends SceneEntity {
    private Color centerColor = new Color(255, 215, 0, 255);
    private Color edgeColor   = new Color(255, 215, 0, 0);

    public GradientEllipse(double x, double y, double w, double h, Color center, Color edge) {
        super(x, y, w, h);
        this.centerColor = center;
        this.edgeColor   = edge;
    }

    @Override
    public void draw(Graphics2D g) {
        int w = Math.max(1, (int) Math.round(getWidth()));
        int h = Math.max(1, (int) Math.round(getHeight()));

        PixelSampler sampler = new RadialEllipseSampler(w / 2.0, h / 2.0, centerColor, edgeColor);

        int[] argb = Raster.rasterize(w, h, sampler);
        Image img = Raster.toImage(argb, w, h);

        g.drawImage(img, (int) Math.round(getOriginX()), (int) Math.round(getOriginY()), null);
    }
}
