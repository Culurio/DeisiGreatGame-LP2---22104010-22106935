package pt.ulusofona.lp2.deisiGreatGame;

public class Tool extends Effect {


    Tool(int id, int position) {
        super(id, position);
        switch (id){
            case 0:
                name = "Herança";
                break;
            case 1:
                name = "Programação funcional";
                break;
            case 2:
                name = "Testes unitários";
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

    @Override
    int getType() {
        return 1;
    }

    @Override
    String effect(Programmer programmer) {
        switch (id) {
            case 0:
                /*
                Herança Evita os efeitos de:
                - Duplicated Code
                 */
                programmer.addEffect(this);

                return "Incesto :o";
            case 1:
                /*
                Programação funcional Evita os efeitos de:
                - Duplicated Code
                - Efeitos secundários
                 */
                return "Programação funcional ha ha ha(Não sei o que dizer)";
            case 2:
                /*
                Testes unitários Evita os efeitos de:
                - Erro de lógica
                 */
                return "Para de testar as coisas na Main O.o";
            case 3:
                /*
                Tratamento de Excepções Evita os efeitos de:
                - Exception
                - File Not Found Exception
                 */
                return "hehehe Vamos tratar dessas excepções";
            case 4:
                /*
                IDE Evita os efeitos de:
                - Erro de sintaxe
                 */
                return "Para de usar o bloco de notas >:(";
            case 5:
                /*
                Ajuda Do Professor Evita os efeitos de:
                - Erro de Sintaxe
                - Erro de Lógica
                - Exception
                - File Not Found Exception
                 */
                return "Toma aí uma ajudinha heheheh";
            default:
                return "";
        }
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    int getId(){
        return id;
    }

    @Override
    String getPng() {
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
