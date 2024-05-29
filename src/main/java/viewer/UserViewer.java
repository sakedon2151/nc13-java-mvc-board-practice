package viewer;

import model.UserDTO;
import controller.UserController;

import util.ScannerUtil;
import java.util.Scanner;

public class UserViewer {
    // UserViewer 에 있는 필드, 메소드
    // 필드 - scanner, controller, login
    // 메소드 - index, auth, register, menu, info, update, delete

    private Scanner scanner;
    private UserController userController;
    private UserDTO logIn;

    // 메인 메뉴(첫 화면) 메소드
    public void showIndex() {
        System.out.println("==================================================");
        System.out.println("|--------------- 로컬 게시판 프로그램 ---------------|");
        String message = "[ 1. 로그인 | 2. 회원가입 | 3. 종료 ]";
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);

            if (userChoice == 1) {
                // 로그인
                auth();
                if (logIn != null) {
//                    showMenu();
                }
            } else if (userChoice == 2) {
                // 회원가입
                register();
            } else if (userChoice == 3) {
                System.out.println("<프로그램이 종료되었습니다.>");
                break;
            }
        }
    }

    // 아이디 비밀번호 입력 메소드
    private void auth() {
        String message;
        message = "<아이디를 입력해주세요.>";
        String username = ScannerUtil.nextLine(scanner, message);
        message = "<비밀번호를 입력해주세요.>";
        String password = ScannerUtil.nextLine(scanner, message);
        logIn = userController.auth(username, password);
        if (logIn == null) {
            System.out.println("<<<주의! 잘못 입력하셨습니다. 다시 입력해주세요.>>>");
        }
    }

    // 회원가입 메소드
    private void register() {
        UserDTO userDTO = new UserDTO();
        String message;

        message = "<사용하실 아이디를 입력해주세요.>";
        String username = ScannerUtil.nextLine(scanner, message);
        userDTO.setUsername(username);

        message = "<사용하실 비밀번호를 입력해주세요.>";
        String password = ScannerUtil.nextLine(scanner, message);
        userDTO.setPassword(password);

        message = "<사용하실 닉네임을 입력해주세요.>";
        String nickname = ScannerUtil.nextLine(scanner, message);
        userDTO.setNickname(nickname);

        userController.register(userDTO);
    }




}
