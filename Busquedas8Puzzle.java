package aima.gui.demo.search;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctionFactory;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest;
import aima.core.environment.eightpuzzle.ManhattanHeuristicFunction;
import aima.core.environment.eightpuzzle.MisplacedTilleHeuristicFunction;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.util.math.Biseccion;

/**
 * @author Ravi Mohan
 * 
 */

public class Busquedas8Puzzle {

	private static long [] [] resultadosNG = new long [4][24];
	private static double [] [] resultadosb = new double [4][24];
	private static int depthmin = 2;
	private static int depthmax = 24;

	public static void main(String[] args) throws Exception {
		experimento(new BreadthFirstSearch(),depthmin, depthmax, 0);
		experimento(new IterativeDeepeningSearch(),depthmin, 10, 1);
		experimento(new AStarSearch(new GraphSearch(),new ManhattanHeuristicFunction()),depthmin, depthmax, 3);
		experimento(new AStarSearch(new GraphSearch(),new MisplacedTilleHeuristicFunction()),depthmin, depthmax, 2);
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("||    ||          Nodos Generados              ||                   b*                  ||");
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("||   d||    BFS  |    IDS  |  A*h(1) |  A*h(2) ||    BFS  |    IDS  |  A*h(1) |  A*h(2) ||");
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------------------------");
		for(int i=depthmin; i<depthmax; i++){
			System.out.printf("||  %2d||%7d  |%7d  |%7d  |%7d  ||%7.2f  |%7.2f  |%7.2f  |%7.2f  ||\n", i,
					resultadosNG[0][i],resultadosNG[1][i],resultadosNG[2][i],resultadosNG[3][i],
					resultadosb[0][i],resultadosb[1][i],resultadosb[2][i],resultadosb[3][i]);
		}

	}

	public static void experimento (Search search, int dmin, int dmax, int indiceTablas) throws Exception{
		for (int i=dmin; i<dmax; i++){
			System.out.println(i);
			int nodos = 0;
			for (int j=0; j<100; j++){
				EightPuzzleBoard ini = new EightPuzzleBoard(GenerateInitialEightPuzzleBoard.randomIni());
				EightPuzzleBoard fini = new EightPuzzleBoard(GenerateInitialEightPuzzleBoard.random(i,ini));
				fini.setGoalState(fini);
				Problem problem = new Problem(ini, EightPuzzleFunctionFactory
						.getActionsFunction(), EightPuzzleFunctionFactory
						.getResultFunction(), fini);
				SearchAgent agent = new SearchAgent(problem, search);
				nodos += Integer.parseInt(agent.getInstrumentation().getProperty("nodesGenerated"));
			}
			resultadosNG[indiceTablas][i]=nodos/100;
			Biseccion bisec = new Biseccion((nodos/100), i, 0.0001);
			resultadosb[indiceTablas][i]=bisec.b();
		}
	}

	/*
	 * Método executeActions.
	 * Dada una lista de acciones realizados sobre un problema, dibuja
	 * los pasos realizados para resolver el problema citado.
	 */
	public static void executeActions (List<Action> actions, Problem problem){

		Object initialState = problem.getInitialState();
		ResultFunction resultFunction = problem.getResultFunction();

		System.out.println("GOAL STATE");
		System.out.println((EightPuzzleGoalTest.getGoaldState()));
		Object state = initialState;
		System.out.println("INITIAL STATE");
		System.out.println(state);

		for (Action action : actions){
			System.out.println(action.toString());
			state = resultFunction.result(state, action);
			System.out.println(state);
			System.out.println("- - -");
		}

	}

}