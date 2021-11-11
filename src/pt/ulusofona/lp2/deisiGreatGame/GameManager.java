package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class GameManager {

    String[][] playerInfo;
    Posicao[] tabuleiro;
    int boardSize;
    Programmer jogadorAtual;
    ArrayList<Programmer> jogadores;

    GameManager(int boardSize){
        playerInfo = new String[jogadores.size()][4];
        this.boardSize=boardSize;
        jogadores= new ArrayList<>();
    }

    GameManager(){

    }

    //public void DadosFinais(){}

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
            for (int col = 0; col < playerInfo[row].length; col++) {
                switch (col){
                    case 0:
                        playerInfo[row][col] = jogadores.get(col).getProgrammerId()+"";
                        break;
                    case 1:
                        playerInfo[row][col] = jogadores.get(col).getProgrammerName();
                        break;
                    case 2:
                        playerInfo[row][col] = jogadores.get(col).getProgrammerFavLan();
                        break;
                    case 3:
                        playerInfo[row][col] = jogadores.get(col).getProgrammerColor().toString();
                }
            }
        }
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

    /*Devolve uma lista com os objectos Programmer que se encontrem numa
    determinada posição do tabuleiro.*/
    /*Caso o position seja inválido ou caso não existam programadores na
    posição indicada, a função deve devolver null.*/
    public ArrayList<Programmer> getProgrammers(int position){
        if (position < 0 || position > 50 ){
            return null;
        }
        return null;
    }



}

