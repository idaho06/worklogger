package org.akinosoft.worklogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serial;

public class SwitchInsertTimeAtStartAction extends AbstractAction {

    private final WorkLogger worklogger;

    @Serial
    private static final long serialVersionUID = -8_483_173_593_322_509_048L;

    public SwitchInsertTimeAtStartAction(WorkLogger wl) {
        worklogger = wl;
        putValue(NAME, "Insert Time At Start");
        putValue(SHORT_DESCRIPTION, "Add the timestamp at the beginning of the log");
        putValue(LONG_DESCRIPTION, "When starting WorkLogger, inserts automatically the timestamp at the beginning.");
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        putValue(MNEMONIC_KEY, KeyEvent.VK_I);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Change word wrap in config
        worklogger.conf.setInsertTime();
        // Set new word wrap to text editor
        // worklogger.notepad.setLineWrap(worklogger.conf.getWordWrap());
        System.err.println("Action [" + e.getActionCommand() + "]!");
    }
}
