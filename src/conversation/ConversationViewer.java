package conversation;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class ConversationViewer extends JPanel
{
    LinkedHashMap<String, Conversation> conversationMap;
    private String currentConversation;
    private Dimension outerMargin;
    private Dimension bubbleInnerMargin;
    private int bubbleInterval;
    private Color myColor;
    private Bubble testBubble;

    public ConversationViewer()
    {
        testBubble = new Bubble(new Dimension(100, 100), new Dimension(20, 20), "Test", true);
        conversationMap = new LinkedHashMap<>();
        currentConversation = "test";
    }

    public void addMessage(String text, boolean me)
    {
        if (!conversationMap.containsKey(currentConversation))
            conversationMap.put(currentConversation, new Conversation(this));
        conversationMap.get(currentConversation).addMessage(text, true);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        testBubble.drawRound(g2);
//        if (conversationMap.containsKey(currentConversation))
//            conversationMap.get(currentConversation).paintImmediately();

    }
}
