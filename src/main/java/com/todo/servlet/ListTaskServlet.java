package com.todo.servlet;

import com.todo.dao.TaskDAO;
import com.todo.model.Task;
import com.todo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        List<Task> tasks;
        try {
            tasks = taskDAO.getPendingTasks(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            tasks = new ArrayList<>();
        }

        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("task-list.jsp").forward(request, response);
    }
}
