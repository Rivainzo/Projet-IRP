import java.util.*;

public class Puissance4Jeu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String commencer;
        boolean premier_joueur;
        System.out.println("Voulez-vous jouer en premier ? (Oui / Non)");
        commencer = scanner.nextLine();
        while (!commencer.equals("Oui") && !commencer.equals("Non")) {
            System.out.println("Voulez-vous jouer en premier ? (Oui / Non)");
            commencer = scanner.nextLine();
        }
        premier_joueur = commencer.equals("Oui");

        Puissance4 game = new Puissance4(6, 7, premier_joueur, 5);

        Paire<Integer[][], Boolean> state;
        Paire<Integer, Integer> choix_joueur = new Paire<Integer, Integer>(-1,-1);

        List<Integer> myArrayList = new ArrayList<Integer>();
        AlphaBetaDepth<Paire<Integer[][], Boolean>, Paire<Integer, Integer>> alphaBetaDepth =
                AlphaBetaDepth.createFor(game);


        state = game.getInitialState();

        System.out.println("Grille initiale :\n");
        game.AffichageState(state);

        Paire<Integer, Integer> action = new Paire<Integer, Integer>(-1,-1);

        if (!premier_joueur) {
            System.out.println("Ordinateur, que voulez-vous faire?");
            action = alphaBetaDepth.makeDecision(state);
            System.out.println("L'ordinateur place un pion en (" + action.get_g() + "," + action.get_d() + ").");
            state.get_g()[action.get_g()][action.get_d()] = -1;
            state.set_d(!state.get_d());
            if (game.isTerminal(state)){
                game.AffichageState(state);
                for (int i = 0; i < game.get_nb_lignes(); i++) {
                    for (int j = 0; j < game.get_nb_colonnes(); j++) {
                        if (game.victoire(state.get_g(), false, i, j)) {
                            System.out.println("L'ordinateur a gagné la partie.");
                            return;
                        }
                    }
                }
                System.out.println("Egalité, aucun gagnant dans cette partie.");
                return;
            }
            else {
                System.out.println("Grille :\n");
                game.AffichageState(state);
            }
        }

        while (!game.isTerminal(state)){
            boolean choix_valide = false;
            while (!choix_valide){
                System.out.println("Joueur, dans quelle colonne voulez-vous ajouter un pion?");
                scanner = new Scanner(System.in);
                int choix_joueur_colonne = scanner.nextInt();

                choix_valide = false;

                for (Paire<Integer, Integer> x : game.getActions(state)) {
                    if (x.get_d() == choix_joueur_colonne) {
                        choix_joueur = new Paire<>(x.get_g(), choix_joueur_colonne);
                        choix_valide = true;
                        break;
                    }
                }


                if (!choix_valide){
                    System.out.println("Impossible de placer le pion à cet endroit.\n");
                }
            }
            System.out.println("Vous placez un pion en (" + choix_joueur.get_g() + "," + choix_joueur.get_d() + ").");
            state.get_g()[choix_joueur.get_g()][choix_joueur.get_d()] = 1;
            state.set_d(!state.get_d());
            if (game.isTerminal(state)){
                game.AffichageState(state);
                for (int i = 0; i < game.get_nb_lignes(); i++) {
                    for (int j = 0; j < game.get_nb_colonnes(); j++) {
                        if (game.victoire(state.get_g(), true, i, j)) {
                            System.out.println("Vous a gagné la partie, félicitation!");
                            return;
                        }
                    }
                }
                System.out.println("Egalité, aucun gagnant dans cette partie C FO.\n");
                return;
            }
            else {
                System.out.println("Grille :\n");
                game.AffichageState(state);
                System.out.println("Ordinateur, que voulez-vous faire?");
                action = alphaBetaDepth.makeDecision(state);
                System.out.println("L'ordinateur place un pion en (" + action.get_g() + "," + action.get_d() + ").");
                state.get_g()[action.get_g()][action.get_d()] = -1;
                state.set_d(!state.get_d());
                if (game.isTerminal(state)){
                    game.AffichageState(state);
                    for (int i = 0; i < game.get_nb_lignes(); i++) {
                        for (int j = 0; j < game.get_nb_colonnes(); j++) {
                            if (game.victoire(state.get_g(), false, i, j)) {
                                System.out.println("L'ordinateur a gagné la partie.");
                                return;
                            }
                        }
                    }
                    System.out.println("Egalité, aucun gagnant dans cette partie.\n");
                    return;
                }
                else {
                    System.out.println("Grille :\n");
                    game.AffichageState(state);
                }
            }
        }
    }

}