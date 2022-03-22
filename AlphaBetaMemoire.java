//package jeux;

//import jeux.Game;

import java.util.Hashtable;
//import jeux.Triple;

public class AlphaBetaMemoire<STATE, ACTION> implements Search<STATE, ACTION>{

    private Game<STATE, ACTION> game;
    private int expandedNodes;

    //private Hashtable<Long, Triple<Double, Double, Integer>> _table = new Hashtable<>();


    public static <STATE, ACTION> AlphaBetaMemoire<STATE, ACTION>
    createFor(Game<STATE, ACTION> game) {
        return new AlphaBetaMemoire<STATE, ACTION>(game);
    }

    public AlphaBetaMemoire(Game<STATE, ACTION> game) {
        this.game = game;
        game.set_hashcode_joueurMax();
        game.set_hashcode_joueurMin();
    }


    public double maxValue(STATE state, boolean player, int depth, double alpha, double beta, Hashtable<Long, Triple<Double, Double, Integer>> table) {
        // calcule une valeur d'utilité pour un noeud max
        assert (player);
        expandedNodes++;
        if (game.isTerminal(state) || depth <= 0)
            return game.getUtility(state, player);
        long hash_state = game.ZobristHashState(state);
        if (table.containsKey(hash_state) && table.get(hash_state).get_d() <= depth) {
            alpha = table.get(hash_state).get_g();
            beta = table.get(hash_state).get_m();
            //return game.getUtility(state, player);
        }
        double value = Double.NEGATIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.max(value,
                    minValue(game.getResult(state, action), !player, depth-1, alpha, beta, table));
            if (value > beta) return value;
            alpha = Math.max(alpha,value);
        }
        if (table.get(hash_state).get_d() > depth) {
            Triple<Double, Double, Integer> alpha_beta_depth = new Triple<>(alpha, beta, depth);
            table.put(hash_state, alpha_beta_depth);
        }
        return value;
    }

    public double minValue(STATE state, boolean player, int depth, double alpha, double beta, Hashtable<Long, Triple<Double, Double, Integer>> table) {
        // calcule une valeur d'utilité pour un noeud min
        assert (!(player));
        expandedNodes++;
        if (game.isTerminal(state) || depth <= 0) {
            return game.getUtility(state, player);
        }
        long hash_state = game.ZobristHashState(state);
        if (table.containsKey(hash_state) && table.get(hash_state).get_d() <= depth) {
            alpha = table.get(hash_state).get_g();
            beta = table.get(hash_state).get_m();
            //return game.getUtility(state, player);
        }
        double value = Double.POSITIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.min(value,
                    maxValue(game.getResult(state, action), !player, depth-1, alpha, beta, table));
            if (value < alpha) return value;
            beta = Math.min(beta, value);
        }
        if (table.get(hash_state).get_d() > depth) {
            Triple<Double, Double, Integer> alpha_beta_depth = new Triple<>(alpha, beta, depth);
            table.put(hash_state, alpha_beta_depth);
        }
        return value;
    }


    public ACTION makeDecision(STATE state) {

        Triple<Double, Double, Integer> alpha_beta_depth_init = new Triple<>(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,0);
        Hashtable<Long, Triple<Double, Double, Integer>> table = new Hashtable<>();
        table.put(game.ZobristHashState(state), alpha_beta_depth_init);

        expandedNodes = 0;
        ACTION result = null ;
        double resultValue = Double.NEGATIVE_INFINITY;
        boolean p = true;
        for (ACTION action : game.getActions(state)) {
            double value = minValue(game.getResult(state, action), !p, game.getDepth(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, table);
            if (value > resultValue) {
                result = action;
                resultValue = value;
            }
        }
        return result;
    }

    public int getMetrics(){
        return expandedNodes;
    }

}
