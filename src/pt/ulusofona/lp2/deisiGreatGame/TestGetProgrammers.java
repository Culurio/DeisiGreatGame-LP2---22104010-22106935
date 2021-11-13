package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Test;

public class TestGetProgrammers {
    @Test
    public void TestProgrammers1(){
        String[][]playersInfo = new String[2][4];
        playersInfo[0][0]= "12";
        playersInfo[0][1]= "Joao";
        playersInfo[0][2]= "java";
        playersInfo[0][3]= "Blue";
        playersInfo[1][0]= "1";
        playersInfo[1][1]= "gongas";
        playersInfo[1][2]= "python;java";
        playersInfo[1][3]= "Brown";
        GameManager teste = new GameManager();

        System.out.println(teste.createInitialBoard(playersInfo,20));
        System.out.println(teste.getProgrammers(1));
        teste.gameIsOver();
        //System.out.println(teste.getCurrentPlayerID());
    }

}
