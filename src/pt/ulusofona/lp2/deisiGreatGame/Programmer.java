package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;
import java.util.Comparator;

public class Programmer {
    /*Pode ser util mais para a frente
        static int[] usedId = {-1,-1,-1,-1};
        static ProgrammerColor[] usedColors = {ProgrammerColor.NONE,ProgrammerColor.NONE,ProgrammerColor.NONE,ProgrammerColor.NONE};
    */
    String nome;
    ArrayList<String> linguagensFavoritas;
    private int id;
    private int position;
    private boolean status; // Derrotado = false  em jogo = true
    private ProgrammerColor corDoAvatar;

    Programmer(String nome, int id, ArrayList<String> linguagensFavoritas, ProgrammerColor corDoAvatar) {
        this.nome = nome;
        this.id = id;
        this.linguagensFavoritas = linguagensFavoritas;
        this.corDoAvatar = corDoAvatar;
        status = true;
        position = 1;

    }

    public String getProgrammerFavLan() {
        return ((linguagensFavoritas.toString().replace(",",";")).replace("[", "").replace("]", ""));
    }

    public String getStatus() {
        if (status) {
            return "Em Jogo";
        }
        return "Derrotado";
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public ProgrammerColor getColor() {
        return corDoAvatar;
    }


    public boolean isNameValid() {
        return nome != null && !nome.isEmpty();
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

    public void move(int posicoesParaAvancar) {
        position += posicoesParaAvancar;
    }

    @Override
    public String toString() {
        return getId() + " | " + getName() + " | " + getPosition() + " | " + getProgrammerFavLan()
                + " | " + getStatus();
    }

    public static class PositionComparator implements Comparator<Programmer> {

        @Override
        public int compare(Programmer emp1, Programmer emp2) {
            return emp2.getPosition() - emp1.getPosition();
        }
    }
}
