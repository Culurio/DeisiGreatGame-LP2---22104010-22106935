package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameManager {
    int numberOfPlayers;
    int boardSize;
    int plays = 1;
    int currentPlayer;
    ArrayList<Programmer> players = new ArrayList<>();
    ArrayList<String> gameResults = new ArrayList<>();
    ArrayList<Programmer> playOrder = new ArrayList<>();

    public GameManager(int boardSize, ArrayList<Programmer> players, int numberOfPlayer) {
        this.players = players;
        this.numberOfPlayers = numberOfPlayer;
        this.boardSize = boardSize;
    }

    public GameManager() {

    }

    /*
    A função createInitialBoard vai ler a matriz que contem a informação toda acerta dos jogadores que vão ser criados
     */

    public boolean createInitialBoard(String[][] playerInfo, int boardSize) {
        players.clear();
        this.boardSize = boardSize;
        ArrayList<Integer> usedInts = new ArrayList<>();
        numberOfPlayers = playerInfo.length;
        ArrayList<String> usedColor = new ArrayList<>();
        plays = 1;
        playOrder.clear();
        currentPlayer = 0;
        gameResults.clear();

        //Resets feitos

        if (boardSize < 0 || boardSize < 2 * numberOfPlayers || numberOfPlayers<=1) {
            return false;
        }
        for (int row = 0; row < numberOfPlayers; row++) {
            String name;
            ArrayList<String> favoriteLanguages = new ArrayList<>();
            int id;
            ProgrammerColor corDoAvatar;
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
            name = playerInfo[row][1];
            /*
            Guardar as linguagens favoritas
             */
            String[] save = playerInfo[row][2].split(";");
            Collections.addAll(favoriteLanguages, save);

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


            Programmer player = new Programmer(name, id, favoriteLanguages, corDoAvatar);
            Collections.sort(player.favoriteLanguages);
            players.add(player);
            playOrder.add(player);
            currentPlayer = 0;
        }
        return true;
    }

    public String getImagePng(int position) {
        if (position == boardSize) {
            return "glory.png";
        }
        return "blank.png";
    }

    public ArrayList<Programmer> getProgrammers() {
        return players;
    }

    public ArrayList<Programmer> getProgrammers(int position) {
        ArrayList<Programmer> jogadoresNaPosicao = new ArrayList<>();
        for (Programmer jogador : players) {
            if (jogador.getPosition() == position) {
                jogadoresNaPosicao.add(jogador);
            }
        }
        return jogadoresNaPosicao;
    }

    public int getCurrentPlayerID() {
        return players.get(currentPlayer).getId();
    }

    /*
    Faz com que o jogador se mova e dá next no jogador atual
     */
    public boolean moveCurrentPlayer(int nrPositions) {
        if (nrPositions < 1 || nrPositions > 6) {
            return false;
        } else {
            Programmer programmer = players.get(currentPlayer);
            if (nrPositions + programmer.getPosition() > boardSize) {
                nrPositions = boardSize - programmer.getPosition() - nrPositions;
            }
            programmer.move(nrPositions);
            currentPlayer = (currentPlayer + 1) % numberOfPlayers;
            plays++;
            return true;
        }
    }

    public boolean gameIsOver() {
        players.sort(new Programmer.PositionComparator());
        for (Programmer programmer : players) {
            if (programmer.getPosition() == boardSize) {
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
        gameResults.add("O GRANDE JOGO DO DEISI");
        gameResults.add("");
        gameResults.add("NR. DE TURNOS");
        gameResults.add(plays+"");
        gameResults.add("");
        gameResults.add("VENCEDOR");
        gameResults.add(players.get(0).getName());
        gameResults.add("");
        gameResults.add("RESTANTES");
        for (Programmer programmer : players) {
            if (programmer == players.get(0)) {
            }else{
                gameResults.add(programmer.getName() + " " + programmer.getPosition());
            }
        }
        return gameResults;
    }
}

