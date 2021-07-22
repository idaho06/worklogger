package org.akinosoft.worklogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serial;

public class ChooseDirectoryAction extends AbstractAction {

    private final WorkLogger worklogger;

    @Serial
    private static final long serialVersionUID = 4_833_586_275_117_657_316L;

    public ChooseDirectoryAction(WorkLogger wl) {
        worklogger = wl;
        putValue(NAME, "Log Directory...");
        putValue(SHORT_DESCRIPTION, "Select log directory.");
        putValue(LONG_DESCRIPTION, "Select the directory where the work log will be created.");
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        putValue(MNEMONIC_KEY, KeyEvent.VK_L);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Test Action");
        //worklogger.notepad.setText(worklogger.notepad.getText() + "This is a test action ");
        //worklogger.notepad.append("This is a test action ");
        //System.out.println("Action [" + e.getActionCommand( ) + "]!");
        JFileChooser directoryChooser = new JFileChooser();
        directoryChooser.setCurrentDirectory(new java.io.File(worklogger.conf.getLogPath()));
        directoryChooser.setDialogTitle("Select Log Directory");
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        directoryChooser.setAcceptAllFileFilterUsed(false); // disable the "All files" option.

        if (directoryChooser.showOpenDialog(worklogger.frame) == JFileChooser.APPROVE_OPTION) {
            //System.err.println("getCurrentDirectory(): "
            //        +  directoryChooser.getCurrentDirectory());
            //System.err.println("getSelectedFile() : "
            //       +  directoryChooser.getSelectedFile());
            if (directoryChooser.getSelectedFile().isDirectory() && directoryChooser.getSelectedFile().canWrite()) {
                worklogger.conf.setLogPath(directoryChooser.getSelectedFile().toString());
            }
        } else {
            System.err.println("No Directory Selection ");
        }
    }
}
