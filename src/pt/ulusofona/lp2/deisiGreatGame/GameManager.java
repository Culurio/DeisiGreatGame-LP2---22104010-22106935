package pt.ulusofona.lp2.deisiGreatGame;

public class GameManager {

    String[][] playerInfo;
    int boardSize;
    int position;
    Programmer jogador;

    GameManager(){

    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize){
        playerInfo[0][0] = Integer.toString(jogador.id);
        return true;
    }


}

