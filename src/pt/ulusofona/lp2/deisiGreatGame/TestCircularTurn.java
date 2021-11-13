package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

public class TestCircularTurn {
    /*
    O primeiro turno é do jogador que está na primeira linha da matriz
     */
    @Test
    public void TestCurrentId(){
        String[][]playersInfo = new String[2][4];
        GameManager gameManager = new GameManager();
        playersInfo[0][0]= "12";
        playersInfo[0][1]= "Joao";
        playersInfo[0][2]= "java";
        playersInfo[0][3]= "Blue";
        playersInfo[1][0]= "1";
        playersInfo[1][1]= "gongas";
        playersInfo[1][2]= "python;java";
        playersInfo[1][3]= "Brown";
        gameManager.createInitialBoard(playersInfo,79);

        Assert.assertEquals(12,gameManager.getCurrentPlayerID());
    }

    /*
    Vai jogar 1 turno e quando 1 jogardor joga o turno passa
     */
    @Test
    public void TestCurrentId2(){
        String[][]playersInfo = new String[2][4];
        GameManager gameManager = new GameManager();
        playersInfo[0][0]= "12";
        playersInfo[0][1]= "Joao";
        playersInfo[0][2]= "java";
        playersInfo[0][3]= "Blue";
        playersInfo[1][0]= "1";
        playersInfo[1][1]= "gongas";
        playersInfo[1][2]= "python;java";
        playersInfo[1][3]= "Brown";
        gameManager.createInitialBoard(playersInfo,79);
        gameManager.moveCurrentPlayer(2);
        Assert.assertEquals(1,gameManager.getCurrentPlayerID());
    }

    @Test
    public void TestCurrentId3(){
        String[][]playersInfo = new String[4][4];
        GameManager gameManager = new GameManager();
        playersInfo[0][0]= "12";
        playersInfo[0][1]= "Joao";
        playersInfo[0][2]= "java";
        playersInfo[0][3]= "Blue";
        playersInfo[1][0]= "1";
        playersInfo[1][1]= "gongas";
        playersInfo[1][2]= "python;java";
        playersInfo[1][3]= "Brown";
        playersInfo[2][0]= "28";
        playersInfo[2][1]= "Claudio";
        playersInfo[2][2]= "GoLang;Lua;Rust";
        playersInfo[2][3]= "Purple";
        playersInfo[3][0]= "69";
        playersInfo[3][1]= "Jovane";
        playersInfo[3][2]= "C;C++;C#";
        playersInfo[3][3]= "Green";
        gameManager.createInitialBoard(playersInfo,79);
        gameManager.moveCurrentPlayer(2);//ID 12 vai para ID 1
        gameManager.moveCurrentPlayer(5);//ID 1 vai para ID 28
        gameManager.moveCurrentPlayer(3);//ID 28 vai para ID 69
        Assert.assertEquals(69,gameManager.getCurrentPlayerID());
    }
}
