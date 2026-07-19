package fluks.timerng;

import fluks.timerng.Settings.ActiveTab;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox.KeySelectionManager;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 */
public class TimezonesTab extends javax.swing.JPanel implements Tab {
    private Timer timer;
    private final ActiveTab tab = Settings.ActiveTab.TIMEZONES_TAB;
    private final GridBagConstraints gbc = new GridBagConstraints();
    private Map<TZObject, JLabel> tzs = new HashMap();
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     */
    public TimezonesTab() {
        initComponents();
        gbc.insets = new Insets(2, 4, 2, 4);
        gbc.anchor = GridBagConstraints.WEST;

        addTimeZones();
        startClockTimer();
        timezonesComboBox.setKeySelectionManager(new ZoneKeySelectionManager());
        getState();
        addSelectedTimeZones();
    }

    /**
     * @return 
     */
    @Override
    public ActiveTab getTab() {
        return tab;
    }

    /**
     */
    private void startClockTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(TimezonesTab.this::updateAllClocks);
            }
        }, 0, 1000);
    }

    /**
     */
    private void updateAllClocks() {
        tzs.forEach((t, l) -> {
            var now = ZonedDateTime.now(t.getId());
            l.setText(TIME_FORMAT.format(now));
        });
    }

    /**
     * @param row
     */
    private void pushTimezonesToTop(int row) {
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        timezonesPanel.add(Box.createVerticalGlue(), gbc);
    }

    /**
     * @param tz
     */
    private void addTimeZoneRow(TZObject tz, int row) {
        gbc.gridy = row;

        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        timezonesPanel.add(new JLabel(tz.toString()), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;

        var now = ZonedDateTime.now(tz.getId());
        var timeLabel = new JLabel(TIME_FORMAT.format(now));
        tzs.put(tz, timeLabel);
        timezonesPanel.add(timeLabel, gbc);

        gbc.gridx = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        timezonesPanel.add(Box.createGlue(), gbc);

        gbc.gridx = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        var removeButton = new JButton();
        removeButton.setIcon(new ImageIcon(getClass().getResource("/resources/remove.png"))); // NOI18N
        removeButton.setToolTipText("Remove");
        removeButton.setBorderPainted(false);
        removeButton.setContentAreaFilled(false);
        removeButton.setFocusPainted(false);
        removeButton.addActionListener((ae) -> {
            tzs.remove(tz);
            timezonesPanel.removeAll();
            addSelectedTimeZones();
        });
        timezonesPanel.add(removeButton, gbc);
    }

    /**
     */
    private void addSelectedTimeZones() {
        int row = 0;
        for (var t : tzs.keySet()) {
            addTimeZoneRow(t, row++);
        }
        pushTimezonesToTop(row);
        timezonesPanel.revalidate();
        timezonesPanel.repaint();
    }

    /**
     */
    private void addTimeZones() {
        var ids = new TreeSet<>(ZoneId.getAvailableZoneIds());
        for (var id : ids) {
            var tz = ZoneId.of(id);
            timezonesComboBox.addItem(new TZObject(tz));
        } 
    }

    /**
     */
    public void saveState() {
        Settings.INSTANCE.setTimezones(tzs.keySet().stream()
            .map((t) -> t.getId()).toList());
    }

    /**
     */
    private void getState() {
        tzs = Settings.INSTANCE.getTimezones().stream()
            .collect(Collectors.toMap(
                id -> new TZObject(id),
                id -> new JLabel()
            ));
    }

    /**
     */
    private final class TZObject {
        private ZoneId id;

        /**
         * @param id 
         */
        public TZObject(ZoneId id) {
            this.id = id;
        }

        /**
         * @return
         */
        public ZoneId getId() {
            return id;
        }

        /**
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof TZObject other)) {
                return false;
            }
            return id.equals(other.id);
        }

        /**
         * @return
         */
        @Override
        public int hashCode() {
            return id.hashCode();
        }

        /**
         * @return 
         */
        @Override
        public String toString() {
            var now = ZonedDateTime.now(id);
            return now.getOffset().toString() + " " + id;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        timezonesComboBox = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        timeZonesScrollPane = new javax.swing.JScrollPane();
        timezonesPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        resetButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 1, 20, 1));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        timezonesComboBox.setToolTipText("Type to search");
        jPanel5.add(timezonesComboBox);

        addButton.setMnemonic('a');
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel5.add(addButton);

        jPanel2.add(jPanel5);

        jPanel3.setMinimumSize(new java.awt.Dimension(67, 32));
        jPanel3.setPreferredSize(new java.awt.Dimension(420, 44));
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setLayout(new java.awt.CardLayout());

        timeZonesScrollPane.setToolTipText("Type to search");

        timezonesPanel.setLayout(new java.awt.GridBagLayout());
        timeZonesScrollPane.setViewportView(timezonesPanel);

        jPanel3.add(timeZonesScrollPane, "card2");

        jPanel2.add(jPanel3);

        resetButton.setMnemonic('r');
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        jPanel4.add(resetButton);

        jPanel2.add(jPanel4);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param evt 
     */
    private void addActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        var tz = (TZObject) timezonesComboBox.getSelectedItem();
        tzs.put(tz, null);
        timezonesPanel.removeAll();
        addSelectedTimeZones();

        var bar = timeZonesScrollPane.getVerticalScrollBar();
        SwingUtilities.invokeLater(() -> {
            bar.setValue(bar.getMaximum());
        });
    }//GEN-LAST:event_addActionPerformed

    /**
     * @param evt 
     */
    private void resetButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        tzs = new HashMap<>();
        timezonesPanel.removeAll();
        timezonesPanel.revalidate();
        timezonesPanel.repaint();
    }//GEN-LAST:event_resetButtonActionPerformed

    /**
     */
    private static final class ZoneKeySelectionManager implements KeySelectionManager {
        private String pattern = "";
        private long lastKeyTime = 0;

        /**
         * @param keyChar
         * @param model
         * @return 
         */
        @Override
        public int selectionForKey(char keyChar, ComboBoxModel model) {
            long now = System.currentTimeMillis();
            // Reset if paused too long.
            pattern = (now - lastKeyTime > 1000) ? "" : pattern;
            pattern += Character.toLowerCase(keyChar);
            lastKeyTime = now;

            int match = findMatch(model, pattern);
            if (match == -1 && pattern.length() > 1) {
                // No match on the accumulated string, restart with just this key.
                pattern = String.valueOf(Character.toLowerCase(keyChar));
                match = findMatch(model, pattern);
            }
            return match;
        }

        /**
         * @param model
         * @param prefix
         * @return 
         */
        private int findMatch(ComboBoxModel model, String prefix) {
            for (int i = 0; i < model.getSize(); i++) {
                var item = (TZObject) model.getElementAt(i);
                if (item.getId().getId().toLowerCase().contains(prefix)) {
                    return i;
                }
            }
            return -1;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton resetButton;
    private javax.swing.JScrollPane timeZonesScrollPane;
    private javax.swing.JComboBox<TZObject> timezonesComboBox;
    private javax.swing.JPanel timezonesPanel;
    // End of variables declaration//GEN-END:variables
}