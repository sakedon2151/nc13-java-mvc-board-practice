package model;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private Date entry_date;
    private Date modify_date;
    private int writerId;
    // 이제 SQL 의 INNER JOIN 을 통해 다른 테이블, 다른 클래스라도 값을 가져올수 있음
    private String nickname;
}
