package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameManager {
    int numberOfPlayers;
    int boardSize;
    int tamanhoDoTabuleiro;
    int numeroDeJogadas = 1;
    ArrayList<Programmer> jogadores = new ArrayList<>();
    ArrayList<String> resultadosDoJogo = new ArrayList<>();
    ArrayList<Programmer> ordemDeJogada = new ArrayList<>();
    int jogadorAtual;

    public GameManager(int boardSize, ArrayList<Programmer> jogadores, int numberOfPlayer) {
        this.jogadores = jogadores;
        this.numberOfPlayers = numberOfPlayer;
        this.boardSize = boardSize;
    }

    public GameManager() {

    }

    /*
    A função createInitialBoard vai ler a matriz que contem a informação toda acerta dos jogadores que vão ser criados
     */
    public boolean createInitialBoard(String[][] playerInfo, int boardSize) {
        jogadores.clear();
        tamanhoDoTabuleiro = boardSize;
        ArrayList<Integer> usedInts = new ArrayList<>();
        numberOfPlayers = playerInfo.length;
        ArrayList<String> usedColor = new ArrayList<>();
        numeroDeJogadas = 1;
        ordemDeJogada.clear();
        jogadorAtual = 0;
        resultadosDoJogo.clear();

        //Resets feitos

        if (boardSize < 0 || boardSize < 2 * numberOfPlayers || numberOfPlayers<=1) {
            return false;
        }
        for (int row = 0; row < numberOfPlayers; row++) {
            String nome = "";
            ArrayList<String> linguagensFavoritas = new ArrayList<>();
            int id = 0;
            ProgrammerColor corDoAvatar = ProgrammerColor.NONE;
            /*
            Verificar se o Id é valido
             */
            if (Integer.parseInt(playerInfo[row][0].trim()) < 0 ||
                    usedInts.contains(Integer.parseInt(playerInfo[row][0].trim()))) {
                return false;
            }
            id = Integer.parseInt(playerInfo[row][0].trim());
            usedInts.add(Integer.parseInt(playerInfo[row][0].trim()));
            /*
            Verificar se o nome está vazio ou é null
             */
            if (playerInfo[row][1] == null || playerInfo[row][1].isEmpty()) {
                return false;
            }
            nome = playerInfo[row][1];
            /*
            Guardar as linguagens favoritas
             */
            String[] guardar = playerInfo[row][2].split(";");
            Collections.addAll(linguagensFavoritas, guardar);

            /*
            Ver se a cor do jogador já foi utilizada e associar cor
             */
            if (usedColor.contains(playerInfo[row][3])) {
                return false;
            }
            switch (playerInfo[row][3]) {
                case "Blue":
                    corDoAvatar = ProgrammerColor.BLUE;
                    break;
                case "Brown":
                    corDoAvatar = ProgrammerColor.BROWN;
                    break;
                case "Green":
                    corDoAvatar = ProgrammerColor.GREEN;
                    break;
                case "Purple":
                    corDoAvatar = ProgrammerColor.PURPLE;
                    break;
                default:
                    corDoAvatar = ProgrammerColor.NONE;
            }
            usedColor.add(playerInfo[row][3]);


            Programmer player = new Programmer(nome, id, linguagensFavoritas, corDoAvatar);
            Collections.sort(player.linguagensFavoritas);
            jogadores.add(player);
            ordemDeJogada.add(player);
            jogadorAtual = 0;
        }
        return true;
    }

    public String getImagePng(int position) {
        if (position == tamanhoDoTabuleiro) {
            return "glory.png";
        }
        return "blank.png";
    }

    public ArrayList<Programmer> getProgrammers() {
        return jogadores;
    }

    public ArrayList<Programmer> getProgrammers(int position) {
        ArrayList<Programmer> jogadoresNaPosicao = new ArrayList<>();
        for (Programmer jogador : jogadores) {
            if (jogador.getPosition() == position) {
                jogadoresNaPosicao.add(jogador);
            }
        }
        return jogadoresNaPosicao;
    }

    public int getCurrentPlayerID() {
        return jogadores.get(jogadorAtual).getId();
    }

    /*
    Faz com que o jogador se mova e dá next no jogador atual
     */
    public boolean moveCurrentPlayer(int nrPositions) {
        if (nrPositions < 1 || nrPositions > 6) {
            return false;
        } else {
            Programmer programmer = jogadores.get(jogadorAtual);
            if (nrPositions + programmer.getPosition() > tamanhoDoTabuleiro) {
                nrPositions = tamanhoDoTabuleiro - programmer.getPosition() - nrPositions;
            }
            programmer.move(nrPositions);
            jogadorAtual = (jogadorAtual + 1) % numberOfPlayers;
            numeroDeJogadas++;
            return true;
        }
    }

    public boolean gameIsOver() {
        jogadores.sort(new Programmer.PositionComparator());
        for (Programmer programmer : jogadores) {
            if (programmer.getPosition() == tamanhoDoTabuleiro) {
                return true;
            }
        }
        return false;
    }

    /*
    Função um pouco nojenta mas devido a termos feito o trabalho em cima da hora
    não conseguimos fazer um painel melhor mas a ideia é esta
     */
    public JPanel getAuthorsPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 300, 300);
        panel.setBackground(Color.gray);
        JButton button1 = new JButton("Créditos");
        button1.setBounds(50, 100, 80, 30);
        button1.setBackground(Color.white);
        JButton button2 = new JButton("Inspiração");
        button2.setBounds(100, 100, 80, 30);
        button2.setBackground(Color.white);
        panel.add(button1);
        panel.add(button2);
        button1.addActionListener(event -> {
            JLabel label = new JLabel("<html>Realizado por:<br/>Cláudio Costa<br/>Gonçalo Antunes</html>", SwingConstants.CENTER);
            label.setFont(new Font("Verdana", Font.BOLD, 20));
            JLabel imageLabel = new JLabel();
            JLabel imageLabel2 = new JLabel();
            imageLabel.setIcon(new ImageIcon("src\\pt\\ulusofona\\lp2\\deisiGreatGame\\resources\\Cláudio.png"));
            imageLabel2.setIcon(new ImageIcon("src\\pt\\ulusofona\\lp2\\deisiGreatGame\\resources\\Gonçalo.png"));
            panel.add(label);
            panel.add(imageLabel);
            panel.add(imageLabel2);
            panel.setBorder(new LineBorder(Color.BLACK));
        });
        button2.addActionListener(event -> {
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(new ImageIcon("src\\pt\\ulusofona\\lp2\\deisiGreatGame\\resources\\Ric.png"));
            panel.add(imageLabel);
            panel.setBorder(new LineBorder(Color.BLACK));
        });
        return panel;
    }


    public ArrayList<String> getGameResults() {
        resultadosDoJogo.add("O GRANDE JOGO DO DEISI");
        resultadosDoJogo.add("");
        resultadosDoJogo.add("NR. DE TURNOS");
        resultadosDoJogo.add(numeroDeJogadas+"");
        resultadosDoJogo.add("");
        resultadosDoJogo.add("VENCEDOR");
        resultadosDoJogo.add(jogadores.get(0).nome);
        resultadosDoJogo.add("");
        resultadosDoJogo.add("RESTANTES");
        for (Programmer programmer : jogadores) {
            if (programmer == jogadores.get(0)) {
            }else{
                resultadosDoJogo.add(programmer.nome + " " + programmer.getPosition());
            }
        }
        return resultadosDoJogo;
    }
}

