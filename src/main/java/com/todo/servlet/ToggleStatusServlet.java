package com.todo.servlet;

import com.todo.dao.TaskDAO;
import com.todo.model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ToggleStatusServlet")
public class ToggleStatusServlet extends HttpServlet {

    private TaskDAO taskDAO = new TaskDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("taskId"));

            // Get current task from DB
            Task task = taskDAO.getTaskById(id);

            if (task != null) {
                // Toggle status: Pending -> Completed, Completed -> Pending
                String newStatus = "Pending".equals(task.getStatus()) ? "Completed" : "Pending";
                task.setStatus(newStatus);

                // Update in DB
                taskDAO.updateTaskStatus(task);
            }

            // Redirect back to task list
            response.sendRedirect("listTasks");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listTasks");
        }
    }
}
