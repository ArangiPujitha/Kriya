package com.todo.servlet;

import com.todo.dao.TaskDAO;
import com.todo.model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listTasks")
public class ListTaskServlet extends HttpServlet {

    private TaskDAO taskDAO = new TaskDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get date parameter from request (optional: if user wants to filter)
        String dateParam = request.getParameter("date");
        LocalDate date = null;
        if (dateParam != null && !dateParam.isEmpty()) {
            date = LocalDate.parse(dateParam);
        }

        List<Task> tasks;
        try {
            if (date != null) {
                // Fetch tasks for the selected date
                tasks = taskDAO.getTasksByDate(date);
            } else {
                // Fetch all pending tasks if no date is selected
                tasks = taskDAO.getPendingTasks();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            tasks = new ArrayList<>(); // empty list on error
        }

        // Set tasks as request attribute
        request.setAttribute("tasks", tasks);

        // Forward to HTML page
        request.getRequestDispatcher("task-list.html").forward(request, response);
    }
}
