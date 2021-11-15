package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestGetProgrammers {
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

    //guardar os tempos para melhorar no futuro
    @Test
    public void TestCreateInitialBoard1() {
        GameManager gameManager = new GameManager();
        long startTime = System.currentTimeMillis();
        Assert.assertTrue(gameManager.createInitialBoard(createMatrix2Players(), 79));
        long elapsedTime =System.currentTimeMillis() - startTime;
        System.out.println(elapsedTime);
    }

    @Test
    public void TestCreateInitialBoard2() {
        GameManager gameManager = new GameManager();
        ArrayList<Programmer> playersTest = new ArrayList<>();
        ArrayList<String> languagesTest = new ArrayList<>();
        ArrayList<String> languagesTest2 = new ArrayList<>();
        languagesTest2.add("java");
        languagesTest2.add("python");
        languagesTest.add("java");
        playersTest.add(new Programmer("Joao", 12, languagesTest, ProgrammerColor.BLUE));
        playersTest.add(new Programmer("gongas", 1, languagesTest2, ProgrammerColor.BROWN));
        gameManager.createInitialBoard(createMatrix2Players(), 79);
        Assert.assertEquals(playersTest.toString(), gameManager.players.toString());
    }

    @Test
    public void TestCreateInvalidInitialBoard() {
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

    @Test
    public void TestCreateInvalidInitialBoard2() {
        String[][] playersInfo = new String[1][4];
        GameManager gameManager = new GameManager();
        playersInfo[0][0] = "12";
        playersInfo[0][1] = "Joao";
        playersInfo[0][2] = "java";
        playersInfo[0][3] = "Blue";
        Assert.assertFalse(gameManager.createInitialBoard(playersInfo, 79));
    }

}
