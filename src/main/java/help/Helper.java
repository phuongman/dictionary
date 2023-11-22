package help;

import javax.sound.sampled.*;
import java.util.Objects;

public class Helper {

    /**
     * chuẩn hóa lại xâu.
     */

    public static String stringNormalization(String word, boolean isWord) {
        if(word == null) return null;
        String res = word.trim().replaceAll("\\s+", " ");
        if(isWord) res = res.toLowerCase();
        return res;
    }

    public static void playSound(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(Helper.class.getResource(filePath))) ;
            System.out.println(filePath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
