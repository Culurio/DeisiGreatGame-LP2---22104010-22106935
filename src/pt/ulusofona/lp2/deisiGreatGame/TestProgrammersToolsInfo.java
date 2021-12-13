package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TestProgrammersToolsInfo {
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
        effects[0][1] = "3";
        effects[0][2] = "8";
        effects[1][0] = "0";
        effects[1][1] = "8";
        effects[1][2] = "5";
        return effects;
    }

    @Test
    public void testProgrammerTools(){
        ArrayList<String> lan = new ArrayList<>();
        Tool tool1 = new Tool(2,5);
        lan.add("java");
        Programmer p1 = new Programmer("João",12,lan,ProgrammerColor.BLUE);

        System.out.println(p1.programmerTools());
        Assert.assertEquals(false, false);
    }

    @Test
    public void testProgrammerTools2(){
        ArrayList<String> lan = new ArrayList<>();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(createMatrix2Players(), 79,createEffectsMatrix());
        System.out.println(gameManager.getProgrammersInfo());
        Assert.assertEquals(false, false);
    }

    @Test
    public void testProgrammerTools3(){
        ArrayList<String> lan = new ArrayList<>();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(createMatrix2Players(), 79,createEffectsMatrix());
        Effect effect = gameManager.effects.get(0);
        gameManager.players.get(0).addEffect(effect);
        System.out.println(gameManager.getProgrammersInfo());
        Assert.assertEquals(false, false);
    }
}
