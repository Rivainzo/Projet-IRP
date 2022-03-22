import java.util.*;

public class NimJeu {

    public static void main(String[] args) {
        Nim game = new Nim(20, 5);
        System.out.println("Nombre d'allumettes initial : " + 20 + "\n");
        Scanner scanner = new Scanner(System.in);
        int state;
        int choix_joueur = 0;
        String commencer;
        boolean premier_joueur = true;
        List<Integer> myArrayList = new ArrayList<Integer>();
        /*MinimaxSearch<Integer, Integer> minimaxSearch =
                MinimaxSearch.createFor(game);*/
        /*MinimaxDepthSearch<Integer, Integer> minimaxDepthSearch =
                MinimaxDepthSearch.createFor(game);*/
        /*AlphaBeta<Integer, Integer> alphaBeta =
                AlphaBeta.createFor(game);*/
        AlphaBetaDepth<Integer, Integer> alphaBetaDepth =
                AlphaBetaDepth.createFor(game);


        System.out.println("Voulez-vous jouer en premier ? (Oui / Non)");
        commencer = scanner.nextLine();
        while (!commencer.equals("Oui") && !commencer.equals("Non")) {
            System.out.println("Voulez-vous jouer en premier ? (Oui / Non)");
            commencer = scanner.nextLine();
        }
        premier_joueur = commencer.equals("Oui");

        state = game.getInitialState();
        System.out.println("\nNombre d'allumettes : " + state + "\n");
        int action = -1;

        if (!premier_joueur) {
            System.out.println("Ordinateur, que voulez-vous faire?");
            action = alphaBetaDepth.makeDecision(state);
            System.out.println("L'ordinateur retire " + action + " allumette(s)");
            state -= action;
            if (game.isTerminal(state)){
                System.out.println("Vous avez gagné contre l'ordinateur, félicitation!");
                return;
            }
            else {
                System.out.println("Nombre d'allumettes restantes : " + state + "\n");
            }
        }

        while (!game.isTerminal(state)){
            boolean choix_valide = false;
            while (!choix_valide){
                System.out.println("Combien d'allumettes voulez-vous enlever?");
                choix_joueur = scanner.nextInt();
                choix_valide = game.getActions(state).contains(choix_joueur);
                if (!choix_valide){
                    System.out.println("Nombre d'allumettes à retirer impossible\n");
                }
            }
            System.out.println("Vous retirez " + choix_joueur + " allumette(s)");
            state -= choix_joueur;
            if (game.isTerminal(state)){
                System.out.println("L'ordinateur a gagné la partie.");
                return;
            }
            else {
                System.out.println("Nombre d'allumettes restantes : " + state + "\n");
                System.out.println("Ordinateur, que voulez-vous faire?");
                action = alphaBetaDepth.makeDecision(state);
                System.out.println("L'ordinateur retire " + action + " allumette(s)");
                state -= action;
                if (game.isTerminal(state)){
                    System.out.println("Vous avez gagné contre l'ordinateur, félicitation!");
                    return;
                }
                else {
                    System.out.println("Nombre d'allumettes restantes : " + state + "\n");
                }
            }
        }
    }
}
