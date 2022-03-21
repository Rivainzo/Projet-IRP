package jeux.puissance4;

import jeux.Game;
import jeux.Paire;
import java.util.*;

public class Puissance4 implements Game<Paire<Integer[][], Boolean>, Paire<Integer, Integer>> {
    private Paire<Integer[][], Boolean> _init_state;
    private int _nb_lignes;
    private int _nb_colonnes;
    private int _depth;

    public Puissance4(int nb_lignes, int nb_colonnes, boolean premier_joueur, int depth) {
        Integer[][] grille = new Integer[nb_lignes][nb_colonnes];
        _nb_lignes = nb_lignes;
        _nb_colonnes = nb_colonnes;
        _depth = depth;
        for (int i = 0; i < get_nb_lignes(); i++) {
            for (int j = 0; j < get_nb_colonnes(); j++) {
                grille[i][j] = 0;
            }
        }
        _init_state = new Paire<Integer[][], Boolean>(grille, premier_joueur);
    }

    public int get_nb_lignes() {return _nb_lignes;}
    public int get_nb_colonnes() {return _nb_colonnes;}

    @Override
    public Paire<Integer[][], Boolean> getInitialState() {
        Integer[][] tab = new Integer[_init_state.get_g().length][];
        for (int i = 0; i < _init_state.get_g().length; i++) {
            //tab[i] = Arrays.copyOf(_init_state.get_g()[i], _init_state.get_g()[i].length);
            tab[i] = _init_state.get_g()[i].clone();
        }
        //Integer[][] tab = _init_state.get_g().clone();
        Paire<Integer[][], Boolean> res = new Paire<>(tab, _init_state.get_d());
        return res;
    }

    @Override
    public List<Paire<Integer, Integer>> getActions(Paire<Integer[][], Boolean> state) {
        List<Paire<Integer, Integer>> myArrayList = new ArrayList<Paire<Integer, Integer>>();

        /*for (int i = 0; i < get_nb_lignes(); i++) {
            for (int j = 0; j < get_nb_colonnes(); j++) {
                System.out.print(((state.get_g()[i][j] >= 0) ? " " : "") + state.get_g()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();*/

        for (int j = 0; j < get_nb_colonnes(); j++) {
            // On regarde s'il est possible d'ajouter un pion dans la colonne j
            if (state.get_g()[get_nb_lignes()-1][j] == 0) {
                int i = 5;
                while (i > 0 && state.get_g()[i-1][j] == 0) {
                    i -= 1;
                }
                Paire<Integer, Integer> action = new Paire<Integer, Integer>(i,j);
                //System.out.println("TEST GetActions 2 Action (" + i + ", " + j +")");
                myArrayList.add(action);
            }
        }

        return myArrayList;
    }

    @Override
    public Paire<Integer[][], Boolean> getResult(Paire<Integer[][], Boolean> state, Paire<Integer, Integer> action) {
        Integer[][] nouvelle_grille = new Integer[state.get_g().length][];
        for (int i = 0; i < state.get_g().length; i++) {
            //nouvelle_grille[i] = Arrays.copyOf(state.get_g()[i], state.get_g()[i].length);
            nouvelle_grille[i] = state.get_g()[i].clone();

        }
        //Integer[][] nouvelle_grille = state.get_g().clone();
        int valeur = (state.get_d()) ? 1 : -1;
        nouvelle_grille[action.get_g()][action.get_d()] = valeur;
        Paire<Integer[][], Boolean> res = new Paire<>(nouvelle_grille, !state.get_d());
        /*state.set_g(nouvelle_grille);
        state.set_d(!state.get_d());*/
        return res;
    }

    public boolean victoire(Integer[][] grille, boolean joueur, int i, int j) {
        int tmp = (joueur) ? 1:-1;
        if (grille[i][j] == tmp)
        {
            int nb_horizontal = 1, nb_vertical = 1, nb_diag_ascendante = 1, nb_diag_descendate = 1;
            /* Alignement horizontal */
            int curseur_j = 1;
            /* Il suffit de regarder s'il y a trois pions à droite du point */
            while (j + curseur_j < get_nb_colonnes() && grille[i][j + curseur_j] == grille[i][j]) { // On regarde les pions à droite
                nb_horizontal++;
                j++;
            }
            if (nb_horizontal >= 4) return true;

            /* Alignement verticale */
            int curseur_i = 1;
            /* Il suffit de regarder s'il y a trois pions au dessus du point */
            while (i + curseur_i < get_nb_lignes() && grille[i + curseur_i][j] == grille[i][j]) { // On regarde les pions au dessus
                nb_vertical++;
                i++;
            }
            if (nb_vertical >= 4) return true;

            /* Alignement diagonale ascendante */
            int curseur_d = 1;
            /* Il suffit de regarder s'il y a trois pions au dessus et à droite du point */
            while (i + curseur_d < get_nb_lignes() && j + curseur_d < get_nb_colonnes() && grille[i + curseur_d][j + curseur_d] == grille[i][j]) { // On regarde les pions au dessus
                nb_diag_ascendante++;
                curseur_d++;
            }
            if (nb_diag_ascendante >= 4) return true;

            /* Alignement diagonale descendante */
            curseur_d = 1;
            /* Il suffit de regarder s'il y a trois pions au dessus et à gauche du point */
            while (i + curseur_d < get_nb_lignes() && j - curseur_d >= 0 && grille[i + curseur_d][j - curseur_d] == grille[i][j]) { // On regarde les pions au dessus
                nb_diag_ascendante++;
                curseur_d++;
            }
            if (nb_diag_ascendante >= 4) return true;
        }

        return false;
    }

    @Override
    public boolean isTerminal(Paire<Integer[][], Boolean> state) {
        boolean remplie = true;
        for (int i = 0; i < get_nb_lignes(); i++) {
            for (int j = 0; j < get_nb_colonnes(); j++) {
                if (victoire(state.get_g(), true, i,j) || victoire(state.get_g(), false, i,j)) return true; // Un des deux joueurs gagne
                if (state.get_g()[i][j] == 0) remplie = false;
            }
        }
        return remplie;
    }

    private double Utility_aux_horizontale(int i, int j) {
        return Math.max(0, Math.min(get_nb_colonnes()-1,j+3) - Math.max(0,j-3) - 2);
    }

    private double Utility_aux_verticale(int i, int j) {
        return Math.max(0, Math.min(get_nb_lignes()-1,i+3) - Math.max(0,i-3) - 2);
    }

    private double Utility_aux_diag_ascendante(int i, int j) {
        int dist1 = Math.min(i,j); // On regarde lequel des bords gauche et bas est le plus proche
        int dist2 = Math.min(get_nb_lignes()-1-i, get_nb_colonnes()-1-j); // On regarde lequel des bords droit et haut est le plus proche
        return Math.max(0, Math.min(3,dist1) + Math.min(3,dist2) - 2);
    }

    private double Utility_aux_diag_descendante(int i, int j) {
        int dist1 = Math.min(get_nb_lignes()-1-i,j); // On regarde lequel des bords gauche et haut est le plus proche
        int dist2 = Math.min(i, get_nb_colonnes()-1-j); // On regarde lequel des bords droit et bas est le plus proche
        return Math.max(0, Math.min(3,dist1) + Math.min(3,dist2) - 2);
    }

    private double Utility_aux(int i, int j) { // Donne la valeur d'un endroit de la grille en fonction du nombre de possibilité de victoire qu'il donne
        /*System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS  " + (Utility_aux_horizontale(i,j) + Utility_aux_verticale(i,j)
                + Utility_aux_diag_ascendante(i,j) + Utility_aux_diag_descendante(i,j)));*/
        return Utility_aux_horizontale(i,j) + Utility_aux_verticale(i,j)
                + Utility_aux_diag_ascendante(i,j) + Utility_aux_diag_descendante(i,j);
    }

    @Override
    public double getUtility(Paire<Integer[][], Boolean> state, boolean player) {
        //System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS " + isTerminal(state));
        if (isTerminal(state)){
            for (int i = 0; i < get_nb_lignes(); i++) {
                for (int j = 0; j < get_nb_colonnes(); j++) {
                    if (victoire(state.get_g(), player, i,j)) return Double.POSITIVE_INFINITY; // Le joueur gagne
                    if (victoire(state.get_g(), !player, i,j)) return Double.NEGATIVE_INFINITY; // L'adversaire gagne
                    //if (victoire(state.get_g(), !player, i,j)) return -100; // L'adversaire gagne
                }
            }
            return 0; // Cas d'égalité
        }
        else {

            /*System.out.println("----------------------------------------");
            for (int i = 0; i < get_nb_lignes(); i++) {
                for (int j = 0; j < get_nb_colonnes(); j++) {
                    System.out.print(((state.get_g()[i][j] >= 0) ? " " : "") + state.get_g()[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();*/

            double utilite = 0;
            for (int i = 0; i < get_nb_lignes(); i++) {
                for (int j = 0; j < get_nb_colonnes(); j++) {
                    utilite += state.get_g()[i][j] * Utility_aux(i,j);
                }
            }

            /*System.out.println("Vérification heuristique");
            for (int i = 0; i < get_nb_lignes(); i++) {
                for (int j = 0; j < get_nb_colonnes(); j++) {
                    System.out.print(Utility_aux(i,j) + " ");
                }
                System.out.println();
            }*/

            //System.out.println("TEST GetUtility " + utilite);
            return -utilite;
        }
    }

    @Override
    public int getDepth() {
        return _depth;
    }

    public void AffichageState(Paire<Integer[][], Boolean> state) {
        for (int i = get_nb_lignes()-1; i >= 0; i-=1) {
            System.out.println("+---".repeat(get_nb_colonnes()) + "+");
            //System.out.println("|   ".repeat(get_nb_colonnes()) + "|");
            for (int j = 0; j < get_nb_colonnes(); j++) {
                String joueur = (state.get_g()[i][j] > 0) ? "x" : "o";
                System.out.print("| " + ((state.get_g()[i][j] == 0) ? " " : joueur) + " ");
            }
            System.out.println("|");
            //System.out.println("|   ".repeat(get_nb_colonnes()) + "|");
        }
        System.out.println("+---".repeat(get_nb_colonnes()) + "+\n");
        System.out.println("Au tour " + ((state.get_d()) ? "du joueur" : "de l'ordinateur") + ".");
    }
}
