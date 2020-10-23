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

    public Conversation(JPanel conversationPanel, Dimension outerMargin, Dimension bubbleInnerMargin, Color myColor, int bubbleInterval)
    {
        this.messages = new LinkedHashMap<>();
        this.conversationPanel = conversationPanel;
        this.bubbleInnerMargin = bubbleInnerMargin;
        this.myColor = myColor;
        this.outerMargin = outerMargin;
        this.bubbleInterval = bubbleInterval;
        this.usernameToBubbleDistance = 10;
        lastMessageBottom = 0;
    }

    public void setBubbleInnerMargin(Dimension bubbleInnerMargin)
    {
        this.bubbleInnerMargin = bubbleInnerMargin;
        for (Bubble bubble : messages.values())
            bubble.setInnerMargin(bubbleInnerMargin);
    }

    public void addMessage(String text, boolean me)
    {
        Dimension position = new Dimension(lastMessageBottom + bubbleInterval, outerMargin.width);
        Bubble newMessage = new Bubble(this.conversationPanel, position, bubbleInnerMargin, text);
        if (me)
        {
            newMessage.positionAsMyBubble(this.conversationPanel, this.outerMargin.width);
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
        for (Bubble bubble : messages.values())
            bubble.drawRound();
    }
}
