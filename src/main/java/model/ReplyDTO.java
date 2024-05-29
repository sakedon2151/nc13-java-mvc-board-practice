package model;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyDTO {
    private int id;
    private String content;
    private Date entry_date;
    private Date modify_date;
    private int writer_id;
    private int board_id;
    private String nickname;
}
