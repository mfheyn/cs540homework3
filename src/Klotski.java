import java.util.List;

public class Klotski {   
    public static void printNextStates(GameState s) {
        List<GameState> states = s.getNextStates();
        for (GameState state: states) {
            state.printBoard();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        if (args == null || args.length < 21) {
            return;
        }
        int flag = Integer.parseInt(args[0]);
        int[][] board = new int[5][4];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = Integer.parseInt(args[i * 4 + j + 1]);
            }                
        }        
        GameState s = new GameState(board, 0);

        if (flag == 100) {
            printNextStates(s);
            return;
        }

        AStarSearch search = new AStarSearch();        
        search.aStarSearch(flag, s);  
        
    }

}
