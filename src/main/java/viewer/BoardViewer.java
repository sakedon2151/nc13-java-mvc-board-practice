package viewer;

import connector.ConnectionMaker;
import model.UserDTO;

import util.ScannerUtil;
import java.util.Scanner;

public class BoardViewer {
    private ConnectionMaker connectionMaker;
    private final Scanner SCANNER;
    private UserDTO logIn;

    public BoardViewer(ConnectionMaker connectionMaker, Scanner scanner, UserDTO logIn) {
        this.connectionMaker = connectionMaker;
        SCANNER = scanner;
        this.logIn = logIn;
    }

    public void showMenu() {
        String message = "[ 1. 게시글 작성 | 2. 게시글 목록 | 3. 뒤로가기 ]";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);
            if (userChoice == 1) {
                // insert()
            } else if (userChoice == 2) {
                // list()
            } else if (userChoice == 3) {
                System.out.println("<뒤로 갑니다.>");
                break;
            }
        }
    }





}
