package ui;

import model.StudyLog;

import javax.swing.*;

// Inspired by SmartHome
public class StudyLogGUI extends JFrame {
    public static final int HOME_TAB_INDEX = 0;
    public static final int SETTINGS_TAB_INDEX = 1;
    public static final int REPORT_TAB_INDEX = 2;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JTabbedPane sidebar;
    private StudyLog studyLog;

    public static void main(String[] args)  {
        new StudyLogGUI();
//        StopWatchLanterna swl = new StopWatchLanterna();
//        swl.run();
//        new StudyLogDisplay();
    }

    // Inspired by SmartHome
    //MODIFIES: this
    //EFFECTS: creates StudyLogGUI
    private StudyLogGUI() {
        super("StudyLog Console");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        studyLog = new StudyLog();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        add(sidebar);

        setVisible(true);
    }
}
