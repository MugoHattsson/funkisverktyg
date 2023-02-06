package funkis;

import javax.swing.*;

import java.awt.*;
import java.io.*;

public class PdfMakerApp extends JFrame {

    File dir = null;
    JButton directoryButton;
    JButton generateButton;
    JLabel pathLabel;
    JTextArea pathField;

    public PdfMakerApp() {
        setTitle("PDF-generator");

        directoryButton = new JButton("Välj mapp");
        directoryButton.addActionListener((e) -> openDirectoryDialog());
        add(directoryButton);

        pathLabel = new JLabel("Filsökväg till vald mapp:");
        add(leftAlign(pathLabel));

        pathField = new JTextArea("vänligen välj mapp");
        pathField.setEditable(false);
        add(pathField);

        generateButton = new JButton("Skapa PDF");
        generateButton.setEnabled(false);

        add(generateButton);

        setSize(400, 500);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void openDirectoryDialog() {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int state = fc.showOpenDialog(this);
        if (state == JFileChooser.APPROVE_OPTION) {
            dir = fc.getSelectedFile();
            generateButton.addActionListener(e -> PdfMaker.createPdf(dir));
            generateButton.setEnabled(true);
            pathField.setText(dir.getPath());
        }
        System.out.println("Mapp vald: " + dir.getPath());
    }

    private Component leftAlign(Component comp) {
        Box b = Box.createHorizontalBox();
        b.add(comp);
        b.add(Box.createHorizontalGlue());
        return b;
    }
}
