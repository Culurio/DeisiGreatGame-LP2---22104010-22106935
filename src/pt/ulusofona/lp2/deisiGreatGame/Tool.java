package pt.ulusofona.lp2.deisiGreatGame;

public class Tool {

    protected int id;
    protected String name;
    protected int position;

    public Tool(int id, int position) {
        this.id = id;
        this.position = position;

        switch (id){
            case 0:
                name = "Herança";
                break;
            case 1:
                name = "Programação Funcional";
                break;
            case 2:
                name = "Testes Unitários";
                break;
            case 3:
                name = "Tratamento de Excepções";
                break;
            case 4:
                name = "IDE";
                break;
            case 5:
                name = "Ajuda Do Professor";
                break;
        }
    }

    public void giveTool(Programmer programmer){
        programmer.addTool(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public String getPng() {
        switch (id){
            case 0:
                return "inheritance.png";
            case 1:
                return "functional.png";
            case 2:
                return "unit-tests.png";
            case 3:
                return "catch.png";
            case 4:
                return "IDE.png";
            case 5:
                return "ajuda-professor.png";
            default:
                return null;
        }
    }

}
