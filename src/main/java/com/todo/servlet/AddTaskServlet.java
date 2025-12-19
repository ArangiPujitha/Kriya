package com.todo.servlet;

import com.todo.dao.TaskDAO;
import com.todo.model.Task;
import com.todo.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime time = LocalTime.parse(request.getParameter("time"));

        Task task = new Task(title, description, date, time);
        task.setUserId(user.getId());
        
        try {
            taskDAO.addTask(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("listTasks");
    }
}
