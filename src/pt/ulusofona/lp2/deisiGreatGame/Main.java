package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> teste1 = new ArrayList<>();
        teste1.add("Java");
        teste1.add("C#");
        Programmer teste = new Programmer("Jorge",teste1,ProgrammerColor.BLUE);
        System.out.println(teste.getProgrammerId());
    }
}
