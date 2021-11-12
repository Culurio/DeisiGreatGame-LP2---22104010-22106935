package pt.ulusofona.lp2.deisiGreatGame;

import javax.sound.sampled.Port;
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

    /*Deve devolver o nome do ficheiro de imagem (formato PNG) que representa
    no tabuleiro a posição cujo número é dado pelo argumento position.*/
    public String getImagePng(int position){


        return "";
    }

    /*Devolve uma lista com todos os objectos Programmer que existem em jogo.*/
    public ArrayList<Programmer> getProgrammers(){
        return jogadores;
    }

    //

    /*Devolve uma lista com os objectos Programmer que se encontrem numa
    determinada posição do tabuleiro.*/
    /*Caso o position seja inválido ou caso não existam programadores na
    posição indicada, a função deve devolver null.*/
    public ArrayList<Programmer> getProgrammers(int position){
        if (position < 1 || position > tabuleiro.length - 1 || tabuleiro[position - 1] == null){
            return null;
        }
        return tabuleiro[position - 1].programmers;
    }

    /* Devolve o ID do programador que se encontra activo no turno actual.*/
    public int getCurrentPlayerID(){
        return jogadorAtual.value.id;
    }

    /*Move o programador do turno actual tantas casas quantas as indicadas
    no argumento nrPositions.*/
    /*O argumento nrPositions não pode ser menor do que 1 ou maior do que 6,
    porque o dado tem 6 lados*/
    public boolean moveCurrentPlayer(int nrPositions){
        if (nrPositions < 1 || nrPositions > 6){
            return false;
        } else{
            jogadorAtual.value.position += nrPositions;
            jogadorAtual = jogadorAtual.nextNode;
            return true;
        }
    }

    public String verVencedorESeguintes(){
        for (int i = tabuleiro.length-1 ; i >= 0 ; i--){
            if (tabuleiro[i].programmers != null){
                podio.add(tabuleiro[i].programmers.get(0));
            }
        }
        return "";
    }

    public ArrayList<String> getGameResults(){
        resultadosDoJogo.add("O GRANDE JOGO DO DEISI\n\n");
        resultadosDoJogo.add("NR. DE TURNOS\n");
        //VER COMO FAZER O NUMERO DE TURNOS
        resultadosDoJogo.add("\n");
        resultadosDoJogo.add("VENCEDOR\n");
        resultadosDoJogo.add("");
        return resultadosDoJogo;
    }


}

