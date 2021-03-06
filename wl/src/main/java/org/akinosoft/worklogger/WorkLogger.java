package org.akinosoft.worklogger;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
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

    public static Font CustomFont(String path, float size) {
        Font customFont = loadFont(path, size);
        if (customFont == null) {
            System.err.println("loadFont did not return a Font object!");
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);
        return customFont;
    }

    public static Font loadFont(String path, float size){
        try {
            Font myFont = Font.createFont(Font.TRUETYPE_FONT, ClassLoader.getSystemResourceAsStream(path));
            return myFont.deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
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
        //Font font = new Font("Consolas", Font.PLAIN, 18);
        Font font = CustomFont("Inconsolata-Regular.ttf", 18f);
        notepad.setFont(font);
        notepad.setLineWrap(conf.getWordWrap());
        notepad.setWrapStyleWord(true);
        // ESC Key captured when focus is in notepad. Does not break Menus.
        notepad.registerKeyboardAction(
                evt -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, KeyEvent.KEY_LOCATION_UNKNOWN),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        JScrollPane notepadverticalscroll = new JScrollPane(
                notepad,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        notepadverticalscroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // no ugly borders


        // Let's create the Quit action menu
        JMenuItem quitMenuItem = new JMenuItem(new QuitAction(frame));
        JMenuItem insertTimeMenuItem = new JMenuItem(new InsertTimeAction(this));
        JMenuItem switchThemeMenuItem = new JMenuItem(new SwitchThemeAction(this));
        JCheckBoxMenuItem switchWordWrapCheckBox = new JCheckBoxMenuItem(new SwitchWordWrapAction(this));
        JCheckBoxMenuItem switchInsertTimeCheckBox = new JCheckBoxMenuItem(new SwitchInsertTimeAtStartAction(this));
        JMenuItem changeDirectoryMenuItem = new JMenuItem(new ChooseDirectoryAction(this));
        JMenuItem changeProjectMenuItem = new JMenuItem(new ChooseProjectNameAction(this));
        JMenuItem aboutMenuItem = new JMenuItem(new AboutAction(frame));

        switchWordWrapCheckBox.setSelected(conf.getWordWrap());
        switchInsertTimeCheckBox.setSelected(conf.getInsertTime());

        JMenu actionMenu = new JMenu("Action");
        actionMenu.setMnemonic(KeyEvent.VK_A);
        JMenu insertMenu = new JMenu("Insert");
        insertMenu.setMnemonic(KeyEvent.VK_I);
        JMenu optionMenu = new JMenu("Options");
        optionMenu.setMnemonic(KeyEvent.VK_O);
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        actionMenu.add(quitMenuItem);
        insertMenu.add(insertTimeMenuItem);
        optionMenu.add(switchThemeMenuItem);
        optionMenu.add(switchInsertTimeCheckBox);
        optionMenu.add(switchWordWrapCheckBox);
        optionMenu.add(changeDirectoryMenuItem);
        optionMenu.add(changeProjectMenuItem);
        helpMenu.add(aboutMenuItem);


        JMenuBar menuBar = new JMenuBar();
        menuBar.add(actionMenu);
        menuBar.add(insertMenu);
        menuBar.add(optionMenu);
        menuBar.add(helpMenu);

        mainPanel.add(notepadverticalscroll, BorderLayout.CENTER);

        frame.setJMenuBar(menuBar);
        frame.setContentPane(mainPanel);

        if (conf.getInsertTime()) {
            insertTimeAtStart(insertTimeMenuItem);
        }

        frame.setVisible(true); // show the window

        // force focus on the notepad
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //notepad.grabFocus();
                notepad.requestFocusInWindow();
            }
        });


    }

    public void saveText() {
        System.err.println("Saving Text to file....");
        String newLine = System.getProperty("line.separator");
        String text = this.notepad.getText();
        if (!text.isEmpty() || !text.isBlank()) {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String todayDateString = dateTime.format(formatter);
            String pathname = this.conf.getLogPath() + File.separator + this.conf.getProjectName() + todayDateString + ".txt";
            System.err.println(pathname);

            File file = new File(pathname);
            try {
                FileUtils.writeStringToFile(file, newLine + newLine + text, "UTF-8", true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            System.err.println("Empty content! File left untouched.");
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

    private void insertTimeAtStart(AbstractButton insertTimeMenuItem) {
        for (ActionListener a : insertTimeMenuItem.getActionListeners()) {
            a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Insert Time") {
                //Nothing need go here, the actionPerformed method (with the
                //above arguments) will trigger the respective listener
            });
        }
        String newLine = System.getProperty("line.separator");
        notepad.append(newLine);
    }
}
