package aima.core.environment.eightpuzzle;

import aima.core.search.framework.HeuristicFunction;
import aima.core.util.datastructure.XYLocation;

/**
 * @author Ravi Mohan
 * 
 */
public class ManhattanHeuristicFunction implements HeuristicFunction {

	public double h(Object state) {
		EightPuzzleBoard board = (EightPuzzleBoard) state;
		EightPuzzleBoard goalboard = EightPuzzleBoard.goal;
		int retVal = 0;
		for (int i = 1; i < 9; i++) {
			XYLocation loc = board.getLocationOf(i);
			XYLocation locGoal = goalboard.getLocationOf(i);
			retVal += evaluateManhattanDistanceOf(i, loc, locGoal);
		}
		return retVal;

	}

	public int evaluateManhattanDistanceOf(int i, XYLocation loc, XYLocation locGoal ) {
		int retVal = -1;
		int xpos = loc.getXCoOrdinate();
		int ypos = loc.getYCoOrdinate();
		int xgoal = locGoal.getXCoOrdinate();
		int ygoal = locGoal.getYCoOrdinate();
		switch (i) {

		case 1:
			retVal = Math.abs(xpos - xgoal) + Math.abs(ypos - ygoal);
			break;
		case 2:
			retVal = Math.abs(xpos - xgoal) + Math.abs(ypos - ygoal);
			break;
		case 3:
			retVal = Math.abs(xpos - xgoal) + Math.abs(ypos - ygoal);
			break;
		case 4:
			retVal = Math.abs(xpos - xgoal) + Math.abs(ypos - ygoal);
			break;
		case 5:
			retVal = Math.abs(xpos - xgoal) + Math.abs(ypos - ygoal);
			break;
		case 6:
			retVal = Math.abs(xpos - xgoal) + Math.abs(ypos - ygoal);
			break;
		case 7:
			retVal = Math.abs(xpos - xgoal) + Math.abs(ypos - ygoal);
			break;
		case 8:
			retVal = Math.abs(xpos - xgoal) + Math.abs(ypos - ygoal);
			break;

		}
		return retVal;
	}
}