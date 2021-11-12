package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class GameManager {

    String[][] playerInfo;
    boolean gameStatus;
    Posicao[] tabuleiro;
    Programmer jogadorAtual;
    ArrayList<Programmer> jogadores;
    int numeroDeTurnos = 0;
    ArrayList<Programmer> podio = new ArrayList<Programmer>();
    ArrayList<String> resultadosDoJogo = new ArrayList<String>();

    GameManager(int boardSize){
        playerInfo = new String[jogadores.size()][4];
        jogadores= new ArrayList<>();
    }

    GameManager(){

    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize){
        if(jogadores.size() < 2 || boardSize < 2 * jogadores.size()){
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
                        playerInfo[row][col] = jogadores.get(col).getId()+"";
                        break;
                    case 1:
                        playerInfo[row][col] = jogadores.get(col).getName();
                        break;
                    case 2:
                        playerInfo[row][col] = jogadores.get(col).getProgrammerFavLan();
                        break;
                    case 3:
                        playerInfo[row][col] = jogadores.get(col).getColor().toString();
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

    public String verVencedorESeguintes(){
        Programmer joker;
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

    public ArrayList<Programmer> getProgrammers(){
        return jogadores;
    }

    public ArrayList<Programmer> getProgrammers(int position){
        if (position < 1 || position > tabuleiro.length + 1 || tabuleiro[position + 1] == null){
            return null;
        }
        return tabuleiro[position - 1].programmers;
    }

    public int getCurrentPlayerID(){
        return jogadorAtual.id;
    }

    public boolean moveCurrentPlayer(int nrPositions){
        if (nrPositions < 1 || nrPositions > 6){
            return false;
        } else{
            jogadorAtual.position += nrPositions;
            return true;
        }
    }

}

