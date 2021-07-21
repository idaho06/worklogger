package org.akinosoft.worklogger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class MainJFrame extends JFrame {

    private WorkLogger workLogger;

    public MainJFrame(WorkLogger workLogger) {
        this.workLogger = workLogger;

        super.setTitle("WorkLogger");
        // Configure window icon here
        try {
            this.initAppIcons();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // General Frame config
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack(); // size of the window
        this.setSize(800, 600);
        this.setLocationByPlatform(true); // location of the window in the screen

        // Capture the exit event
        this.setWindowListener();
    }

    private void initAppIcons() throws IOException {
        ArrayList<Image> icons = new ArrayList<>();
        icons.add(ImageIO.read(ClassLoader.getSystemResource("wl16.png")));
        icons.add(ImageIO.read(ClassLoader.getSystemResource("wl24.png")));
        icons.add(ImageIO.read(ClassLoader.getSystemResource("wl32.png")));
        icons.add(ImageIO.read(ClassLoader.getSystemResource("wl48.png")));
        icons.add(ImageIO.read(ClassLoader.getSystemResource("wl64.png")));
        icons.add(ImageIO.read(ClassLoader.getSystemResource("wl72.png")));
        icons.add(ImageIO.read(ClassLoader.getSystemResource("wl96.png")));
        icons.add(ImageIO.read(ClassLoader.getSystemResource("wl128.png")));
        icons.add(ImageIO.read(ClassLoader.getSystemResource("wl256.png")));
        super.setIconImages(icons);
    }


    private void setWindowListener() {
        this.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window has been opened.
             *
             * @param e
             */
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                MainJFrame.super.requestFocusInWindow();
            }

            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                System.err.println("Window is CLOSING!!! Let's save text first");
                workLogger.saveText();
                super.windowClosing(e);
            }

            /**
             * Invoked when a window has been closed.
             *
             * @param e
             */
            @Override
            public void windowClosed(WindowEvent e) {
                System.err.println("Window is CLOSED!!");
                super.windowClosed(e);
            }

            /**
             * Invoked when a window is iconified.
             *
             * @param e
             */
            @Override
            public void windowIconified(WindowEvent e) {
                super.windowIconified(e);
            }

            /**
             * Invoked when a window is de-iconified.
             *
             * @param e
             */
            @Override
            public void windowDeiconified(WindowEvent e) {
                super.windowDeiconified(e);
            }

            /**
             * Invoked when a window is activated.
             *
             * @param e
             */
            @Override
            public void windowActivated(WindowEvent e) {
                super.windowActivated(e);
            }

            /**
             * Invoked when a window is de-activated.
             *
             * @param e
             */
            @Override
            public void windowDeactivated(WindowEvent e) {
                super.windowDeactivated(e);
            }

            /**
             * Invoked when a window state is changed.
             *
             * @param e
             * @since 1.4
             */
            @Override
            public void windowStateChanged(WindowEvent e) {
                super.windowStateChanged(e);
            }

            /**
             * Invoked when the Window is set to be the focused Window, which means
             * that the Window, or one of its subcomponents, will receive keyboard
             * events.
             *
             * @param e
             * @since 1.4
             */
            @Override
            public void windowGainedFocus(WindowEvent e) {
                super.windowGainedFocus(e);
            }

            /**
             * Invoked when the Window is no longer the focused Window, which means
             * that keyboard events will no longer be delivered to the Window or any of
             * its subcomponents.
             *
             * @param e
             * @since 1.4
             */
            @Override
            public void windowLostFocus(WindowEvent e) {
                super.windowLostFocus(e);
            }
        });
    }
}
