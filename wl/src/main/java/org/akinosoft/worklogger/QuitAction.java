package org.akinosoft.worklogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.Serial;

public class QuitAction extends AbstractAction {

    private final JFrame frame;

    @Serial
    private static final long serialVersionUID = -7_961_256_374_142_311_396L;

    public QuitAction(JFrame frame) {
        this.frame = frame;
        putValue(NAME, "Quit");
        putValue(SHORT_DESCRIPTION, "Exit WorkLogger");
        putValue(LONG_DESCRIPTION, "Saves content to the log file and exits");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
