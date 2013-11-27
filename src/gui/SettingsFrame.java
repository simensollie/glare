package gui;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.MouseInputAdapter;

import bll.SettingsPicture;
import bll.ViewController;

import java.awt.event.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Andreas Johnstad
 */
@SuppressWarnings("serial")
final public class SettingsFrame extends JInternalFrame {

	private static final Dimension PREFERRED_SIZE = new Dimension(800, 600);

	// Variables
	private ViewController viewCtrl;
	private DisplaySettingsPanel dispset;
	private HashtagSettingsPanel hashpan;
	private TableSettingsPanel tablepanel;
	private JLabel backgroundImageLabel;
	private Component parent;
	private Dimension dim;
	private JButton saveButton;

	/**
	 * Creates new form SettingsFrame
	 */
	public SettingsFrame(ViewController viewCtrl) {
		super("Settings", false, // resizable
				true, // closable
				false, // maximizable
				false);// iconifiable'

		this.viewCtrl = viewCtrl;
		dim = Toolkit.getDefaultToolkit().getScreenSize();

		initComponents();
		initFrame();
		dim = Toolkit.getDefaultToolkit().getScreenSize();

		// Testing add hashtags to list
		hashpan.setHashtagList(viewCtrl.getHashtags());
		initFrame();

	}

	@Override
	public void setLocation(int x, int y) {
	}

	@Override
	public void setLocation(Point p) {
	}

	@Override
	public void setBounds(Rectangle r) {
	}

	public SettingsFrame() {
		super("Settings", false, // resizable
				true, // closable
				false, // maximizable
				false);// iconifiable';
		setJMenuBar(null);
		getJMenuBar();
		initComponents();
		initFrame();

	}

	public void initFrame() {

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setDoubleBuffered(true);
		setLayout(null);
		getContentPane().add(dispset);
		getContentPane().add(tablepanel);
		getContentPane().add(hashpan);
		getContentPane().add(backgroundImageLabel);
		// setFrameIcon(new
		// ImageIcon(getClass().getResource("/resource/img/settings.gif")));
	}

	/**
	 
	 */
	private void initComponents() {
		// TableSettingsPanel declaration
		tablepanel = new TableSettingsPanel();
		tablepanel.setBounds(300, 10, 510, 510);
		// HashSettingsPanel declaration
		hashpan = new HashtagSettingsPanel();
		hashpan.setBounds(50, -12, 180, 320);
		// DisplaySettingsPanel
		dispset = new DisplaySettingsPanel();
		dispset.setBounds(60, 360, 185, 70);
		// Background Image declaration
		backgroundImageLabel = new JLabel();

		backgroundImageLabel.setIcon(new ImageIcon(getClass().getResource(
				"/resource/img/backgr.jpg")));
		backgroundImageLabel.setIconTextGap(0);
		backgroundImageLabel.setPreferredSize(PREFERRED_SIZE);
		backgroundImageLabel.setBounds(0, 0, (int) PREFERRED_SIZE.getWidth(),
				(int) PREFERRED_SIZE.getHeight());

	}

	// Will be called when the frame is in the process of closing

	// Will call the necessary methods to update settings

	class closeAndSave extends InternalFrameAdapter {

		public void internalFrameClosing(InternalFrameEvent e) {
			System.out.println("internalFrameClosing");
			/*
			 * update,set displaysettings here
			 * 
			 * Call: updateDisplaySettings()
			 */
			updateDisplaySettings();
			/*
			 * update,set hashtags here
			 * 
			 * Call: updateHashtags
			 */
			updateHashtags();
			/*
			 * update thumnails
			 * 
			 * TODO : I have implemented a way to get the List of
			 * SettingsPictures from ImageTableModel
			 * 
			 * updateTableSettings()
			 */
			tablepanel.getImageTableModel().getTableModelData();

		}

	}

	public void updateTableSettings() {
		ImageTableModel imtabmod = tablepanel.getImageTableModel();
		// not implemented a method to get selected SettingsPicture object and
		// flag them yet.
		// This can be done here for test purposes
		// f.ex we want to flag picture in row 10 column 10:
		imtabmod.flagPicture(10, 10);

		// here is the data to send, picture in row 10 colum 10 should be
		// flagged

		List<List<SettingsPicture>> datatosend = imtabmod.getTableModelData();

		// TODO: iterate through the data
		// can be done with enchanced for loop, like this:
		/*
		 * for(List<SettingsPicture> row: datatosend){
		 * 
		 * for(SettingsPicture pic: row){
		 * 
		 * //TODO: send id of pic to DAL } }
		 */

	}

	// / send updated display settings

	// get updated displaysettings and set them
	public void updateDisplaySettings() {

		viewCtrl.setRandom(dispset.getViewMode());
		viewCtrl.setDisplayTime(dispset.getViewDelay());

	}

	// send updated hashtags to ViewCtrl
	public void updateHashtags() {
		Set<String> hashtagList = hashpan.getHashtagList();
		viewCtrl.updateHashtags(hashtagList);
	}

}
