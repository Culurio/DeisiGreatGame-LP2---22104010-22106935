package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;
import java.util.List;

abstract class Position {
    int id;
    String name;
    List<Programmer> players = new ArrayList<>();

    abstract void effect();
}
