package org.akinosoft.worklogger;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WorkLogger implements Runnable {

    // Private stuff
    //private TestAction testaction;

    // Public stuff
    public Configuration conf; // General configuration manager
    public JFrame frame; // Quit Action needs the frame. Custom JFrame.
    public JTextArea notepad; // the text edition space, this contains the text


    public static void main(String[] args) { // <-- Entry point here!!!
        SwingUtilities.invokeLater(new WorkLogger()); // Swing needs a thread to work correctly. This calls "run()"
    }

    public WorkLogger() {
    }

    @Override
    public void run() { // <-- Here we build the window (JFrame) with all the elements and create all the event-driven-actions
        // First we set the look and feel
        conf = new Configuration();

        setTheme();

        frame = new MainJFrame(this);

        JPanel mainPanel = new JPanel(new BorderLayout(0, 0)); // main panel with north, south, east, west, center locations
        //Panel exterior border
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // and a scrollpane containing a text area in the BorderLayout.CENTER
        // JTextArea notepad; // the text edition space. Moved to public element of the class
        notepad = new JTextArea();
        Font font = new Font("Consolas", Font.PLAIN, 18); // TODO: use custom Inconsolata font bundled with the app
        notepad.setFont(font);

        JScrollPane notepadverticalscroll = new JScrollPane(
                notepad,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        notepadverticalscroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // no ugly borders


        // Let's create the Quit action menu
        JMenuItem quitMenuItem = new JMenuItem(new QuitAction(frame));
        JMenuItem insertTimeMenuItem = new JMenuItem(new InsertTimeAction(this));
        JMenuItem switchThemeMenuItem = new JMenuItem(new SwitchThemeAction(this));
        JMenuItem changeDirectoryMenuItem = new JMenuItem(new ChooseDirectoryAction(this));
        //JMenuItem changeProjectMenuItem = new JMenuItem(new ChangeProjectAction(options));
        //JMenuItem aboutMenuItem = new JMenuItem(new AboutAction(frame));


        JMenu actionMenu = new JMenu("Action");
        JMenu insertMenu = new JMenu("Insert");
        JMenu optionMenu = new JMenu("Options");
        JMenu helpMenu = new JMenu("Help");

        actionMenu.add(quitMenuItem);
        insertMenu.add(insertTimeMenuItem);
        optionMenu.add(switchThemeMenuItem);
        optionMenu.add(changeDirectoryMenuItem);
        //optionMenu.add(changeProjectMenuItem);
        //helpMenu.add(aboutMenuItem);


        JMenuBar menuBar = new JMenuBar();
        menuBar.add(actionMenu);
        menuBar.add(insertMenu);
        menuBar.add(optionMenu);
        menuBar.add(helpMenu);


        mainPanel.add(notepadverticalscroll, BorderLayout.CENTER);

        frame.setJMenuBar(menuBar);
        frame.setContentPane(mainPanel);

        frame.setVisible(true); // show the window

        // insert current time by default
        for (ActionListener a : insertTimeMenuItem.getActionListeners()) {
            a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Insert Time") {
                //Nothing need go here, the actionPerformed method (with the
                //above arguments) will trigger the respective listener
            });
        }

        notepad.append("\n");

        notepad.requestFocusInWindow();

    }

    public void saveText() {
        System.err.println("Saving Text to file....");
        String text = this.notepad.getText();
        if (!text.isEmpty() || !text.isBlank()) {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String todayDateString = dateTime.format(formatter);
            String pathname = this.conf.getLogPath() + File.separator + this.conf.getProjectName() + todayDateString + ".txt";
            System.err.println(pathname);

            File file = new File(pathname);
            try {
                FileUtils.writeStringToFile(file, "\n\n" + text, "UTF-8", true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    public void switchTheme() {
        System.err.println("Changing UI look and feel...");
        conf.setGuiTheme("switch");
        setTheme();
        SwingUtilities.updateComponentTreeUI(frame); // refreshes UI
    }

    private void setTheme() {
        if (conf.getGuiTheme().equals("dark")) {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (Exception ex) {
                System.err.println("Failed to initialize LaF Look-and-Feel");
            }
        } else {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception ex) {
                System.err.println("Failed to initialize LaF Look-and-Feel");
            }
        }
    }
}
