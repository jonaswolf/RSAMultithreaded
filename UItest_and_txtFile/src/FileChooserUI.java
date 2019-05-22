import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

public class FileChooserUI extends JPanel implements ActionListener {

    //newline für den Zeilenwechsel
    static private final String newline = "\n";
    private JButton openButton, saveButton;
    private JTextArea log;
    private JFileChooser fc;

    private FileChooserUI() {
        super(new BorderLayout());

        //Erstellt zuerst den Log, da die ActionListener darauf referenzieren müssen
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //FileChooser erstellen
        fc = new JFileChooser();

        //Erlaube nur TextFiles
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TextFiles", "txt", "text");
        fc.setFileFilter(filter);

        //Open-Button erstellen
        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);

        //Save-Button erstellen
        saveButton = new JButton("Save a File...");
        saveButton.addActionListener(this);

        //Buttons in ein extra Panel einlagern
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        //Buttons und Log dem Panel hinzufügen
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {

        //Open-Button Aktion
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(FileChooserUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //Datei öffnen und auslesen
                log.append("Opening: " + file.getName() + "." + newline);
                try(FileReader myFileReader = new FileReader(file); BufferedReader myLineReader = new BufferedReader(myFileReader)) {
                    String line;
                    while((line = myLineReader.readLine()) != null) {
                        log.setText(log.getText() + newline + line);
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

            //Save-Button Aktion
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(FileChooserUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //Datei speichern und beschriften
                log.append("Saving TextFile: " + file.getName() + newline);
                String filename = fc.getSelectedFile().getName();
                //Prüfen, ob Dateiendung bereits mit angegeben wurde
                final String EXTENSION = ".txt";
                String filepath = fc.getSelectedFile().toString();
                if(!filename.endsWith(EXTENSION)) {
                    filename = filepath + EXTENSION;
                }
                //String filepath = fc.getSelectedFile().toString();
                File newFile = new File(/*filepath, */ filename);
                if(!newFile.exists()) {
                    try {
                        newFile.createNewFile();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                try (FileWriter myFileWriter = new FileWriter(newFile)) {
                        log.setText(log.getText() + newline);
                    myFileWriter.write(log.getText());
                } catch (IOException ioe3) {
                    ioe3.printStackTrace();
                }
            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    /*
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Fenster erstellen
        JFrame frame = new JFrame("FileChooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Content hinzufügen
        frame.add(new FileChooserUI());

        //Fenster anzeigen
        frame.pack();
        frame.setVisible(true);

        //Lass den Frame in Bildschirmmitte erscheinen
        //WICHTIG: Das hier muss nach .pack und .setVisible stehen!
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            //Abschalten der BOLD-Schrift für dieses Theme
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            createAndShowGUI();
        });
    }
}
