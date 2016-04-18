package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jannou Brohee
 */

public class ChoseBar extends JPanel {
	private  JTextArea mFile;
	private JButton button;
	private OpenFileWindow open ;
	public ChoseBar(OpenFileWindow _open){
		open = _open;
		mFile = new JTextArea("No file") ;
		mFile.setColumns(1);
		mFile.setEditable(false);
		open.setmFile(mFile);
		button = new JButton("chose your file");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				open.open();
			}
		});
		GridBagLayout gridbag = new GridBagLayout();
		this.setLayout(gridbag);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		gridbag.setConstraints(button, c);
		gridbag.setConstraints(mFile ,c);
		this.add(mFile);
		this.add(button);
	}
}
