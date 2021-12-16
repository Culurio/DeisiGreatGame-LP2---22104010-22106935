package pt.ulusofona.lp2.deisiGreatGame;

public class BlueScreenError extends Abyss {
    BlueScreenError(int id, int position) {
        super(id, position);
        name = "Blue Screen of Death";
    }

    @Override
    String effect(Programmer programmer) {
        /*
                Crash (aka Rebentanço) - O programador volta à primeira casa do jogo.
        */

        programmer.setInitialPosition();

        return "Ahhhh *bolas* Here We Go Again\n";
    }

    @Override
    String getPng() {
        return "bsod.png";
    }


}
