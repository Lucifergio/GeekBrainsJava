package Race;


import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class Race {

    private CopyOnWriteArrayList<Stage> stages;

    public CopyOnWriteArrayList<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) throws InterruptedException {
        this.stages = new CopyOnWriteArrayList<>(Arrays.asList(stages));
    }
}
