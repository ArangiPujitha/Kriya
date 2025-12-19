package com.todo.dao;

import com.todo.dao.DBUtil;
import com.todo.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    /* ================= ADD TASK ================= */
    public void addTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (title, description, task_date, task_time, status) VALUES (?, ?, ?, ?, 'Pending')";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setDate(3, Date.valueOf(task.getTaskDate()));   // LocalDate → SQL Date
            ps.setTime(4, Time.valueOf(task.getTaskTime()));   // LocalTime → SQL Time

            ps.executeUpdate();
        }
    }

    /* ================= GET PENDING TASKS ================= */
    public List<Task> getPendingTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE status='Pending' ORDER BY task_date, task_time";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tasks.add(extractTask(rs));
            }
        }
        return tasks;
    }

    /* ================= GET TASK BY ID ================= */
    public Task getTaskById(int id) throws SQLException {
        Task task = null;
        String sql = "SELECT * FROM tasks WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                task = extractTask(rs);
            }
        }
        return task;
    }

    /* ================= UPDATE TASK ================= */
    public void updateTask(Task task) throws SQLException {
        String sql = "UPDATE tasks SET title=?, description=?, task_date=?, task_time=? WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setDate(3, Date.valueOf(task.getTaskDate()));
            ps.setTime(4, Time.valueOf(task.getTaskTime()));
            ps.setInt(5, task.getId());

            ps.executeUpdate();
        }
    }

    /* ================= DELETE TASK ================= */
    public void deleteTask(int id) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /* ================= TOGGLE STATUS ================= */
    public void toggleStatus(int id) throws SQLException {
        String sql = "UPDATE tasks SET status = CASE WHEN status='Pending' THEN 'Completed' ELSE 'Pending' END WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /* ================= UPDATE STATUS EXPLICIT ================= */
    public void updateTaskStatus(Task task) throws SQLException {
        String sql = "UPDATE tasks SET status=? WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, task.getStatus());
            ps.setInt(2, task.getId());
            ps.executeUpdate();
        }
    }

    /* ================= GET TASKS BY DATE (PAST TASKS) ================= */
    public List<Task> getTasksByDate(LocalDate date) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE task_date=? ORDER BY task_time";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tasks.add(extractTask(rs));
            }
        }
        return tasks;
    }

    /* ================= RESULTSET → TASK ================= */
    private Task extractTask(ResultSet rs) throws SQLException {
        Task task = new Task();

        task.setId(rs.getInt("id"));
        task.setTitle(rs.getString("title"));
        task.setDescription(rs.getString("description"));
        task.setTaskDate(rs.getDate("task_date").toLocalDate());
        task.setTaskTime(rs.getTime("task_time").toLocalTime());
        task.setStatus(rs.getString("status"));

        return task;
    }
}
