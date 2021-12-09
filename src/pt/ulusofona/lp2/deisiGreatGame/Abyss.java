package pt.ulusofona.lp2.deisiGreatGame;

public class Abyss extends Position {
    @Override
    void effect() {
        switch (id){
            case 0:
                /*
                Erro de sintaxe O programador recua 1 casa.
                 */
                name = "Erro de sintaxe";
                recuar(1);
                break;
            case 1:
                /*
                Erro de lógica O programador recua N casas, sendo N metade do
                valor que tiver saído no dado, arredondado para baixo.
                Por exemplo, se o dado deu 6, o programador recua 3
                casas. Se o dado deu 3, o programador recua 1 casa.
                 */
                name = "Erro de lógica";
                recuar((id - players.get(0).retornarUltimaCasa()));
                break;
            case 2:
                /*
                Exception O programador recua 2 casas.
                 */
                name = "Exception";
                recuar(2);
                break;
            case 3:
                /*
                File Not Found Exception O programador recua 3 casas.
                 */
                name = "File Not Found";
                recuar(3);
                break;
            case 4:
                /*
                Crash (aka Rebentanço) O programador volta à primeira casa do jogo.
                 */
                name = "Crash (aka Rebentanço)";
                voltarParaAPrimeiraCasa();
                break;
            case 5:
                /*
                Duplicated Code O programador recua até à casa onde estava antes de chegar a esta casa.
                 */
                name = "Duplicated Code";
                players.get(0).recuarParaCasasAnteriores(0);
                break;
            case 6:
                /*
                Efeitos secundários O programador recua para a posição onde estava há 2 movimentos atrás.
                */
                name = "Efeitos secundários";
                players.get(0).recuarParaCasasAnteriores(1);
                break;
            case 7:
                /*
                Blue Screen of Death O programador perde imediatamente o jogo.
                 */
                name = "Blue Screen of Death";
                players.remove(0);
                break;
            case 8:
                /*
                Ciclo infinito O programador fica preso na casa onde está até que
                lá apareça outro programador para o ajudar.
                O programador que aparecer para ajudar, fica ele
                próprio preso (mas liberta o que já lá estava).
                Caso o programador que aparece tenha uma
                ferramenta que permita livrar-se do abismo, ele não
                fica preso mas também já não liberta o programador
                que lá estava.
                 */
                name = "Ciclo infinito";
                break;
            case 9:
                /*
                Segmentation Fault Este Abismo apenas é activado caso existam dois ou
                mais programadores na mesma casa.
                Todos os jogadores nessa casa recuam 3 casas.
                Caso apenas esteja um programador neste Abismo,
                então não existe nenhum efeito a aplicar.
                 */
                name = "Segmentation Fault";
                if (players.size() > 1){
                    for (Programmer player : players) {
                        recuar(3);
                    }
                }
                break;
        }
    }

    @Override
    String getName() {
        return null;
    }

    @Override
    int getId() {
        return 0;
    }

    @Override
    void avancar(int moves) {

    }

    @Override
    void recuar(int moves) {

    }

    @Override
    void voltarParaAPrimeiraCasa() {

    }
}
