//package jeux;


public interface Search<STATE, ACTION> {

        ACTION makeDecision(STATE state);
        int getMetrics();
}
