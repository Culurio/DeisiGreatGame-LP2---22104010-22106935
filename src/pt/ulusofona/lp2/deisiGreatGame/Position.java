package pt.ulusofona.lp2.deisiGreatGame;

public class Position {
    private int position;
    private String abyss = "";

    public Position(int position) {
        this.position = position;
    }

    public Position(int position, String abyss) {
        this.position = position;
        this.abyss = abyss;
    }

    public int getPosition() {
        return position;
    }

    public String getAbyss() {
        return abyss;
    }
}
