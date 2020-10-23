package conversation;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class ConversationSingleton
{
    Map<String, Conversation> conversationMap;
    private String currentConversation;
    private Dimension innerMargin;
    private JPanel conversationPanel;

    public ConversationSingleton(JPanel conversationPanel)
    {
        this.conversationPanel = conversationPanel;
        conversationMap = new TreeMap<>();
        Dimension bubbleInnerMargin = new Dimension(20, 20);
        innerMargin = new Dimension(20, 20);
        Conversation testConversation = new Conversation(conversationPanel, bubbleInnerMargin, innerMargin, Color.CYAN, 20);
        conversationMap.put("Test", testConversation);
        currentConversation = "Test";
    }

    public void addMessage(String text, boolean me)
    {
        conversationMap.get(currentConversation).addMessage(text, true);
    }

    public Dimension getInnerMargin()
    {
        return innerMargin;
    }

    public void setInnerMargin(Dimension innerMargin)
    {
        this.innerMargin = innerMargin;
    }

    public void paintConversation()
    {
        conversationMap.get(currentConversation).paintConversation();
    }

    @Override
    public String toString()
    {
        return "ConversationSingleton{" +
                "conversationMap=" + conversationMap +
                ", currentConversation='" + currentConversation + '\'' +
                ", innerMargin=" + innerMargin +
                ", conversationPanel=" + conversationPanel +
                '}';
    }
}
