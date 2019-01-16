package threeSAT;

// TODO: Auto-generated Javadoc
/**
 * The Class Literal.
 */
public class Literal {
	
	/** The negated. */
	private boolean negated;
	
	/** The id. */
	private String id;
	
	
	/**
	 * Instantiates a new literal.
	 *
	 * @param id the id
	 * @param negated the negated
	 */
	Literal(String id, boolean negated){
		this.id = id;
		this.negated = negated;
	}
	
	/**
	 * Checks if is negated.
	 *
	 * @return true, if is negated
	 */
	public boolean isNegated() {
		return negated;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Converts the literal into a string
	 * 
	 * @return string
	 */
	public String toString() {
		if(negated)
			return "!"+id;
		else return id;
	}
}
