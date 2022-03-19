package jeux.nim;

import jeux.MinimaxSearch;

import java.util.*;

public class NimTestDeux {

    public static void main(String[] args) {
        Nim game = new Nim(3, 10);
        Scanner scanner = new Scanner(System.in);
        int state;
        int choix_joueur = 0;
        List<Integer> myArrayList = new ArrayList<Integer>();
        MinimaxSearch<Integer, Integer> minimaxSearch =
                MinimaxSearch.createFor(game);

        state = game.getInitialState();
        int action = -1;
        while (game.isTerminal(state)){
            boolean choix_valide = false;
            while (!choix_valide){
                System.out.println("Maximilien, combien d'allumettes veux-tu enlever maxi bg ?");
                choix_joueur = scanner.nextInt();
                choix_valide = game.getActions(state).contains(choix_joueur);
                if (!choix_valide){
                    System.out.println("Nombre d'allumettes à retirer impossible");
                }
            }
            state -= choix_joueur;
            if (game.isTerminal(state)){
                System.out.println("Le joueur humain a perdu (gros naze)");
                return;
            }
            else {
                System.out.println("Machine player, what is your action?");
                action = minimaxSearch.makeDecision(state);
                System.out.println("Chosen action is " + action);
                state -= action;
            }


        }
        //System.out.println("Metrics for Minimax : " + minimaxSearch.getMetrics());
    }
}
