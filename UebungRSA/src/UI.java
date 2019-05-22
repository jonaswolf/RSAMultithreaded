import java.io.*;
import java.math.BigInteger;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class UI extends JFrame {

    private String word;
    private static RSA rsa;
    private static final String COPYKEYS_COMMAND = "copyKeys";
    private static final String GENERATE_COMMAND = "generate";
    private static final String ENCRYPT_COMMAND = "encrypt";
    private static final String DECRYPT_COMMAND = "decrypt";
    private static final String COPYENCRYPTEDTEXT_COMMAND = "copyEncryptedText";
    private static final String IMPORTTEXT_COMMAND = "ImportText";

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
        JButton copyKeysBtn = new JButton("COPY KEYS");
        generate.add(copyKeysBtn);
        generate.add(generateButton);

        //create & assign elements for inputAndOutput area
        JTextField inputText = new JTextField("INSERT TEXT");
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
        copyKeysBtn.setActionCommand(COPYKEYS_COMMAND);
        copyEncryWordBtn.setActionCommand(COPYENCRYPTEDTEXT_COMMAND);
        importDecryptWord.setActionCommand(IMPORTTEXT_COMMAND);

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
                    decryptedWordsList = RSA.decry(encryptedWord.getText(),n, d);
                    String result = ("");
                    for(int i = 0;i<decryptedWordsList.length;i++){
                        result = result+(decryptedWordsList[i].toString())+" ";
                    }
                    decryptedWord.setText(result);
                }
                else if(inputText.getText().length()==0){ }
                else {
                    inputText.setText("Gib was ein das sich lohnt zu entschlüsseln");
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
            } else if(a.getActionCommand().equals(COPYKEYS_COMMAND)){
                if(!generated){
                    System.out.println("NO KEYS TO BE COPIED");
                }else{
                if(keyfilename.getText() == ""){
                    System.out.println("PLEASE CHOOSE A SPECIFIC FILENAME");
                    }else{
                    String filename = keyfilename.getText();
                    File PublicKeyTxt = new File(filename + ".pub");
                    File PrivateKeyTxt = new File (filename + ".key");
                    if (!PublicKeyTxt.exists() || !PrivateKeyTxt.exists()) {
                        try {
                            PublicKeyTxt.createNewFile();
                            PrivateKeyTxt.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try (FileWriter myFileWriter = new FileWriter(PublicKeyTxt, false)) {
                        myFileWriter.write("n: " + n + "\n");
                        myFileWriter.write("e: " + e + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try (FileWriter myFileWriter = new FileWriter(PrivateKeyTxt, false)) {
                        myFileWriter.write("n: " + n + "\n");
                        myFileWriter.write("d: " + d + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("KEYS COPIED");
                    System.out.println("Public Key: " + keyfilename.getText() + ".pub");
                    System.out.println("Secret Key: " + keyfilename.getText() + ".key");
                }}
            }else if(a.getActionCommand().equals(COPYENCRYPTEDTEXT_COMMAND)){

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
        copyKeysBtn.addActionListener(buttonlistener);
        copyEncryWordBtn.addActionListener(buttonlistener);
        importDecryptWord.addActionListener(buttonlistener);

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
