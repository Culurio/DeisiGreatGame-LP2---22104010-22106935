package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;
import java.util.Comparator;

public class    Programmer {
    /*Pode ser util mais para a frente
        static int[] usedId = {-1,-1,-1,-1};
        static ProgrammerColor[] usedColors = {ProgrammerColor.NONE,ProgrammerColor.NONE,ProgrammerColor.NONE,ProgrammerColor.NONE};
    */
    String name;
    ArrayList<String> favoriteLanguages;
    private final int id;
    private Position position;
    private boolean status; //Derrotado = false ; em jogo = true
    private final ProgrammerColor avatarColor;
    ArrayList<Integer> percursoDeCasas = new ArrayList<>();

    Programmer(String name, int id, ArrayList<String> favoriteLanguages, ProgrammerColor avatarColor) {
        this.name = name;
        this.id = id;
        this.favoriteLanguages = favoriteLanguages;
        this.avatarColor = avatarColor;
        status = true;

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


    public boolean isNameValid() {
        return name != null && !name.isEmpty();
    }

    /*boolean isIdUsed(){
        for (int num :usedId) {
            if (id == num){
                return true;
            }
        }
        return false;
    }*/

    /*boolean isColorUsed(){
        for(ProgrammerColor color : usedColors){
            if(corDoAvatar == color){
                return true;
            }
        }
        return false;
    }*/

    public void loseStatus() {
        status = false;
    }

    int retornarUltimaCasa(){
        return percursoDeCasas.get(percursoDeCasas.size() - 1);
    }

    void blueScreen(){

    }

    void recuarParaCasasAnteriores(int opcao){
        switch (opcao){
            case 0:
                position.recuar(percursoDeCasas.get(percursoDeCasas.size() - 1));
                break;
            case 1:
                position.recuar(percursoDeCasas.get(percursoDeCasas.size() - 2));
                break;
        }
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
