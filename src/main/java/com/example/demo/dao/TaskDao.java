package com.example.demo.dao;

import com.example.demo.model.TaskPaginationModel;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskDao {
    public List<TaskPaginationModel> getTaskByUserIdAndByNumberPage(Long userId, Integer countTask, Integer numberPage) {
        List<TaskPaginationModel> taskList = new ArrayList<>();
        try {
            String url = "jdbc:postgresql://localhost:5432/it_academy";
            String usernameDB = "postgres";
            String passwordDB = "postgres";

            try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
                int offset = (numberPage - 1) * countTask;
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM task WHERE user_id = " + userId + " LIMIT " + countTask + " OFFSET " + offset);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String status = resultSet.getString("status");
                    String description = resultSet.getString("description");
                    Timestamp issuedDate = resultSet.getTimestamp("issued_date");
                    TaskPaginationModel taskPaginationModel = new TaskPaginationModel();
                    taskPaginationModel.setTitle(title);
                    taskPaginationModel.setDescription(description);
                    taskPaginationModel.setStatus(status);
                    taskPaginationModel.setIssuedDate(issuedDate);
                    taskList.add(taskPaginationModel);
                    System.out.println(id + title + description + status + issuedDate);
                }
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        return taskList;
    }

}
