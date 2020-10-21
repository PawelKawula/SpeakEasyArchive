package gui;

import javax.swing.*;

public class SpeakEasyGUI extends JFrame
{
    private JPanel mainPanel;
    private JPanel groupsPanel;
    private JPanel chatPanel;
    private JPanel friendsPanel;
    private JPanel groupManagmentPanel;
    private JButton addGroupButton;
    private JTextField addGroupTextField;
    private JTextArea chatInput;
    private JPanel conversationPanel;
    private JPanel addFriendPanel;
    private JButton addFriendButton;
    private JTextArea addFriendTextField;

    public SpeakEasyGUI(String title)
   {
       super(title);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setContentPane(mainPanel);
       this.pack();
   }
}
