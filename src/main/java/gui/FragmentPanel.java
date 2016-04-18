package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author Jannou Brohee
 */
public class FragmentPanel extends JPanel{
	protected JPanel dismain;
	private OpenFileWindow open;
    public FragmentPanel(){
		setLayout(new BorderLayout(0,0));
		// haut,gauche,bas,droit
		setBorder(new EmptyBorder(new Insets(0, 60, 20, 0)));
		dismain = new JPanel();
		dismain.setLayout(new GridLayout(3,1));
		add(dismain,BorderLayout.CENTER);

		JButton button = new JButton("Choisir un fichier");
		/*button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = null;
				open = new OpenFileWindow();
				open.setVisible(true);
			}
		});*/
		dismain.add(button);
		//ajouter 2 boutons
		//ajouter du text pour le log
    }
}
