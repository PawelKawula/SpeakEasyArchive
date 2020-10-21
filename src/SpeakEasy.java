import java.awt.*;
import gui.*;

public class SpeakEasy
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            SpeakEasyGUI frame = new SpeakEasyGUI("Speak Easy");
            frame.setVisible(true);
        });

    }
}
