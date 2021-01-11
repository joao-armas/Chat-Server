package client;

import javax.swing.*;
import java.awt.*;

public class ClientWindow {
    private JFrame frame;
    private JPanel panel1;
    private JTextArea textArea1;
    private JScrollPane scrollPane1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ClientWindow window = new ClientWindow();
                    window.frame.setVisible(true);
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ClientWindow() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

