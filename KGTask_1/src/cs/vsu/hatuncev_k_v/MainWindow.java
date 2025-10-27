package cs.vsu.hatuncev_k_v;

import javax.swing.JFrame;
import java.awt.*;

public class MainWindow extends JFrame {
    private final ScenePanel scenePanel;

    public MainWindow(String mainWindowTitle, int width, int height, int fps) throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(width, height);
        this.setTitle(mainWindowTitle);
        scenePanel = new ScenePanel(this.getWidth(), this.getHeight(), fps);
        this.add(scenePanel);
    }

    public ScenePanel getScenePanel(){
        return scenePanel;
    }
}

