package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class GameManager {

    String[][] playerInfo;
    boolean gameStatus;//true quando está em jogo e false caso contrario
    CircularLinkedList ordemDeJogada;
    int numberOfPlayer;
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
        ArrayList<Integer> usedInts = new ArrayList<>();
        ArrayList<String> usedColor = new ArrayList<>();

        if(boardSize < 0 ||  boardSize < 2 * numberOfPlayer)
            return false;

        for (int row = 0; row < numberOfPlayer; row++) {
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
                        break;
                    case 3:
                        if(usedColor.contains(playerInfo[row][col])){
                            return false;
                        }
                        usedColor.add(playerInfo[row][col]);
                }
            }
        }
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

