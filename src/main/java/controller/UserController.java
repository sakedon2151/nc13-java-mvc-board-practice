package controller;

import connector.ConnectionMaker;
import model.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    private Connection connection; // DBMS 와 상호작용하기 위한 객체

    // cr: 외부 connection 을 이용한 다형성을 구현한다.
    // UserController 클래스는 파라미터로 받아온 ConnectionMaker 인터페이스를 통해 유연하게 DBMS 정보를 받아 connection 에 저장한다.
    public UserController(ConnectionMaker connectionMaker) {
        connection = connectionMaker.makeConnection();
    }

    // 유저 로그인 메소드
    // cr: String query 문을 connection 으로 DB에 넘겨 파라미터 String 2개를 중복 검증한다.
    // ResultSet 으로 결과값을 받고, while 과 resultSet.next() 로 탐색하여 새로운 객체에 담아준다.
    // 대상이 존재하지 않는다면 SQLException 를 출력한다.
    public UserDTO auth(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(resultSet.getInt("id"));
                userDTO.setUsername(resultSet.getString("username"));
                userDTO.setPassword(resultSet.getString("password"));
                userDTO.setNickname(resultSet.getString("nickname"));

                return userDTO;
            }
        } catch (SQLException e) {
            // SQL 에 대한 Exception 캐치
            e.printStackTrace();
        }

        // DB 에 없으면 null
        return null;
    }

    // 유저 회원가입 메소드
    // cr: query 문을 통해 파라미터로 들어온 userDTO 객체 속 속성을 각각 담아서 테이블에 저장하게 한다.
    // 테이블 속성이 UQ 인 기존 username, nickname 과 중복 값이라면 SQLException 을 통해 false 를 리턴한다.
    public boolean register(UserDTO userDTO) {
        String query = "INSERT INTO user(username, password, nickname) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userDTO.getUsername());
            preparedStatement.setString(2, userDTO.getPassword());
            preparedStatement.setString(3, userDTO.getNickname());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // 유저 정보 업데이트 메소드
    // cr: query 문을 통해 파라미터로 들어온 userDTO 객체 속 속성을 각각 담아서 기존 테이블 정보를 업데이트 한다.
    // 테이블 속성이 UQ 인 기존 nickname 과 중복 값이라면 SQLException 을 통해 false 를 리턴한다.
    public boolean update(UserDTO userDTO) {
        String query = "UPDATE user SET password = ?, nickname = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userDTO.getPassword());
            preparedStatement.setString(2, userDTO.getNickname());
            preparedStatement.setInt(3, userDTO.getId());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // 유저 정보 삭제 메소드
    // cr: query 문을 통해 파라미터로 들어온 int 값을 담아서 동일한 해당 PK 값이 있는 ROW 정보를 삭제한다.
    public void delete(int id) {
        String query = "DELETE FROM user WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
