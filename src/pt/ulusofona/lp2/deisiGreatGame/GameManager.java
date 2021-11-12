package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class GameManager {

    String[][] playerInfo;
    boolean gameStatus;//true quando está em jogo e false caso contrario
    CircularLinkedList ordemDeJogada;
    Node jogadorAtual;
    int boardSize;
    int numeroDeJogadas = 0;
    ArrayList<Programmer> podio = new ArrayList<>();
    ArrayList<Programmer> jogadores;
    ArrayList<String> resultadosDoJogo = new ArrayList<>();

    public GameManager(){

    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize){
        ArrayList<Integer> usedInts = new ArrayList<>();
        for (int row = 0; row < 4; row++) {
            ordemDeJogada.addNode(jogadores.get(row));
            jogadorAtual = ordemDeJogada.head;
            for (int col = 0; col < 4; col++) {
                switch (col){
                    case 0:
                        if(Integer.parseInt(playerInfo[row][col].trim())<0 ||
                                usedInts.contains(Integer.parseInt(playerInfo[row][col].trim()))){
                            return false;
                        }
                        usedInts.add(Integer.parseInt(playerInfo[row][col].trim()));
                        break;
                    case 1:
                        if(playerInfo[row][col] == null || playerInfo[row][col].isEmpty() ){
                            return false;
                        }
                        break;
                    case 2:
                        playerInfo[row][col] = jogadores.get(row).getProgrammerFavLan();
                        break;
                    case 3:
                        playerInfo[row][col] = jogadores.get(row).getColor().toString();
                }
            }
        }
        return true;
    }

    public String getImagePng(int position){
        if(position == boardSize ){
            return "images/glory.png" ;
        }
        return "images/blank.pbg";
    }

    public ArrayList<Programmer> getProgrammers(){
        return jogadores;
    }

    public ArrayList<Programmer> getProgrammers(int position){
        return null;
    }

    public int getCurrentPlayerID(){
        return jogadorAtual.value.id;
    }

    public boolean moveCurrentPlayer(int nrPositions){
        if (nrPositions < 1 || nrPositions > 6){
            return false;
        } else{
            jogadorAtual.value.position += nrPositions;
            jogadorAtual = jogadorAtual.nextNode;
            numeroDeJogadas++;
            return true;
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

