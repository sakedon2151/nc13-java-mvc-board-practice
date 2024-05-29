package model;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String nickname;

    // 이제 SQL 문법으로 데이터베이스에게 직접 물어보기 때문에 equals 오버라이드를 써줄 필요가 없음
}
