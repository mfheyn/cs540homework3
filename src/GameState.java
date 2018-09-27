import java.util.ArrayList;
import java.util.List;

class GameState {      
  public int[][] board = new int[5][4];
  public GameState parent = null;
  public int cost = 0;
  public int steps = 0;

  public GameState(int [][] inputBoard, int steps) {
    for(int i = 0; i < 5; i++)
      for(int j = 0; j < 4; j++)
        this.board[i][j] = inputBoard[i][j];
    this.steps = steps;
  }

  public GameState(GameState gameState, int addCost) {
    for(int i = 0; i < 5; i++)
      for(int j = 0; j < 4; j++)
        this.board[i][j] = gameState.board[i][j];
    this.parent = gameState;
    this.cost += addCost;
    this.steps += 1;
  }

  public GameState(int [][] inputBoard) {
    for(int i = 0; i < 5; i++)
      for(int j = 0; j < 4; j++)
        this.board[i][j] = inputBoard[i][j];
  }


  // TODO: get all successors and return them in sorted order
      public List<GameState> getNextStates() {
        List<GameState> successors = new ArrayList<>();        

        ArrayList<ZeroLocation> zeroLocations = new ArrayList();
        for (int i = 0; i < board.length; i++) {
          for (int j = 0; j < board[j].length; j++) {
            if (board[i][j] == 0) {
              zeroLocations.add(new ZeroLocation(i,j));
            }
          }
        }


        for (ZeroLocation zeroLoc : zeroLocations) {
          ArrayList<GameState> potentialNextStates = new ArrayList();
          Integer up,down,left,right;
//          up = null;
//          down = null;
//          left = null;
//          right = null;
//          try {
//            up = board[zeroLoc.row + 1][zeroLoc.column];
//          } catch(Exception e) {};
//          try {
//            right = board[zeroLoc.row][zeroLoc.column + 1];
//          } catch(Exception e) {};
//          try {
//            down = board[zeroLoc.row - 1][zeroLoc.column];
//          } catch(Exception e) {};
//          try {
//            left = board[zeroLoc.row][zeroLoc.column -1];
//          } catch(Exception e) {};
//
//          if (up != null) {
//            GameState potentialState = getUpSuccessor(up, zeroLoc, potentialNextStates);
//            if (potentialState == null) {
//              potentialNextStates.add(potentialState);
//            }
//          }
//          if (down != null) {
//
//          }
//          if(right != null) {
//
//          }
//          if(left != null) {
//
//          }
        }


        return successors;
      }

      public GameState getSuccessor(ZeroLocation zeroLoc, char direction) {
        Integer number;
        GameState potentialState = null;

        /* =============================================================UP============================================================================ */
        if (direction == 'u') {
          number = getValueFromBoard(zeroLoc.row - 1, zeroLoc.column);
          if (number == null) return null;
          Integer storedRight, storedLeft, storedUpRight, storedUpLeft, storedUpUp, storedUpUpRight, storedUpUpLeft;
          storedRight = getValueFromBoard(zeroLoc.row, zeroLoc.column + 1);
          // try the left side
          storedLeft = getValueFromBoard(zeroLoc.row, zeroLoc.column - 1);

          storedUpRight = getValueFromBoard(zeroLoc.row - 1, zeroLoc.column + 1);
          storedUpLeft = getValueFromBoard(zeroLoc.row - 1, zeroLoc.column - 1);

          storedUpUp = getValueFromBoard(zeroLoc.row - 2, zeroLoc.column);
          storedUpUpRight = getValueFromBoard(zeroLoc.row - 2, zeroLoc.column + 1);
          storedUpUpLeft = getValueFromBoard(zeroLoc.row - 2, zeroLoc.column);
          switch(number)
          {
            case 2:
              potentialState = new GameState(this, 0);
              potentialState.board[zeroLoc.row][zeroLoc.column] = 2;
              potentialState.board[zeroLoc.row - 2][zeroLoc.column] = 0;
              return potentialState;
            case 1:
              // FIRST: check if 0's are also to the right or to the left
              // try the right side      

              if(storedRight != null && storedUpRight != null  && storedUpUp != null && storedUpUpRight != null && storedRight == 0 && storedUpRight == 1 && storedUpUp == 1 && storedUpUpRight == 1) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 1;
                potentialState.board[zeroLoc.row][zeroLoc.column + 1] = 1;
                potentialState.board[zeroLoc.row - 2][zeroLoc.column] = 0;
                potentialState.board[zeroLoc.row - 2][zeroLoc.column + 1] = 0;
                return potentialState;
              }

              if(storedLeft != null && storedUpLeft != null && storedUpUp != null && storedUpUpLeft != null && storedLeft == 0 && storedUpLeft == 1 && storedUpUp == 1 && storedUpUpLeft == 1) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 1;
                potentialState.board[zeroLoc.row][zeroLoc.column - 1] = 1;
                potentialState.board[zeroLoc.row - 2][zeroLoc.column] = 0;
                potentialState.board[zeroLoc.row - 2][zeroLoc.column - 1] = 0;
                return potentialState;
              }

              return null;
            case 3:             
              if(storedRight != null && storedUpRight != null && storedRight == 0 && storedUpRight == 3) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 3;
                potentialState.board[zeroLoc.row][zeroLoc.column + 1] = 3;
                potentialState.board[zeroLoc.row - 1][zeroLoc.column] = 0;
                potentialState.board[zeroLoc.row - 1][zeroLoc.column + 1] = 0;
                return potentialState;
              }

              if(storedLeft != null && storedUpLeft != null && storedLeft == 0 && storedUpLeft == 3) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 3;
                potentialState.board[zeroLoc.row][zeroLoc.column - 1] = 3;
                potentialState.board[zeroLoc.row - 1][zeroLoc.column] = 0;
                potentialState.board[zeroLoc.row - 1][zeroLoc.column - 1] = 0;
                return potentialState;
              }
              return null;
          }
        }
        /* =============================================================DOWN============================================================================ */
        if (direction == 'd') {
          number = getValueFromBoard(zeroLoc.row + 1, zeroLoc.column);
          if (number == null) return null;
          Integer storedLeft, storedRight, storedDownRight, storedDownLeft;
          storedLeft = getValueFromBoard(zeroLoc.row, zeroLoc.column - 1);
          storedRight = getValueFromBoard(zeroLoc.row, zeroLoc.column + 1);
          storedDownRight = getValueFromBoard(zeroLoc.row + 1, zeroLoc.column + 1);
          storedDownLeft = getValueFromBoard(zeroLoc.row + 1, zeroLoc.column - 1);

          switch(number)
          {
            case 1:
              //check right first
              if (storedRight != null && storedDownRight != null && storedRight == 0 && storedDownRight == 1) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 1;
                potentialState.board[zeroLoc.row][zeroLoc.column + 1] = 1;
                potentialState.board[zeroLoc.row + 2][zeroLoc.column] = 0;
                potentialState.board[zeroLoc.row + 2][zeroLoc.column + 1] = 0;
                return potentialState;
              }
              //check left next
              if (storedLeft != null && storedDownLeft != null && storedLeft == 0 && storedDownLeft == 1) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 1;
                potentialState.board[zeroLoc.row][zeroLoc.column - 1] = 1;
                potentialState.board[zeroLoc.row + 2][zeroLoc.column] = 0;
                potentialState.board[zeroLoc.row + 2][zeroLoc.column - 1] = 0;
                return potentialState;
              }

              return null;
            case 2:
              potentialState = new GameState(this, 0);
              potentialState.board[zeroLoc.row][zeroLoc.column] = 2;
              potentialState.board[zeroLoc.row + 2][zeroLoc.column] = 0;
              return potentialState;
            case 3:
              if (storedRight != null && storedDownRight != null && storedRight == 0 && storedDownRight == 1) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 3;
                potentialState.board[zeroLoc.row][zeroLoc.column + 1] = 3;
                potentialState.board[zeroLoc.row + 1][zeroLoc.column] = 0;
                potentialState.board[zeroLoc.row + 1][zeroLoc.column + 1] = 0;
                return potentialState;
              }
              
              //check left next
              if (storedLeft != null && storedDownLeft != null && storedLeft == 0 && storedDownLeft == 1) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 3;
                potentialState.board[zeroLoc.row][zeroLoc.column - 1] = 3;
                potentialState.board[zeroLoc.row + 1][zeroLoc.column] = 0;
                potentialState.board[zeroLoc.row + 1][zeroLoc.column - 1] = 0;
                return potentialState;
              }
              return null;
          }
        }
        /* =============================================================RIGHT============================================================================ */
        if (direction == 'r') {
          number = getValueFromBoard(zeroLoc.row, zeroLoc.column + 1);
          if (number == null) return null;
          Integer storedDown, storedUp, storedDownRight, storedUpRight;
          storedDown = getValueFromBoard(zeroLoc.row + 1, zeroLoc.column);
          storedUp = getValueFromBoard(zeroLoc.row - 1, zeroLoc.column);
          storedDownRight = getValueFromBoard(zeroLoc.row + 1, zeroLoc.column + 1);
          storedUpRight = getValueFromBoard(zeroLoc.row - 1, zeroLoc.column + 1);

          switch(number) {
            case 1:
              //first check down
              if (storedDown != null && storedDownRight != null && storedDown == 0 && storedDownRight == 1) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 1;
                potentialState.board[zeroLoc.row + 1][zeroLoc.column] = 1;
                potentialState.board[zeroLoc.row][zeroLoc.column + 2] = 0;
                potentialState.board[zeroLoc.row + 1][zeroLoc.column + 2] = 0;
                return potentialState;
              }
              //then check up
              if (storedUp != null && storedUpRight != null && storedUp == 0 && storedUpRight == 1) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 1;
                potentialState.board[zeroLoc.row - 1][zeroLoc.column] = 1;
                potentialState.board[zeroLoc.row][zeroLoc.column + 2] = 0;
                potentialState.board[zeroLoc.row - 1][zeroLoc.column + 2] = 0;
                return potentialState;
              }
              return null;
            case 2:
              if (storedDown != null && storedDownRight != null && storedDown == 0 && storedDownRight == 1) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 2;
                potentialState.board[zeroLoc.row + 1][zeroLoc.column] = 2;
                potentialState.board[zeroLoc.row][zeroLoc.column + 1] = 0;
                potentialState.board[zeroLoc.row + 1][zeroLoc.column + 1] = 0;
                return potentialState;
              }
              if (storedUp != null && storedUpRight != null && storedUp == 0 && storedUpRight == 1) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 2;
                potentialState.board[zeroLoc.row - 1][zeroLoc.column] = 2;
                potentialState.board[zeroLoc.row][zeroLoc.column + 1] = 0;
                potentialState.board[zeroLoc.row - 1][zeroLoc.column + 1] = 0;
                return potentialState;
              }
              return null;
            case 3:
              potentialState = new GameState(this, 0);
              potentialState.board[zeroLoc.row][zeroLoc.column] = 3;
              potentialState.board[zeroLoc.row][zeroLoc.column + 2] = 0;
              return potentialState;
          }
        }
        /* =============================================================LEFT============================================================================ */
        if (direction == 'l') {
          number = getValueFromBoard(zeroLoc.row, zeroLoc.column - 1);
          if (number == null) return null;
          Integer storedDown, storedUp, storedDownLeft, storedUpLeft;
          storedDown = getValueFromBoard(zeroLoc.row + 1, zeroLoc.column);
          storedUp = getValueFromBoard(zeroLoc.row - 1, zeroLoc.column);
          storedDownLeft = getValueFromBoard(zeroLoc.row + 1, zeroLoc.column - 1);
          storedUpLeft = getValueFromBoard(zeroLoc.row - 1, zeroLoc.column - 1);

          switch(number) {
            case 1:
              //first check down
              if (storedDown != null && storedDownLeft != null && storedDown == 0 && storedDownLeft == 1) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 1;
                potentialState.board[zeroLoc.row + 1][zeroLoc.column] = 1;
                potentialState.board[zeroLoc.row][zeroLoc.column - 2] = 0;
                potentialState.board[zeroLoc.row + 1][zeroLoc.column - 2] = 0;
                return potentialState;
              }
              //then check up
              if (storedUp != null && storedUpLeft != null && storedUp == 0 && storedUpLeft == 1) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 1;
                potentialState.board[zeroLoc.row - 1][zeroLoc.column] = 1;
                potentialState.board[zeroLoc.row][zeroLoc.column - 2] = 0;
                potentialState.board[zeroLoc.row - 1][zeroLoc.column - 2] = 0;
                return potentialState;
              }
              return null;
            case 2:
              if (storedDown != null && storedDownLeft != null && storedDown == 0 && storedDownLeft == 2) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 2;
                potentialState.board[zeroLoc.row + 1][zeroLoc.column] = 2;
                potentialState.board[zeroLoc.row][zeroLoc.column - 1] = 0;
                potentialState.board[zeroLoc.row + 1][zeroLoc.column - 1] = 0;
                return potentialState;
              }
              if (storedUp != null && storedUpLeft != null && storedUp == 0 && storedUpLeft == 1) {
                potentialState = new GameState(this, 0);
                potentialState.board[zeroLoc.row][zeroLoc.column] = 2;
                potentialState.board[zeroLoc.row - 1][zeroLoc.column] = 2;
                potentialState.board[zeroLoc.row][zeroLoc.column - 1] = 0;
                potentialState.board[zeroLoc.row - 1][zeroLoc.column - 1] = 0;
                return potentialState;
              }
              return null;
            case 3:
              potentialState = new GameState(this, 0);
              potentialState.board[zeroLoc.row][zeroLoc.column] = 3;
              potentialState.board[zeroLoc.row][zeroLoc.column - 2] = 0;
              return potentialState;
          }
        }
        
        return potentialState;
      }


      public Integer getValueFromBoard(int row, int column) {
        Integer value = null;

        //      if (direction == 'u')
        //      {
        //        try {
        //          value = board[row + 1][column];
        //        } catch (Exception e) {};
        //        return value;
        //      }
        //      else if (direction == 'd')
        //      {
        //        try {
        //          value = board[row - 1][column];
        //        } catch (Exception e) {};
        //        return value;
        //      }
        //      else if (direction == 'r')
        //      {
        //        try {
        //          value = board[row][column + 1];
        //        } catch (Exception e) {};
        //        return value;
        //      }
        //      else if (direction == 'l')
        //      {
        //        try {
        //          value = board[row][column - 1];
        //        } catch (Exception e) {};
        //        return value;
        //      }
        //      //if the direction isn't specified, then return the current location
        //      else
        //      {
        //        try {
        //          value = board[row][column];
        //        } catch (Exception e) {};
        //        return value;
        //      }
        try {
          value = board[row][column];
        } catch (Exception e) {};

        return value;
      }

      // return the 20-digit number as ID
      public String getStateID() {  
        String s = "";
        for (int i = 0; i < 5; i++) {
          for (int j = 0; j < 4; j++)
            s += this.board[i][j];
        }
        return s;
      }

      public void printBoard() {
        for (int i = 0; i < 5; i++) {
          for (int j = 0; j < 4; j++)
            System.out.print(this.board[i][j]);
          System.out.println();
        }
      }

      // check whether the current state is the goal
      public boolean goalCheck() {        

        return false;
      }

      // add new methods for the GameState if necessary        

}