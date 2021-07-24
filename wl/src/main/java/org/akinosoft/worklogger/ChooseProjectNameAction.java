package org.akinosoft.worklogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serial;

public class ChooseProjectNameAction extends AbstractAction {

    private final WorkLogger worklogger;
    private final int MAX_PROJECT_NAME = 80;

    @Serial
    private static final long serialVersionUID = 6_738_799_592_416_406_583L;

    public ChooseProjectNameAction(WorkLogger wl) {
        worklogger = wl;
        putValue(NAME, "Project Name...");
        putValue(SHORT_DESCRIPTION, "Select the filename of the log files.");
        putValue(LONG_DESCRIPTION, "Select the name of the log files. Filename will be of the form: ProjectNameYYYYMMDD.txt");
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        putValue(MNEMONIC_KEY, KeyEvent.VK_P);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String projectName = (String) JOptionPane.showInputDialog(
                worklogger.frame,
                this.getValue(SHORT_DESCRIPTION),
                (String) this.getValue(NAME),
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                worklogger.conf.getProjectName());
        //System.err.println("New Project name is: " + projectName);
        if (projectName != null) {
            String trimmedProjectName = projectName.trim();
            int len = MAX_PROJECT_NAME;
            if (trimmedProjectName.length() < MAX_PROJECT_NAME)
                len = trimmedProjectName.length();
            if (len > 0) {
                String shortProjectName = trimmedProjectName.substring(0, len).replace(' ', '_');
                System.err.println("New Project name is: " + shortProjectName);
                worklogger.conf.setProjectName(shortProjectName);
            }
        }
    }
}
