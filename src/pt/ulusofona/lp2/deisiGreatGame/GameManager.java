package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameManager {
    int numberOfPlayers;
    int boardSize;
    int plays;
    int currentPlayer;
    List<Programmer> players = new ArrayList<>();
    ArrayList<String> gameResults = new ArrayList<>();

    public GameManager(int boardSize, List<Programmer> players, int numberOfPlayer) {
        this.players = players;
        this.numberOfPlayers = numberOfPlayer;
        this.boardSize = boardSize;
    }

    public GameManager() {

    }

    /*
    A função createInitialBoard vai ler a matriz que contem a informação toda acerta dos jogadores que vão ser criados
     */

    public boolean createInitialBoard(String[][] playerInfo, int worldSize) {
        players.clear();
        this.boardSize = worldSize;
        ArrayList<Integer> usedInts = new ArrayList<>();
        numberOfPlayers = playerInfo.length;
        ArrayList<String> usedColor = new ArrayList<>();
        plays = 1;
        currentPlayer = 0;
        gameResults.clear();

        //Resets feitos

        if (worldSize < 0 || worldSize < 2 * numberOfPlayers || numberOfPlayers<=1) {
            return false;
        }
        for (int row = 0; row < numberOfPlayers; row++) {
            String name;
            ArrayList<String> favoriteLanguages = new ArrayList<>();
            int id;
            ProgrammerColor avatarColor;
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
                    avatarColor = ProgrammerColor.BLUE;
                    break;
                case "Brown":
                    avatarColor = ProgrammerColor.BROWN;
                    break;
                case "Green":
                    avatarColor = ProgrammerColor.GREEN;
                    break;
                case "Purple":
                    avatarColor = ProgrammerColor.PURPLE;
                    break;
                default:
                    avatarColor = ProgrammerColor.NONE;
            }
            usedColor.add(playerInfo[row][3]);


            Programmer player = new Programmer(name, id, favoriteLanguages, avatarColor);
            Collections.sort(player.getProgrammerFavLanList());
            players.add(player);
            currentPlayer = 0;
        }
        return true;
    }

    boolean createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools){
        if(createInitialBoard(playerInfo,worldSize)){

            return  true;
        }
        return false;
    }

    public String getImagePng(int position) {
        if (position == boardSize) {
            return "glory.png";
        }
        return "blank.png";
    }

    public List<Programmer> getProgrammers() {
        return players;
    }

    public List<Programmer> getProgrammers(boolean includeDefeated) {
        List<Programmer> playersIncludeDefeated = new ArrayList<Programmer>();
        for (Programmer player : players) {
            if (!player.getStatusBool()) {
                playersIncludeDefeated.add(player);
            }
        }
        return playersIncludeDefeated;
    }

    public List<Programmer> getProgrammers(int position) {
        List<Programmer> playerOnPosition = new ArrayList<Programmer>();
        for (Programmer player : players) {
            if (player.getPosition() == position) {
                playerOnPosition.add(player);
            }
        }
        return playerOnPosition;
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
        players.sort(new Programmer.PositionComparator());
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

