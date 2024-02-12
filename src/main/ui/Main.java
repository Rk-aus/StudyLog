package ui;

import model.StopWatch;
import model.StudyLog;

public class Main {
    public static void main(String[] args) {
        new StudyLog();
//        long time;
//        time = System.currentTimeMillis();
//        for (int i = 0; i < 1000000; i++) {
//            System.out.println(i);
//        }
//        long timeInterval = System.currentTimeMillis() - time;
//        System.out.println(timeInterval);
        StopWatch sw = new StopWatch();
        sw.startStopWatch();
        sw.getIntervalStudyTime();
//        sw.pauseStopWatch();
//        System.out.println(sw.getTotalStudyTime());
//        sw.startStopWatch();
//        sw.pauseStopWatch();
//        System.out.println(sw.getTotalStudyTime());
//        sw.startStopWatch();
//        sw.pauseStopWatch();
//        System.out.println(sw.getTotalStudyTime());
//        sw.startStopWatch();
//        sw.pauseStopWatch();
//        System.out.println(sw.getTotalStudyTime());
//        long time = System.currentTimeMillis();
//        for (int i = 0; i < 1000000; i++) {
//            System.out.println((System.currentTimeMillis() - time) / 1000);
//        }


    }
}
