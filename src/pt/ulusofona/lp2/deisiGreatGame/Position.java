package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;
import java.util.List;

public class Position {
    int id = 1;
    List<Programmer> players = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void move(int position){
        id += position;
    }
}
