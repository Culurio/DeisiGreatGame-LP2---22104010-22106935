package pt.ulusofona.lp2.deisiGreatGame;

public class DuplicatedCode extends Abyss {
    DuplicatedCode(int id, int position) {
        super(id, position);
        name = "Duplicated Code";
    }

    @Override
    String effect(Programmer programmer) {
        /*
                Duplicated Code - O programador recua até à casa onde estava antes de chegar a esta casa.
        */

        if (programmer.verifyTool(0) || programmer.verifyTool(1)){
            return ":) Boa poupaste umas boas linhas";
        }

        programmer.move(-programmer.diferencaAteAUltimaCasa());
        return "Duplicated Code\nDuplicated Code\n";
    }

    @Override
    String getPng() {
        return "duplicated-code.png";
    }
}
