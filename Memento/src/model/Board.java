package model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int BOARD_HEIGHT = 7;
    private static final int BOARD_WIDTH = 7;
    private static final String SUCCESS_MESSAGE = "succesvol gelopen!";
    public static final String ALREADY_DEAD_MESSAGE = "Je bent al dood..";

    private List<List<String>> board = new ArrayList<>();

    private int currentX;
    private int currentY;

    private boolean dead;

    public Board(int x, int y) {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < BOARD_WIDTH; j++) {
                row.add(" - ");
            }
            board.add(row);
        }
        this.currentX = x;
        this.currentY = y;
        this.dead = false;
        board.get(currentY).set(currentX, " x ");
    }

    public String walk(String direction) {
        if (dead) {
            return ALREADY_DEAD_MESSAGE;
        }
        switch (direction) {
            case "a":
                return changeX(-1);
            case "d":
                return changeX(1);
            case "w":
                return changeY(-1);
            case "s":
                return changeY(1);
            default:
                return "ongeldige input";
        }
    }


    public void printBoard(String message) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); //"clear" console
        System.out.println(message);
        for (List<String> row : board) {
            for (String point : row) {
                System.out.print(point);
            }
            System.out.println();
        }
    }


    private String changeX(int change) {
        if (currentX + change < 0 || currentX + change == BOARD_WIDTH) {
            playerDied();
            return "Je bent van de rand afgevallen.";
        }
        if (board.get(currentY).get(currentX + change).equals(" x ")) {
            playerDied();
            return "Je bent tegen jezelf aangelopen.";
        }
        currentX += change;
        board.get(currentY).set(currentX, " x ");
        return SUCCESS_MESSAGE;
    }

    private String changeY(int change) {
        if (currentY + change < 0 || currentY + change == BOARD_HEIGHT) {
            playerDied();
            return "Je bent van de rand afgevallen.";
        }
        if (board.get(currentY + change).get(currentX).equals(" x ")) {
            playerDied();
            return "Je bent tegen jezelf aangelopen.";
        }
        currentY += change;
        board.get(currentY).set(currentX, " x ");
        return SUCCESS_MESSAGE;
    }

    private void playerDied() {
        dead = true;
    }

    public Memento save() {
        return new Memento(board, currentX, currentY, dead);
    }

    public void restore(Memento m) {
        this.board = m.board;
        this.currentX = m.currentX;
        this.currentY = m.currentY;
        this.dead = m.dead;
    }


    public class Memento {
        private List<List<String>> board;

        private int currentX;
        private int currentY;

        private boolean dead;

        private Memento(List<List<String>> board, int currentX, int currentY, boolean dead) {
            this.board = new ArrayList<>();
            for (int i = 0; i < board.size(); i++) {
                List<String> row = new ArrayList<>();
                for (int j = 0; j < board.get(i).size(); j++) {
                    row.add(board.get(i).get(j));
                }
                this.board.add(row);
            }
            this.currentX = currentX;
            this.currentY = currentY;
            this.dead = dead;
        }
    }
}