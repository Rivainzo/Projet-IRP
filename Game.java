package jeux;

import java.util.Hashtable;
import java.util.List;

public interface Game<STATE, ACTION> {

	STATE getInitialState();

	List<ACTION> getActions(STATE state);

	STATE getResult(STATE state, ACTION action);

	boolean isTerminal(STATE state);

	double getUtility(STATE state, boolean player);

	Hashtable<ACTION, Long> get_hashcode_joueurMax();
	Hashtable<ACTION, Long> get_hashcode_joueurMin();

	void set_hashcode_joueurMax();
	void set_hashcode_joueurMin();
	long ZobristHashState(STATE state);


	int getDepth();
}
