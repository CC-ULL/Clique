package threeSAT;

import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Clause.
 */
public class Clause {

	/** The literals of the clause. */
	Vector<Literal> literals;

	/**
	 * Instantiates a new clause.
	 */
	Clause() {
		literals = new Vector<Literal>();
	}

	/**
	 * Adds a new literal to the clause.
	 *
	 * @param literal
	 *            the literal
	 */
	protected void addLiteral(Literal literal) {
		literals.add(literal);
		// We check if there's more than 3 literals, which would be illegal in a 3-SAT
		// problem
		if (literals.size() > 3) {
			throw new IllegalArgumentException("There can't be more than three literals per clause");
		}
	}

	/**
	 * Gets the literals.
	 *
	 * @return the literals
	 */
	public Vector<Literal> getLiterals() {
		return literals;
	}

	/**
	 * Checks the truth of the clause (unimplemented, would receive the solution's
	 * values and return a boolean with the result of their implementation in the
	 * clause)
	 *
	 * @return true, if successful
	 */
	public boolean checkTruth() {
		// Not implemented
		return false;
	}

	/**
	 * Converts the clause into a string, creating a clause of format (l1+l2+l3)
	 * 
	 * @return string
	 */
	public String toString() {
		String string = "(";
		for (Literal aux : literals)
			string += aux.toString() + "+";
		string = string.substring(0, string.length() - 1);
		string += ")";
		return string;
	}
}
