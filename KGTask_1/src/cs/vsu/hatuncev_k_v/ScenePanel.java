package cs.vsu.hatuncev_k_v;

import cs.vsu.hatuncev_k_v.Models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;

public class ScenePanel extends JPanel implements ActionListener {

    private SceneState currentSceneState;
    private final ArrayList<SceneEntity> sceneEntities = new ArrayList<>();
    public  final Timer timer;

    public ScenePanel(int width, int  height, int fps) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);
        setOpaque(true);
        setDoubleBuffered(true);
        currentSceneState = new SceneState(TimeOfDay.DAY);
        initializeKeyListeners();
        int delay = Math.max(5, 1000 / Math.max(1, fps));
        timer = new Timer(delay, this);
        timer.start();
    }


    private void initializeKeyListeners() {
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override public void keyPressed(java.awt.event.KeyEvent e) {
                int code = e.getKeyCode();
                double step = 0.5;

                if (code == java.awt.event.KeyEvent.VK_SPACE) {
                    boolean night = (currentSceneState.timeOfDay == TimeOfDay.DAY);
                    currentSceneState.timeOfDay = night ? TimeOfDay.NIGHT : TimeOfDay.DAY;
                    for (SceneEntity se : sceneEntities) se.onToggleDayNight(night);
                    repaint();
                } else if (code == java.awt.event.KeyEvent.VK_P) {
                    currentSceneState.paused = !currentSceneState.paused;
                    for (SceneEntity se : sceneEntities) se.onPause(currentSceneState.paused);
                } else if (code == java.awt.event.KeyEvent.VK_F) {
                    for (SceneEntity se : sceneEntities) se.onToggleHeadlights();
                    repaint();
                } else if (code == java.awt.event.KeyEvent.VK_RIGHT) {
                    for (SceneEntity se : sceneEntities) se.onChangeSpeed(+step);
                } else if (code == java.awt.event.KeyEvent.VK_LEFT) {
                    for (SceneEntity se : sceneEntities) se.onChangeSpeed(-step);
                } else if (code == java.awt.event.KeyEvent.VK_0) {
                    for (SceneEntity se : sceneEntities) se.onChangeSpeed(-se.getAnimationSpeed());
                }
            }
        });
    }


    public void addEntity(SceneEntity entity) {
        sceneEntities.add(entity);
        /*if(isAnimated){
            animatedSceneEntities.add(entity);
        }*/
    }
    public void removeEntity(SceneEntity entity) {
        sceneEntities.remove(entity);
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) gr.create();
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        for (SceneEntity e : sceneEntities) e.draw(g2);

        new cs.vsu.hatuncev_k_v.Primitives.TextLabel(
                "SPACE—день/ночь  P—пауза  F—фары  ←/→—скорость  0—стоп",
                16, getHeight() - 20,
                new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 13),
                new java.awt.Color(255, 255, 255, 220),
                cs.vsu.hatuncev_k_v.Primitives.TextLabel.Anchor.LEFT
        ).draw(g2);

        g2.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentSceneState.paused || e.getSource() != timer) return;

        int panelW = getWidth();

        for(SceneEntity entity : sceneEntities){
            entity.animateMove(currentSceneState.speedCoefficient, 0);
            wrapHorizontally(entity, panelW);
        }

        repaint();
    }

    private void wrapHorizontally(SceneEntity ent, int panelW) {
        if (ent.getOriginX() > panelW) {
            ent.setOriginX(-ent.getWidth());
        } else if (ent.getOriginX() + ent.getWidth() < 0) {
            ent.setOriginX(panelW);
        }
    }


}
