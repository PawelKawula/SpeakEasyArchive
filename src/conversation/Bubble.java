package conversation;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Bubble
{
    private Dimension innerMargin;
    private Rectangle2D box;
    private ArrayList<String> text;
    private Color bgColor;
    private Color fontColor;

    public void setFont(Font font)
    {
        this.font = font;
    }

    private Font font;

    public Bubble(Graphics2D g2, Dimension position, Dimension innerMargin, String text, boolean me)
    {
        this.innerMargin = innerMargin;
        this.text = new ArrayList<>();
        this.text.add(text);
        this.bgColor = Color.BLUE;
        this.fontColor = Color.BLACK;
        this.font = new Font("Comic Sans", Font.PLAIN, 12);

        breakLine(g2, 200);
    }

    public Color getBgColor()
    {
        return bgColor;
    }

    public void setBgColor(Color bgColor)
    {
        this.bgColor = bgColor;
    }

    public void setBox(Rectangle2D box)
    {
        this.box = box;
    }

    public void setInnerMargin(Dimension innerMargin)
    {
        this.innerMargin = innerMargin;
    }

    public Rectangle2D getBox()
    {
        return box;
    }

    public ArrayList<String> getText()
    {
        return text;
    }

    public void update()
    {

    }

    public void breakLine(Graphics2D g2, int lineWidth)
    {
        Font prevFont = g2.getFont();
        g2.setFont(this.font);

        FontRenderContext context = g2.getFontRenderContext();
        StringBuilder stringBuilder = new StringBuilder();
        for (String row : this.text)
            stringBuilder.append(row);
        String jointRows = stringBuilder.toString();
        Rectangle2D bounds = font.getStringBounds(jointRows, context);
        double currentWidth = bounds.getWidth();
        double lineBreakPercentage = currentWidth / lineWidth;

        int supposedCharsPerLine = (int)(jointRows.length() * lineBreakPercentage);
        int i = 0;
        int boxWidth = 0;
        int boxHeight = innerMargin.height;
        int maxLineWidth = 0;
        this.text = new ArrayList<>();
        while (i < jointRows.length())
        {
            supposedCharsPerLine = (i + supposedCharsPerLine < jointRows.length()) ? supposedCharsPerLine : jointRows.length() - 1;
            text.add(jointRows.substring(i, i + supposedCharsPerLine));
            i += supposedCharsPerLine;

            Rectangle2D rowBounds = font.getStringBounds(text.get(text.size() - 1), context);
            if (rowBounds.getWidth() > maxLineWidth)
                maxLineWidth = (int)rowBounds.getWidth();
            boxHeight += rowBounds.getHeight();
        }
        boxWidth += innerMargin.width;
        this.box = new Rectangle2D.Double(box.getX(), box.getY(), boxWidth, boxHeight);

    }

    public void drawRound(Graphics2D g2)
    {
        g2.drawRoundRect((int) box.getX(), (int) box.getY(), (int) box.getWidth(), (int) box.getHeight(), 2, 2);
        Font prevFont = g2.getFont();
        g2.setFont(this.font);
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = this.font.getStringBounds(this.text.get(0), context);
        int x = (int) (this.box.getX() + this.innerMargin.width), y = (int) (this.box.getY() + this.innerMargin.height);
        for (int i = 0; i < this.text.size(); ++i)
            g2.drawString(this.text.get(i), x, (int) (y + i * bounds.getHeight()));
    }

    public void drawSharp(Graphics2D g2)
    {
        g2.draw(this.box);
        Font prevFont = g2.getFont();
        g2.setFont(this.font);
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = this.font.getStringBounds(this.text.get(0), context);
        int x = (int) (this.box.getX() + this.innerMargin.width), y = (int) (this.box.getY() + this.innerMargin.height);
        for (int i = 0; i < this.text.size(); ++i)
            g2.drawString(this.text.get(i), x, (int) (y + i * bounds.getHeight()));
    }
}