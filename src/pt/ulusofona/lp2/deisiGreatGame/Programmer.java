package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Programmer {
    String name;
    ArrayList<String> favoriteLanguages;
    private final int id;
    private int position;
    private boolean status; //Derrotado = false ; em jogo = true
    private final ProgrammerColor avatarColor;
    ArrayList<Integer> percursoDeCasas = new ArrayList<>();
    List<Tool> tools = new ArrayList<>();

    Programmer(String name, int id, ArrayList<String> favoriteLanguages, ProgrammerColor avatarColor) {
        this.name = name;
        this.id = id;
        this.favoriteLanguages = favoriteLanguages;
        this.avatarColor = avatarColor;
        status = true;
        position = 1;
    }

    public String getProgrammerFavLan() {
        return ((favoriteLanguages.toString().replace(",",";")).replace("[", "").replace("]", ""));
    }

    public ArrayList<String> getProgrammerFavLanList() {
        return favoriteLanguages;
    }

    public String getStatus() {
        if (status) {
            return "Em Jogo";
        }
        return "Derrotado";
    }

    public boolean getStatusBool() {
        return status;
    }

    public int getPosition() {
        return getId();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ProgrammerColor getColor() {
        return avatarColor;
    }

    public void move(int moves){
        position += moves;
    }

    public boolean isNameValid() {
        return name != null && !name.isEmpty();
    }

    public void loseStatus() {
        status = false;
    }

    int retornarUltimaCasa(){
        return percursoDeCasas.get(percursoDeCasas.size() - 1);
    }

    void blueScreen(){

    }

    @Override
    public String toString() {
        return getId() + " | " + getName() + " | " + getPosition() + " | " + getProgrammerFavLan()
                + " | " + getStatus();
    }

    public static class PositionComparator implements Comparator<Programmer> {

        @Override
        public int compare(Programmer prog1, Programmer prog2) {
            return prog2.getPosition() - prog1.getPosition();
        }
    }
}
