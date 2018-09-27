import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class AStarSearch{
    Queue<GameState> openSet;
    Set<GameState> closedSet;

    //Comparator for the GameState
    public Comparator<GameState> stateComparator = new Comparator<GameState>() {
        @Override
        public int compare(GameState o1, GameState o2) {
            if (o1.cost - o2.cost != 0)
                return o1.cost - o2.cost;
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
        int steps = 0;

        if (state != null) {
          openSet.add(state);
          
        }

        while (goalCheck != 1) {
            
        }
        System.out.println("goalCheckTimes " + goalCheck);
        System.out.println("maxOPENSize " + maxOPEN);
        System.out.println("maxCLOSEDSize " + maxCLOSED);
        System.out.println("steps " + steps);
        return state;
    }    

    // add more methods for the A* search if necessary
}

