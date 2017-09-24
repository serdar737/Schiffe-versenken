import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
	
	public ManualView() {
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
		mpane.setCaretPosition(0);
		scrollpane.getVerticalScrollBar().setValue(1);
		scrollpane.setViewportView(mpane);
		scrollpane.setBorder(new EmptyBorder(30, 30, 30, 30));
		scrollpane.setBackground(null);
		add(scrollpane, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void setBTMListener(ActionListener btm) {
		
		mbpanel.button[0][0].addActionListener (btm);
	}
	
	/**
	 * ButtonPanel fuer den Button mit dem man zurueck ins Hauptmenue kommt.
	 * Heißt der Button wird implementiert und einem JPanel mit GridBagLayout hinzugefuegt
	 * @author kiki
	 *
	 */
	class ManualButtonPanel extends JPanel {
		
		private JButton[][] button = new JButton[1][1];
		
		public ManualButtonPanel() {
			
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
			
		}

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
	class ManualPane extends JTextPane {
		
		public ManualPane() {
		
			setSize(new Dimension(700, 500));
			setEditable(false);
			setText("Anleitung\n\n"
					+"Schiffe Versenken ist ein beliebtes Strategiespiel für 2 Spieler, bei dem der Spieler gewinnt, der zuerst alle Schiffe auf dem Spielfeld des Gegners versenkt hat.\n"
					+"Nach dem Starten des Spieles wird die Größe des Spielfeldes festgelegt und anschließend werden die Namen für\nSpieler 1 und Spieler 2 eingegeben.\n"
					+"Anschließend erscheint ein Fenster, bei dem der Spieler 1 seine Schiffe auf seinem Feld platzieren muss.\nEs gibt ein Fünferschiff, zwei Viererschiffe, drei Dreierschiffe und vier Zweierschiffe.\n"
					+"Diese dürfen frei auf dem Spielfeld vertikal und horizontal platziert werden, jedoch dürfen keine zwei Schiffe unmittelbar aneinander gesetzt werden.\nSchiffe diagonal übereinanderzusetzen ist jedoch erlaubt.\n"
					+"Nachdem der Spieler 1 alle seine Schiffe platziert hat, muss er auf „Fertig“ klicken.\n"
					+"Dann erscheint ein kleines Fenster mit der Meldung, dass jetzt Spieler 2 an der Reihe ist.\n"
					+"Diese Übergangsbildschirme kommen nach jedem Spielerwechsel, damit der jeweils andere Spieler nicht auf das Feld\ndes Anderen schauen kann.\n"
					+"Nachdem dieser die Meldung mit „Weiter“ geschlossen hat, ist er nun damit an der Reihe, seine Schiffe zu platzieren.\nWenn auch er alle seine Schiffe gesetzt hat und auf „Fertig“ geklickt hat, erscheint erneut ein Fenster mit der Meldung,\ndass der Spieler 1 an der Reihe ist.\n"
					+"Dieser bekommt jetzt, nachdem er die Meldung mit „Weiter“ geschlossen hat, ein Fenster mit zwei Spielfeldern zu sehen,\nvon denen eins größer und das andere kleiner ist.\n"
					+"Das größere Spielfeld ist das des Gegners, mit dem der aktuelle Spieler interagieren muss, indem er auf die einzelnen Felder klickt, um die Schiffe des Gegners zu treffen.\n"
					+"Trifft der erste Schuss ins Leere, erscheint an der Stelle ein „O“ und der Spieler kann keine Schüsse mehr setzen.\nDann muss auf „Weiter“ geklickt werden und der Spieler 2 ist an der Reihe.\n"
					+"Sollte man jedoch ein Schiffsteil des Gegners treffen, dann erscheint an der Stelle ein „X“ und man darf nochmal schießen. Wenn alle Teile eines Schiffes getroffen wurden, werden die „X“ alle rot gefärbt und das Schiff ist versenkt. Der Spieler darf solange weiterschießen, bis er ins Leere trifft und an der Stelle ein „O“ erscheint.\n"
					+"Mit dem kleineren Bildschirm kann nicht interagiert werden, da dieser nur dazu dient, um dem Spieler, der an der Reihe ist, zu zeigen, wo er seine eigenen Schiffe platziert hatte (blaues „X“),  wo der Gegner bereits\nein Schuss gesetzt hat (wenn ein  Schiffsteil getroffen, dann schwarzes „X“, wenn ins Leere geschossen, dann „O“) und welche Schiffe vom Gegner bereits versenkt wurden (rotes „X“).\n"
					+"Gewonnen hat der Spieler, der als Erster alle Schiffe des Gegners versenkt hat.\n\n"
					+"Dieses Spiel wurde als Prüfungsleistung für das Modul Programmiersprachen II im Jahre 2017 von Kirsten Anika Peine und\nSerdar Sarsar erstellt.");
		}
	}