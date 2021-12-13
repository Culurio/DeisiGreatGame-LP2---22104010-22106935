package pt.ulusofona.lp2.deisiGreatGame;

abstract class Effect {
    protected int id;
    protected String name = null;
    protected int position;

    Effect(int id, int position){

        this.id = id;
        this.position = position;

    }

    public int getPosition(){
        return position;
    };

    abstract int getType();

    abstract String effect(Programmer programmer);

    abstract int getId();

    abstract String getName();

    abstract String getPng();
}
