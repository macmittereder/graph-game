package svc.com.graphgame.GameFiles;

/*
* Created 3/20/2016 by Mac Mittereder
* This is the game rules class
*/

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.browse.MediaBrowser;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class GameRules extends View{

    /*
    * TODO: Add rectangle to either restart game or select another map (probably just select another map)
    * TODO: Add functionality to rectangle to go to the MapList.class via touch_down
    * TODO: Can draw text to show how many turns have happened, etc.
    * TODO: Create better description of class
    */

    //Create global variables and import classes
    PebbleNode lastNodeSelected = null, lastNodeMovedFrom, lastNodeMovedTo, goalNode;
    ArrayList<ConnectNodes> listOfConnectedNodes;
    ArrayList<PebbleNode> listOfPebbleNodes;
<<<<<<< HEAD
    boolean attackersTurn;
=======
    boolean attackersTurn = true;
>>>>>>> origin/master
    int screenX, screenY;
    Paint paint;
    Context context;

    //Default initialization
    public GameRules(Context context) {
        super(context);
        this.context = context;
        listOfConnectedNodes = new ArrayList<>();
        listOfPebbleNodes = new ArrayList<>();
        paint = new Paint();
        attackersTurn = true;
    }

    //This method gets executed after it's called to draw the item to your 'canvas' or layout
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //displays to the screen either attacker or defender's turn
        paint.setTextSize(75f);
        paint.setColor(Color.GREEN);
        String turnText;
        if (checkLose())
            turnText = "";
        else
            turnText = attackersTurn ? "Attacker's Turn" : "Defender's Turn";
        canvas.drawText(turnText, (screenX * 3/8),(screenY * 7/8), paint);

        //Checks if the goal node has a pebble on it
        if (goalNode.getPebbles() > 0){
            paint.setColor(Color.WHITE);
            canvas.drawRect(0, 0, screenX, screenY, paint);
            paint.setColor(Color.BLACK);
            paint.setTextSize(120f);
            canvas.drawText("Attacker Wins!", (screenX / 4) , (screenY / 2), paint);
        }
        //Checks if there are more than 1 pebble on a node at any time
        if(checkLose()) {
            paint.setColor(Color.WHITE);
            canvas.drawRect(0, 0, screenX, screenY, paint);
            paint.setColor(Color.BLACK);
            paint.setTextSize(120f);
            canvas.drawText("Defender Wins!", (screenX / 4) , (screenY / 2), paint);
        }
        invalidate(); //Keeps drawing on the screen so if the values above change it will redraw instantly
    }

    //Checks if the pebbles are allowed to move via list of connected nodes
    //If node1 from connected nodes is either the start or destination and node 2 from
    //connected nodes is either the start or destination then the move is valid so return true
    //otherwise return false
    public boolean checkPebbleMove(PebbleNode start, PebbleNode destination) {
        if (start.getPebbles() < 1)
            return false;
        for (ConnectNodes cntNode: listOfConnectedNodes)
            if ( (cntNode.node1 == start || cntNode.node1 == destination) &&
                    (cntNode.node2 == start || cntNode.node2 == destination))
                return true;
        return false;
    }

    //Checks if the defender tries to reverse the attacker's last move. If the start of the
    //defender's move is the destination the the attacker's last move, and the destination is the
    //start of the attacker's last move, this is illegal. Returns true if the move is illegal.
    public boolean checkIllegalDefenderMove(PebbleNode start, PebbleNode destination) {
<<<<<<< HEAD
        if (start == lastNodeMovedTo && destination == lastNodeMovedFrom && !(attackersTurn)) {
            Toast.makeText(context, "Defender Can't Move Pebbles Back", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            swapTurns();
            return false;
        }
=======
        if (start == lastNodeMovedTo && destination == lastNodeMovedFrom && !(attackersTurn))
            return true;
        return false;
>>>>>>> origin/master
    }

    //For each PebbleNode in the list of nodes check if any of them have 2 or more pebbles return false (attacker hasn't lost)
    //if none of them have 2 or more pebbles then returns true (attacker has lost)
    public boolean checkLose() {
        for (PebbleNode node: listOfPebbleNodes)
            if (node.getPebbles() >= 2)
                return false;
        return true;
    }

    //This is the touch handling class
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
        }
        return super.onTouchEvent(event);
    }

    //Adder method
    public void addConnectedNodes(ConnectNodes connectedNodes) {
        listOfConnectedNodes.add(connectedNodes);
    }

    //Adder method
    public void addPebbleNodes(PebbleNode pebbleNode) {
        listOfPebbleNodes.add(pebbleNode);
    }

    //Setter method
    public void setScreenSize(int x, int y) {
        screenX = x;
        screenY = y;
    }

    //Setter method
    public void setGoalNode(PebbleNode goalNode) {
        this.goalNode = goalNode;
    }

    //Getter method
    public PebbleNode getLastNode() {
        return lastNodeSelected;
    }

    //Setter methods
    public void setLastNode(PebbleNode nodeSelected) {
        lastNodeSelected = nodeSelected;
    }

<<<<<<< HEAD
    //Getter method
    public ArrayList<ConnectNodes> getListOfConnectedNodes() {
        return listOfConnectedNodes;
    }

    //Getter method
    public ArrayList<PebbleNode> getListOfPebbleNodes(){
        return  listOfPebbleNodes;
    }

    //Setter method
    public void setLastMoveFrom(PebbleNode node) { lastNodeMovedFrom = node; }

    //Setter method
    public void setLastMoveTo(PebbleNode node) { lastNodeMovedTo = node; }

    //Swaps whose move it is
    public void swapTurns() {
        attackersTurn = !(attackersTurn);
    }
=======
    public void setLastMoveFrom(PebbleNode node) { lastNodeMovedFrom = node; }

    public void setLastMoveTo(PebbleNode node) { lastNodeMovedTo = node; }

    //Swaps whose move it is
    public void swapTurns() { attackersTurn = !(attackersTurn); }
>>>>>>> origin/master
}
