package ui;


import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class EmojiFont {

    private File notoFont;
    private Font font;

    public EmojiFont() {
        this.notoFont = new File("res/fonts/NotoEmoji.ttf");
        try {
            this.font = Font.createFont(Font.TRUETYPE_FONT, notoFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        } 
    }

    public Font getEmojiFont(float size) {
        return this.font.deriveFont(size);
    }

}
