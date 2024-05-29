package util;

import java.util.Scanner;

public class ScannerUtil {

    private static void printMessage(String message) {
        System.out.println(message);
        System.out.print("> ");
    }

    public static int nextInt(Scanner scanner, String message) {
        String temp = nextLine(scanner, message);
        while (!temp.matches("^-?\\d+$")) {
            System.out.println("숫자가 아닙니다.\n 다시 입력해주세요.");
            temp = nextLine(scanner, message);
        }

        return Integer.parseInt(temp);
    }

    public static int nextInt(Scanner scanner, String message, int min, int max) {
        int temp = nextInt(scanner, message);
        while (!(temp >= min && temp <= max)) {
            System.out.println("범위를 벗어난 값입니다.");
            temp = nextInt(scanner, message);
        }

        return temp;
    }

    public static String nextLine(Scanner scanner, String message) {
        printMessage(message);
        String temp = scanner.nextLine();
        if (temp.isEmpty()) {
            temp = scanner.nextLine();
        }

        return temp;
    }
}
