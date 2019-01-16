package clique;

import java.util.Vector;

import threeSAT.Literal;
import threeSAT.Clause;
import threeSAT.ThreeSAT;

// TODO: Auto-generated Javadoc
/**
 * The Class Clique.
 */
public class Clique {
	
	/** The node groups. */
	Vector<Vector<Literal>> nodeGroups;
	
	/** The literal index. */
	Vector<Integer> literalIndex;
	
	/** The connected. */
	boolean[][] connected;
	
	/** The solution. */
	Vector<Literal> solution;

	/**
	 * Instantiates a new clique.
	 *
	 * @param threeSAT the three SAT
	 */
	public Clique(ThreeSAT threeSAT) {
		nodeGroups = new Vector<Vector<Literal>>();
		Vector<Clause> aux = threeSAT.getClauses();
		for (Clause clause : aux) {
			nodeGroups.add(clause.getLiterals());
		}
	}

	/**
	 * Solve.
	 *
	 * @return the vector
	 */
	public Vector<Literal> solve() {
		solution = new Vector<Literal>();
		connected = new boolean[3 * nodeGroups.size()][3 * nodeGroups.size()];
		for (int m = 0; m < connected.length; m++)
			for (int n = 0; n < connected.length; n++)
				connected[m][n] = false;

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

		literalIndex = new Vector<Integer>();
		for (int i = 0; i < 3; i++) {
			literalIndex.add(i);
			if (deepDive())
				break;
		}
		for (int i = 0; i < nodeGroups.size(); i++)
			solution.add(
					nodeGroups.get((literalIndex.get(i) - literalIndex.get(i) % 3) / 3).get(literalIndex.get(i) % 3));

		return solution;
	}

	/**
	 * Deep dive.
	 *
	 * @return true, if successful
	 */
	private boolean deepDive() {
		boolean nice = true;
		for (int i = literalIndex.lastElement() + 3 - literalIndex.lastElement() % 3; i < literalIndex.lastElement() + 6
				- literalIndex.lastElement() % 3; i++) {
			nice = true;
			for (Integer aux : literalIndex) {
				if (!connected[aux][i]) {
					nice = false;
					break;
				}
			}
			if (nice) {
				literalIndex.add(i);
				if (i + 3 >= connected.length)
					return true;
				else if (deepDive()) {
					return true;
				} else
					literalIndex.remove(literalIndex.size() - 1);
			}
		}
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
