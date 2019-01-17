import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;

import clique.Clique;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import threeSAT.Literal;
import threeSAT.ThreeSAT;

// TODO: Auto-generated Javadoc
/**
 * The Class Demo.
 */
public class Demo {

	/**
	 * The main method.
	 * 
	 * Here the input file will be read and processed to create a 3-SAT instance.
	 * Then, after printing the 3-SAT problem, we'll translate it into a Clique problem, solve it, and print it.
	 * With calculations over, the program proceeds to represent the problem's complete graph.
	 *
	 * @param args the arguments. [0] should be the path to the input file.
	 * @throws NumberFormatException Signals that a number format exception has occurred.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		ThreeSAT threeSAT = new ThreeSAT();

		FileReader input = new FileReader(args[0]);
		BufferedReader reader = new BufferedReader(input);
		// We read how many clauses there are in order to read them smoothly
		int nClauses = Integer.parseInt(reader.readLine());
		for (int i = 0; i < nClauses; i++)
			threeSAT.addClause(reader.readLine());
		reader.close();

		System.out.printf(threeSAT.toString());

		Clique clique = new Clique(threeSAT);
		System.out.printf("\n" + clique.solve().toString());

		createAndShowGUI(threeSAT, clique);
	}

	/**
	 * Creates and shows the GUI where the graph will be represented.
	 *
	 * @param threeSAT the three SAT
	 * @param clique the clique
	 */
	private static void createAndShowGUI(ThreeSAT threeSAT, Clique clique) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Graph<String, String> g = createGraph(clique);

		Dimension size = new Dimension(800, 800);
		VisualizationViewer<String, String> vv = new VisualizationViewer<String, String>(
				new FRLayout<String, String>(g, size));
		DefaultModalGraphMouse<String, Double> graphMouse = new DefaultModalGraphMouse<String, Double>();
		vv.setGraphMouse(graphMouse);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

		f.getContentPane().add(vv);
		f.setSize(size);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	/**
	 * Creates the graph with help of the JUNG library.
	 *
	 * @param clique the clique
	 * @return the graph
	 */
	public static Graph<String, String> createGraph(Clique clique) {
		Graph<String, String> g = new SparseMultigraph<String, String>();
		Vector<Vector<Literal>> nodeGroups = clique.getNodeGroups();
		int numVertices = clique.getNodeGroups().size() * 3;
		
		for (int i = 0; i < numVertices; i++) {
			g.addVertex("" + ((i - i % 3)/3) + "." + nodeGroups.get((i - i % 3)/3).get(i % 3).toString());
		}

		boolean[][] connections = clique.getConnections();
		for (int i = 0; i < numVertices; i++) {
			for (int j = i + 1; j < numVertices; j++) {
				if (connections[i][j])
					g.addEdge(""+i+"."+j, "" + ((i - i % 3)/3) + "." + nodeGroups.get((i - i % 3)/3).get(i % 3).toString(),
							"" + ((j - j % 3)/3) + "." + nodeGroups.get((j - j % 3)/3).get(j % 3).toString());
			}
		}

		return g;
	}
}
