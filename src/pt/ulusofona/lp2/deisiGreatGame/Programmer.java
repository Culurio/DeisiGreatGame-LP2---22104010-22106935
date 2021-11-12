package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class Programmer {
    /*Pode ser util mais para a frente
        static int[] usedId = {-1,-1,-1,-1};
        static ProgrammerColor[] usedColors = {ProgrammerColor.NONE,ProgrammerColor.NONE,ProgrammerColor.NONE,ProgrammerColor.NONE};
    */
    String nome;
    ArrayList<String> linguagensFavoritas;
    int id;
    int position;
    boolean status; // Derrotado = false  em jogo = true
    ProgrammerColor corDoAvatar;

    Programmer(String nome, int id, ArrayList<String> linguagensFavoritas, ProgrammerColor corDoAvatar) {
        this.nome = nome;
        this.id = id;
        this.linguagensFavoritas = linguagensFavoritas;
        this.id = (int) (Math.random() * (100 + 1) + 0);
        this.corDoAvatar = corDoAvatar;
        position = 1;

    }

    Programmer(String nome) {
        this.nome = nome;
    }

    public void move(int posicoesParaAvancar) {
        position += posicoesParaAvancar;
    }

    public String getProgrammerFavLan() {
        return ((linguagensFavoritas.toString()).replace(',', ';')).replace("[", "").replace("]", "");
    }

    public String getStatus() {
        if (status)
            return "Em Jogo";
        return "Derrotado";
    }

    public int getPosition() {
        return position;
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

    public boolean colorExists() {
        return corDoAvatar.toString().equals("Blue") || corDoAvatar.toString().equals("Green")
                || corDoAvatar.toString().equals("Purple") || corDoAvatar.toString().equals("Brown");
    }

    /*boolean isColorUsed(){
        for(ProgrammerColor color : usedColors){
            if(corDoAvatar == color){
                return true;
            }
        }
        return false;
    }*/

    public String getName() {
        return nome;
    }

    public int getId() {
        return id;
    }

    ProgrammerColor getColor() {
        return corDoAvatar;
    }


    @Override
    public String toString() {
        return getId() + " | " + getName() + " | " + getPosition() + " | " + getProgrammerFavLan()
                + " | " + getStatus() + "\n";
    }
}
