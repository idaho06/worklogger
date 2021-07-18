package org.akinosoft.worklogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serial;

public class TestAction extends AbstractAction {

    private final WorkLogger worklogger;

    @Serial
    private static final long serialVersionUID = -8_483_173_593_322_509_048L;

    public TestAction(WorkLogger wl){
        worklogger = wl;
        putValue(NAME, "Test Action");
        putValue(SHORT_DESCRIPTION, "This is a test action");
        putValue(LONG_DESCRIPTION, "A test action for testing testable thingies");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Test Action");
        //worklogger.notepad.setText(worklogger.notepad.getText() + "This is a test action ");
        worklogger.notepad.append("This is a test action ");
        System.out.println("Action [" + e.getActionCommand( ) + "]!");

    }
}
