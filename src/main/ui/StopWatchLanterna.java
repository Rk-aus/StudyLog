//package ui;
//
//import com.googlecode.lanterna.graphics.TextGraphics;
//import com.googlecode.lanterna.input.KeyStroke;
//import com.googlecode.lanterna.input.KeyType;
//import com.googlecode.lanterna.screen.Screen;
//import com.googlecode.lanterna.screen.TerminalScreen;
//import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
//import com.googlecode.lanterna.terminal.Terminal;
//
//import java.io.IOException;
//
//public class StopWatchLanterna {
//
//
//    public StopWatchLanterna() throws Exception {
//
//    }
//
//    public void run() throws Exception {
//        Terminal terminal = new DefaultTerminalFactory().createTerminal();
//        Screen screen = new TerminalScreen(terminal);
//
//        TextGraphics tg = screen.newTextGraphics();
//
//        screen.startScreen();
//
//        boolean keepRunning = true;
//        boolean isStudying = true;
//        StringBuilder sb = new StringBuilder();
//
//        while (keepRunning) {
//            KeyStroke keyPressed = terminal.pollInput();
//
//            if (keyPressed != null) {
//                System.out.println(keyPressed);
//                switch (keyPressed.getKeyType()) {
//                    case Escape:
//                        keepRunning = false;
//                        break;
//                    case Character:
//                        sb.append(keyPressed.getCharacter());
//                        System.out.println(keyPressed.getCharacter());
//                        break;
//                    case Enter:
//                        screen.clear();
//                        tg.putString(10, 10, sb.toString());
//                        screen.refresh();
//                        break;
//                    case ArrowUp:
//                        screen.clear();
//                        int n = 0;
//                        while (isStudying) {
//                            tg.putString(10, 10, String.valueOf(n));
//                            n++;
//                            Thread.sleep(1000);
//                            screen.refresh();
//
//                            KeyStroke keyPressed1 = terminal.pollInput();
//
//                            if (keyPressed1 != null) {
//                                if (keyPressed1.getKeyType() == KeyType.Enter) {
//                                    isStudying = false;
//                                }
//                            }
//                            while (!isStudying) {
//                                KeyStroke keyPressed2 = terminal.pollInput();
//
//                                if (keyPressed2 != null) {
//                                    if (keyPressed2.getKeyType() == KeyType.Enter) {
//                                        isStudying = true;
//                                    } else if (keyPressed2.getKeyType() == KeyType.Backspace) {
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                }
//            }
//        }
//    }
//}
