package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class GameManager {

    String[][] playerInfo;
    boolean gameStatus;//true quando está em jogo e false caso contrario
    CircularLinkedList ordemDeJogada;
    Node jogadorAtual;
    int numeroDeJogadas = 0;
    ArrayList<Programmer> podio = new ArrayList<>();
    ArrayList<Programmer> jogadores;
    ArrayList<String> resultadosDoJogo = new ArrayList<>();
    int tamanhoDoTabuleiro;

    public GameManager(int boardSize){
        playerInfo = new String[jogadores.size()][4];
        jogadores= new ArrayList<>();
    }

    public GameManager(){

    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize){
        if(jogadores.size()<2 || boardSize<2*jogadores.size()){
            return false;
        }

        for (Programmer programmer : jogadores){
            if (!programmer.colorExists() || !programmer.isNameValid() ||
                    programmer.colorExists() || programmer.isIdUsed() || programmer.isColorUsed()){
                return false;
            }
        }

        for (int row = 0; row < jogadores.size(); row++) {
            ordemDeJogada.addNode(jogadores.get(row));
            jogadorAtual = ordemDeJogada.head;
            for (int col = 0; col < playerInfo[row].length; col++) {
                switch (col){
                    case 0:
                        playerInfo[row][col] = jogadores.get(row).getId()+"";
                        break;
                    case 1:
                        playerInfo[row][col] = jogadores.get(row).getName();
                        break;
                    case 2:
                        playerInfo[row][col] = jogadores.get(row).getProgrammerFavLan();
                        break;
                    case 3:
                        playerInfo[row][col] = jogadores.get(row).getColor().toString();
                }
            }
        }
        tabuleiro = new Posicao[boardSize-1];
        return true;
    }

    public String getImagePng(int position){
        if(position == tamanhoDoTabuleiro){
            return "images/glory.png" ;
        }
        return "images/blank.pbg";
    }

    public ArrayList<Programmer> getProgrammers(){
        return jogadores;
    }

    public ArrayList<Programmer> getProgrammers(int position){
        ArrayList<Programmer> jogadoresNaPosicao = new ArrayList<Programmer>();
        for (int i = 0; i < jogadores.size(); i++){
            if (jogadores.get(i).getPosition() == position){
                jogadoresNaPosicao.add(jogadores.get(i));
            }
        }
        return jogadoresNaPosicao;
    }

    public int getCurrentPlayerID(){
        return jogadorAtual.value.getId();
    }

    public boolean moveCurrentPlayer(int nrPositions){
        if (nrPositions < 1 || nrPositions > 6){
            return false;
        } else{
            jogadorAtual.value.move(nrPositions);
            jogadorAtual = jogadorAtual.nextNode;
            numeroDeJogadas++;
            return true;
        }
    }

    public void OrdenarPodio(Programmer current){
        Programmer aux = new Programmer();

        for (int i = 0; i < jogadores.size(); i++){
            if (current != jogadores.get(i)){
                if ()
            }
        }
    }

    public ArrayList<String> getGameResults(){
        resultadosDoJogo.add("O GRANDE JOGO DO DEISI\n\n");
        resultadosDoJogo.add("NR. DE TURNOS\n");
        resultadosDoJogo.add(numeroDeJogadas + "\n\n");
        resultadosDoJogo.add("VENCEDOR\n");
        resultadosDoJogo.add(podio.get(0).nome + "\n");
        resultadosDoJogo.add("RESTANTES\n");
        resultadosDoJogo.add(podio.get(1).nome + " 2");
        if (jogadores.size() == 3){
            resultadosDoJogo.add(podio.get(2).nome + " 3");
            if (jogadores.size() == 4){
                resultadosDoJogo.add(podio.get(3).nome + " 4");
            }
        }
        return resultadosDoJogo;
    }


}

