package fluks.timerng;

import fluks.timerng.sound.AbstractClipWrapper;
import fluks.timerng.sound.Alarm;
import fluks.timerng.sound.Beep;
import fluks.timerng.sound.NoSound;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

class Global {
    private static Sound sound;
    public static final String PROGRAM_NAME = "Timer-ng";
    public static final int RESOLUTION_NOT_SET = -1;

    public record AlarmTab(int volume, boolean muted, List<Map<TimeUnits, TimeUnits>> alarms) {}
    public record TimerTab(int volume, boolean muted, boolean interval, TimeUnits time, TimeUnits timer) {}
    public record StopwatchTab(List<TimeUnits> laps, TimeUnits time) {}
    public record TimezonesTab(List<ZoneId> zoneIds) {}
    public record Resolution(int width, int height) {}


    static class Sound {
        private AbstractClipWrapper beep;
        private AbstractClipWrapper alarm;
        private static final String BEEP_FILE = "/resources/beep.wav";
        private static final String ALARM_FILE = "/resources/alarm.wav";

        {
            beep = new Beep();
            beep = loadSound(beep, BEEP_FILE);
            alarm = new Alarm();
            alarm = loadSound(alarm, ALARM_FILE);
        }

        public AbstractClipWrapper getAlarm() {
            return alarm;
        }

        public AbstractClipWrapper getBeep() {
            return beep;
        }

        /** Load a sound object by opening a clip for it.
         * @param cw An object, which clip will be opened.
         * @param resource A path to resource. cw opens a clip to this resource.
         * @return An object, which clip is opened to the resource, or if something
         * fails, a {@link fi.fluks.NoSound NoSound} instance, which doesn't do
         * anything.
         */
        private AbstractClipWrapper loadSound(AbstractClipWrapper cw, String resource) {
            try (var is = getClass().getResourceAsStream(resource)) {
                if (is == null) {
                    throw new Exception("Can't get system resource, " + resource);
                }
                cw.openClip(is);
            }
            catch (Exception e) {
                cw.closeClip();
//                JOptionPane.showMessageDialog(this,
//                    "Failed to load audio.\n\n" + e.getMessage());
                cw = new NoSound();
            }

            return cw;
        }
    }

    public static Sound getSoundInstance() {
        if (sound == null) {
            sound = new Sound();
        }
        return sound;
    }
}
