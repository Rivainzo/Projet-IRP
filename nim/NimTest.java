package jeux.nim;

import java.util.List;
import jeux.nim.Nim;
import jeux.MinimaxSearch;
import jeux.MinimaxDepthSearch;
import jeux.AlphaBeta;

public class NimTest {

    public static void main(String[] args) {
        Nim game = new Nim(10, 3);
        int state;
        MinimaxSearch<Integer, Integer> minimaxSearch = 
            MinimaxSearch.createFor(game);
        MinimaxDepthSearch<Integer, Integer> minimaxDepthSearch =
                MinimaxDepthSearch.createFor(game);
        AlphaBeta<Integer, Integer> alphaBeta =
                AlphaBeta.createFor(game);
	
        state = game.getInitialState();
	    int action = -1;

        /* MinimaxSearch */
        System.out.println("MinimaxSearch:\n");
	    System.out.println("Machine player, what is your action?");
	//
        action = minimaxSearch.makeDecision(state);
        System.out.println("Metrics for Minimax : " + minimaxSearch.getMetrics());
	    System.out.println("Chosen action is " + action);


        /* MinimaxDepthSearch */
        System.out.println("\n\nMinimaxDepthSearch:\n");
        System.out.println("Machine player, what is your action?");
        //
        action = minimaxDepthSearch.makeDecision(state);
        System.out.println("Metrics for Minimax with limited depth : " + minimaxDepthSearch.getMetrics());
        System.out.println("Chosen action is " + action);


        /* AlphaBeta */
        System.out.println("\n\nAlphaBeta:\n");
        System.out.println("Machine player, what is your action?");
        //
        action = alphaBeta.makeDecision(state);
        System.out.println("Metrics for AlphaBeta : " + alphaBeta.getMetrics());
        System.out.println("Chosen action is " + action);
   }
}
