package jeux.puissance4;

import java.util.List;

import jeux.Paire;
import jeux.AlphaBetaDepth;
import jeux.puissance4.Puissance4;
import jeux.MinimaxSearch;
import jeux.MinimaxDepthSearch;
import jeux.AlphaBeta;
import jeux.AlphaBetaDepth;

//Game<Paire<Integer[][], Boolean>, Paire<Integer, Integer>>
// Paire<Integer[][], Boolean>
// Paire<Integer, Integer>

public class Puissance4Test {

    public static void main(String[] args) {
        Puissance4 game = new Puissance4(6,7,false,5);
        Paire<Integer[][], Boolean> state;
        MinimaxSearch<Paire<Integer[][], Boolean>, Paire<Integer, Integer>> minimaxSearch =
                MinimaxSearch.createFor(game);
        MinimaxDepthSearch<Paire<Integer[][], Boolean>, Paire<Integer, Integer>> minimaxDepthSearch =
                MinimaxDepthSearch.createFor(game);
        AlphaBeta<Paire<Integer[][], Boolean>, Paire<Integer, Integer>> alphaBeta =
                AlphaBeta.createFor(game);
        AlphaBetaDepth<Paire<Integer[][], Boolean>, Paire<Integer, Integer>> alphaBetaDepth =
                AlphaBetaDepth.createFor(game);
        AlphaBetaDepth<Paire<Integer[][], Boolean>, Paire<Integer, Integer>> alphaBetaDepth2 =
                AlphaBetaDepth.createFor(game);

        state = game.getInitialState();
        Paire<Integer, Integer> action = new Paire<Integer, Integer>(-1,-1);

        /* MinimaxSearch */
        /*System.out.println("MinimaxSearch:\n");
        System.out.println("Machine player, what is your action?");
        action = minimaxSearch.makeDecision(state);
        System.out.println("Metrics for Minimax : " + minimaxSearch.getMetrics());
        System.out.println("Chosen action is (" + action.get_g() + ", " + action.get_d() + ")");*/


        /* MinimaxDepthSearch */
        System.out.println("\n\nMinimaxDepthSearch:\n");
        System.out.println("Machine player, what is your action?");
        action = minimaxDepthSearch.makeDecision(state);
        System.out.println("Metrics for Minimax with limited depth : " + minimaxDepthSearch.getMetrics());
        System.out.println("Chosen action is (" + action.get_g() + ", " + action.get_d() + ")");


        /* AlphaBeta */
        /*System.out.println("\n\nAlphaBeta:\n");
        System.out.println("Machine player, what is your action?");
        action = alphaBeta.makeDecision(state);
        System.out.println("Metrics for AlphaBeta : " + alphaBeta.getMetrics());
        System.out.println("Chosen action is (" + action.get_g() + ", " + action.get_d() + ")");*/

        /* AlphaBetaDepth */
        System.out.println("\n\nAlphaBetaDepth:\n");
        System.out.println("Machine player, what is your action?");
        action = alphaBetaDepth.makeDecision(state);
        System.out.println("Metrics for AlphaBeta : " + alphaBetaDepth.getMetrics());
        System.out.println("Chosen action is (" + action.get_g() + ", " + action.get_d() + ")");

    }
}
