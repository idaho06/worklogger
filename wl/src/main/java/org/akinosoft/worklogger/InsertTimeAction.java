package org.akinosoft.worklogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serial;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InsertTimeAction extends AbstractAction {

    private final WorkLogger worklogger;

    @Serial
    private static final long serialVersionUID = -1_946_232_714_716_886_328L;

    public InsertTimeAction(WorkLogger wl) {
        worklogger = wl;
        putValue(NAME, "Time hh:mm");
        putValue(SHORT_DESCRIPTION, "Insert current time");
        putValue(LONG_DESCRIPTION, "Inserts current time in hh:mm format in the current cursor position.");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        putValue(MNEMONIC_KEY, KeyEvent.VK_T);
        //putValue(DISPLAYED_MNEMONIC_INDEX_KEY, 7);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Test Action");
        //worklogger.notepad.setText(worklogger.notepad.getText() + "This is a test action ");
        int cursorPosition = worklogger.notepad.getCaretPosition();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        worklogger.notepad.insert(time.format(formatter), cursorPosition);
        System.err.println("Action [" + e.getActionCommand() + "]!");
    }
}
