package jeux.nim;

import jeux.MinimaxSearch;

import java.util.*;

public class NimTestDeux {

    public static void main(String[] args) {
        Nim game = new Nim(5, 10);
        System.out.println("Nombre d'allumettes initial : " + 20 + "\n");
        Scanner scanner = new Scanner(System.in);
        int state;
        int choix_joueur = 0;
        String commencer;
        boolean premier_joueur = true;
        List<Integer> myArrayList = new ArrayList<Integer>();
        MinimaxSearch<Integer, Integer> minimaxSearch =
                MinimaxSearch.createFor(game);

        System.out.println("Voulez-vous commencer la partie ? (Oui / Non)");
        commencer = scanner.nextLine();
        while (!commencer.equals("Oui") && !commencer.equals("Non")) {
            System.out.println("Voulez-vous commencer la partie ? (Oui / Non)");
            commencer = scanner.nextLine();
        }
        premier_joueur = commencer.equals("Oui");

        state = game.getInitialState();
        System.out.println("\nNombre d'allumettes : " + state + "\n");
        int action = -1;

        if (!premier_joueur) {
            System.out.println("Machine player, what is your action?");
            action = minimaxSearch.makeDecision(state);
            System.out.println("Chosen action is " + action);
            state -= action;
            if (game.isTerminal(state)){
                System.out.println("Tu as gagné contre l'ordinateur ! Tu mérites ton titre de maxi bg");
                return;
            }
            else {
                System.out.println("Nombre d'allumettes restantes : " + state + "\n");
            }
        }

        while (!game.isTerminal(state)){
            boolean choix_valide = false;
            while (!choix_valide){
                System.out.println("Maximilien, combien d'allumettes veux-tu enlever maxi bg ?");
                choix_joueur = scanner.nextInt();
                choix_valide = game.getActions(state).contains(choix_joueur);
                if (!choix_valide){
                    System.out.println("Nombre d'allumettes à retirer impossible\n");
                }
            }
            state -= choix_joueur;
            if (game.isTerminal(state)){
                System.out.println("Le joueur humain a perdu (gros naze)");
                return;
            }
            else {
                System.out.println("Nombre d'allumettes restantes : " + state + "\n");
                System.out.println("Machine player, what is your action?");
                action = minimaxSearch.makeDecision(state);
                System.out.println("Chosen action is " + action);
                state -= action;
                if (game.isTerminal(state)){
                    System.out.println("Tu as gagné contre l'ordinateur ! Tu mérites ton titre de maxi bg");
                    return;
                }
                else {
                    System.out.println("Nombre d'allumettes restantes : " + state + "\n");
                }
            }
        }
    }
}
