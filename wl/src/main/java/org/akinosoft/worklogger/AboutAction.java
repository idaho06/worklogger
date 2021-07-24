package org.akinosoft.worklogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serial;

public class AboutAction extends AbstractAction {

    private final JFrame frame;
    private final AboutDialog aboutDialog;

    @Serial
    private static final long serialVersionUID = 5_213_670_326_241_168_083L;

    public AboutAction(JFrame frame) {
        this.frame = frame;
        putValue(NAME, "About WorkLogger...");
        putValue(SHORT_DESCRIPTION, "Information about WorkLogger");
        putValue(LONG_DESCRIPTION, "Copyright, contact and source information for this application.");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.KEY_LOCATION_UNKNOWN));
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        this.aboutDialog = new AboutDialog(this.frame);
        SwingUtilities.invokeLater(() -> this.aboutDialog.okButton.requestFocusInWindow());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println("Action [" + e.getActionCommand() + "]!");
        this.aboutDialog.setVisible(true);
    }

    private class AboutDialog extends JDialog {
        public JButton okButton;

        public AboutDialog(JFrame frame) {
            super(frame, "About WorkLogger", true);

            JPanel aboutPanel = new JPanel(new BorderLayout(0, 0)); // main panel with north, south, east, west, center locations
            aboutPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            Icon logoIcon = new ImageIcon(ClassLoader.getSystemResource("wl256.png"));
            JLabel logoLabel = new JLabel(logoIcon, JLabel.CENTER);
            aboutPanel.add(logoLabel, BorderLayout.WEST);

            String newLine = System.getProperty("line.separator");
            String akinoSoft = "Idaho06 from Akinosoft presents WorkLogger v1.0.1." + newLine +
                    "A small text editor that saves your entries in a file, marking the time of each entry." + newLine +
                    newLine +
                    "Follow me on Twitter: @idaho06" + newLine +
                    "Source code: https://github.com/idaho06/worklogger";
            JTextArea akinoText = new JTextArea(akinoSoft);
            akinoText.setEditable(false);
            aboutPanel.add(akinoText, BorderLayout.CENTER);

            okButton = new JButton("Ok");
            aboutPanel.add(okButton, BorderLayout.SOUTH);
            getContentPane().add(aboutPanel, BorderLayout.CENTER);

            okButton.addActionListener(evt -> setVisible(false));

            this.getRootPane().registerKeyboardAction(
                    evt -> setVisible(false),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                    JComponent.WHEN_IN_FOCUSED_WINDOW);
            /* Same, with no lambda, for reference
            this.getRootPane().registerKeyboardAction(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            setVisible(false);
                        }
                    },
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                    JComponent.WHEN_IN_FOCUSED_WINDOW);
             */


            this.pack();
            this.setResizable(false);
            this.setLocationByPlatform(true); // location of the window in the screen
        }
    }
}
