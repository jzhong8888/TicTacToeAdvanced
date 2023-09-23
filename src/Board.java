public class Board {
    private char [][] board;

    // The row of the last placed piece.
    public static int lastPlaceRow;

    // The column of the last placed piece.
    public static int lastPlaceColumn;

    // Default Constructor
    public Board(){
        board = new char[0][0];
        lastPlaceRow = -1;
        lastPlaceColumn = -1;
    }

    // Constructor
    public Board(int row, int column){
        board = new char[row][column];
        createBoard(row, column);
        lastPlaceRow = -1;
        lastPlaceColumn = -1;
    }

    /**
     * This method will create a board given the number of rows and columns.
     * @param row
     * @param column
     */
    public void createBoard(int row, int column){
        board = new char[row][column];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = '~';
            }
        }
    }

    /**
     * This method will print the board.
     */
    public void printBoard(){
        System.out.print("  ");
        for (int i = 0; i < board.length; i++){
            System.out.print(i);
        }
        System.out.println();
        for (int i = 0; i < board.length; i++){
            System.out.print(i + " ");
            for (int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * This method will set the piece row and column in the location.
     * @param piece
     * @param row
     * @param column
     */
    public void setPiece(char piece, int row, int column){
            board[row][column] = piece;
            lastPlaceRow = row;
            lastPlaceColumn = column;
    }

    /**
     * This method will return the character at the specified row and column
     * @param row
     * @param column
     * @return
     */
    public char getPiece(int row, int column){
        return board[row][column];
    }

    /**
     * Accessor to access the board.
     * @param
     * @return
     */
    public char[][] getTheBoard(){
        return board;
    }
}
