package com.todo.servlet;

import com.todo.dao.TaskDAO;
import com.todo.model.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {

    private TaskDAO taskDAO = new TaskDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get task parameters
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime time = LocalTime.parse(request.getParameter("time"));

        // Create task object and save
        Task task = new Task(title, description, date, time);
        try {
            taskDAO.addTask(task);

            // Fetch updated task list for the given date
            List<Task> taskList = taskDAO.getTasksByDate(date);

            // Set task list as request attribute
            request.setAttribute("tasks", taskList);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("tasks", new ArrayList<>()); // empty on error
        }

        // Redirect to task list
        response.sendRedirect("listTasks");
    }
}
