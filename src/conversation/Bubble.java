package conversation;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Bubble
{
    private Rectangle2D box;
    private ArrayList<String> text;
    private boolean update;
    private ConversationViewer conversationViewer;
    boolean me;

    public Bubble(ConversationViewer conversationViewer, Dimension position, String text, boolean me)
    {
        this.conversationViewer = conversationViewer;
        this.text = new ArrayList<>();
        this.text.add(text);
        this.box = new Rectangle2D.Double(position.width, position.height, 0, 0);
        this.update = true;
        this.me = me;
        Graphics2D g2 = (Graphics2D) this.conversationViewer.getGraphics();
        Font font = g2.getFont();
        FontRenderContext context = g2.getFontRenderContext();
        this.breakLine(font, context);
    }

    public void setBox(Rectangle2D box)
    {
        this.box = box;
    }

    public Rectangle2D getBox()
    {
        return box;
    }

    public ArrayList<String> getText()
    {
        return text;
    }

    public void breakLine(Font font, FontRenderContext context)
    {
        int lineBreak = this.conversationViewer.getLineBreak();
        Dimension innerMargin = this.conversationViewer.getBubbleInnerMargin();
        StringBuilder stringBuilder = new StringBuilder();
        for (String row : this.text)
            stringBuilder.append(row);
        String jointRows = stringBuilder.toString();
        Rectangle2D bounds = font.getStringBounds(jointRows, context);
        double currentWidth = bounds.getWidth();
        int nrOfLines = (int)(currentWidth / lineBreak) + 1;
        int supposedCharsPerLine = (int)(jointRows.length() / nrOfLines);
        System.out.println("supposedCharsPerLine=" + supposedCharsPerLine);
        System.out.println("jointrows.length()=" + jointRows.length());
        System.out.println("nrOfLines=" + nrOfLines);
        System.out.println("currentWidth=" + currentWidth);
        System.out.println("lineBreak");

        int i = 0;
        int boxWidth = innerMargin.width;
        int boxHeight = innerMargin.height;
        int maxLineWidth = 0;
        this.text = new ArrayList<>();
        while (i < jointRows.length())
        {
            supposedCharsPerLine = (i + supposedCharsPerLine < jointRows.length()) ? supposedCharsPerLine : jointRows.length() - i;
            text.add(jointRows.substring(i, i + supposedCharsPerLine));
            i += supposedCharsPerLine;
            System.out.println(i);

            Rectangle2D rowBounds = font.getStringBounds(text.get(text.size() - 1), context);
            if (rowBounds.getWidth() > maxLineWidth)
                maxLineWidth = (int)rowBounds.getWidth();
            boxHeight += rowBounds.getHeight();
        }
        System.out.println("Wyjscie z petli");
        boxWidth += maxLineWidth;
        this.box = new Rectangle2D.Double(box.getX(), box.getY(), boxWidth, boxHeight);
    }

    public void drawRound(Graphics2D g2)
    {
        if (update)
        {
            Font font = g2.getFont();
            FontRenderContext context = g2.getFontRenderContext();
            breakLine(font, context);
            update = false;
        }
        g2.drawRoundRect((int) box.getX(), (int) box.getY(), (int) box.getWidth(), (int) box.getHeight(), 10, 10);
        drawText(g2);
    }

    private void drawText(Graphics2D g2)
    {
        Font font = g2.getFont();
        Dimension innerMargin = this.conversationViewer.getBubbleInnerMargin();
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(this.text.get(0), context);
        int x = (int) (this.box.getX() + innerMargin.width / 2), y = (int) (this.box.getY() - bounds.getY() + innerMargin.height / 2);
        for (int i = 0; i < this.text.size(); ++i)
            g2.drawString(this.text.get(i), x, (int) (y + i * bounds.getHeight()));
    }

    public void drawSharp(Graphics2D g2)
    {
        if (update)
        {
            Font font = g2.getFont();
            FontRenderContext context = g2.getFontRenderContext();
            breakLine(font, context);
            update = false;
        }
        g2.draw(this.box);
        drawText(g2);
    }

    public void positionAsMyBubble()
    {
        int panelWidth = this.conversationViewer.getWidth();
        int panelInnerMarginWidth = this.conversationViewer.getOuterMargin().width;
        this.box.setFrame(panelWidth - panelInnerMarginWidth - this.box.getWidth(),
                this.box.getY(), this.box.getWidth(), this.box.getHeight());
    }
}
