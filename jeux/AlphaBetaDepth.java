public class AlphaBetaDepth<STATE, ACTION> implements Search<STATE, ACTION>{

    private Game<STATE, ACTION> game;
    private int expandedNodes;

    public static <STATE, ACTION> AlphaBetaDepth<STATE, ACTION>
    createFor(Game<STATE, ACTION> game) {
        return new AlphaBetaDepth<STATE, ACTION>(game);
    }

    public AlphaBetaDepth(Game<STATE, ACTION> game) {
        this.game = game;
    }


    public double maxValue(STATE state, boolean player, int depth, double alpha, double beta) {
        // calcule une valeur d'utilité pour un noeud max
        assert (player);
        expandedNodes++;
        if (game.isTerminal(state) || depth <= 0)
            return game.getUtility(state, player);
        double value = Double.NEGATIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.max(value,
                    minValue(game.getResult(state, action), !player, depth-1, alpha, beta));
            if (value > beta) return value;
            alpha = Math.max(alpha,value);
        }
        return value;
    }

    public double minValue(STATE state, boolean player, int depth, double alpha, double beta) {
        // calcule une valeur d'utilité pour un noeud min
        assert (!(player));
        expandedNodes++;
        if (game.isTerminal(state) || depth <= 0) {
            return game.getUtility(state, player);
        }
        double value = Double.POSITIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.min(value,
                    maxValue(game.getResult(state, action), !player, depth-1, alpha, beta));
            if (value < alpha) return value;
            beta = Math.min(beta, value);
        }
        return value;
    }


    public ACTION makeDecision(STATE state) {
        expandedNodes = 0;
        ACTION result = null ;
        double resultValue = Double.NEGATIVE_INFINITY;
        boolean p = true;
        for (ACTION action : game.getActions(state)) {
            double value = minValue(game.getResult(state, action), !p, game.getDepth(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
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
