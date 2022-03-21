package jeux.nim;

import jeux.Game;

import java.util.*;

class Nim implements Game<Integer, Integer> {
    private int _init_state;
    private int _depth;

    public Nim(int state, int depth) {
        _init_state = state;
        _depth   = depth;
    }

    @Override
    public Integer getInitialState() {
        return _init_state;
    }

    @Override
    public List<Integer> getActions(Integer state) {
        List<Integer> myArrayList = new ArrayList<Integer>();
        if (state == 1){
            myArrayList.add(1);
        }
        else if (state == 2){
            myArrayList.add(1);
            myArrayList.add(2);
        }
        else {
            myArrayList.add(1);
            myArrayList.add(2);
            myArrayList.add(3);
        }
        return myArrayList;
    }

    @Override
    public Integer getResult(Integer state, Integer action) {
        return state-action;
    }

    @Override
    public boolean isTerminal(Integer state) {
        return state == 0;
    }

    @Override
    public double getUtility(Integer state, boolean player) {
        if (isTerminal(state)){
            return (player)?1:-1;
        }
        else {
            return 0;
        }
    }

    @Override
    public int getDepth() {
        return _depth;
    }
}

