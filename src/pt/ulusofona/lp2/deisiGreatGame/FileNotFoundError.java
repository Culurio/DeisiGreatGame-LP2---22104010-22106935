package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

public class FileNotFoundError extends Abyss {
    FileNotFoundError(int id, int position) {
        super(id, position);
        name = "File Not Found";
    }

    @Override
    String effect(Programmer programmer, List<Programmer> programmers) {
        /*
                File Not Found Exception - O programador recua 3 casas.
        */

        if (programmer.verifyTool(3)){
            return "Estás Safo >:)";
        }
        programmer.move(-3);
        return "Vais ter de recuar ainda mais :C\n";
    }

    @Override
    String getPng() {
        return "exception.png";
    }
}
