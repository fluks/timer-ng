package fluks.timerng;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

/**
 */
public class Utils {
    /**
     * @param o
     * @return
     */
    public static String writeObjectToString(Serializable o) {
        var baos = new ByteArrayOutputStream();
        try (var oos = new ObjectOutputStream(baos)) {
            oos.writeObject(o);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException ex) {
            System.getLogger(Settings.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return "";
        }
    }

    /**
     * @param s
     * @return
     */
    public static Serializable readObjectFromString(String s) {
        var decoded = Base64.getDecoder().decode(s);
        try (var ois = new ObjectInputStream(new ByteArrayInputStream(decoded))) {
            return (Serializable) ois.readObject();
        } catch (IOException|ClassNotFoundException ex) {
            System.getLogger(Settings.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return null;
        }
    }
}
