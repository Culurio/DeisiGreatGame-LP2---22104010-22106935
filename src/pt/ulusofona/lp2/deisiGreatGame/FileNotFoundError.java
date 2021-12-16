package pt.ulusofona.lp2.deisiGreatGame;

public class FileNotFoundError extends Abyss {
    FileNotFoundError(int id, int position) {
        super(id, position);
        name = "File Not Found";
    }

    @Override
    String effect(Programmer programmer) {
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
