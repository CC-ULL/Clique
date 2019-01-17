package clique;

import java.util.Vector;

import threeSAT.Literal;
import threeSAT.Clause;
import threeSAT.ThreeSAT;

// TODO: Auto-generated Javadoc
/**
 * The Class Clique, which represents a Clique problem.
 */
public class Clique {

	/** The node groups. Each of them will represent a clause of a 3-SAT problem */
	Vector<Vector<Literal>> nodeGroups;

	/**
	 * The literal index. A vector in which the index of all the nodes involved in a
	 * potential Clique are stored through resolution
	 */
	Vector<Integer> literalIndex;

	/** A matrix which indicates whether two nodes are connected or not. */
	boolean[][] connected;

	/**
	 * The solution. A vector with all the nodes/literals involved in a successful
	 * Clique
	 */
	Vector<Literal> solution;

	/**
	 * Instantiates a new Clique problem.
	 *
	 * @param threeSAT
	 *            the 3-SAT problem we want to solve
	 */
	public Clique(ThreeSAT threeSAT) {
		nodeGroups = new Vector<Vector<Literal>>();
		Vector<Clause> aux = threeSAT.getClauses();
		for (Clause clause : aux) {
			nodeGroups.add(clause.getLiterals());
		}
	}

	/**
	 * Solve. Method used for calculating a solution to this Clique problem.
	 *
	 * @return the vector of literals/nodes involved in a successful Clique
	 */
	public Vector<Literal> solve() {
		solution = new Vector<Literal>();
		connected = new boolean[3 * nodeGroups.size()][3 * nodeGroups.size()];
		for (int m = 0; m < connected.length; m++)
			for (int n = 0; n < connected.length; n++)
				connected[m][n] = false;

		/*
		 * After proper initialization of all the variables, the connections matrix is
		 * created. If two nodes have different IDs, they're connected. If two nodes
		 * with the same ID share the same negation value (true/false), they're
		 * connected. Each node is compared with all the nodes of all the node groups in
		 * front of it.
		 */
		for (int i = 0; i < nodeGroups.size(); i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = i + 1; k < nodeGroups.size(); k++) {
					for (int l = 0; l < 3; l++) {
						if (!nodeGroups.get(i).get(j).getId().equals(nodeGroups.get(k).get(l).getId())) {
							connected[i * 3 + j][k * 3 + l] = true;
							connected[k * 3 + l][i * 3 + j] = true;
						} else if (nodeGroups.get(i).get(j).isNegated() == nodeGroups.get(k).get(l).isNegated()) {
							connected[i * 3 + j][k * 3 + l] = true;
							connected[k * 3 + l][i * 3 + j] = true;
						} else {
							connected[i * 3 + j][k * 3 + l] = false;
							connected[k * 3 + l][i * 3 + j] = false;
						}
					}
				}
			}
		}

		// We try to find a successful Clique starting from the 3 different nodes of the
		// first node group. If no Clique is found starting with one, it's tried again
		// with the next
		literalIndex = new Vector<Integer>();
		for (int i = 0; i < 3; i++) {
			literalIndex.add(i);
			if (deepDive())
				break;
			literalIndex.remove(0);
		}
		// All nodes of the solution found are compiled in a vector of literals
		for (int i = 0; i < nodeGroups.size(); i++)
			solution.add(
					nodeGroups.get((literalIndex.get(i) - literalIndex.get(i) % 3) / 3).get(literalIndex.get(i) % 3));
		return solution;
	}

	/**
	 * Deep dive. A recursive function to navigate through the node groups searching
	 * for a successful clique.
	 *
	 * @return true, if successful
	 */
	private boolean deepDive() {
		boolean nice = true;
		int lastElement = literalIndex.lastElement();
		// Look for a nice node in the next node group
		for (int i = lastElement + 3 - lastElement % 3; i < lastElement + 6 - lastElement % 3; i++) {
			nice = true;
			/* Check if the actual node connects with all the previously 
			 * selected nodes, and discard it if a connection is missing */
			for (Integer aux : literalIndex) {
				if (!connected[aux][i]) {
					nice = false;
					break;
				}
			}
			/* If the node looks nice, it's added to the potential solution */
			if (nice) {
				literalIndex.add(i);
				/* If it's from the last group of nodes, a successful
				 * Clique has been found*/
				if (i + 3 >= connected.length) {
					return true;
				/* If it's not, the function is called again to work
				 * with the next group of nodes */
				} else if (deepDive()) {
					return true;
				/* If, after calling itself, no Clique is found, the last
				 * node is discarded for checking the next one*/
				} else {
					literalIndex.remove(literalIndex.size() - 1);
				}
			}
		}
		/* If none of the nodes of the group are valid, the program returns to the
		 * last node group and discards the selected node */
		return false;
	}

	/**
	 * Gets the connections.
	 *
	 * @return the connections
	 */
	public boolean[][] getConnections() {
		return connected;
	}

	/**
	 * Gets the node groups.
	 *
	 * @return the node groups
	 */
	public Vector<Vector<Literal>> getNodeGroups() {
		return nodeGroups;
	}
}
