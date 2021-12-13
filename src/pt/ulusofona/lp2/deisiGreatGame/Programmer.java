package pt.ulusofona.lp2.deisiGreatGame;

import java.util.*;

public class Programmer {
    String name;
    ArrayList<String> favoriteLanguages;
    private final int id;
    private int position;
    private int dice;
    private boolean status; //Derrotado = false ; em jogo = true
    private boolean stuck; //preso = true ; em jogo false;
    private final ProgrammerColor avatarColor;
    private ArrayList<Integer> percursoDeCasas = new ArrayList<>();
    private HashMap<String,Effect> effects = new HashMap<>();

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

    public void setDice(int dice) {
        this.dice = dice;
    }

    public int getDice() {
        return dice;
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

    public void setInitialPosition(){
        position = 1;
    }

    public void move(int moves){
        position += moves;
    }

    public boolean isNameValid() {
        return name != null && !name.isEmpty();
    }

    public void lose() {
        status = false;
    }

    public void stuck() {
        status = true;
    }

    public void save() {
        status = false;
    }

    public boolean isStuck(){
        return stuck;
    }

    public int retornarUltimaCasa(){
        return percursoDeCasas.get(percursoDeCasas.size() - 1);
    }

    public void addEffect(Effect effect){
        effects.put(effect.getName(),effect);
        effect.effect();
    }

    public HashMap get

    public String programmerTools(){
        StringBuilder toolsString = new StringBuilder();
        if(effects.size() == 0){
            return name +" : No tools";
        }
        for (Effect effect : effects){
            toolsString.append(effect.getName()).append(";");
        }
        toolsString.deleteCharAt(toolsString.toString().length()-1);
        return name + " : " + toolsString.toString();
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
