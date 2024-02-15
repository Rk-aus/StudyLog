//package ui;
//
//import com.googlecode.lanterna.input.KeyStroke;
//import com.googlecode.lanterna.screen.Screen;
//import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
//import model.StopWatch;
//
//import java.io.IOException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//
//
//
//public class StopWatchDisplay {
//
//    private Scanner input;
//
//    public StopWatchDisplay() {
//
//    }
//
//    public void displayStudyingStopWatch(StopWatch stopWatch) {
//        while (stopWatch.isStudying()) {
//            long time = System.currentTimeMillis();
//            List secondsList = new ArrayList<Integer>();
//            List minutesList = new ArrayList<String>();
//            List hoursList = new ArrayList<Integer>();
//            stopWatch = stopWatch;
//            try {
//                display(time, secondsList, minutesList, hoursList, stopWatch);
//            } catch (IOException e) {
//                System.out.println("ERROR");
//            }
//
//        }
//    }
//
//    public void display(long time, List sl, List ml, List hl, StopWatch sw) throws IOException {
////        input = new Scanner(System.in);
////        String command = null;
////        command = input.next();
////        command = command.toLowerCase();
//
////        Screen screen = new DefaultTerminalFactory().createScreen();
////        KeyStroke stroke = screen.pollInput();
////
////        if (stroke == null) {
////            sw.pauseStopWatch();
////        }
//        while (sw.isStudying()) {
//            long timeInterval = (System.currentTimeMillis() - time) / 1000;
//            Integer second = (int) timeInterval;
//
//            Integer minute = (int) timeInterval / 60;
//            Integer remainderSecond0 = second - 60 * minute;
//            String minutes = minute + ":" + remainderSecond0;
//
//            Integer hour = (int) timeInterval / 3600;
//            int remainderMinute = minute - 60 * hour;
//            int remainderSecond1 = second - 3600 * hour - 60 * remainderMinute;
//            String hours = hour + ":" + remainderMinute + ":" + remainderSecond1;
//
//            if (hour >= 1) {
//                printNonDuplicate(hl, hours);
//            } else if (minute >= 1) {
//                printNonDuplicate(ml, minutes);
//            } else {
//                printNonDuplicate(sl, second);
//            }
//        }
//
////        while (command != "q") {
////            while (sw.isStudying()) {
////            }
////            sw.setStudyingToFalse();
////        }
//
//    }
//
//    public void printNonDuplicate(List l, Integer i) {
//        if (!l.contains(i)) {
//            System.out.println(i);
//            l.add(i);
//        }
//    }
//
//    public void printNonDuplicate(List l, String s) {
//        if (!l.contains(s)) {
//            System.out.println(s);
//            l.add(s);
//        }
//    }
//}
