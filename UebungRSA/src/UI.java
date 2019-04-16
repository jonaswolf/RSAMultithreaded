import java.math.BigInteger;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
public class UI extends JFrame {

    private String word;
    private static RSA rsa;
    private static final String GENERATE_COMMAND = "generate";
    private static final String ENCRYPT_COMMAND = "encrypt";
    private static final String DECRYPT_COMMAND = "decrypt";
    boolean generated = false;
    BigInteger p;
    BigInteger q;
    BigInteger e;
    BigInteger d;
    BigInteger[] wordsList;

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

        //create & assign elements for generate area
        JButton generateButton = new JButton("GENERATE");
        generate.add(generateButton);

        //create & assign elements for inputAndOutput area
        JTextField inputText = new JTextField("INSERT TEXT");
        JTextField encryptedWord = new JTextField("");
        JTextField decryptedWord = new JTextField("");

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

        decryptAndEncryptButtonPanel.add(encryptButton);
        decryptAndEncryptButtonPanel.add(decryptButton);

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
                    generated = true;
                       System.out.println("KEYS GENERATED");
                }
                if(inputText.getText().length()>4){
                    wordsList = RSA.encry(inputText.getText(),p,q,e);
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
                word = inputText.getText();
                inputText.setText("");
                System.out.println(word);
                decryptedWord.setText("");

            } else if (a.getActionCommand().equals(DECRYPT_COMMAND)) {
                System.out.println("DECRYPT");
                decryptedWord.setText("THIS WORD HAS BEEN DECRYPTED");
                encryptedWord.setText("");

            }else if (a.getActionCommand().equals(GENERATE_COMMAND)){
                p = RSA.getPrime();
                System.out.println(p);
                q = RSA.getPrime();
                System.out.println(q);
                e = RSA.getE(p,q);
                System.out.println(e);
                d = RSA.getD(p,q,e);
                System.out.println(d);
                generated = true;
                System.out.println("KEYS GENERATED");
            }
        };

        encryptButton.addActionListener(buttonlistener);
        decryptButton.addActionListener(buttonlistener);
        generateButton.addActionListener(buttonlistener);

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
