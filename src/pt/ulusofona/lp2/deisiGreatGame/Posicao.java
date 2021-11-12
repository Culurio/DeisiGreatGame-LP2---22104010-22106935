package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class Posicao {
    ArrayList<Programmer> programmers;
    int boardSize;

    Posicao(ArrayList<Programmer> programmers, int boardSize){
        this.programmers = programmers;
        this.boardSize = boardSize;
    }
}
