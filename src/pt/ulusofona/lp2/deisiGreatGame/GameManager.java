package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameManager {
    CircularLinkedList ordemDeJogada = new CircularLinkedList();
    int numberOfPlayer;
    int boardSize;
    Node jogadorAtual;
    int numeroDeJogadas = 0;
    ArrayList<Programmer> jogadores = new ArrayList<>();
    ArrayList<String> resultadosDoJogo = new ArrayList<>();
    int tamanhoDoTabuleiro;

    public GameManager(int boardSize, ArrayList<Programmer> jogadores, int numberOfPlayer) {
        this.jogadores = jogadores;
        this.numberOfPlayer = numberOfPlayer;
        this.boardSize = boardSize;
    }

    public GameManager() {

    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize) {
        jogadores.clear();
        tamanhoDoTabuleiro = boardSize;
        ArrayList<Integer> usedInts = new ArrayList<>();
        numberOfPlayer = playerInfo.length;
        ArrayList<String> usedColor = new ArrayList<>();
        numeroDeJogadas = 0;
        ordemDeJogada = new CircularLinkedList();
        jogadorAtual = null;
        resultadosDoJogo.clear();

        //Resets feitos

        if (boardSize < 0 || boardSize < 2 * numberOfPlayer || numberOfPlayer<=1) {
            return false;
        }
        for (int row = 0; row < numberOfPlayer; row++) {
            String nome = "";
            ArrayList<String> linguagensFavoritas = new ArrayList<>();
            int id = 0;
            ProgrammerColor corDoAvatar = ProgrammerColor.NONE;
            for (int col = 0; col < 4; col++) {
                switch (col) {
                    case 0:
                        if (Integer.parseInt(playerInfo[row][col].trim()) < 0 ||
                                usedInts.contains(Integer.parseInt(playerInfo[row][col].trim()))) {
                            return false;
                        }
                        id = Integer.parseInt(playerInfo[row][col].trim());
                        usedInts.add(Integer.parseInt(playerInfo[row][col].trim()));
                        break;
                    case 1:
                        if (playerInfo[row][col] == null || playerInfo[row][col].isEmpty()) {
                            return false;
                        }
                        nome = playerInfo[row][col];
                        break;
                    case 2:
                        String[] guardar = playerInfo[row][col].split(";");
                        Collections.addAll(linguagensFavoritas, guardar);
                        break;
                    case 3:
                        if (usedColor.contains(playerInfo[row][col])) {
                            return false;
                        }
                        switch (playerInfo[row][col]) {
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
                                break;
                        }
                        usedColor.add(playerInfo[row][col]);
                }
            }
            Programmer player = new Programmer(nome, id, linguagensFavoritas, corDoAvatar);
            Collections.sort(player.linguagensFavoritas);
            jogadores.add(player);
            ordemDeJogada.addNode(player);
            jogadorAtual = ordemDeJogada.head;
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
        return jogadorAtual.value.getId();
    }

    public boolean moveCurrentPlayer(int nrPositions) {
        if (nrPositions < 1 || nrPositions > 6) {
            return false;
        } else {
            if (nrPositions + jogadorAtual.value.getPosition() > tamanhoDoTabuleiro) {
                nrPositions = tamanhoDoTabuleiro - jogadorAtual.value.getPosition() - nrPositions;
            }
            jogadorAtual.value.move(nrPositions);
            jogadorAtual = jogadorAtual.nextNode;
            numeroDeJogadas++;
            return true;
        }
    }

    public boolean gameIsOver() {
        jogadores.sort(new Programmer.PositionComparator());
        for (Programmer programmer : jogadores) {
            if (programmer.position == tamanhoDoTabuleiro) {
                return true;
            }
        }
        return false;
    }

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
            imageLabel.setBounds(20, 150, 50, 50);
            imageLabel.setIcon(new ImageIcon("D:\\LP2\\DeisiGreatGame\\src\\pt\\ulusofona\\lp2\\deisiGreatGame\\resources\\Cláudio.png"));
            panel.add(imageLabel);
            panel.add(label);
            panel.setBorder(new LineBorder(Color.BLACK));
        });
        button2.addActionListener(event -> {
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(new ImageIcon("D:\\LP2\\DeisiGreatGame\\src\\pt\\ulusofona\\lp2\\deisiGreatGame\\resources\\Ric.png"));
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
                resultadosDoJogo.add(programmer.nome + " " + programmer.position + "\n");
            }
        }
        return resultadosDoJogo;
    }
}

