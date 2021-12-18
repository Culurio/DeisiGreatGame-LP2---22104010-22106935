package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

public class TestProgrammerAbyss {
    @Test
    public void testProgrammerAbyss(){
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix3());

        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();
        System.out.println(gameManager.getAbysses().get(0).name);
        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();

        Assert.assertEquals("Duplicated Code\n" +
                "Duplicated Code","Duplicated Code\n" +
                "Duplicated Code");
        Assert.assertEquals(1,gameManager.getPlayers().get(0).getPosition());
    }

    @Test
    public void testProgrammerAbyss2(){
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix4());

        gameManager.moveCurrentPlayer(6);
        gameManager.reactToAbyssOrTool();
        System.out.println(gameManager.getAbysses().get(0).name);
        gameManager.moveCurrentPlayer(6);
        gameManager.reactToAbyssOrTool();
        Assert.assertEquals(4,gameManager.getPlayers().get(0).getPosition());
        Assert.assertEquals(4,gameManager.getPlayers().get(1).getPosition());
        gameManager.moveCurrentPlayer(3);
        gameManager.reactToAbyssOrTool();
        Assert.assertEquals(7,gameManager.getPlayers().get(0).getPosition());
    }

    @Test
    public void testProgrammerAbyss3(){
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix4());

        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();
        System.out.println(gameManager.getAbysses().get(1).name);
        Assert.assertEquals(2,gameManager.getPlayers().get(0).getPosition());

    }
}
