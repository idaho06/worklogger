package org.akinosoft.worklogger;

import javax.swing.filechooser.FileSystemView;
import java.util.prefs.Preferences;

public class Configuration {

    private static final String GUI_THEME = "gui_theme";
    private static final String PROJECT_NAME = "project_name";
    private static final String LOG_PATH = "log_path";
    private static final String WORD_WRAP = "word_wrap";
    private static final String INSERT_TIME = "insert_time";

    private final Preferences prefs;


    public Configuration() {
        System.err.println("Starting configuration manager");
        //System.err.println(new StringBuilder().append("Class Name: ").append(this.getClass().getName()).toString());
        // org.akinosoft.worklogger.Configuration

        prefs = Preferences.userRoot().node(this.getClass().getName());

    }

    public String getGuiTheme() {
        return prefs.get(GUI_THEME, "dark");
    }

    public Boolean getWordWrap() {
        return prefs.getBoolean(WORD_WRAP, false);
    }

    public String getLogPath() {
        return prefs.get(LOG_PATH, FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath());
    }

    public boolean getInsertTime() {
        return prefs.getBoolean(INSERT_TIME, true);
    }

    public String getProjectName() {
        return prefs.get(PROJECT_NAME, "WorkLog");
    }

    public void setGuiTheme(String theme) {
        if (theme.equals("dark")) {
            prefs.put(GUI_THEME, "dark");
        }
        if (theme.equals("light")) {
            prefs.put(GUI_THEME, "light");
        }
        if (theme.equals("switch")) {
            if (getGuiTheme().equals("dark")) {
                setGuiTheme("light");
            } else {
                setGuiTheme("dark");
            }
        }
    }

    public void setWordWrap() {
        prefs.putBoolean(WORD_WRAP, !getWordWrap());
    }

    public void setLogPath(String directory) {
        prefs.put(LOG_PATH, directory);
    }

    public void setInsertTime() {
        prefs.putBoolean(INSERT_TIME, !getInsertTime());
    }

    public void setProjectName(String name) {
        prefs.put(PROJECT_NAME, name);
    }


}
