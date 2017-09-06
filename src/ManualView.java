import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

/**
 * ManualView: Fenster in welchem die Regeln des Spiels sowie eine kurze Spielanleitung
 * zu finden sind.
 * Enthaelt zudem den ActionListener zur Rueckkehr ins Hauptmenue.
 * @author kiki
 *
 */
public class ManualView extends JFrame {

	Model model = new Model();
	ManualButtonPanel mbpanel = new ManualButtonPanel();
	ManualPane mpane = new ManualPane();
	
	public ManualView(){
		super("Schiffe Versenken - Regeln und Kurzanleitung");
		fensterGenerierenManual();
	}

	private void fensterGenerierenManual() {
		//Grundlayout des ManualViews
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		//Hinzufuegen des Buttons ueber das unten erstellte JPanel
		mbpanel.setBorder(new EmptyBorder(10,10,30,10));
		add(mbpanel, BorderLayout.SOUTH);
		//JScrollPane mit JTextArea wird gesetzt
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.getVerticalScrollBar().setValue(1);
		scrollpane.setViewportView(mpane);
		scrollpane.setBorder(new EmptyBorder(30, 30, 30, 30));
		scrollpane.setBackground(null);
		add(scrollpane, BorderLayout.CENTER);
		setVisible(true);
	}
	
	/**
	 * ButtonPanel fuer den Button mit dem man zurueck ins Hauptmenue kommt.
	 * Heißt der Button wird implementiert und einem JPanel mit GridBagLayout hinzugefuegt
	 * @author kiki
	 *
	 */
	class ManualButtonPanel extends JPanel{
		
		private JButton[][] button = new JButton[1][1];
		
		public ManualButtonPanel(){
			
			//Layout
			GridBagLayout mgblayout = new GridBagLayout();
			setLayout(mgblayout);
			GridBagConstraints mconstraints = new GridBagConstraints();
			
			mconstraints.gridy = 0;
			mconstraints.gridx = 0;
			
			//Erstellen des eigentlichen Buttons, setzten der Groesse, 
			//sowie hinzufuegen des ActionListeners
			button[0][0] = new JButton();
			button[0][0].setText("Zurück zum Hauptmenü");
			button[0][0].setPreferredSize(new Dimension(200, 60));
			add(button[0][0], mconstraints);
			button[0][0].addActionListener (new BackToMenuListener());
		}

	}
	
	/**
	 * BackToMenuListener: Der ActionListener mit den Anweisungen was passieren soll wenn der
	 * "Zurueck" Button gedrückt wurde.
	 * @author kiki
	 *
	 */
	class BackToMenuListener implements ActionListener{
		
		private MenuView menuview;

		public BackToMenuListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent backtomenu){
			this.menuview = new MenuView();
			dispose();
		}
	}
	/**
	 * Notiz für die Programmierer: \n ist der Zeilenumbruch (alt+Shift+7)
	 * Die ManualPane ist der Bereich in welchem die Regeln und eine kurze
	 * Anleitung zum Spiel stehen.
	 * Die ManualPane ist ein JTextPane welches vom Spieler nicht bearbeitet werden kann.
	 * Hier muss eventuell an der Übersicht nochmal was gemacht werden, entweder die ganze Klasse
	 * oder wenigstens den Text auslagern
	 * @author kiki
	 *
	 */
	class ManualPane extends JTextPane{
		
		public ManualPane(){
			setSize(new Dimension(700, 500));
			setText("Regeln und Kurzanleitung\n\n");
			setEditable(false);
		}
		
	}
}
