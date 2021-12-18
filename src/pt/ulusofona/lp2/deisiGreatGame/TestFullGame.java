package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

public class TestFullGame {
    @Test
    public void testProgrammerAbyss5(){
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players2(), 10,m1.createEffectsMatrix3());


        gameManager.moveCurrentPlayer(5);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(3);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(4);
        gameManager.reactToAbyssOrTool();


        System.out.println(gameManager.getPlayers().get(0).getPosition());
        System.out.println(gameManager.getPlayers().get(0).getName());
        System.out.println(gameManager.getPlayers().get(1).getPosition());

        Assert.assertEquals("[O GRANDE JOGO DO DEISI, , NR. DE TURNOS, 4, , VENCEDOR, Bongas, , RESTANTES, Aoao 4]"
                ,gameManager.getGameResults().toString());

    }
}
