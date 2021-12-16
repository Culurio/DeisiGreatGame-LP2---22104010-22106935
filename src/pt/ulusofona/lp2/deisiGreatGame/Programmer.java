package pt.ulusofona.lp2.deisiGreatGame;

import java.awt.event.PaintEvent;
import java.util.*;

public class Programmer {
    private String name;
    private final int id;
    private int position;
    private int dice;
    private boolean status; //Derrotado = false ; em jogo = true
    private boolean stuck; //preso = true ; em jogo false;
    private final ProgrammerColor avatarColor;
    private ArrayList<String> favoriteLanguages;
    private ArrayList<Integer> percursoDeCasas = new ArrayList<>();
    private HashMap<Integer, Tool> tools = new HashMap<>();

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

    boolean verifyTool(int id){
        for (Tool tool : tools.values()){
            if (tool.getId() == id){
                return true;
            }
        }

        return false;
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
        return position;
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
        stuck = true;
    }

    public void save() {
        stuck = false;
    }

    public boolean isStuck(){
        return stuck;
    }

    public int diferencaAteAUltimaCasa(){
        return position - percursoDeCasas.get(percursoDeCasas.size() - 1);
    }

    public int diferencaAtePenultimaCasa(){
        return position - percursoDeCasas.get(percursoDeCasas.size() - 2);
    }

    public void addTool(Tool tool){
        tools.put(tool.getId(),tool);
    }

    public String programmerTools(){
        StringBuilder toolsString = new StringBuilder();
        if(tools.size() == 0){
            return name +" : No tools";
        }
        for (Tool tool : tools.values()){
            toolsString.append(tool.getName()).append(";");
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
