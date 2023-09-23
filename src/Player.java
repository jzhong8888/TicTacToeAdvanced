public class Player {

    /**
     * This method checks whether a character is already in a string. If yes, then the character is not valid and return false.
     * @param takenChar
     * @param chosenChar
     * @return
     */
    public static boolean isCharValid(String takenChar, char chosenChar){
        if (takenChar.equals("")){
            return true;
        }
        for (int i = 0; i < takenChar.length(); i++){
            char ch = takenChar.charAt(i);
            if (ch == chosenChar){
                return false;
            }
        }
        return true;
    }
}
