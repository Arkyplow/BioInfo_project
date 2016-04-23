package gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;

/**
 *
 * @author Jannou Brohee
 *
 */
public class OpenFileWindow extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<String> paths = new ArrayList<String>();
	private static String path = null;
	private JTextArea mFile;
	/**
	 * Create the frame.
	 */
	public OpenFileWindow() {


	}
	public void setmFile(JTextArea _mFile){
		mFile= _mFile;
	}
	public void open(){
		Initialize();
	}
	/**
	 * Get the maps list.
	 *
	 * @return The maps list.
	 */


	/**
	 * Get the path to the map.
	 *
	 * @return The path.
	 */
	public String getPath() {

		return path;
	}

	/**
	 * Initialize file window.
	 */
	private void Initialize() {
		setType(Type.POPUP);
		this.setVisible(false);
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		// ouvrire map
		paths = new ArrayList<String>();
		chooser.setMultiSelectionEnabled(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				".fasta", "fasta");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(getParent());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path = chooser.getSelectedFile().getAbsolutePath();
			mFile.setText(path.substring(path.lastIndexOf("/")+1));
		}

	}
}
