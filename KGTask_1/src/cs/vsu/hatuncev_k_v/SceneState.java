package cs.vsu.hatuncev_k_v;

public class SceneState {
    public TimeOfDay timeOfDay;
    public boolean paused;
    public double speedCoefficient;

    public SceneState(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
        this.paused = false;
        this.speedCoefficient = 1;
    }
}
