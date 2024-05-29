package controller;

import connector.ConnectionMaker;
import model.BoardDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardController {
    private Connection connection;

    public BoardController(ConnectionMaker connectionMaker) {
        connection = connectionMaker.makeConnection();
    }

    // 전체 Board 테이블을 ArrayList 배열로 리턴하는 메소드
    public List<BoardDTO> selectAll() {
        List<BoardDTO> list = new ArrayList<>();
        String query = "SELECT * FROM board AS b INNER JOIN user ON b.writer_id = user.id";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setId(resultSet.getInt("id"));
                boardDTO.setTitle(resultSet.getString("title"));
                boardDTO.setContent(resultSet.getString("content"));
                boardDTO.setEntry_date(resultSet.getTimestamp("entry_date"));
                boardDTO.setModify_date(resultSet.getTimestamp("modify_date"));
                boardDTO.setWriterId(resultSet.getInt("writer_id"));
                boardDTO.setNickname(resultSet.getString("nickname"));

                list.add(boardDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 파라미터 int id 와 동일한 PK id 를 가진 ROW 값을 BoardDTO 객체로 리턴하는 메소드
    public BoardDTO selectOne(int id) {
        String query = "SELECT * FROM board INNER JOIN user ON board.writer_id = user.id WHERE board.id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setId(resultSet.getInt("id"));
                boardDTO.setTitle(resultSet.getString("title"));
                boardDTO.setContent(resultSet.getString("content"));
                boardDTO.setEntry_date(resultSet.getTimestamp("entry_date"));
                boardDTO.setModify_date(resultSet.getTimestamp("modify_date"));
                boardDTO.setWriterId(resultSet.getInt("writer_id"));
                boardDTO.setNickname(resultSet.getString("nickname"));

                return boardDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 해당 id 값을 가진 대상이 없을 경우
        return null;
    }

    // 파라미터 BoardDTO 객체의 값을 query 문에 맞춰 테이블에 새로 입력하는 메소드
    public void insert(BoardDTO boardDTO) {
        String query = "INSERT INTO board(title, content, writer_id) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, boardDTO.getTitle());
            preparedStatement.setString(2, boardDTO.getContent());
            preparedStatement.setInt(3, boardDTO.getWriterId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 파라미터 BoardDTO 객체의 값을 query 문에 맞춰 기존 테이블에 재입력하는 메소드
    public void update(BoardDTO boardDTO) {
        String query = "UPDATE board SET title = ?, content = ?, modify_date = NOW() WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, boardDTO.getTitle());
            preparedStatement.setString(2, boardDTO.getContent());
            preparedStatement.setInt(3, boardDTO.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 파라미터 int id 와 동일한 PK id 가 존재하는 ROW 를 삭제하는 메소드
    public void delete(int id) {
        String query = "DELETE FROM board WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    // 게시글 수정 메소드

    // 게시글 삭제 메소드

}
