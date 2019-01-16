package threeSAT;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Clause.
 */
public class Clause {
	
	/** The literals. */
	Vector<Literal> literals;
	
	/**
	 * Instantiates a new clause.
	 */
	Clause(){
		literals = new Vector<Literal>();
	}
	
	/**
	 * Adds the literal.
	 *
	 * @param literal the literal
	 */
	protected void addLiteral(Literal literal) {
		literals.add(literal);
		if(literals.size()>3) {
			throw new IllegalArgumentException("There can't be more than three literals per clause");
		}
	}
	
	/**
	 * Gets the literals.
	 *
	 * @return the literals
	 */
	public Vector<Literal> getLiterals(){
		return literals;
	}
	
	/**
	 * Check truth.
	 *
	 * @return true, if successful
	 */
	public boolean checkTruth() {
		//Not implemented
		return false;
	}
	
	/**
	 * Converts the clause into a string
	 * 
	 * @return string
	 */
	public String toString() {
		String string = "(";
		for(Literal aux: literals)
			string+=aux.toString()+"+";
		string = string.substring(0, string.length()-1);
		string += ")";
		return string;
	}
}
