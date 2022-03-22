/*package jeux;

import jeux.Game;*/


public class MinimaxSearch<STATE, ACTION> implements
		Search<STATE, ACTION> {

	private Game<STATE, ACTION> game;
	private int expandedNodes;

	/** Creates a new search object for a given game. */
	public static <STATE, ACTION> MinimaxSearch<STATE, ACTION>
	    createFor(Game<STATE, ACTION> game) {
	return new MinimaxSearch<STATE, ACTION>(game);
	}

	public MinimaxSearch(Game<STATE, ACTION> game) {
		this.game = game;
	}


	public ACTION makeDecision(STATE state) {
		expandedNodes = 0;
		ACTION result = null ;
		double resultValue = Double.NEGATIVE_INFINITY;
		boolean p = true;
		for (ACTION action : game.getActions(state)) {
	        double value = minValue(game.getResult(state, action), !p);
		 if (value > resultValue) {
		   result = action;
		   resultValue = value;
		 }
		}
		return result;
	}

	public double maxValue(STATE state, boolean player) {
	    // calcule une valeur d'utilité pour un noued max
		assert (player);
		expandedNodes++;
		if (game.isTerminal(state))
	     	return game.getUtility(state, player);
		double value = Double.NEGATIVE_INFINITY;
		for (ACTION action : game.getActions(state))
                 value = Math.max(value,
                         minValue(game.getResult(state, action),!player));
		return value;
	}

	public double minValue(STATE state, boolean player) {
	     // calcule une valeur d'utilité pour un noeud min
	        assert (!(player));
	        expandedNodes++;
		if (game.isTerminal(state)){
			//System.out.println("TEST minValue " + game.getUtility(state, player));
		    return game.getUtility(state, player);}
		double value = Double.POSITIVE_INFINITY;
		for (ACTION action : game.getActions(state)) {
			//System.out.println("TEST minValue " + game.getResult(state, action));
			value = Math.min(value,
					maxValue(game.getResult(state, action), !player));
		}
		return value;
	}


    

	public int getMetrics() {
		return expandedNodes;
	}
}
