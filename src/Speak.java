public class Speak implements Runnable {
    String message;

    public Speak(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            speak(this.message);
        } catch (Exception e) {
            System.out.println("HANDLE EXCEPTION");
        }
    }
    
     void speak(String words) throws Exception {
        if (System.getProperty("os.name").contains("Windows")) {
            String cmd = "PowerShell -Command \"Add-Type -AssemblyName System.Speech; (New-Object System.Speech.Synthesis.SpeechSynthesizer).Speak('"
                    + words + "');\"";
            Runtime.getRuntime().exec(cmd).waitFor();
        } else {
            Runtime.getRuntime().exec("say " + words).waitFor();
        }
    }
}
