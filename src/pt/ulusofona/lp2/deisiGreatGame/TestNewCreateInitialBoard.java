package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestNewCreateInitialBoard {
    public String [][]createMatrix2Players(){
        String[][] playersInfo = new String[2][4];
        playersInfo[0][0] = "12";
        playersInfo[0][1] = "Joao";
        playersInfo[0][2] = "java";
        playersInfo[0][3] = "Blue";
        playersInfo[1][0] = "1";
        playersInfo[1][1] = "gongas";
        playersInfo[1][2] = "python;java";
        playersInfo[1][3] = "Brown";
        return playersInfo;
    }

    public String [][]createEffectsMatrix(){
        String[][] effects = new String[2][4];
        effects[0][0] = "1";
        effects[0][1] = "-3";
        effects[0][2] = "8";
        effects[1][0] = "0";
        effects[1][1] = "8";
        effects[1][2] = "5";
        return effects;
    }

    public String [][]createMatrix4Players(){
        String[][] playersInfo = new String[4][4];
        playersInfo[0][0] = "12";
        playersInfo[0][1] = "Joao";
        playersInfo[0][2] = "java";
        playersInfo[0][3] = "Blue";
        playersInfo[1][0] = "1";
        playersInfo[1][1] = "gongas";
        playersInfo[1][2] = "python;java";
        playersInfo[1][3] = "Brown";
        playersInfo[2][0] = "28";
        playersInfo[2][1] = "Claudio";
        playersInfo[2][2] = "GoLang;Lua;Rust";
        playersInfo[2][3] = "Purple";
        playersInfo[3][0] = "69";
        playersInfo[3][1] = "Jovane";
        playersInfo[3][2] = "C;C++;C#";
        playersInfo[3][3] = "Green";
        return playersInfo;
    }

    @Test
    public void testFunction() {
        GameManager gameManager = new GameManager();
        boolean teste = gameManager.createInitialBoard(createMatrix2Players(), 79,createEffectsMatrix());
        Assert.assertEquals(false, teste);
    }
}
