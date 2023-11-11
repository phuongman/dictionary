package services;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {
    /**
     * @param VOICENAME kevin16.
     * @param text text to speak.
     */
    private final String VOICENAME = "kevin16";
    /**
     * @param text text to speak
     */
    private String text;

    /**
     * Constructor.
     */
    public TextToSpeech(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    /**
     * speak text.
     */
    public void speak() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice voice = voiceManager.getVoice(VOICENAME);
        if (voice == null) {
            System.err.println("Cannot find voice: kevin16");
            System.exit(1);
        }
        voice.allocate();
        voice.speak(this.text);
        voice.deallocate();
    }
}