package pt.ulusofona.lp2.deisiGreatGame;

public class PosicaoNormal extends Position{

    @Override
    void voltarParaAPrimeiraCasa(){
        id = 1;
    }
    @Override
    void recuar(int moves){
        id -= moves;
    }

    @Override
    void avancar(int moves){
        id += moves;
    }

    @Override
    int getId() {
        return id;
    }

    @Override
    void effect() {

    }

    String getName() {
        return name;
    }
}
