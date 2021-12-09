package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;
import java.util.List;

abstract class Position {
    int id = 1;
    String name;
    List<Programmer> players = new ArrayList<>();

    abstract void effect();

    abstract String getName();

    abstract int getId();

    abstract void avancar(int moves);

    abstract void recuar(int moves);

    abstract void voltarParaAPrimeiraCasa();
}
