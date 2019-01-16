package threeSAT;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class ThreeSAT.
 */
public class ThreeSAT {
	
	/** The literals. */
	Vector<Literal> literals;
	
	/** The clauses. */
	Vector<Clause> clauses;
	
	/**
	 * Instantiates a new three SAT.
	 */
	public ThreeSAT() {
		literals = new Vector<Literal>();
		clauses = new Vector<Clause>();
	}
	
	/**
	 * Adds a new literal.
	 *
	 * @param id the id
	 */
	public void addLiteral(String id) {
		literals.add(new Literal(id, false));
	}
	
	/**
	 * Adds a new clause.
	 *
	 * @param clause the clause
	 */
	public void addClause(String clause) {
		String clauseLiterals[] = clause.split("\\s+");
		Clause newClause = new Clause();
		for(String aux:clauseLiterals) {
			if(aux.charAt(0) == '!')
				newClause.addLiteral(new Literal(aux.substring(1), true));
			else
				newClause.addLiteral(new Literal(aux, false));
		}
		clauses.add(newClause);
		
	}
	
	/**
	 * Gets the clauses.
	 *
	 * @return the clauses
	 */
	public Vector<Clause> getClauses(){
		return clauses;
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
	 * Converts the 3-SAT problem into a string
	 * 
	 * @return string
	 */
	public String toString() {
		String string = "";
		for(Clause aux : clauses)
			string += aux.toString()+"*";
		string = string.substring(0,string.length()-1);
		return string;
	}
}
