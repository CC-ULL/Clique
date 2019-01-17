package threeSAT;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class ThreeSAT, which represents a 3-SAT problem.
 */
public class ThreeSAT {
	
	/** The literals involved in the problem. */
	Vector<Literal> literals;
	
	/** The clauses of the problem. */
	Vector<Clause> clauses;
	
	/**
	 * Instantiates a new 3-SAT problem.
	 */
	public ThreeSAT() {
		literals = new Vector<Literal>();
		clauses = new Vector<Clause>();
	}
	
	/**
	 * Adds a new literal to the problem.
	 *
	 * @param id the id
	 */
	public void addLiteral(String id) {
		literals.add(new Literal(id, false));
	}
	
	/**
	 * Adds a new clause to the problem.
	 *
	 * @param clause the new clause
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
	 * Gets the clauses of the problem.
	 *
	 * @return the clauses
	 */
	public Vector<Clause> getClauses(){
		return clauses;
	}
	
	/**
	 * Gets the literals involved in this problem.
	 * (Not used in the Clique demonstration)
	 * 
	 * @return the literals
	 */
	public Vector<Literal> getLiterals(){
		return literals;
	}
	
	/**
	 * Converts the 3-SAT problem into a string of format (l1+l2+l3)*(l2+l3+l1)...
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
