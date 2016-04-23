package gui;

import overlap.Arc;
import overlap.HamiltonPath;
import overlap.Overlap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * fenetre principale
 * @author Jannou Brohee
 */
public class MainFrame {
	private  static JPanel mainContentPane ;
	private static OpenFileWindow open;
	private static JTextArea log;
	private static JTextArea mFile;
	/**
	 *
	 * @param args parametre min fenetre principale
     */
	public static void main(String[] args) {
		initializeMain();

		//MainFrame.run2(run1());
	}

	/**
	 * initialisation fenetre principale
	 */
	private static void initializeMain() {
		final JFrame frame = new JFrame("BioInformatique");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,600);
		frame.setResizable(false);
		mainContentPane = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		mainContentPane.setLayout(gridbag);
		open = new OpenFileWindow();
		ChoseBar cbar = new ChoseBar(open);
		log = new JTextArea("log", 30,1);
		log.setBackground(Color.BLACK);
		//log.setDisabledTextColor(Color.white);
		log.setForeground(Color.white);
		log.setEditable(false);

		JButton button2 = new JButton("Start ");
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t = new Thread(){
					@Override
					public void run() {
						MainFrame.run2(run1());
					}
				};
				if(open.getPath() != null)
					t.start();
				else {
					JOptionPane.showMessageDialog(frame, "Please select a file ", "WARNING", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;

		c.gridwidth = GridBagConstraints.REMAINDER; //end row
		gridbag.setConstraints(cbar, c);
		mainContentPane.add(cbar);

		c.weightx = 0.0;
		gridbag.setConstraints(button2, c);
		mainContentPane.add(button2);
		c.gridwidth = GridBagConstraints.REMAINDER; //end row
		c.gridheight = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(log, c);
		mainContentPane.add(log);
		frame.setContentPane(mainContentPane);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void log(String _log){
		log.insert(_log,log.getText().length());
	}

	public static Overlap run1(){
		String logg = "\n";
		Overlap graph;
		graph = Overlap.build(open.getPath());
		//graph = Overlap.build("/home/nanabaskint/Git/BioInfo_project/datas/Collections/10000/collection1.fasta");
		int  n = graph.getSommets().size();

		logg+=" \n"+"> # fragments d'ADN  n = "+n;
		logg+=" \n"+"> # alignements a calculer => (n*(n-1))*4 = "+(n*(n-1))*4;
		logg+="\n> # alignements à prendre theorie : "+(n-1);
		logg+="\n------------------------------------------------";
		//System.out.println(logg);
		log(logg);
		return graph;
	}

	public static void run2(Overlap graph){
		int  n = graph.getSommets().size();
		String logg = "";
		long debut = System.currentTimeMillis();
		long debut2 = System.currentTimeMillis();
		graph.run();
		logg+="\n Temps calcule des alignements : "+(double)(System.currentTimeMillis()-debut)/1000+"s";
		logg+="\n> # alignements manquants  : " +(((n*(n-1))*4)- graph.getArcs().size() );
		log(logg);
		logg = "";
		debut = System.currentTimeMillis();
		graph.sort();
		logg+="\n Temps calcul trie des alignements : "+(double)(System.currentTimeMillis()-debut)/1000+"s";
		log(logg);
		logg = "";
		debut = System.currentTimeMillis();
		ArrayList<Arc> arcs = HamiltonPath.greedy(graph);
		logg+="\n Temps calcul chemin Hamiltonien  : "+(double)(System.currentTimeMillis()-debut)/1000+"s";
		logg+="\n Temps total : "+(double)(System.currentTimeMillis()-debut2)/1000+"s";
		log(logg);
		logg = "";
		//for(Arc arc : arcs)
		//	System.out.print(arc.getSource() + " - " );
		//System.out.println(arcs.get(arcs.size()-1).getDestination());
		logg+="\n> # alignements selectionés : "+ arcs.size();
		//System.out.println(logg);
		log(logg);
	}
}
