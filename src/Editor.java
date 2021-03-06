import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.event.*;

class Editor extends JFrame implements ActionListener {
    // Text component
    JTextArea textArea;
    LineNumber lineNumber;

    int numberOfLines = 1;

    // Frame
    JFrame f;

    JScrollPane scrollPane;
    JLabel fontLabel;
    JSpinner fontSizeSpinner;
    JButton fontColorButton;
    JComboBox<String> fontBox;

    // Constructor
    Editor() {
        // Create a frame
        f = new JFrame("editor");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);

        // Text component
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 20));

        textArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                String content = textArea.getText();
                int num = 1;
                for (int i = 0; i < content.length(); ++i) {
                    if (content.charAt(i) == '\n') {
                        num++;
                    }
                }

                int diff = num - numberOfLines;
                if (diff < 0) {
                    diff *= -1;

                    for (int i = 0; i < diff; ++i) {
                        lineNumber.removeLine();
                    }
                }
                else if (diff > 0) {
                    for (int i = 0; i < diff; ++i) {
                        lineNumber.addNewLine();
                    }
                }

                numberOfLines = num;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                String content = textArea.getText();
                int num = 1;
                for (int i = 0; i < content.length(); ++i) {
                    if (content.charAt(i) == '\n') {
                        num++;
                    }
                }

                int diff = num - numberOfLines;
                if (diff < 0) {
                    diff *= -1;

                    for (int i = 0; i < diff; ++i) {
                        lineNumber.removeLine();
                    }
                }
                else if (diff > 0) {
                    for (int i = 0; i < diff; ++i) {
                        lineNumber.addNewLine();
                    }
                }

                numberOfLines = num;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                
            }

        });
        
        lineNumber = new LineNumber();
        // f.add(lineNumber, BorderLayout.WEST);

        // Create a menubar
        JMenuBar mb = new JMenuBar();

        // Create a menu for File options
        JMenu m1 = new JMenu("File");

        // Create menu items
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi9 = new JMenuItem("Print");

        // Add action listener
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        // Create amenu for Text Editing options
        JMenu m2 = new JMenu("Edit");

        // Create menu items
        JMenuItem mi4 = new JMenuItem("cut");
        JMenuItem mi5 = new JMenuItem("copy");
        JMenuItem mi6 = new JMenuItem("paste");

        // Add action listener
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        JMenuItem mc = new JMenuItem("close");

        mc.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(mc);

        textArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                
            }

        });

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(lineNumber, BorderLayout.WEST);
        contentPanel.add(textArea, BorderLayout.CENTER);

        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setPreferredSize(new Dimension(450, 450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fontLabel = new JLabel("Font: ");

        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
        fontSizeSpinner.setValue(20);

        fontSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue()));
            }
        });

        fontColorButton = new JButton("Color");
        fontColorButton.addActionListener(this);

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        fontBox = new JComboBox<>(fonts);
        fontBox.addActionListener(this);
        fontBox.setSelectedItem("Arial");

        JPanel optionPanel = new JPanel();
        optionPanel.add(fontLabel);
        optionPanel.add(fontSizeSpinner);
        optionPanel.add(fontColorButton);
        optionPanel.add(fontBox);

        f.setJMenuBar(mb);
        f.add(optionPanel, BorderLayout.NORTH);
        f.add(scrollPane, BorderLayout.CENTER);
        f.setSize(500, 500);
        f.setVisible(true);
        // f.show();
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (e.getSource() == fontColorButton) {
            Color color = JColorChooser.showDialog(null, "Choose a color", Color.black);

            textArea.setForeground(color);
        }

        if (e.getSource() == fontBox) {
            textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
        }

        if (s.equals("cut")) {
            // TO DO, design our own editing functions
            textArea.cut();
        } else if (s.equals("copy")) {
            textArea.copy();
        } else if (s.equals("paste")) {
            textArea.paste();
        } else if (s.equals("Save")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser();

            // Invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {

                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // Create a file writer
                    FileWriter wr = new FileWriter(fi, false);

                    // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);

                    // Write
                    w.write(textArea.getText());

                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        } else if (s.equals("Print")) {
            try {
                // print the file
                textArea.print();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        } else if (s.equals("Open")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser();

            // Invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);

            // If the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // String
                    String s1 = "", sl = "";

                    // File reader
                    FileReader fr = new FileReader(fi);

                    // Buffered reader
                    BufferedReader br = new BufferedReader(fr);

                    // Initialize sl
                    sl = br.readLine();

                    // Take the input from the file
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }

                    // Set the text
                    textArea.setText(sl);
                    br.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        } else if (s.equals("New")) {
            textArea.setText("");
        } else if (s.equals("close")) {
            f.setVisible(false);
        }
    }

    public String getContent(){
        return textArea.getText();
    }

    public void setContent(String content){
        textArea.setText(content);
    }

    public void setCursorToEndOfText(){
        textArea.setCaretPosition(getContent().length());
    }

    // Main class
    public static void main(String[] args) {
        new Editor();
    }
}