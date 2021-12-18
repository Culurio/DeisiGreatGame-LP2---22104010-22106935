package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameManager {
    private int numberOfPlayers;
    private int boardSize;
    private int plays;
    private int currentPlayer;
    private final List<Tool> tools = new ArrayList<>();
    private final List<Abyss> abysses = new ArrayList<>();
    private List<Programmer> players = new ArrayList<>();
    private final List<String> gameResults = new ArrayList<>();


    public GameManager(int boardSize, List<Programmer> players, int numberOfPlayer) {
        this.players = players;
        this.numberOfPlayers = numberOfPlayer;
        this.boardSize = boardSize;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getPlays() {
        return plays;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public List<Abyss> getAbysses() {
        return abysses;
    }

    public List<Programmer> getPlayers() {
        return players;
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
            players.sort(new Programmer.IDComparator());
            Collections.reverse(players);
            currentPlayer = 0;
        }

        return true;
    }

    public boolean createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools){
        tools.clear();
        int effectId;
        Abyss abyss = null;
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
                    tools.add(new Tool(effectId,effectPosition));
                }

                if(type.equals("0")){
                    switch (effectId){
                        case 0:
                            abyss = new SyntaxError(effectId,effectPosition);
                        break;
                        case 1:
                            abyss = new LogicError(effectId,effectPosition);
                        break;
                        case 2:
                            abyss = new ExceptionError(effectId, effectPosition);
                        break;
                        case 3:
                            abyss = new FileNotFoundError(effectId,effectPosition);
                        break;
                        case 4:
                            abyss = new CrashError(effectId,effectPosition);
                        break;
                        case 5:
                            abyss = new DuplicatedCode(effectId,effectPosition);
                        break;
                        case 6:
                            abyss = new SecundaryEffects(effectId,effectPosition);
                        break;
                        case 7:
                            abyss = new BlueScreenError(effectId,effectPosition);
                        break;
                        case 8:
                            abyss = new InfiniteCicle(effectId,effectPosition);
                            break;
                        case 9:
                            abyss = new SegmentationFault(effectId,effectPosition);
                            break;
                    }
                    abysses.add(abyss);
                }
            }
            return  true;
        }
        return false;
    }

    public String getImagePng(int position) {
        if (position == boardSize) {
            return "glory.png";
        }

        for (Abyss abyss: abysses) {
            if(abyss.position == position){
                return abyss.getPng();
            }
        }

        for (Tool tool: tools) {
            if(tool.position == position){
                return tool.getPng();
            }
        }

        return null;
    }

    public String getTitle(int position){
        for (Abyss abyss: abysses) {
            if(abyss.position == position){
                return abyss.getName();
            }
        }

        for (Tool tool: tools) {
            if(tool.position == position){
                return tool.getName();
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
        int count = 0;
        for (Programmer programmer : players) {
            if(programmer.getStatusBool()){
                info.append(programmer.programmerTools()).append(" | ");
                count++;
            }
        }
        if (count == 0){
            return "No Players";
        }
        return info.substring(0,info.length()-3);
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
            return true;
        }
    }

    public String reactToAbyssOrTool(){
        int staticCurrent = currentPlayer;
        Programmer programmer = players.get(staticCurrent);
        currentPlayer = (currentPlayer + 1) % numberOfPlayers;
        plays++;
        for (Abyss abyss: abysses) {
            if(programmer.getPosition() == abyss.getPosition() && programmer.getStatusBool()){
                return abyss.effect(programmer,players);
            }
        }

        for (Tool tool: tools) {
            if(programmer.getPosition() == tool.getPosition() && programmer.getStatusBool()){
                tool.giveTool(programmer);
                return tool.getName();
            }
        }
        return null;
    }


    public boolean gameIsOver() {
        int contador=0;
        for (Programmer programmer : players) {
            if (programmer.getPosition() == boardSize) {
                return true;
            }
            if (!programmer.getStatusBool()){
                contador++;
            }
        }
        if(contador == numberOfPlayers - 1){
            return true;
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

