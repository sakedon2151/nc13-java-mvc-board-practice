package viewer;

import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;

import model.UserDTO;
import controller.UserController;

import util.ScannerUtil;
import java.util.Scanner;

public class UserViewer {

    private final Scanner SCANNER = new Scanner(System.in);
    private UserDTO logIn;
    private ConnectionMaker connectionMaker;

    public UserViewer() {
        connectionMaker = new MySqlConnectionMaker();
    }

    // -메인 인덱스 메소드-
    // cr: while 문을 이용한 메뉴 구현
    // auth() 를 통해 얻어낸 logIn 객체를 if 문을 통해 유효한지 확인
    public void showIndex() {
        System.out.println("==================================================");
        System.out.println("|--------------- 로컬 게시판 프로그램 ---------------|");
        String message = "[ 1. 로그인 | 2. 회원가입 | 3. 종료 ]";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);
            if (userChoice == 1) {
                auth();
                if (logIn != null) {
                    showMenu();
                }
            } else if (userChoice == 2) {
                register();
            } else if (userChoice == 3) {
                System.out.println("<프로그램이 종료되었습니다.>");
                break;
            }
        }
    }

    // -로그인 메소드-
    // cr: 새로운 controller 가져옴.
    private void auth() {
        String message = "<아이디를 입력해주세요.>";
        String username = ScannerUtil.nextLine(SCANNER, message);

        message = "<비밀번호를 입력해주세요.>";
        String password = ScannerUtil.nextLine(SCANNER, message);

        UserController userController = new UserController(connectionMaker);
        UserDTO temp = userController.auth(username, password);

        if (temp == null) {
            System.out.println("<<<주의! 잘못 입력하셨습니다.>>>");
        } else {
            logIn = temp;
        }
    }

    // -회원가입 메소드-
    // cr: 사용자에게서 username, password, nickname 값을 각각의 String 에 받고, 새로운 attempt UserDTO 객체를 생성해서 대입함.
    // 새로운 UserController 인스턴스를 가져오고 파라미터로 connectionMaker 를 전달함
    //
    private void register() {
        String message = "<사용하실 아이디를 입력해주세요.>";
        String username = ScannerUtil.nextLine(SCANNER, message);

        message = "<사용하실 비밀번호를 입력해주세요.>";
        String password = ScannerUtil.nextLine(SCANNER, message);

        message = "<사용하실 닉네임을 입력해주세요.>";
        String nickname = ScannerUtil.nextLine(SCANNER, message);

        UserDTO attempt = new UserDTO();
        attempt.setUsername(username);
        attempt.setPassword(password);
        attempt.setNickname(nickname);

        UserController userController = new UserController(connectionMaker);
        if (!userController.register(attempt)) {
            System.out.println("<<<주의! 잘못 입력하셨습니다.>>>");
        } else {
            System.out.println("<정상적으로 회원가입이 완료되었습니다.>");
        }
    }

    // -유저메뉴 메소드-
    private void showMenu() {
        System.out.println("==================================================");
        System.out.println("|--------------- 게시판에 어서오세요 ---------------|");
        String message = "[ 1. 게시판 | 2. 회원정보 조회 | 3. 로그아웃 ]";
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);
            if (userChoice == 1) {
                // ArrayList 데이터베이스처럼 배열 하나만 가질 필요가 없다.
                // 원래 컨트롤러에서 사용하던 어레이리스트 1개는 이제 사용할 필요가 없기 때문에
                BoardViewer boardViewer = new BoardViewer(connectionMaker, SCANNER, logIn);
                boardViewer.showMenu();
            } else if (userChoice == 2) {

            } else if (userChoice == 3) {
                System.out.println("정상적으로 로그아웃 되었습니다.");
                logIn = null;
            }
        }
    }


}
