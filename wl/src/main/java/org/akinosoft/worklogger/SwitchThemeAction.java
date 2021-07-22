package org.akinosoft.worklogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serial;

public class SwitchThemeAction extends AbstractAction {

    private final WorkLogger worklogger;

    @Serial
    private static final long serialVersionUID = -1_872_148_250_966_560_087L;

    public SwitchThemeAction(WorkLogger wl) {
        worklogger = wl;
        putValue(NAME, "Change Theme");
        putValue(SHORT_DESCRIPTION, "Dark or light theme change");
        putValue(LONG_DESCRIPTION, "Changes the theme of the application from dark to light or back");
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        putValue(MNEMONIC_KEY, KeyEvent.VK_C);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Test Action");
        //worklogger.notepad.setText(worklogger.notepad.getText() + "This is a test action ");
        //worklogger.notepad.append("This is a test action ");
        //System.out.println("Action [" + e.getActionCommand( ) + "]!");
        worklogger.switchTheme();
    }
}
