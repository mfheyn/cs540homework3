import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class AStarSearch{
  static boolean debug = false;
  Queue<GameState> openSet;
  Set<GameState> closedSet;

  //Comparator for the GameState
  public Comparator<GameState> stateComparator = new Comparator<GameState>() {
    @Override
    public int compare(GameState o1, GameState o2) {
      if (o1.heuristicCost + o1.steps - o2.heuristicCost - o1.steps != 0)
        return o1.heuristicCost + o1.steps - o2.heuristicCost - o2.steps;
      else
        return o1.getStateID().compareTo(o2.getStateID());
    }
  };   

  // print the states of board in open set
  public void printOpenList(int flag, GameState state) {
    System.out.println("OPEN");


  }

  public void printClosedList(int flag, GameState state) {
    System.out.println("CLOSED");

  }

  // implement the A* search
  public GameState aStarSearch(int flag, GameState state) {   
    // feel free to using other data structures if necessary
    openSet = new PriorityQueue<>(stateComparator);
    closedSet = new HashSet<>();
    int goalCheck = 0;
    int maxOPEN = -1;
    int maxCLOSED = -1;
    int iteration = 1;
    int doIts = 10;

    if (state != null) {
      openSet.add(state);
    }

    while (doIts-- >= 0) {
      if (debug && iteration == 3) {
        @SuppressWarnings("unused")
        int x = 5;
      }
      //step 1 - pull out state from openSet
      if (openSet.isEmpty())
        return null;
      GameState topPrio = openSet.remove();
      List<GameState> gameStates = topPrio.getNextStates();
      for (GameState gameState : gameStates) {
        if (!closedSetContains(gameState)) {
          openSet.add(gameState);
        }
      }

      System.out.println("iteration " + iteration++);
      System.out.println(topPrio.getStateID());
      topPrio.printBoard();
      topPrio.printCosts();
      if (topPrio.parent != null) {
        System.out.println(topPrio.parent.getStateID());
      }
      else
        System.out.println("null");
      goalCheck++;
      if (topPrio.goalCheck()) {
        return topPrio;
      }
      if (!closedSetContains(topPrio))
        closedSet.add(topPrio);
      // step 2 - print open
      System.out.println("OPEN");
      for (GameState gameState : openSet) {
        //id
        //board
        //f g h
        //parent
        System.out.println(gameState.getStateID());
        gameState.printBoard();
        gameState.printCosts();
        if (gameState.parent != null) {
          System.out.println(gameState.parent.getStateID());
        }
      }
      System.out.println("CLOSED");
      for (GameState closedState : closedSet) {
        System.out.println(closedState.getStateID());
        closedState.printBoard();
        closedState.printCosts();
        if (closedState.parent != null) {
          System.out.println(closedState.parent.getStateID());
        }
      }
    }
    return null;
  }

  public boolean closedSetContains(GameState gameState) {
    for (GameState closedState : closedSet) {
      if (closedState.equals(gameState))
        return true;
    }
    return false;
  }
}

// add more methods for the A* search if necessary

