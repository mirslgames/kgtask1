package cs.vsu.hatuncev_k_v;

import cs.vsu.hatuncev_k_v.Models.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MainWindow window = new MainWindow("Рисование эллипса", 975, 640, 60);
        window.setVisible(true);

        /*Sky sky = new Sky(0, 0, 960, 600);
        Bridge bridge = new Bridge(0, 360, 960, 40);
        River river   = new River(0, 400, 960, 200);
        Cloud cloud   = new Cloud(120, 90, 160, 60, 2);
        Tram tram     = new Tram(60, 290, 240, 84, 4);
        Cloud cloud2  = new Cloud(360, 70,  130, 48, 1.2, 0.85);
        Cloud cloud3  = new Cloud(720, 120, 170, 62, 0.9, 1.15);


        window.getScenePanel().addEntity(sky);
        window.getScenePanel().addEntity(river);
        window.getScenePanel().addEntity(bridge);
        window.getScenePanel().addEntity(cloud);
        window.getScenePanel().addEntity(tram);
        window.getScenePanel().addEntity(cloud2);
        window.getScenePanel().addEntity(cloud3);*/

        GradientEllipse ge = new GradientEllipse(10,10,400,200, Color.BLACK, Color.GRAY);
        window.getScenePanel().addEntity(ge);
    }
}