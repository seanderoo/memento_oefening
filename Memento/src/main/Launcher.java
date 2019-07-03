package main;

import model.Board;
import model.Caretaker;

import java.util.Scanner;

public class Launcher {

    private Scanner scanner = new Scanner(System.in);
    private Board board = new Board(3, 3);
    Caretaker caretaker = new Caretaker(board);
    private boolean keepOnGoing = true;
    private String message = "Succes! ";


    public static void main(String[] args) {
        new Launcher().run();
    }

    public void run(){
        while (keepOnGoing) {
            String input = askForInput(message);
            if (input.equals("e")) {
                break;
            } else if (input.equals("h")) {
                message = "lopen: w / a / s / d, exit: e, undo: z";
                continue;
            } else if (input.equals("z")){
                caretaker.undo();
                message = "Ongedaan gemaakt";
                continue;
            }
            message = caretaker.walk(input);
            if (message.equals(Board.ALREADY_DEAD_MESSAGE)) {
                caretaker.undo();
            }
        }

    }

    private String askForInput(String message){
        board.printBoard(message);
        System.out.print("Input (h for help): ");
        return scanner.next();
    }
}

