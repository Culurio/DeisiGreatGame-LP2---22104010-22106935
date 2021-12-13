package pt.ulusofona.lp2.deisiGreatGame;

public class Abyss extends Effect {


    Abyss(int id, int position) {
        super(id, position);
        switch (id){
            case 0:
                name = "Erro de sintaxe";
                break;
            case 1:
                name = "Erro de lógica";
                break;
            case 2:
                name = "Exception";
                break;
            case 3:
                name = "File Not Found";
                break;
            case 4:
                name = "Crash (aka Rebentanço)";
                break;
            case 5:
                name = "Duplicated Code";
                break;
            case 6:
                name = "Efeitos secundários";
                break;
            case 7:
                name = "Blue Screen of Death";
                break;
            case 8:
                name = "Ciclo infinito";
                break;
            case 9:
                name = "Segmentation Fault";
                break;
        }
    }

    @Override
    int getType() {
        return 0;
    }

    @Override
    String effect(Programmer programmer) {
        switch (id){
            case 0:
                /*
                Erro de sintaxe O programador recua 1 casa.
                 */
                if(programmer.)
                programmer.move(-1);
                return "i'm not as think as you drunk i am\n";
            case 1:
                /*
                Erro de lógica O programador recua N casas, sendo N metade do
                valor que tiver saído no dado, arredondado para baixo.
                Por exemplo, se o dado deu 6, o programador recua 3
                casas. Se o dado deu 3, o programador recua 1 casa.
                 */
                int moves = programmer.getDice() / 2;
                programmer.move(moves);
                return "Benfica > Barcelona ou seja Portimonense > Barcelona\n";
            case 2:
                /*
                Exception O programador recua 2 casas.
                 */
                programmer.move(-2);
                return "Vais ter de recuar :C\n";
            case 3:
                /*
                File Not Found Exception O programador recua 3 casas.
                 */
                programmer.move(-3);
                return "Vais ter de recuar ainda mais:C\n";
            case 4:
                /*
                Crash (aka Rebentanço) O programador volta à primeira casa do jogo.
                 */
                programmer.setInitialPosition();
                return "Ahhhh *bolas* Here We Go Again\n";
            case 5:
                /*
                Duplicated Code O programador recua até à casa onde estava antes de chegar a esta casa.
                 */
                return "Duplicated Code\nDuplicated Code\n";
            case 6:
                /*
                Efeitos secundários O programador recua para a posição onde estava há 2 movimentos atrás.
                */
                return "Chernobyl\n";
            case 7:
                /*
                Blue Screen of Death O programador perde imediatamente o jogo.
                 */
                programmer.lose();
                return "#$(/&)&/)#$(/&)&/)&%#)&/)&%##$(/&)&/)&%#\n";
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
                programmer.stuck();
                return "sadasdasd\nsdsdfsdf\nasdasdasd\nfsdfsdfdfg\netc...\n";
            case 9:
                /*
                Segmentation Fault Este Abismo apenas é activado caso existam dois ou
                mais programadores na mesma casa.
                Todos os jogadores nessa casa recuam 3 casas.
                Caso apenas esteja um programador neste Abismo,
                então não existe nenhum efeito a aplicar.
                 */
                programmer.move(-3);
                return "segmentation fault (core dumped)";
            default:
                return "";
        }
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    String getPng() {
        switch (id){
            case 0:
                return "syntax.png";
            case 1:
                return "logic.png";
            case 2:
                return "exception.png";
            case 3:
                return "file-not-found.png";
            case 4:
                return "crash.png";
            case 5:
                return "duplicated-code.png";
            case 6:
                return "secondary-effects.png";
            case 7:
                return "bsod.png";
            case 8:
                return "infinite-loop.png";
            case 9:
                return "core-dumped.png";
            default:
                return null;
        }
    }
}
