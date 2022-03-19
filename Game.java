package jeux;

import java.util.List;

public interface Game<STATE, ACTION> {

	STATE getInitialState();

	List<ACTION> getActions(STATE state);

	STATE getResult(STATE state, ACTION action);

	boolean isTerminal(STATE state);

	double getUtility(STATE state, boolean player);

        int getDepth();
}
