// This is a gui code which will ask the user to enter a txt file name then it will will read and display rhe content of the
//file using a text area, there is also a copy button 




import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.io.IOException;

public class Interface {
    private static Object FileUtils;
    private JPanel Main;
    private JButton read;
    public JTextPane Display;
    private JButton copy;
    private JButton Instagram;
    private JTextArea fileNameTXT;
    private JButton addFile;
    public static String filename;
    public static ArrayList<String> lines;

    public Interface() {
        read.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                filename = fileNameTXT.getText();
                try {
                    readLines();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                for (int i = 0; i < lines.size(); i++) Display.setText(lines.get(i));
            }

        });
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection ciplo = new StringSelection(Display.getText());
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(ciplo, null);

            }
        });
        Instagram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
Display.setText("https://instagram.com/p.r3");
            }
        });

    }

    public static void main(String[] args) throws FileNotFoundException {


        JFrame fram = new JFrame("Interface");
        fram.setContentPane(new Interface().Main);
        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fram.pack();
        fram.setVisible(true);


    }

    public void readLines() throws IOException   //this function will read the data from the txt and then convert them into a public var
    {
        FileReader fileReader = new FileReader(filename);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        lines = new ArrayList<String>();
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
            Display.setText(String.valueOf(lines));
        }

        bufferedReader.close();

        lines.toArray(new String[0]);

    }


}

