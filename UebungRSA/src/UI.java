import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.math.BigInteger;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class UI extends JFrame {

    private String word;
    private static RSA rsa;
    private static final String GENERATE_COMMAND = "generate";
    private static final String ENCRYPT_COMMAND = "encrypt";
    private static final String DECRYPT_COMMAND = "decrypt";
    private static final String COPYENCRYPTEDTEXT_COMMAND = "copyEncryptedText";
    private static final String IMPORTTEXT_COMMAND = "ImportText";
    private static final String EXPORTPRIV_COMMAND = "exportPriv";
    private static final String EXPORTPUB_COMMAND = "exportPub";
    private JFileChooser fc;

    File encryptedTxt = new File("encrypted.txt");

    boolean generated = false;
    BigInteger p;
    BigInteger q;
    BigInteger e;
    BigInteger d;
    BigInteger n;
    BigInteger[] wordsList;
    BigInteger[] decryptedWordsList;

    public UI (){

        //initialise RSA
        rsa = new RSA();
        // initialize Panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new FlowLayout());

        ((FlowLayout)centerPanel.getLayout()).setHgap(20);
        ((FlowLayout)centerPanel.getLayout()).setVgap(20);


        JPanel keyGeneratorPanel = new JPanel(new GridLayout(0, 1));
        JPanel encrypt  = new JPanel(new GridLayout(0, 1));

        ((GridLayout)keyGeneratorPanel.getLayout()).setVgap(15);
        ((GridLayout)encrypt.getLayout()).setVgap(15);

        JPanel keys = new JPanel(new GridLayout(0,5));
        JPanel generate = new JPanel(new FlowLayout(FlowLayout.CENTER));

        ((GridLayout)keys.getLayout()).setHgap(15);
        ((GridLayout)keys.getLayout()).setVgap(10);

        JPanel inputAndOutput = new JPanel(new GridLayout(0,1));
        JPanel decryptAndEncryptButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        ((GridLayout)inputAndOutput.getLayout()).setVgap(10);
        ((GridLayout)inputAndOutput.getLayout()).setHgap(15);

        //create & assign elements for keys area

        JTextField n1TF = new JTextField();
        JTextField n2TF = new JTextField();
        JTextField dTF = new JTextField();
        JTextField eTF = new JTextField();
        JTextField keyfilename = new JTextField("Keyfile");

        n1TF.setEditable(false);
        n2TF.setEditable(false);
        dTF.setEditable(false);
        eTF.setEditable(false);


        keys.add(new JLabel("Private Key: "));
        keys.add(new JLabel(" n: "));
        keys.add(n1TF);
        keys.add(new JLabel(" d: "));
        keys.add(dTF);

        keys.add(new JLabel("Public Key: "));
        keys.add(new JLabel(" n: "));
        keys.add(n2TF);
        keys.add(new JLabel(" e: "));
        keys.add(eTF);

        keys.add(new JLabel("Filename for Export:"));
        keys.add(keyfilename);

        n1TF.setAutoscrolls(true);
        n2TF.setAutoscrolls(true);
        eTF.setAutoscrolls(true);
        dTF.setAutoscrolls(true);
        keyfilename.setAutoscrolls(true);

        //create & assign elements for generate area
        JButton generateButton = new JButton("GENERATE");
        JButton exportPriv = new JButton("Export PrivateKey");
        JButton exportPub = new JButton("Export PublicKey");
        generate.add(generateButton);
        generate.add(exportPriv);
        generate.add(exportPub);

        //create & assign elements for inputAndOutput area
        JTextField inputText = new JTextField("INSERT TEXT");
        //If user clicks on the textfield, hint will disappear
        inputText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                inputText.setText("");
            }
        });
        JTextField encryptedWord = new JTextField("");
        JTextField decryptedWord = new JTextField("");

        inputText.setAutoscrolls(true);
        encryptedWord.setAutoscrolls(true);
        decryptedWord.setAutoscrolls(true);

        encryptedWord.setEditable(false);
        decryptedWord.setEditable(false);

        inputAndOutput.add(inputText);
        inputAndOutput.add(new JLabel("Ecrypted word"));
        inputAndOutput.add(encryptedWord);
        inputAndOutput.add(new JLabel("Decrypted word"));
        inputAndOutput.add(decryptedWord);

        //create & assign elements for decryptAndEnctyptButton
        JButton encryptButton = new JButton("ENCRYPT");
        JButton decryptButton = new JButton("DECRYPT");
        JButton copyEncryWordBtn = new JButton("COPY");
        JButton importDecryptWord = new JButton("IMPORT");

        decryptAndEncryptButtonPanel.add(copyEncryWordBtn);
        decryptAndEncryptButtonPanel.add(encryptButton);
        decryptAndEncryptButtonPanel.add(decryptButton);
        decryptAndEncryptButtonPanel.add(importDecryptWord);

        // create & assign Borders
        Border etchedBorder = BorderFactory.createEtchedBorder();
        Border connectionBorder = BorderFactory.createTitledBorder(etchedBorder, "Key Generator");
        Border fileBorder = BorderFactory.createTitledBorder(etchedBorder, "Verschlüsselung");
        Border centerBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

        keyGeneratorPanel.setBorder(connectionBorder);
        encrypt.setBorder(fileBorder);
        centerPanel.setBorder(centerBorder);

        //set Button Functions
        encryptButton.setActionCommand(ENCRYPT_COMMAND);
        decryptButton.setActionCommand(DECRYPT_COMMAND);
        generateButton.setActionCommand(GENERATE_COMMAND);
        copyEncryWordBtn.setActionCommand(COPYENCRYPTEDTEXT_COMMAND);
        importDecryptWord.setActionCommand(IMPORTTEXT_COMMAND);
        exportPriv.setActionCommand(EXPORTPRIV_COMMAND);
        exportPub.setActionCommand(EXPORTPUB_COMMAND);

        ActionListener buttonlistener = a -> {
            if (a.getActionCommand().equals(ENCRYPT_COMMAND)) {
                if(!generated){
                    p = RSA.getPrime();
                    System.out.println(p);
                    q = RSA.getPrime();
                    System.out.println(q);
                    e = RSA.getE(p,q);
                    System.out.println(e);
                    d = RSA.getD(p,q,e);
                    System.out.println(d);
                    n = RSA.getN(p,q);
                    System.out.println(n);
                    generated = true;
                       System.out.println("KEYS GENERATED");
                }
                if(inputText.getText().length()>4){
                    wordsList = RSA.encry(inputText.getText(), n, e);
                    String result = ("");
                    for(int i = 0;i<wordsList.length;i++){
                        result = result+(wordsList[i].toString())+" ";
                    }
                    encryptedWord.setText(result);
                }
                else if(inputText.getText().length()==0){ }
                else {
                    inputText.setText("Gib was ein das sich lohnt zu verschlüsseln");
                }
                System.out.println("ENCRYPT");
                //System.out.println(encryptedWord.getText());
                word = inputText.getText();
                inputText.setText("");
                System.out.println(word);
                decryptedWord.setText("");

            } else if (a.getActionCommand().equals(DECRYPT_COMMAND)) {
                if(!generated){
                    System.out.println("YOU CAN NOT DECRYPT WITHOUT KEY");
                }
                if(encryptedWord.getText().length()>4){
                    decryptedWord.setText(RSA.decry(wordsList,n,d));
                    encryptedWord.setText("");
                }
                else if(inputText.getText().length()==0){ }
                else {
                    inputText.setText("TYPE IN SOMETHING THATS WORTH DECRYPTING.");
                }
                System.out.println("DECRYPT");
                System.out.println(decryptedWord.getText());
                encryptedWord.setText("");
            }else if (a.getActionCommand().equals(GENERATE_COMMAND)){
                p = RSA.getPrime();
                System.out.println("p: " + p);
                q = RSA.getPrime();
                System.out.println("q: " + q);
                n = RSA.getN(p,q);
                n1TF.setText(n + " ");
                n2TF.setText(n + " ");
                e = RSA.getE(p,q);
                eTF.setText(" " + e);
                System.out.println("e: " + e);
                d = RSA.getD(p,q,e);
                dTF.setText(" " + d);
                System.out.println("d: " + d);
                generated = true;
                System.out.println("KEYS GENERATED");
            } else if(a.getActionCommand().equals(EXPORTPRIV_COMMAND)) { // von Dennis
                if(!generated) {
                    System.out.println("NO KEYS TO EXPORT.");
                } else {
                    //FileChooser erstellen
                    fc = new JFileChooser();

                    //Erlaube nur TextFiles
                    fc.setAcceptAllFileFilterUsed(false);
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("TextFiles", "txt", "text");
                    fc.setFileFilter(filter);

                    int returnVal = fc.showSaveDialog(UI.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        //Datei speichern und beschriften
                        System.out.println("Saving PrivateKey-TextFile: " + file.getName() + " to " + file.getAbsolutePath());
                        String filename = fc.getSelectedFile().getName();
                        //Prüfen, ob Dateiendung bereits mit angegeben wurde
                        final String EXTENSION = ".txt";
                        String filepath = fc.getSelectedFile().toString();
                        if(!filename.endsWith(EXTENSION)) {
                            filename = filepath + EXTENSION;
                        } else {
                            filename = filepath;
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
                            myFileWriter.write("n: " + n + System.getProperty("line.separator"));
                            myFileWriter.write("d: " + d);
                        } catch (IOException ioe3) {
                            ioe3.printStackTrace();
                        }
                    } else {
                        System.out.println("Save command cancelled by user.");
                    }
                }
            } else if(a.getActionCommand().equals(EXPORTPUB_COMMAND)) { //von Dennis
                if(!generated) {
                    System.out.println("NO KEYS TO EXPORT.");
                } else {
                    //FileChooser erstellen
                    fc = new JFileChooser();

                    //Erlaube nur TextFiles
                    fc.setAcceptAllFileFilterUsed(false);
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("TextFiles", "txt", "text");
                    fc.setFileFilter(filter);

                    int returnVal = fc.showSaveDialog(UI.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        //Datei speichern und beschriften
                        System.out.println("Saving PublicKey-TextFile: " + file.getName() + " to " + file.getAbsolutePath());
                        String filename = fc.getSelectedFile().getName();
                        //Prüfen, ob Dateiendung bereits mit angegeben wurde
                        final String EXTENSION = ".txt";
                        String filepath = fc.getSelectedFile().toString();
                        if(!filename.endsWith(EXTENSION)) {
                            filename = filepath + EXTENSION;
                        } else {
                            filename = filepath;
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
                            myFileWriter.write("n: " + n + System.getProperty("line.separator"));
                            myFileWriter.write("e: " + e);
                        } catch (IOException ioe3) {
                            ioe3.printStackTrace();
                        }
                    } else {
                        System.out.println("Save command cancelled by user.");
                    }
                }
            } else if(a.getActionCommand().equals(COPYENCRYPTEDTEXT_COMMAND)){

                if(!encryptedTxt.exists()){
                    try{
                        encryptedTxt.createNewFile();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                try(FileWriter myFileWriter = new FileWriter(encryptedTxt, false)){
                    myFileWriter.write("Encrypted Text: \n" + encryptedWord.getText());
                }catch (IOException e){
                    e.printStackTrace();
                }
                System.out.println("ENCRYPTED WORD COPIED");
            }else if (a.getActionCommand().equals(IMPORTTEXT_COMMAND)){
                inputText.setText(" ");
                decryptedWord.setText(" ");
                try (FileReader myFileReader = new FileReader(encryptedTxt); BufferedReader myLineReader = new BufferedReader(myFileReader)){
                    String line;
                    while((line = myLineReader.readLine()) != null){
                        System.out.println(line);
                        encryptedWord.setText(line);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("TEXT IMPORTED");
            }
        };

        encryptButton.addActionListener(buttonlistener);
        decryptButton.addActionListener(buttonlistener);
        generateButton.addActionListener(buttonlistener);
        copyEncryWordBtn.addActionListener(buttonlistener);
        importDecryptWord.addActionListener(buttonlistener);
        exportPriv.addActionListener(buttonlistener);
        exportPub.addActionListener(buttonlistener);

        // combine Panels
        keyGeneratorPanel.add(keys);
        keyGeneratorPanel.add(generate);

        encrypt.add(inputAndOutput);
        encrypt.add(decryptAndEncryptButtonPanel);

        centerPanel.add(keyGeneratorPanel);
        centerPanel.add(encrypt);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        this.setContentPane(mainPanel);

        // set JFrame behavior
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new UI();
    }
}
