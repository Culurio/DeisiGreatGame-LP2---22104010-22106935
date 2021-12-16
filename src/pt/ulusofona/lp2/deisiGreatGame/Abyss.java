package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

abstract public class Abyss{

    protected int id;
    protected String name;
    protected int position;

    public Abyss(int id, int position) {
        this.id = id;
        this.position = position;
    }

    abstract String effect(Programmer programmer, List<Programmer> players);

    abstract String getPng();

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getId(){
        return id;
    }
}
