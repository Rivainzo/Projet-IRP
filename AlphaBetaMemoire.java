//package jeux;

//import jeux.Game;

import java.util.Hashtable;
import java.util.Random;
//import jeux.Triple;

public class AlphaBetaMemoire<STATE, ACTION> implements Search<STATE, ACTION>{

    private Game<STATE, ACTION> game;
    private double _alpha = Double.NEGATIVE_INFINITY;
    private double _beta = Double.POSITIVE_INFINITY;
    private int expandedNodes;

    private Hashtable<Long, Triple<Double, Double, Integer>> _table = new Hashtable<>();


    public static <STATE, ACTION> AlphaBetaMemoire<STATE, ACTION>
    createFor(Game<STATE, ACTION> game) {
        return new AlphaBetaMemoire<STATE, ACTION>(game);
    }

    public AlphaBetaMemoire(Game<STATE, ACTION> game) {
        this.game = game;
        Triple<Double, Double, Integer> alpha_beta_depth_init = new Triple<>(getAlpha(), getBeta(),0);
        _table.put(game.ZobristHashState(game.getInitialState()), alpha_beta_depth_init);
        game.set_hashcode_joueurMax();
        game.set_hashcode_joueurMin();
    }

    public double getAlpha(){
        return _alpha;
    }

    public double getBeta(){
        return _beta;
    }


    public double maxValue(STATE state, boolean player, int depth) {
        // calcule une valeur d'utilité pour un noued max
        assert (player);
        expandedNodes++;
        if (game.isTerminal(state) || depth <= 0)
            return game.getUtility(state, player);
        double value = Double.NEGATIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.max(value,
                    minValue(game.getResult(state, action), !player, depth-1));
            if (value > getBeta()) return value;
            _alpha = Math.max(_alpha,value);
        }
        return value;
    }

    public double minValue(STATE state, boolean player, int depth) {
        // calcule une valeur d'utilité pour un noeud min
        assert (!(player));
        expandedNodes++;
        if (game.isTerminal(state) || depth <= 0) {
            return game.getUtility(state, player);
        }
        long hash_state = game.ZobristHashState(state);
        if (_table.containsKey(hash_state) && _table.get(hash_state).get_d() <= depth) {
            _alpha = _table.get(hash_state).get_g();
            _beta = _table.get(hash_state).get_m();
        }
        double value = Double.POSITIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.min(value,
                    maxValue(game.getResult(state, action), !player, depth-1));
            if (value < getAlpha()) return value;
            _beta = Math.min(_beta, value);
        }
        if (_table.get(hash_state).get_d() > depth) {
            Triple<Double, Double, Integer> alpha_beta_depth = new Triple<>(getAlpha(), getBeta(), depth);
            _table.put(hash_state, alpha_beta_depth);
        }
        return value;
    }


    public ACTION makeDecision(STATE state) {
        expandedNodes = 0;
        ACTION result = null ;
        double resultValue = Double.NEGATIVE_INFINITY;
        boolean p = true;
        for (ACTION action : game.getActions(state)) {
            double value = minValue(game.getResult(state, action), !p, game.getDepth());
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
