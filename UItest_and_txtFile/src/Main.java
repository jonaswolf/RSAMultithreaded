import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends JPanel implements ActionListener {

    private JButton go;
    private JFileChooser chooser;
    private String choosertitle;

    public static void main(String[] args) {

        //Text für Dokument und Methode starten
        String[] text = {"Zeile 1", "2. Auflistungsitem hahaha", "===============", "Und ich bin die letzte Zeile"};
        createWriteFile(text);

        //UI Pfad auswählen
        JFrame frame = new JFrame("Please select your target directory.");
        JFileChooser panel = new JFileChooser();
        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
        frame.getContentPane().add(panel,"Center");
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);

    }

    public void JFileChooser() {
        go = new JButton("Do it");
        go.addActionListener(this);
        add(go);
    }

    public void actionPerformed(ActionEvent e) {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    +  chooser.getSelectedFile());
        }
        else {
            System.out.println("No Selection ");
        }
    }

    public Dimension getPreferredSize(){
        return new Dimension(200, 200);
    }

    private static void createWriteFile(String[] text) {
        try {
            File file = new File("EncryptedText.txt");
            FileWriter fileWriter = new FileWriter(file);


            //Header
            fileWriter.write("ooooooooo.    .oooooo..o       .o.\n");
            fileWriter.write("`888   `Y88. d8P'    `Y8      .888.\n");
            fileWriter.write(" 888   .d88' Y88bo.          .8\"888.\n");
            fileWriter.write(" 888ooo88P'   `\"Y8888o.     .8' `888.\n");
            fileWriter.write(" 888`88b.         `\"Y88b   .88ooo8888.\n");
            fileWriter.write(" 888  `88b.  oo     .d8P  .8'     `888.\n");
            fileWriter.write("o888o  o888o 8\"\"88888P'  o88o     o8888o\n");
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write("===========================================================================\n");
            fileWriter.write("This program was made by Julie, Arthur, Dennis, Jonas, Soufian and Mahmoud.\n");
            fileWriter.write("===========================================================================\n");
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write(System.getProperty("line.separator"));


            //Body
            fileWriter.write(text[0] + "\n");
            fileWriter.write(text[1] + "\n");
            fileWriter.write(text[2] + "\n");
            fileWriter.write(text[3] + "\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}