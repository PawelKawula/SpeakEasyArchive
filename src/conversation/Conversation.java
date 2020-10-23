package conversation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Conversation
{
    private LinkedHashMap<Boolean, Bubble> messages;
    private Dimension outerMargin;
    private Dimension bubbleInnerMargin;
    private int usernameToBubbleDistance;
    private int bubbleInterval;
    private Color myColor;
    private int lastMessageBottom;
    private JPanel conversationPanel;

    public Conversation(JPanel conversationPanel)
    {
        messages = new LinkedHashMap<>();
        this.outerMargin = new Dimension(20, 20);
        this.bubbleInnerMargin = new Dimension(10, 10);
        this.bubbleInterval = 10;
        this.myColor = Color.CYAN;
        this.conversationPanel = conversationPanel;
    }

    public void setBubbleInnerMargin(Dimension bubbleInnerMargin)
    {
        this.bubbleInnerMargin = bubbleInnerMargin;
        for (Bubble bubble : messages.values())
            bubble.setInnerMargin(bubbleInnerMargin);
    }

    public void addMessage(String text, boolean me)
    {
        Graphics2D g2 = (Graphics2D) this.conversationPanel.getGraphics();
        Dimension position = new Dimension(lastMessageBottom + bubbleInterval, this.outerMargin.width);
        Bubble newMessage = new Bubble(position, bubbleInnerMargin, text, me);
        if (me)
        {
            newMessage.positionAsMyBubble(conversationPanel, outerMargin.width);
            newMessage.setBgColor(myColor);
            this.messages.put(true, newMessage);
        }
        else
            this.messages.put(false, newMessage);
        Rectangle messageBox = newMessage.getBox().getBounds();
        lastMessageBottom = messageBox.y + messageBox.height;
    }

    public void paintConversation()
    {
        Graphics2D g2 = (Graphics2D) this.conversationPanel.getGraphics();
        for (Bubble bubble : messages.values())
            bubble.drawRound(g2);
    }
}
