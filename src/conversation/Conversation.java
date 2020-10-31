package conversation;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Conversation
{
    private HashMap<String, ArrayList<Bubble>> bubbles;
    private ConversationViewer conversationViewer;
    private int lastMessageBottom;

    public Conversation(ConversationViewer conversationViewer)
    {
        this.conversationViewer = conversationViewer;
        bubbles = new HashMap<>();
        bubbles.put("me", new ArrayList<>());
        bubbles.put("other", new ArrayList<>());
        lastMessageBottom = conversationViewer.getOuterMargin().height - conversationViewer.getBubbleInterval();
    }

    public int getLastMessageBottom()
    {
        return lastMessageBottom;
    }

    public void addMessage(Font font, FontRenderContext context, String text, boolean me)
    {
        int bubbleInterval = this.conversationViewer.getBubbleInterval();
        int outerMarginWidth = this.conversationViewer.getOuterMargin().width;
        Dimension bubbleInnerMargin = this.conversationViewer.getBubbleInnerMargin();
        Dimension position = new Dimension(outerMarginWidth, lastMessageBottom + bubbleInterval);
        Bubble newMessage = new Bubble(this.conversationViewer, position, text, me);
        if (me)
        {
            newMessage.positionAsMyBubble();
            this.bubbles.get("me").add(newMessage);
        }
        else
            this.bubbles.get("other").add(newMessage);
        Rectangle messageBox = newMessage.getBox().getBounds();
        lastMessageBottom = messageBox.y + messageBox.height;
    }

    public void paintConversation(Graphics2D g2)
    {
        for (ArrayList<Bubble> messages : bubbles.values())
            for (Bubble bubble : messages)
                bubble.drawRound(g2);
    }

    public void moveConversationAfterResize()
    {
        ArrayList<Bubble> myBubbles = bubbles.get("me");
        Dimension pos;
        for (Bubble myBubble : myBubbles)
        {
            pos = new Dimension(this.conversationViewer.getWidth() - (int)myBubble.getBox().getWidth() - this.conversationViewer.getOuterMargin().width,
                    (int)myBubble.getBox().getY());
            myBubble.getBox().setFrame(pos.width, pos.height, myBubble.getBox().getWidth(), myBubble.getBox().getHeight());
        }
    }
}
