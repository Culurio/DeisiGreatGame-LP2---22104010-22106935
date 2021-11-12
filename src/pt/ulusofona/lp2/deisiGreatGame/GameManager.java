package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class GameManager {

    String[][] playerInfo;
    boolean gameStatus;//true quando está em jogo e false caso contrario
    CircularLinkedList ordemDeJogada;
    int numberOfPlayer;
    int boardSize;
    Node jogadorAtual;
    int numeroDeJogadas = 0;
    ArrayList<Programmer> podio = new ArrayList<>();
    ArrayList<Programmer> jogadores = new ArrayList<>();
    ArrayList<String> resultadosDoJogo = new ArrayList<>();
    int tamanhoDoTabuleiro;

    public GameManager(int boardSize,ArrayList<Programmer> jogadores,int numberOfPlayer){
        this.jogadores = jogadores;
        this.numberOfPlayer = numberOfPlayer;
        this.boardSize=boardSize;
    }


    public GameManager(){

    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize){
        ArrayList<Integer> usedInts = new ArrayList<>();
        numberOfPlayer=playerInfo.length;
        ArrayList<String> usedColor = new ArrayList<>();
        String nome="";
        ArrayList<String> linguagensFavoritas = new ArrayList<>();
        int id=0;
        ProgrammerColor corDoAvatar = ProgrammerColor.NONE;
        if(boardSize < 0 ||  boardSize < 2 * numberOfPlayer)
            return false;

        for (int row = 0; row < numberOfPlayer-1; row++) {
            for (int col = 0; col < 4; col++) {
                switch (col){
                    case 0:
                        if(Integer.parseInt(playerInfo[row][col].trim())<0 ||
                                usedInts.contains(Integer.parseInt(playerInfo[row][col].trim()))){
                            return false;
                        }
                        id = Integer.parseInt(playerInfo[row][col].trim());
                        usedInts.add(Integer.parseInt(playerInfo[row][col].trim()));
                        break;
                    case 1:
                        if(playerInfo[row][col] == null || playerInfo[row][col].isEmpty() ){
                            return false;
                        }
                        nome = playerInfo[row][col];
                        break;
                    case 2:
                        String[] guardar = playerInfo[row][col].split(";");
                        for(String string : guardar){
                            linguagensFavoritas.add(string);
                        }
                        break;
                    case 3:
                        if(usedColor.contains(playerInfo[row][col])){
                            return false;
                        }
                        corDoAvatar = ProgrammerColor(playerInfo[row][col]);
                        usedColor.add(playerInfo[row][col]);
                }
            }
            Programmer programador = new Programmer(nome,id,linguagensFavoritas,corDoAvatar);
            jogadores.add(programador);
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
        for (Programmer jogador : jogadores) {
            if (jogador.getPosition() == position) {
                jogadoresNaPosicao.add(jogador);
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

