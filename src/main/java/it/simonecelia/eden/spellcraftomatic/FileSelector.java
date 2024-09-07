package it.simonecelia.eden.spellcraftomatic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileSelector extends JFrame {

	private JTextArea textArea;

	public FileSelector() {
		// Configura la finestra principale
		setTitle("Seleziona un file TXT");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Area di testo per visualizzare il contenuto del file
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);

		// Aggiungi un pulsante per aprire il file chooser
		JButton openButton = new JButton("Apri File TXT");
		openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				apriFile();
			}
		});
		add(openButton, BorderLayout.SOUTH);
	}

	private void apriFile() {
		// Crea un JFileChooser per selezionare il file
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fileChooser.showOpenDialog(this);

		// Se l'utente seleziona un file
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			// Leggi il file e mostra il contenuto nell'area di testo
			try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
				String line;
				textArea.setText(""); // Pulisce l'area di testo
				while ((line = reader.readLine()) != null) {
					textArea.append(line + "\n");
				}
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Errore nella lettura del file.", "Errore", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static void main(String[] args) {
		// Esegui l'applicazione
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FileSelector().setVisible(true);
			}
		});
	}
}
