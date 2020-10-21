import java.awt.*;
import gui.*;

import javax.swing.*;

public class SpeakEasy
{
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
        catch (Exception ex)
        {
            System.out.println("No GTKLookAndFeel, reseting to default...");
        }
        EventQueue.invokeLater(() ->
        {
            SpeakEasyGUI frame = new SpeakEasyGUI("Speak Easy");
            frame.setVisible(true);
        });

    }
}
