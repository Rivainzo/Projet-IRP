public class Triple<GAUCHE, MILIEU, DROITE> {
    GAUCHE _g;
    MILIEU _m;
    DROITE _d;

    public Triple(GAUCHE g, MILIEU m, DROITE d) {
        _g = g;
        _m = m;
        _d = d;
    }

    public GAUCHE get_g() {return _g;}
    public MILIEU get_m() {return _m;}
    public DROITE get_d() {return _d;}

    public void set_g(GAUCHE g) {_g = g;}
    public void set_m(MILIEU m) {_m = m;}
    public void set_d(DROITE d) {_d = d;}
}
