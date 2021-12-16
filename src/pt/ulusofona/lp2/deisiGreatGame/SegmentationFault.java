package pt.ulusofona.lp2.deisiGreatGame;

public class SegmentationFault extends Abyss{
    SegmentationFault(int id, int position) {
        super(id, position);
        name = "Segmentation Fault";
    }

    @Override
    String effect(Programmer programmer) {
        /*
                Segmentation Fault Este Abismo apenas é activado caso existam dois ou
                mais programadores na mesma casa.
                Todos os jogadores nessa casa recuam 3 casas.
                Caso apenas esteja um programador neste Abismo,
                então não existe nenhum efeito a aplicar.
         */

        programmer.move(-3);

        return "segmentation fault (core dumped)";
    }

    @Override
    String getPng() {
        return "secondary-effects.png";
    }
}
