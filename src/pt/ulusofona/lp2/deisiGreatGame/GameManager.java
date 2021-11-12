package pt.ulusofona.lp2.deisiGreatGame;

import javax.sound.sampled.Port;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

public class GameManager {

    String[][] playerInfo;
    boolean gameStatus;//true quando está em jogo e false caso contrario 
    Posicao[] tabuleiro;
    CircularLinkedList ordemDeJogada;
    Node jogadorAtual;
    ArrayList<Programmer> podio = new ArrayList<Programmer>();
    ArrayList<Programmer> jogadores;
    ArrayList<String> resultadosDoJogo = new ArrayList<String>();
    int numeroDeJogadas = 0;

    GameManager(int boardSize){
        playerInfo = new String[jogadores.size()][4];
        jogadores= new ArrayList<>();
    }

    GameManager(){

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


        return "";
    }

    public ArrayList<Programmer> getProgrammers(){
        return jogadores;
    }

    public ArrayList<Programmer> getProgrammers(int position){
        if (position < 1 || position > tabuleiro.length - 1 || tabuleiro[position - 1] == null){
            return null;
        }
        return tabuleiro[position - 1].programmers;
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

    public void verVencedorESeguintes(){
        for (int i = tabuleiro.length -  1 ; i >= 0 ; i--){
            if (tabuleiro[i].programmers != null){
                podio.add(tabuleiro[i].programmers.get(0));
                if (tabuleiro[i].programmers.get(1) != null){
                    podio.add(tabuleiro[i].programmers.get(1));
                }
            }
        }
    }

    public ArrayList<String> getGameResults(){
        resultadosDoJogo.add("O GRANDE JOGO DO DEISI\n\n");
        resultadosDoJogo.add("NR. DE TURNOS\n");
        resultadosDoJogo.add(Integer.toString(numeroDeJogadas) + "\n\n");
        resultadosDoJogo.add("VENCEDOR\n");
        resultadosDoJogo.add(podio.get(0).nome + "\n\n");
        resultadosDoJogo.add("RESTANTES\n");
        resultadosDoJogo.add(podio.get(1).nome + " 2\n");
        if (jogadores.size() == 3){
            resultadosDoJogo.add(podio.get(2).nome + " 3\n");
            if (jogadores.size() == 4){
                resultadosDoJogo.add(podio.get(3).nome + " 4\n");
            }
        }
        return resultadosDoJogo;
    }


}

