package ui;


import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {
//        StopWatchLanterna swl = new StopWatchLanterna();
//        swl.run();
        System.out.println(LocalDateTime.now());
        new StudyLogDisplay();
    }
}
