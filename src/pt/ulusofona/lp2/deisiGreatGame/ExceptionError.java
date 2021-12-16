package pt.ulusofona.lp2.deisiGreatGame;

public class ExceptionError extends Abyss {
    ExceptionError(int id, int position) {
        super(id, position);
        name = "Exception";
    }

    @Override
    String effect(Programmer programmer) {
        /*
                File Not Found Exception - O programador recua 3 casas.
        */

        if (programmer.verifyTool(3)){
            return "UFAA SAFASTE TE DESSA agora não vais recuar";
        }

        programmer.move(-3);
        return "Vais ter de recuar ainda mais :C\n";
    }

    @Override
    String getPng() {
        return "exception.png";
    }
}
