public class GameLogic {

    // Set the winning character to '~‘ as default at first, so there is no player wins in the very beginning of the game.
    GameLogic(){
        winningChar = '~';
    }

    public static char winningChar;

    public char getWinningChar(){
        return winningChar;
    }

    /**
     * This method checks whether the game is end. If there is a winner or the game is tie, then the game is end.
     * @param board
     * @param winningNumberInARow
     * @param lastPlaceRow
     * @param lastPlaceColumn
     * @return
     */
    public static boolean isGameEnd(char[][]board, int winningNumberInARow, int lastPlaceRow, int lastPlaceColumn){
        if (
                (hasARowWinner(board, winningNumberInARow)) ||
                (hasADiagonalWinner(board, winningNumberInARow, lastPlaceRow, lastPlaceColumn)) ||
                        (hasAColumnWinner(board, winningNumberInARow)) ||
                                //(hasAReverseDiagonalWinner(board, winningNumberInARow)) ||
                                        (isGameTie(board))
        ){
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * This method checks if the game is tie.
     * @param board
     * @return
     */
    public static boolean isGameTie(char[][]board){


        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == '~'){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * This method checks if there is a row winner.
     * @param board
     * @param winningNumberInARow
     * @return
     */
    public static boolean hasARowWinner(char[][]board, int winningNumberInARow){
        int counter = 0;
        for (int i = 0; i < board.length; i++){
            for (int j =0; j < board[i].length - 1; j++){

                if (board[i][j] == board[i][j+1] && board[i][j] != '~'){
                    counter++;
                }
                else{
                    counter = 0;
                }
                if (counter == winningNumberInARow - 1){

                    winningChar = board[i][j];
                    return true;
                }
            }
            counter = 0;
        }
        return false;

    }

    /**
     * This method checks if there is a column winner.
     * @param board
     * @param winningNumberInARow
     * @return
     */
    public static boolean hasAColumnWinner(char[][]board, int winningNumberInARow){
        int counter = 0;
        for (int j = 0; j < board.length; j++){
            for (int i =0; i < board[i].length - 1; i++){

                if (board[i][j] == board[i+1][j] && board[i][j] != '~'){
                    counter++;
                }
                else{
                    counter = 0;
                }
                if (counter == winningNumberInARow - 1){
                    //System.out.println("There is a column winner!");
                    winningChar = board[i][j];
                    return true;
                }
            }
            counter = 0;
        }
        return false;
    }

    /**
     * This method checks if there is a diagonal winner.
     * @param board
     * @param winningNumberInARow
     * @param lastPlacedRow
     * @param lastPlaceColumn
     * @return
     */
    public static boolean hasADiagonalWinner(char[][]board, int winningNumberInARow, int lastPlacedRow, int lastPlaceColumn){
        char lastChar = board[lastPlacedRow][lastPlaceColumn];
        int numOfRows = board.length;
        int numOfColumns = board[0].length;
        int counter = 0;

        // Check top-left to bottom right diagonal including the middle one.
        for (int i = 0; i <= numOfRows - winningNumberInARow; i++){
            int rowPos = i;
            for (int j = 0; j < numOfColumns && rowPos < numOfRows; j++){
                char currentChar = board[rowPos][j];
                if (currentChar == lastChar){
                    counter++;
                }
                else{
                    counter = 0;
                }

                if (counter == winningNumberInARow){
                    winningChar = lastChar;
                    return true;
                }
                rowPos++;
            }
        }

        // Check top-left to bottom right diagonal excluding the middle one.
        counter = 0;
        for (int j = 1; j <= numOfColumns - winningNumberInARow; j++){
            int columnPos = j;
            for (int i = 0; i < numOfRows && columnPos < numOfColumns; i++){
                char currentChar = board[i][columnPos];
                if (currentChar == lastChar){
                    counter++;
                }
                else{
                    counter = 0;
                }

                if (counter == winningNumberInARow){
                    winningChar = lastChar;
                    return true;
                }
                columnPos++;

            }
        }

        // Check bottom-left to top-right diagonal including the middle one.
        counter = 0;
        for (int i = numOfRows - 1; i >= numOfRows - winningNumberInARow; i--){
            int rowPos = i;
            for (int j = 0; j < numOfColumns && rowPos < numOfRows && rowPos >= 0; j++){
                char currentChar = board[rowPos][j];
                if (currentChar == lastChar){
                    counter++;
                }
                else{
                    counter = 0;
                }
                if (counter == winningNumberInARow){
                    winningChar = lastChar;
                    return true;
                }
                rowPos--;
            }
        }

        // Check bottom-left to top-right diagonal excluding the middle one.
        counter = 0;
        for (int j = 1; j < numOfColumns; j++){
            int columnPos = j;
            for (int i = numOfColumns - 1; i < numOfRows && columnPos < numOfColumns && columnPos >= 1; i--){
                int currentChar = board[i][columnPos];
                if (currentChar == lastChar){
                    counter++;
                }
                else{
                    counter = 0;
                }

                if (counter == winningNumberInARow){
                    winningChar = lastChar;
                    return true;
                }
                columnPos++;
            }
        }
        return false;
    }
}
