import java.awt.*;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {

        // Setting the number of players.
        boolean correctNumberOfPlayers = false;
        int numberOfPlayers = 0;
        while (!correctNumberOfPlayers) {
            Scanner scan = new Scanner(System.in);

            System.out.println("Please enter number of players: 3 to 10");
            if (scan.hasNextInt()) {
                numberOfPlayers = scan.nextInt();
                if (numberOfPlayers > 2 && numberOfPlayers < 11) {
                    correctNumberOfPlayers = true;
                    break;
                }
            }
        }
        System.out.println("Number of players chosen is " + numberOfPlayers);


        // Setting the winning rule.
        int winningNumberInARow = 0;
        boolean correctNumberOfWinningNumberInARow = false;
        while (!correctNumberOfWinningNumberInARow) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter winning number in a row: 3 to " + (numberOfPlayers + 1));
            if (scan.hasNextInt()) {
                winningNumberInARow = scan.nextInt();
                if (winningNumberInARow > 2 && winningNumberInARow <= numberOfPlayers + 1) {
                    correctNumberOfWinningNumberInARow = true;
                    break;
                }
            }
        }
        System.out.println("Number for winning in a row is " + winningNumberInARow);

        //Print out the board.
        Board tictactoe = new Board(numberOfPlayers + 1, numberOfPlayers + 1);
        tictactoe.printBoard();

        //Ask players to choose their character and save it into a String.
        String playersChar = "";
        for (int i = 0; i < numberOfPlayers; i++){
            char chosenChar = ' ';
            boolean correctChar = false;
            while(!correctChar){
                Scanner scan = new Scanner(System.in);
                System.out.println("Hello, Player #" + (i + 1) + ". Please choose a character to represent you.");
                if (scan.hasNext()){
                    chosenChar = scan.next().trim().charAt(0);
                    // Check if the character has already been chosen by other users. Also, the character cannot be '~' or ‘ ’。
                    if (Player.isCharValid(playersChar, chosenChar) && chosenChar != '~' && chosenChar != ' '){
                        correctChar = true;
                        break;
                    }
                    else{
                        System.out.println("This character has been chosen already. Please choose another one.");
                    }
                }
                else{
                    System.out.println("This character is not valid. Please choose another one.");
                }
            }
            playersChar += chosenChar;
        }

        // Print the board again.
        tictactoe.printBoard();


        // Ask players to place their pieces and check if the place is available. If not, ask player to place again.
        do{
            for (int i = 0; i < numberOfPlayers; i++) {
                // Set the row and column to -1 as default first and change them base on players' choice.
                int row = -1;
                int column = -1;
                char piece;
                boolean correctRow = false;
                boolean correctColumn = false;
                while (!correctRow || !correctColumn){
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Hello, Player #" + (i + 1) + ". Please enter the row and column you want to set your piece.(separate row and column with a space)");
                    if (scan.hasNextInt()){
                        row = scan.nextInt();
                        if (scan.hasNextInt()){
                            column = scan.nextInt();
                            // Check whether the chosen place is out of bound.
                            if (row >= 0 && row <= numberOfPlayers && column >= 0 && column <= numberOfPlayers){
                                // Check whether the chosen place has already been taken. If it is '~', then it hasn't been taken yet.
                                if (tictactoe.getPiece(row, column) == '~'){
                                    correctRow = true;
                                    correctColumn = true;
                                    break;
                                }
                                else{
                                    tictactoe.printBoard();
                                    System.out.println("This place has already been taken. Please re-enter the row and column you want to set your piece.");
                                }
                            }
                            else{
                                tictactoe.printBoard();
                                System.out.println("This is not a valid move. Please re-enter the row and column you want to set your piece.");
                            }
                        }
                        else{
                            tictactoe.printBoard();
                            System.out.println("This is not a valid move. Please re-enter the row and column you want to set your piece.");
                        }
                    }
                    else{
                        tictactoe.printBoard();
                        System.out.println("This is not a valid move. Please re-enter the row and column you want to set your piece.");
                    }

                }

                // After validating the place, then set the piece.
                piece = playersChar.charAt(i);
                tictactoe.setPiece(piece, row, column);

                // Print the board again.
                tictactoe.printBoard();

                // Check if there is a row winner after each move. If yes, then announce the winner and break out the loop.
                if (
                        GameLogic.hasARowWinner(tictactoe.getTheBoard(), winningNumberInARow)
                ) {
                    char winChar = GameLogic.winningChar;
                    int index = playersChar.indexOf(winChar);
                    int winner = index + 1;
                    System.out.println("There is a row winner!");
                    System.out.println("The winner is: Player #" + winner + ". Congratulations!");
                    System.out.println("The game is finished.");
                    break;
                }

                // Check if there is a column winner after each move. If yes, then announce the winner and break out the loop.
                if (
                    GameLogic.hasAColumnWinner(tictactoe.getTheBoard(), winningNumberInARow)
                ) {
                    char winChar = GameLogic.winningChar;
                    int index = playersChar.indexOf(winChar);
                    int winner = index + 1;
                    System.out.println("There is a column winner!");
                    System.out.println("The winner is: Player #" + winner + ". Congratulations!");
                    System.out.println("The game is finished.");
                    break;
                }

                // Check if there is a diagonal winner after each move. If yes, then announce the winner and break out the loop.
                if (
                    GameLogic.hasADiagonalWinner(tictactoe.getTheBoard(), winningNumberInARow, row, column)
                ) {
                    char winChar = GameLogic.winningChar;
                    int index = playersChar.indexOf(winChar);
                    int winner = index + 1;
                    System.out.println("There is a diagonal winner!");
                    System.out.println("The winner is: Player #" + winner + ". Congratulations!");
                    System.out.println("The game is finished.");
                    break;
                }

                // Check if the game is tie. If yes, then announce it and break out the loop.
                if (GameLogic.isGameTie(tictactoe.getTheBoard())){
                    System.out.println("The game ended in a tie!");
                    System.out.println("The game is finished.");
                    break;
                }
            }
            // Continue to ask players to set pieces until there is a winner or the game is tie.
        } while (!GameLogic.isGameEnd(tictactoe.getTheBoard(), winningNumberInARow, Board.lastPlaceRow, Board.lastPlaceColumn));

    }
}





