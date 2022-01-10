package pt.ulusofona.lp2.deisiGreatGame;

public class InvalidInitialBoardException extends Exception {

    private String message;
    private boolean isInvalidAbyss;
    private boolean isInvalidTool;
    private String typeID;
    InvalidInitialBoardException(String message) {
        this.message = message;
    }

    public InvalidInitialBoardException(String message, boolean isInvalidAbyss, boolean isInvalidTool, String typeID) {
        this.message = message;
        this.isInvalidAbyss = isInvalidAbyss;
        this.isInvalidTool = isInvalidTool;
        this.typeID = typeID;
    }

    public String getMessage(){
        return message;
    }

    public Boolean isInvalidAbyss(){
        return isInvalidAbyss;
    }

    public Boolean isInvalidTool(){
        return isInvalidTool;
    }
    public String getTypeId(){
        return typeID;
    }
}