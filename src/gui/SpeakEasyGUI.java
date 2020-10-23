package gui;

import conversation.Bubble;
import conversation.Conversation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

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
    private JPanel chatPanel;

    public SpeakEasyGUI(String title)
   {
       super(title);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setContentPane(mainPanel);
       this.pack();

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
               }
           }
       });
   }


    private void createUIComponents()
    {
        chatSegment = new JPanel();
        chatPanel = new ChatPanel();
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
        chatPanel.setBorder(border);
        chatSegment.add(chatPanel, BorderLayout.CENTER);
    }
}

class ChatPanel extends JPanel
{
    Map<String, Conversation> conversationMap;
    private String currentConversation;

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
//        if (conversationMap.containsKey(currentConversation))
//            conversationMap.get(currentConversation).paintImmediately();

    }
}