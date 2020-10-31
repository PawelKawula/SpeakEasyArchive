package gui;

import conversation.ConversationViewer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
//    private JPanel groupList;
    private JPanel friendListPanel;
//    private JPanel friendList;
    private JPanel conversationViewer;
    private ImageIcon xImage;
    private int nrOfFriends;
    private int nrOfGroups;

    public SpeakEasyGUI(String title)
   {
       super(title);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setContentPane(mainPanel);
       this.pack();

       groupsSegment.add(groupListPanel, BorderLayout.CENTER);
       friendsSegment.add(friendListPanel, BorderLayout.CENTER);
       JScrollPane chatScrollPane = new JScrollPane(conversationViewer);
       chatSegment.add(chatScrollPane, BorderLayout.CENTER);
       ConversationViewer conView = (ConversationViewer) conversationViewer;
       xImage = new ImageIcon("x.png");

       addGroupButton.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               String groupName = addGroupTextField.getText();
               if (!groupName.equals(""))
               {
                   JPanel newGroupPanel = new JPanel();
                   newGroupPanel.setLayout(new BorderLayout());
                   JButton newGroup = new JButton(groupName);
                   JButton newGroupDeleteButton = new JButton(xImage);
                   newGroupDeleteButton.addActionListener(ev ->
                   {
                        groupListPanel.remove(newGroupPanel);
                        groupListPanel.revalidate();
                        groupListPanel.repaint();
                   });
                   newGroup.addActionListener(ev ->
                   {
                       conView.changeConversation(groupName);
                       conView.repaint();
                   });
                   newGroupPanel.add(newGroupDeleteButton, BorderLayout.EAST);
                   newGroupPanel.add(newGroup, BorderLayout.CENTER);

//                   GridBagConstraints gbc = new GridBagConstraints();
//                   gbc.weightx = 1;
//                   gbc.gridy = nrOfGroups;
//                   gbc.fill = GridBagConstraints.HORIZONTAL;

                   groupListPanel.add(newGroupPanel);
                   groupListPanel.revalidate();
                   groupListPanel.repaint();
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
                    JPanel newFriendPanel = new JPanel();
                    newFriendPanel.setLayout(new BorderLayout());
                    JButton newFriend = new JButton(friendName);
                    JButton newFriendDeleteButton = new JButton(xImage);
                    newFriendDeleteButton.addActionListener(ev ->
                    {
                        friendListPanel.remove(newFriendPanel);
                        friendListPanel.revalidate();
                        friendListPanel.repaint();
                    });
                    newFriend.addActionListener(ev ->
                    {
                        conView.changeConversation(friendName);
                        conView.repaint();
                    });
//                    GridBagConstraints gbc = new GridBagConstraints();
//                    gbc.gridx = 0;
//                    gbc.weightx = 1;
//                    gbc.fill = GridBagConstraints.HORIZONTAL;
//                    friendList.add(newFriend, gbc);
//                    gbc = new GridBagConstraints();
//                    gbc.gridx = 1;
                    newFriendPanel.add(newFriendDeleteButton, BorderLayout.EAST);
                    newFriendPanel.add(newFriend, BorderLayout.CENTER);
                    friendListPanel.add(newFriendPanel);
                    friendListPanel.revalidate();
                    friendListPanel.repaint();
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
                   String message = chatInput.getText();
                   if (!message.isBlank())
                   {
                       if (message.charAt(0) == 'o')
                            conView.addMessage(message.substring(1), true);
                       else
                           conView.addMessage(message, false);
                       chatInput.setText("");
                       conView.revalidate();
                       conView.repaint();
                   }
               }
           }
       });
       conversationViewer.addComponentListener(new ComponentAdapter()
       {
           @Override
           public void componentResized(ComponentEvent e)
           {
               super.componentResized(e);
               conView.resizeConversation();
           }
       });
   }


    private void createUIComponents()
    {
        groupListPanel = new JPanel();
        groupListPanel.setLayout(new GridLayout(20, 1));
        friendListPanel = new JPanel();
        friendListPanel.setLayout(new GridLayout(20, 1));

        conversationViewer = new ConversationViewer();
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
        conversationViewer.setBorder(border);
    }
}
