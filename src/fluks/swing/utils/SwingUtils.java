package fluks.swing.utils;

import java.awt.AWTException;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.Window;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

public class SwingUtils {
    /** Get range of the slider. |slider.max - slider.min|.
     * @param slider
     * @return The range of the slider.
     */
    public static int getSliderRange(JSlider slider) {
        return Math.abs(slider.getMaximum() - slider.getMinimum());
    }

    /** Get middle value of a slider component.
     * @param slider
     * @return
     */
    public static int getSliderMiddle(JSlider slider) {
        return (slider.getMaximum() - slider.getMinimum()) / 2;
    }

    /**
     * @param title
     * @param message
     * @param appName
     * @param icon
     * @param expire
     * @throws MalformedURLException
     * @throws AWTException
     */
    public static void showDesktopNotification(String title, String message, String appName, String icon, Integer expire) throws MalformedURLException, AWTException {
        title = title != null ? title : "";
        message = message != null ? message : "";
        appName = appName != null ? "--app-name=" + appName : "";
        icon = icon != null ? "--icon=" + icon : "";
        var _expire = expire != null ? "--expire-time=" + expire.toString() : "";

        var os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux")) {
            try {
                var args = new ArrayList<String>(List.of(
                        "notify-send",
                        "--urgency=normal",
                        appName,
                        _expire,
                        icon,
                        title,
                        message));
                args.removeIf((s) -> s.isBlank());
                var builder = new ProcessBuilder(args);
                builder.inheritIO().start();
            }
            catch (IOException ex) {
                Logger.getLogger(SwingUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (os.contains("mac")) {
            try {
                var builder = new ProcessBuilder(
                    "osascript", "-e",
                    "display notification \"" + message + "\""
                        + " with title \"" + title + "\"");
                builder.inheritIO().start();
            }
            catch (IOException ex) {
                Logger.getLogger(SwingUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (SystemTray.isSupported()) {
            var tray = SystemTray.getSystemTray();
            var image = Toolkit.getDefaultToolkit().createImage(new URL(icon));
            var trayIcon = new TrayIcon(image, title);
            trayIcon.setImageAutoSize(true);
            tray.add(trayIcon);
            trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
        }
    }

    /**
     * @param component
     * @param period In milliseconds.
     * @return Timer to cancel blinking.
     */
    public static Timer blinkComponent(JComponent component, int period) {
        var bg = component.getBackground();
        var fg = component.getForeground();
        var t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            private boolean b = true;
            @Override
            public void run() {
                var c = b ? bg : fg;
                b = !b;
                SwingUtilities.invokeLater(() -> {
                    component.setForeground(c);
                });
            }
        }, 0, period);

        return t;
    }

    /** Calculate such a point for a window, that when positioned on that
     * point, the window will be in the center of the screen.
     * @param w
     * @return Point for centering a window.
     */
    public static Point getScreenCenterForWindow(Window w) {
        var height = w.getBounds().getHeight();
        var width = w.getBounds().getWidth();
        var center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        center.setLocation(center.getX() - width / 2, center.getY() - height / 2);

        return center;
    }
}
