package org.akinosoft.worklogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serial;

public class SwitchWordWrapAction extends AbstractAction {

    private final WorkLogger worklogger;

    @Serial
    private static final long serialVersionUID = -8_483_173_593_322_509_048L;

    public SwitchWordWrapAction(WorkLogger wl) {
        worklogger = wl;
        putValue(NAME, "Word Wrap");
        putValue(SHORT_DESCRIPTION, "Line word wrap configuration.");
        putValue(LONG_DESCRIPTION, "Makes long lines to be broken at the end of the editor or not.");
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        putValue(MNEMONIC_KEY, KeyEvent.VK_W);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Change word wrap in config
        worklogger.conf.setWordWrap();
        // Set new word wrap to text editor
        worklogger.notepad.setLineWrap(worklogger.conf.getWordWrap());
        System.err.println("Action [" + e.getActionCommand() + "]!");
    }
}
