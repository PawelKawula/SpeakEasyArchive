package conversation;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
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
    private int lineBreak;

    public ConversationViewer()
    {
//        testBubble = new Bubble(this, new Dimension(100, 100),"Test", true);
        conversationMap = new LinkedHashMap<>();
        outerMargin = new Dimension(20, 20);
        bubbleInnerMargin = new Dimension(10, 10);
        bubbleInterval = 10;
        myColor = Color.CYAN;
        currentConversation = "test";
        lineBreak = 200;
        setBackground(new Color(33, 37, 43));
    }

    public int getLineBreak()
    {
        return lineBreak;
    }

    public FontRenderContext getFontRenderContext()
    {
        Graphics2D g2 = (Graphics2D) this.getGraphics();
        return g2.getFontRenderContext();
    }

    public Dimension getOuterMargin()
    {
        return outerMargin;
    }

    public void setOuterMargin(Dimension outerMargin)
    {
        this.outerMargin = outerMargin;
    }

    public Dimension getBubbleInnerMargin()
    {
        return bubbleInnerMargin;
    }

    public void setBubbleInnerMargin(Dimension bubbleInnerMargin)
    {
        this.bubbleInnerMargin = bubbleInnerMargin;
    }

    public int getBubbleInterval()
    {
        return bubbleInterval;
    }

    public void setBubbleInterval(int bubbleInterval)
    {
        this.bubbleInterval = bubbleInterval;
    }

    public Color getMyColor()
    {
        return myColor;
    }

    public void setMyColor(Color myColor)
    {
        this.myColor = myColor;
    }

    public void addMessage(String text, boolean me)
    {
        if (!conversationMap.containsKey(currentConversation))
            conversationMap.put(currentConversation, new Conversation(this));
        conversationMap.get(currentConversation).addMessage(this.getFont(), this.getFontRenderContext(), text, me);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D background = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
        Color prevColor = g2.getColor();
        g2.setColor(new Color(55, 55, 55));
        g2.fill(background);
        g2.setColor(prevColor);
//        testBubble.drawRound(g2);
        if (conversationMap.containsKey(currentConversation))
            conversationMap.get(currentConversation).paintConversation(g2);

    }

    public void changeConversation(String name)
    {
        currentConversation = name;
    }

    public void resizeConversation()
    {
        if (conversationMap.containsKey(currentConversation))
            conversationMap.get(currentConversation).moveConversationAfterResize();
    }
}
