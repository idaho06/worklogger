package org.akinosoft.worklogger;

import javax.swing.filechooser.FileSystemView;
import java.util.prefs.Preferences;

public class Configuration {

    private static final String GUI_THEME = "gui_theme";
    private static final String PROJECT_NAME = "project_name";
    private static final String LOG_PATH = "log_path";

    private final Preferences prefs;


    public Configuration() {
        System.err.println("Starting configuration manager");
        //System.err.println(new StringBuilder().append("Class Name: ").append(this.getClass().getName()).toString());
        // org.akinosoft.worklogger.Configuration

        prefs = Preferences.userRoot().node(this.getClass().getName());

    }

    public String getGuiTheme(){
        return prefs.get(GUI_THEME, "dark");
    }

    public String getLogPath(){
        return prefs.get(LOG_PATH, FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath());
    }

    public String getProjectName(){
        return prefs.get(PROJECT_NAME, "WorkLog");
    }

    public void setGuiTheme(String theme){
        if (theme.equals("dark")){
            prefs.put(GUI_THEME, "dark");
        }
        if (theme.equals("light")){
            prefs.put(GUI_THEME, "light");
        }
        if (theme.equals("switch")){
            if (getGuiTheme().equals("dark")) {
                setGuiTheme("light");
            } else {
                setGuiTheme("dark");
            }
        }
    }

    public void setLogPath(String directory) {
        prefs.put(LOG_PATH, directory);
    }
}
