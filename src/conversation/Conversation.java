package conversation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Conversation extends JPanel
{
    private ArrayList<Bubble> bubbles;
    private Dimension bubbleInnerMargin;

    public Conversation(Font font, Dimension dimension)
    {
        bubbles = new ArrayList<>();
    }

    public void setBubbleInnerMargin(Dimension bubbleInnerMargin)
    {
        this.bubbleInnerMargin = bubbleInnerMargin;
        for (Bubble bubble : bubbles)
            bubble.setInnerMargin(bubbleInnerMargin);
    }

    public void addMessage(String text)
    {
    }
}
