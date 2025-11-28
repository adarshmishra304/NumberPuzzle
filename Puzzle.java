import java.util.*;

public class Puzzle {

    static int[][] board = new int[3][3];
    static int blankRow, blankCol,count=0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        genPuzzle();
        System.out.println("Number Puzzle (8-Puzzle)");
        System.out.println("Arrange numbers 1-8, blank at last.\n");

        while (true) {
            printBoard();

            if (isSolved()) {
                System.out.println("\n Puzzle Solved! in " + count + " moves.");
                break;
            }

            System.out.print("Move (W/A/S/D): ");
            char move = Character.toUpperCase(sc.next().charAt(0));

            switch (move) {
                case 'W': Up(); 
                break;
                case 'S': Down(); 
                break;
                case 'A': Left(); 
                break;
                case 'D': Right(); 
                break;
                default: System.out.println("Invalid input!");
            }
        }
    }

    static void genPuzzle() {
        int[] nums = new int[9];
        for (int i = 0; i < 9; i++) nums[i] = i;

        shuffle(nums);

        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = nums[k++];
                if (board[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                }
            }
        }
    }

    static void shuffle(int[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    static void Up() {
        if (blankRow < 2) {
            swap(blankRow, blankCol, blankRow + 1, blankCol);
            count++;
        } else 
            System.out.println("Invalid Move!");
    }

    static void Down() {
        if (blankRow > 0) {
            swap(blankRow, blankCol, blankRow - 1, blankCol);
            count++;
        } else 
            System.out.println("Invalid Move!");
    }

    static void Left() {
        if (blankCol < 2) {
            swap(blankRow, blankCol, blankRow, blankCol + 1);
            count++;
        } else 
            System.out.println("Invalid Move!");
    }

    static void Right() {
        if (blankCol > 0) {
            swap(blankRow, blankCol, blankRow, blankCol - 1);
            count++;
        } else 
            System.out.println("Invalid Move!");
    }

    static void swap(int r1, int c1, int r2, int c2) {
        int temp = board[r1][c1];
        board[r1][c1] = board[r2][c2];
        board[r2][c2] = temp;

        blankRow = r2;
        blankCol = c2;
    }

    static void printBoard() {
        System.out.println("\n-------------");
        for (int[] row : board) {
            for (int x : row) {
                if (x == 0) System.out.print("   ");
                else System.out.printf("%2d ", x);
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

    static boolean isSolved() {
        int expected = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 2 && j == 2) 
                    return board[i][j] == 0;

                if (board[i][j] != expected) 
                    return false;
                expected++;
            }
        }
        return true;
    }
}
