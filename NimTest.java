package jeux.nim;

import java.util.List;

public class NimTest {

    public static void main(String[] args) {
        Nim game = new Nim(3, 10);
        MinimaxSearch<Integer, Integer> minimaxSearch = 
            MinimaxSearch.createFor(game);
	
        state = game.getInitialState();
	int action = -1;
	System.out.println("Machine player, what is your action?");
	//
        action = minimaxSearch.makeDecision(state);
        System.out.println("Metrics for Minimax : " + minimaxSearch.getMetrics());
	System.out.println("Chosen action is " + action);
	
   }
}
