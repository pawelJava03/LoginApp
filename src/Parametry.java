import java.awt.*;

public class Parametry {

    // ścieżka plików
    public static final String LOGIN_IMAGE_PATH = "obrazek/user.png";

    public static final Dimension FRAME_SIZE =  new Dimension(540, 760);
    public static final Dimension TEXT_FIELD_SIZE = new Dimension((int)(FRAME_SIZE.width * 0.80), 50);

    // frame size
    public static final Dimension LOGIN_IMAGE_SIZE = new Dimension(255, 262);
    public static final Dimension BUTTON_SIZE = TEXT_FIELD_SIZE;

    // Jdialog size
    public static final Dimension DIALOG_SIZE = new Dimension((int)(FRAME_SIZE.width/3), (int)(FRAME_SIZE.height/6));
    public static final Dimension REGISTER_LABEL_SIZE = new Dimension(FRAME_SIZE.width, 150);

    //kolorki
    public static Color GLOWNY_KOLOR = new Color(0,0,150);
    public static Color DRUGI_KOLOR = new Color (125,125,125);
}
