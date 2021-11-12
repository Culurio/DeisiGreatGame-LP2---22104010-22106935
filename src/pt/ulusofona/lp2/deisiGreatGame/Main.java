package pt.ulusofona.lp2.deisiGreatGame;

public class Main {
    public static void main(String[] args) {
        CircularLinkedList teste = new CircularLinkedList();
        Programmer teste1 = new Programmer("a");
        teste.addNode(teste1);
        teste.addNode(new Programmer("b"));
        System.out.println(teste.head.value.getName());
    }
}
