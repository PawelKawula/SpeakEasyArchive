package gui;

import conversation.ConversationViewer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SpeakEasyGUI extends JFrame
{
    private JPanel mainPanel;
    private JPanel groupsSegment;
    private JPanel chatSegment;
    private JPanel friendsSegment;
    private JPanel groupManagmentPanel;
    private JButton addGroupButton;
    private JTextField addGroupTextField;
    private JTextArea chatInput;
    private JPanel addFriendPanel;
    private JButton addFriendButton;
    private JTextField addFriendTextField;
    private JPanel groupListPanel;
    private JPanel groupList;
    private JPanel friendListPanel;
    private JPanel friendList;
    private JPanel conversationViewer;

    public SpeakEasyGUI(String title)
   {
       super(title);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setContentPane(mainPanel);
       this.pack();
       ConversationViewer conView = (ConversationViewer) conversationViewer;

       addGroupButton.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               String groupName = addGroupTextField.getText();
               if (!groupName.equals(""))
               {
                   JButton newGroup = new JButton(groupName);
                   newGroup.addActionListener(ev ->
                   {
                        groupList.remove(newGroup);
                        groupList.revalidate();
                        groupList.repaint();
                   });
                   GridBagConstraints gbc = new GridBagConstraints();
                   gbc.gridx = 1;
                   gbc.weightx = 1;
                   gbc.fill = GridBagConstraints.HORIZONTAL;

                   groupList.add(newGroup, gbc);
                   groupList.revalidate();
               }
           }
       });
       addFriendButton.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent e)
           {
                String friendName = addFriendTextField.getText();
                if (!friendName.equals(""))
                {
                    JButton newFriend = new JButton(friendName);
                    newFriend.addActionListener(ev ->
                    {
                        friendList.remove(newFriend);
                        friendList.revalidate();
                        friendList.repaint();
                    });
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 1;
                    gbc.weightx = 1;
                    gbc.fill = GridBagConstraints.HORIZONTAL;

                    friendList.add(newFriend, gbc);
                    friendList.revalidate();
                }
           }
       });
       chatInput.addKeyListener(new KeyAdapter()
       {
           @Override
           public void keyReleased(KeyEvent e)
           {
               if (e.getKeyCode() == KeyEvent.VK_ENTER)
               {
                    conView.addMessage(chatInput.getText(), true);
                    conView.revalidate();
                    conView.repaint();
               }
           }
       });
   }


    private void createUIComponents()
    {
        chatSegment = new JPanel();
        conversationViewer = new ConversationViewer();
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
        conversationViewer.setBorder(border);
        chatSegment.add(conversationViewer, BorderLayout.CENTER);
    }
}
