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

@WebServlet("/pastTasks")
public class PastTaskServlet extends HttpServlet {

    private TaskDAO taskDAO = new TaskDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dateStr = request.getParameter("date");

        if (dateStr != null && !dateStr.isEmpty()) {
            LocalDate date = LocalDate.parse(dateStr);
            try {
                List<Task> tasks = taskDAO.getTasksByDate(date);
                request.setAttribute("tasks", tasks);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("tasks", new ArrayList<>()); // empty on error
            }
        }

        request.getRequestDispatcher("past-tasks.html").forward(request, response);
    }
}
