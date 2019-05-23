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
    private static final String IMPORT_PRIVTE_KEY_COMMAND = "importPriv";
    private static final String CLEAR_COMMAND ="clear";
    private JFileChooser fc;

    File encryptedTxt = new File("encrypted.txt");
    File selectedFile;

    BigInteger p;
    BigInteger q;
    BigInteger e;
    BigInteger d;
    BigInteger n;
    JTextField n1TF;
    JTextField n2TF;
    JTextField dTF;
    JTextField eTF;

    String pattern01 = "n: ";
    String pattern02 = "d: ";
    String pattern03 = "e: ";

    boolean generated = false;
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

        //assign elements for keys area

        n1TF = new JTextField();
        n2TF = new JTextField();
        dTF = new JTextField();
        eTF = new JTextField();
        JTextField keyfilename = new JTextField("Keyfile");

        n1TF.setEditable(false);
        n2TF.setEditable(false);
        dTF.setEditable(false);
        eTF.setEditable(false);

        Dimension numberDimension = new Dimension(100, 20);
        n1TF.setPreferredSize(numberDimension);
        n2TF.setPreferredSize(numberDimension);
        dTF.setPreferredSize(numberDimension);
        eTF.setPreferredSize(numberDimension);


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
        JButton importPriv = new JButton("Import PrivateKey");
        JButton clear = new JButton("CLEAR");

        generate.add(importPriv);
        generate.add(generateButton);
        generate.add(exportPriv);
        generate.add(exportPub);
        generate.add(clear);

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

        Dimension wordDimension = new Dimension(300, 20);

        encryptedWord.setPreferredSize(wordDimension);
        decryptedWord.setPreferredSize(wordDimension);


        inputAndOutput.add(inputText);
        inputAndOutput.add(new JLabel("Ecrypted word"));
        inputAndOutput.add(encryptedWord);
        inputAndOutput.add(new JLabel("Decrypted word"));
        inputAndOutput.add(decryptedWord);

        //create & assign elements for decryptAndEnctyptButton
        JButton encryptButton = new JButton("ENCRYPT");
        JButton decryptButton = new JButton("DECRYPT");
        JButton copyEncryWordBtn = new JButton("COPY ENCRY WORD");
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
        importPriv.setActionCommand(IMPORT_PRIVTE_KEY_COMMAND);
        clear.setActionCommand(CLEAR_COMMAND);

        ActionListener buttonlistener = a -> {
            if (a.getActionCommand().equals(ENCRYPT_COMMAND)) {
                if(!generated){
                    //System.out.println("NO KEY, PLEASE GENERATE");
                    //entweder generieren, oder Ausgabe dass kein Schlüssel generiert ist.
                    generateKeys();
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
                generateKeys();
            } else if(a.getActionCommand().equals(EXPORTPRIV_COMMAND)) { // von Dennis
                if(!generated) {
                    //System.out.println("NO KEYS TO EXPORT.");
                    //entweder generieren, oder Ausgabe dass kein Schlüssel generiert ist.
                    generateKeys();}
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
                        // Export in File
                        try (FileWriter myFileWriter = new FileWriter(newFile)) {
                            myFileWriter.write("n: \n" + n + "\n" + "d: \n" + d);
                        } catch (IOException ioe3) {
                            ioe3.printStackTrace();
                        }
                    } else {
                        System.out.println("Save command cancelled by user.");
                    }
            } else if(a.getActionCommand().equals(EXPORTPUB_COMMAND)) { //von Dennis
                if(!generated) {
                    //System.out.println("NO KEYS TO EXPORT.");
                    //entweder generieren, oder Ausgabe dass kein Schlüssel generiert ist.
                    generateKeys(); }
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
                            myFileWriter.write("n: \n" + n + "\n" + "e: \n" + e);
                            System.out.println("e:" + e);
                            System.out.println("n: " + n );
                        } catch (IOException ioe3) {
                            ioe3.printStackTrace();
                        }
                    } else {
                        System.out.println("Save command cancelled by user.");
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
                        if((line = myLineReader.readLine()).contains("Encrypted Text:")){
                            line = myLineReader.readLine();
                        }
                        System.out.println(line);
                        encryptedWord.setText(line);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("TEXT IMPORTED");
            }else if (a.getActionCommand().equals(IMPORT_PRIVTE_KEY_COMMAND)){
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getName());
                }
                try (FileReader myFileReader = new FileReader(selectedFile); BufferedReader myLineReader = new BufferedReader(myFileReader)){
                    String line;
                    while ((line = myLineReader.readLine())!= null){
                        if((line = myLineReader.readLine()).contains("n:") || (line = myLineReader.readLine()).contains("d:") || (line = myLineReader.readLine()).contains("e:")){
                            line = myLineReader.readLine();
                            int t = 0;
                            if(t == 0){
                                n1TF.setText(line);
                                BigInteger cache = new BigInteger(line);
                                n = cache;
                                t++;
                            } else if (t == 1){
                                dTF.setText(line);
                                BigInteger cache = new BigInteger(line);
                                d = cache;
                                t++;
                            }
                        }
                        System.out.println(line);
                    }
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else if (a.getActionCommand().equals(CLEAR_COMMAND)){
                n1TF.setText("");
                n2TF.setText("");
                eTF.setText("");
                dTF.setText("");
            }

        };

        encryptButton.addActionListener(buttonlistener);
        decryptButton.addActionListener(buttonlistener);
        generateButton.addActionListener(buttonlistener);
        copyEncryWordBtn.addActionListener(buttonlistener);
        importDecryptWord.addActionListener(buttonlistener);
        exportPriv.addActionListener(buttonlistener);
        exportPub.addActionListener(buttonlistener);
        importPriv.addActionListener(buttonlistener);

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

    //Generierung von RSA Zeugs
    public void generateKeys() {

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

    }


    public static void main(String[] args) {
        new UI();
    }
}
