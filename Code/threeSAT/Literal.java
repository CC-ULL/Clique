package threeSAT;

// TODO: Auto-generated Javadoc
/**
 * The Class Literal.
 */
public class Literal {
	
	/** The negated variable, which indicates whether the literal is negated or not. */
	private boolean negated;
	
	/** The id, which never includes the negation. */
	private String id;
	
	
	/**
	 * Instantiates a new literal.
	 *
	 * @param id the id
	 * @param negated whether it's negated or not
	 */
	Literal(String id, boolean negated){
		this.id = id;
		this.negated = negated;
	}
	
	/**
	 * Checks if it's negated.
	 *
	 * @return true, if it's negated
	 */
	public boolean isNegated() {
		return negated;
	}
	
	/**
	 * Gets the id (without negation).
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Converts the literal into a string, joining the negation and the id
	 * 
	 * @return string
	 */
	public String toString() {
		if(negated)
			return "!"+id;
		else return id;
	}
}
