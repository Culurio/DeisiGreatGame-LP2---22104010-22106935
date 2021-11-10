package pt.ulusofona.lp2.deisiGreatGame;

public class Programmer {

    String nome;
    String linguagensFavoritas;
    int id;
    ProgrammerColor corDoAvatar;

    Programmer(String nome, String linguagensFavoritas, int id, ProgrammerColor corDoAvatar){
        this.nome = nome;
        this.linguagensFavoritas = linguagensFavoritas;
        this.id = id;
        this.corDoAvatar = corDoAvatar;
    }


    String getProgrammerName(){
        return nome;
    }

    String getProgrammerFavLan(){
        return linguagensFavoritas;
    }

    int getProgrammerId(){
        return id;
    }

    ProgrammerColor getProgrammerColor(){
        return corDoAvatar;
    }

}
