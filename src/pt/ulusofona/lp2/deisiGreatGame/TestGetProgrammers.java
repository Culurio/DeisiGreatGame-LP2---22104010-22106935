package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestGetProgrammers {
    @Test
    public void TestCreateInitialBoard1() {
        String[][] playersInfo = new String[2][4];
        GameManager gameManager = new GameManager();
        playersInfo[0][0] = "12";
        playersInfo[0][1] = "Joao";
        playersInfo[0][2] = "java";
        playersInfo[0][3] = "Blue";
        playersInfo[1][0] = "1";
        playersInfo[1][1] = "gongas";
        playersInfo[1][2] = "python;java";
        playersInfo[1][3] = "Brown";
        Assert.assertTrue(gameManager.createInitialBoard(playersInfo, 79));
    }

    @Test
    public void TestCreateInitialBoard2() {
        String[][] playersInfo = new String[2][4];
        GameManager gameManager = new GameManager();
        ArrayList<Programmer> playersTest = new ArrayList<>();
        ArrayList<String> languagesTest = new ArrayList<>();
        ArrayList<String> languagesTest2 = new ArrayList<>();
        languagesTest2.add("java");
        languagesTest2.add("python");
        languagesTest.add("java");
        playersTest.add(new Programmer("Joao", 12, languagesTest, ProgrammerColor.BLUE));
        playersTest.add(new Programmer("gongas", 1, languagesTest2, ProgrammerColor.BROWN));
        playersInfo[0][0] = "12";
        playersInfo[0][1] = "Joao";
        playersInfo[0][2] = "java";
        playersInfo[0][3] = "Blue";
        playersInfo[1][0] = "1";
        playersInfo[1][1] = "gongas";
        playersInfo[1][2] = "python;java";
        playersInfo[1][3] = "Brown";
        gameManager.createInitialBoard(playersInfo, 79);
        Assert.assertEquals(playersTest.toString(), gameManager.jogadores.toString());
    }

    @Test
    public void TestCreateInitialBoard3() {
        String[][] playersInfo = new String[2][4];
        GameManager gameManager = new GameManager();
        playersInfo[0][0] = "12";
        playersInfo[0][1] = "Joao";
        playersInfo[0][2] = "java";
        playersInfo[0][3] = "Blue";
        playersInfo[1][0] = "12";
        playersInfo[1][1] = "gongas";
        playersInfo[1][2] = "python;java";
        playersInfo[1][3] = "Brown";
        Assert.assertFalse(gameManager.createInitialBoard(playersInfo, 79));
    }

}
