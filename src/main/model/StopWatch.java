package model;

import ui.StopWatchDisplay;

import java.io.IOException;

public class StopWatch {
    private long studyStartTime;
    private long studyEndTime;
    private long totalStudyTime;
    private long intervalStudyTime;
    private boolean isStudying;
    private StopWatchDisplay stopWatchDisplay;

    public StopWatch() {
        this.studyStartTime = 0;
        this.studyEndTime = 0;
        this.intervalStudyTime = 0;
        this.totalStudyTime = 0;
        this.isStudying = false;
        this.stopWatchDisplay = new StopWatchDisplay();
    }

    // getters
    public long getStudyStartTime() {
        return this.studyStartTime;
    }

    public long getStudyEndTime() {
        return this.studyEndTime;
    }

    public long getIntervalStudyTime() {
        return this.intervalStudyTime;
    }

    public long getTotalStudyTime() {
        return this.totalStudyTime;
    }

    public boolean isStudying() {
        return this.isStudying;
    }

    // setters
    public void setStudyingToFalse() {
        this.isStudying = false;
    }

//    public void startStopWatch() {
//        long studyStartTime = System.currentTimeMillis();
//        this.isStudying = true;
//        stopWatchDisplay.displayStudyingStopWatch();
//        long intervalStudyTime = System.currentTimeMillis() - studyStartTime;
//        this.totalStudyTime = this.totalStudyTime + intervalStudyTime;
//    }
//
//    public void pauseStopWatch() {
//        long studyEndTime = System.currentTimeMillis();
//        this.isStudying = false;
//        stopWatchDisplay.displayStudyingStopWatch();
//        long intervalStudyTime = System.currentTimeMillis() - studyStartTime;
//        this.totalStudyTime = this.totalStudyTime + intervalStudyTime;
//
//    }

    public void startStopWatch() {
        studyStartTime = System.currentTimeMillis();
        this.isStudying = true;
        stopWatchDisplay.displayStudyingStopWatch(this);
        intervalStudyTime = (System.currentTimeMillis() - studyStartTime) / 1000;
        this.totalStudyTime = this.totalStudyTime + intervalStudyTime;
    }

    public void pauseStopWatch() {
        studyEndTime = System.currentTimeMillis();
        this.isStudying = false;
        stopWatchDisplay.displayStudyingStopWatch(this);

//        intervalStudyTime = System.currentTimeMillis() - studyStartTime;
//        this.totalStudyTime = this.totalStudyTime + intervalStudyTime;
    }



}
