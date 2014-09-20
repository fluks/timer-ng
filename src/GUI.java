
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GUI extends javax.swing.JFrame {
    final private TimeUnits time;
    final private TimeUnits targetTime;
    private Timer timer;
    private boolean timerIsRunning = false;
    private int volume = 50;
    private Beep beep;
    private Alarm alarm;

    public GUI() {
        time = new TimeUnits();
        targetTime = new TimeUnits();
        
        initComponents();

        try {
            File file = new File(
                ClassLoader.getSystemResource("resources/beep.wav").toURI());
            beep = Beep.getInstance(file);
            beep.start();

            file = new File(
                ClassLoader.getSystemResource("resources/alarm.wav").toURI());
            alarm = new Alarm(file);
        }
        catch (UnsupportedAudioFileException | LineUnavailableException |
            IOException | URISyntaxException e) {
            System.err.println(e.getMessage());
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

        jMenu3 = new javax.swing.JMenu();
        timeLabel = new javax.swing.JLabel();
        intervalCheckbox = new javax.swing.JCheckBox();
        startStopButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        muteCheckbox = new javax.swing.JCheckBox();
        volumeSlider = new javax.swing.JSlider();
        volumeLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        millisecondSpinner = new javax.swing.JSpinner();
        minuteSpinner = new javax.swing.JSpinner();
        hourSpinner = new javax.swing.JSpinner();
        secondSpinner = new javax.swing.JSpinner();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        quitMenuItem = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        timeLabel.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("00:00:00:000");

        intervalCheckbox.setMnemonic('i');
        intervalCheckbox.setText("Interval");
        intervalCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intervalCheckboxActionPerformed(evt);
            }
        });

        startStopButton.setMnemonic('s');
        startStopButton.setText("Start/Stop");
        startStopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStopButtonActionPerformed(evt);
            }
        });

        resetButton.setMnemonic('r');
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        muteCheckbox.setMnemonic('m');
        muteCheckbox.setText("Mute");
        muteCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteCheckboxActionPerformed(evt);
            }
        });

        volumeSlider.setOrientation(javax.swing.JSlider.VERTICAL);
        volumeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                volumeSliderStateChanged(evt);
            }
        });

        volumeLabel.setIcon(new javax.swing.ImageIcon("/usr/share/icons/Mint-X-Dark/status/22/stock_volume.png")); // NOI18N

        millisecondSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 999, 1));
        millisecondSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                millisecondSpinnerStateChanged(evt);
            }
        });

        minuteSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        minuteSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                minuteSpinnerStateChanged(evt);
            }
        });

        hourSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        hourSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                hourSpinnerStateChanged(evt);
            }
        });

        secondSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        secondSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                secondSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hourSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minuteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secondSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(millisecondSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {hourSpinner, millisecondSpinner, minuteSpinner, secondSpinner});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(hourSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minuteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(millisecondSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {hourSpinner, millisecondSpinner, minuteSpinner, secondSpinner});

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        quitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        quitMenuItem.setMnemonic('q');
        quitMenuItem.setText("Quit");
        quitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(quitMenuItem);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volumeLabel)
                    .addComponent(muteCheckbox))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(startStopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(intervalCheckbox))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {resetButton, startStopButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(intervalCheckbox)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(volumeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startStopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(muteCheckbox))
                .addGap(26, 26, 26))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {resetButton, startStopButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void muteCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteCheckboxActionPerformed
        if (muteCheckbox.isSelected())
            volumeSlider.setValue(0);
        else {
            volume = 50;    
            volumeSlider.setValue(volume);
        }
    }//GEN-LAST:event_muteCheckboxActionPerformed

    private void intervalCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intervalCheckboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_intervalCheckboxActionPerformed

    private void quitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitMenuItemActionPerformed

    private void startStopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStopButtonActionPerformed
        if (!timerIsRunning) {
            timerIsRunning = true;
            boolean isIntervalSelected = intervalCheckbox.isSelected();
            boolean isTargetTimeSet = isTargetTimeSet();
            intervalCheckbox.setEnabled(false);
            setSpinnersEnabled(false);

            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (isTargetTimeSet &&
                        !isIntervalSelected &&
                        TimeUnits.timeUnitsAreEqual(time, targetTime)) {
                        timer.cancel();
                        alarm.play();
                        
                        intervalCheckbox.setEnabled(true);
                        setSpinnersEnabled(true);
                    }
                    else {
                        time.advance();
                        timeLabel.setText(time.toString());
                    }

                    if (isIntervalSelected &&
                        isModuloTime(time, targetTime)) {
                        beep.play();
                    }
                }
            }, 1, 1);
        }
        else {
            timer.cancel();
            timerIsRunning = false;
            alarm.stopAndRewind();
            
            intervalCheckbox.setEnabled(true);
            setSpinnersEnabled(true);
        }
    }//GEN-LAST:event_startStopButtonActionPerformed

    private boolean isModuloTime(TimeUnits t1, TimeUnits t2) {
        long t2Ms = t2.timeInMilliseconds();
        // Illegal division by zero. 
        if (t2Ms == 0)
            return false;
        return t1.timeInMilliseconds() % t2Ms == 0;
    }

    private boolean isTargetTimeSet() {
        return ((Integer) hourSpinner.getValue()) != 0 ||
               ((Integer) minuteSpinner.getValue()) != 0 ||
               ((Integer) secondSpinner.getValue()) != 0 ||
               ((Integer) millisecondSpinner.getValue()) != 0;
    }

    private void setSpinnersEnabled(boolean b) {
        hourSpinner.setEnabled(b);
        minuteSpinner.setEnabled(b);
        secondSpinner.setEnabled(b);
        millisecondSpinner.setEnabled(b);
    }

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        timer.cancel();
        time.reset();
        targetTime.reset();
        timeLabel.setText(time.toString());
        hourSpinner.setValue(0);
        minuteSpinner.setValue(0);
        secondSpinner.setValue(0);
        millisecondSpinner.setValue(0);
        intervalCheckbox.setEnabled(true);
        setSpinnersEnabled(true);
        alarm.stopAndRewind();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void hourSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_hourSpinnerStateChanged
        targetTime.setHours((Integer) hourSpinner.getValue());
    }//GEN-LAST:event_hourSpinnerStateChanged

    private void minuteSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_minuteSpinnerStateChanged
        targetTime.setMinutes((Integer) minuteSpinner.getValue());
    }//GEN-LAST:event_minuteSpinnerStateChanged

    private void secondSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_secondSpinnerStateChanged
        targetTime.setSeconds((Integer) secondSpinner.getValue());
    }//GEN-LAST:event_secondSpinnerStateChanged

    private void millisecondSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_millisecondSpinnerStateChanged
        targetTime.setMilliseconds((Integer) millisecondSpinner.getValue());
    }//GEN-LAST:event_millisecondSpinnerStateChanged

    private void volumeSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_volumeSliderStateChanged
        volume = volumeSlider.getValue();
//        beep.setVolume(volume, volumeSlider.getExtent());
        if (volume == 0)
            muteCheckbox.setSelected(true);
    }//GEN-LAST:event_volumeSliderStateChanged

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu fileMenu;
    private javax.swing.JSpinner hourSpinner;
    private javax.swing.JCheckBox intervalCheckbox;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner millisecondSpinner;
    private javax.swing.JSpinner minuteSpinner;
    private javax.swing.JCheckBox muteCheckbox;
    private javax.swing.JMenuItem quitMenuItem;
    private javax.swing.JButton resetButton;
    private javax.swing.JSpinner secondSpinner;
    private javax.swing.JButton startStopButton;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel volumeLabel;
    private javax.swing.JSlider volumeSlider;
    // End of variables declaration//GEN-END:variables
}