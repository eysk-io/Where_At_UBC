package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchApp {
    private JPanel panelMain;
    private JButton submitButton;
    public JTextField userNameTextBox;

    public SearchApp(JFrame userFrame) {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameTextBox.getText();
                JOptionPane.showMessageDialog(null,
                        "Thanks " + userName + "! What can I find for you today?");
                userFrame.dispose();
                JFrame amenityFrame = new JFrame("AmenitySearch");
                new AmenitySearchForm(amenityFrame);
            }
        });
    }

    public static void main(String[] args) {
        JFrame userFrame = new JFrame("SearchApp");
        SearchApp searchApp = new SearchApp(userFrame);
        userFrame.setContentPane(searchApp.getPanel());
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.pack();
        userFrame.setVisible(true);
    }

    private JPanel getPanel() {
        return panelMain;
    }
}
