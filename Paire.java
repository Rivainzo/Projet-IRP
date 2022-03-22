//package jeux;

public class Paire<GAUCHE, DROITE> {
    GAUCHE _g;
    DROITE _d;

    public Paire(GAUCHE g, DROITE d) {
        _g = g;
        _d = d;
    }

    public GAUCHE get_g() {return _g;}
    public DROITE get_d() {return _d;}

    public void set_g(GAUCHE g) {_g = g;}
    public void set_d(DROITE d) {_d = d;}
}