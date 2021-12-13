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
    List<Effect> effects = new ArrayList<>();
    List<Programmer> players = new ArrayList<>();
    List<String> gameResults = new ArrayList<>();


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
        effects.clear();
        int effectId;
        Effect effect = null;
        String type;
        int effectPosition;

        if(createInitialBoard(playerInfo,worldSize)){
            for (int row = 0; row < abyssesAndTools.length; row++) {
                type = abyssesAndTools[row][0];
                effectId = Integer.parseInt(abyssesAndTools[row][1]);
                effectPosition = Integer.parseInt(abyssesAndTools[row][2]);

                if(type == null || !type.equals("1") && !type.equals("0")){
                    return false;
                }

                if(effectId < 0 || (effectId > 9 && type.equals("0") || (effectId > 5 && type.equals("1")))){
                    return false;
                }

                if (effectPosition < 0 || effectPosition > worldSize) {
                    return false;
                }

                if (type.equals("1")){
                    effect = new Tool(effectId,effectPosition);
                }

                if(type.equals("0")){
                    effect = new Abyss(effectId,effectPosition);
                }
                effects.add(effect);
            }
            return  true;
        }
        return false;
    }

    public String getImagePng(int position) {
        if (position == boardSize) {
            return "glory.png";
        }

        for (Effect effect: effects) {
            if(effect.position == position){
                return effect.getPng();
            }
        }

        return null;
    }

    String getTitle(int position){
        for (Effect effect: effects) {
            if(effect.position == position){
                return effect.getName();
            }
        }

        return null;
    }

    public List<Programmer> getProgrammers(boolean includeDefeated) {
        List<Programmer> playersAlive = new ArrayList<Programmer>();
        if(includeDefeated){
            return players;
        }

        for (Programmer player: players) {
            if (player.getStatusBool()){
                playersAlive.add(player);
            }
        }

        return playersAlive;
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

    public String getProgrammersInfo(){
        StringBuilder info = new StringBuilder();

        for (Programmer programmer : players) {
            info.append(programmer.programmerTools()).append(" | ");
        }

        info.deleteCharAt(info.toString().length()-2);

        return info.toString();
    }

    public int getCurrentPlayerID() {
        return players.get(currentPlayer).getId();
    }

    /*
    Faz com que o jogador se mova e dá next no jogador atual
     */
    public boolean moveCurrentPlayer(int nrSpaces) {
        Programmer programmer = players.get(currentPlayer);
        programmer.setDice(nrSpaces);

        if (nrSpaces < 1 || nrSpaces > 6 || !programmer.getStatusBool() || programmer.isStuck()) {
            return false;
        } else {
            if (nrSpaces + programmer.getPosition() > boardSize) {
                nrSpaces = boardSize - programmer.getPosition() - nrSpaces;
            }

            programmer.move(nrSpaces);
            currentPlayer = (currentPlayer + 1) % numberOfPlayers;
            plays++;

            return true;
        }
    }

    public String reactToAbyssOrTool(){
        Programmer programmer = players.get(currentPlayer);

        for (Effect effect : effects) {
            if(programmer.getPosition() == effect.getPosition()){
                if (effect.getType() == 0){
                    return effect.effect(programmer);
                }else {
                    return effect.effect(programmer);
                }
            }
        }
        return "";
    }

    public void stuckPlayer(){
        Programmer current = players.get(currentPlayer);

        if (!current.isStuck() )
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


    public List<String> getGameResults() {
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

