package pt.ulusofona.lp2.deisiGreatGame;

 public class InvalidInitialBoardException extends Exception {

    private String message;
    InvalidInitialBoardException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

     public Boolean isInvalidAbyss(){
        return true;
    }

     public Boolean isInvalidTool(){
        return true;
    }
     public String getTypeId(){
        return "";
    }
}
