package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TestProgrammersToolsInfo {

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
        TestCreateMatrix m1 = new TestCreateMatrix();
        ArrayList<String> lan = new ArrayList<>();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 79,m1.createEffectsMatrix());
        System.out.println(gameManager.getProgrammersInfo());
        Assert.assertEquals(false, false);
    }

    @Test
    public void testProgrammerTools3(){
        TestCreateMatrix m1 = new TestCreateMatrix();
        ArrayList<String> lan = new ArrayList<>();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix());
        Tool tool = gameManager.tools.get(0);
        gameManager.players.get(0).addTool(tool);
        Assert.assertEquals("Joao : Tratamento de Excepções | gongas : No tools", gameManager.getProgrammersInfo());
    }

    @Test
    public void testProgrammerTools4(){
        TestCreateMatrix m1 = new TestCreateMatrix();
        ArrayList<String> lan = new ArrayList<>();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix());
        gameManager.moveCurrentPlayer(6);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(6);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();
        Assert.assertEquals("Joao : Tratamento de Excepções | gongas : No tools", gameManager.getProgrammersInfo());
    }

    @Test
    public void testProgrammerTools5(){
        TestCreateMatrix m1 = new TestCreateMatrix();
        ArrayList<String> lan = new ArrayList<>();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix2());
        gameManager.moveCurrentPlayer(6);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(6);
        gameManager.reactToAbyssOrTool();
        System.out.println(gameManager.players.get(gameManager.currentPlayer).toString());
        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(1);
        System.out.println(gameManager.players.get(gameManager.currentPlayer).toString());
        gameManager.reactToAbyssOrTool();
        System.out.println(gameManager.players.get(gameManager.currentPlayer).toString());
        Assert.assertEquals("Joao","");
    }
}
