package jeux;

import java.beans.beancontext.BeanContext;

public class AlphaBeta<STATE, ACTION> implements Search<STATE, ACTION>{

    private Game<STATE, ACTION> game;
    private double _alpha = Double.NEGATIVE_INFINITY;
    private double _beta = Double.POSITIVE_INFINITY;
    private int expandedNodes;

    public static <STATE, ACTION> AlphaBeta<STATE, ACTION>
    createFor(Game<STATE, ACTION> game) {
        return new AlphaBeta<STATE, ACTION>(game);
    }

    public AlphaBeta(Game<STATE, ACTION> game) {
        this.game = game;
    }

    public double getAlpha(){
        return _alpha;
    }

    public double getBeta(){
        return _beta;
    }


    public double maxValue(STATE state, boolean player) {
        // calcule une valeur d'utilité pour un noued max
        assert (player);
        expandedNodes++;
        if (game.isTerminal(state))
            return game.getUtility(state, player);
        double value = Double.NEGATIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.max(value,
                    minValue(game.getResult(state, action), !player));
            if (value > getBeta()) return value;
            _alpha = Math.max(_alpha,value);
        }
        return value;
    }

    public double minValue(STATE state, boolean player) {
        // calcule une valeur d'utilité pour un noeud min
        assert (!(player));
        expandedNodes++;
        if (game.isTerminal(state)) {
            return game.getUtility(state, player);
        }
        double value = Double.POSITIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.min(value,
                    maxValue(game.getResult(state, action), !player));
            if (value < getAlpha()) return value;
            _beta = Math.min(_beta, value);
        }
        return value;
    }


    /*public ACTION makeDecision(STATE state){
        return null;
    }*/

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







    public int getMetrics(){
        return expandedNodes;
    }



}
