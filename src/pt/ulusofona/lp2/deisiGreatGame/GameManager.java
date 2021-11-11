package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class GameManager {

    String[][] playerInfo;
    int boardSize;
    Programmer jogadorAtual;
    ArrayList<Programmer> jogadores;

    GameManager(int boardSize){
        playerInfo = new String[jogadores.size()][4];
        this.boardSize=boardSize;
        jogadores= new ArrayList<>();
    }

    GameManager(){

    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize){
        if(jogadores.size()<2 || boardSize<2*jogadores.size()){
            return false;
        }

        for (Programmer programmer : jogadores){
            if (!programmer.colorExists() || !programmer.isNameValid() ||
                    programmer.colorExists() || programmer.isIdUsed() || programmer.isColorUsed()){
                return false;
            }
        }

        for (int row = 0; row < jogadores.size(); row++) {
            for (int col = 0; col < playerInfo[row].length; col++) {
                switch (col){
                    case 0:
                        playerInfo[row][col] = jogadores.get(col).getProgrammerId()+"";
                        break;
                    case 1:
                        playerInfo[row][col] = jogadores.get(col).getProgrammerName();
                        break;
                    case 2:
                        playerInfo[row][col] = jogadores.get(col).getProgrammerFavLan();
                        break;
                    case 3:
                        playerInfo[row][col] = jogadores.get(col).getProgrammerColor().toString();
                }
            }
        }
        return true;
    }


}

