<%@ page import="java.util.List" %>
<%@ page import="com.todo.model.Task" %>
<%@ page import="com.todo.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login");
        return;
    }
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Tasks - Todo App</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar">
        <a href="dashboard.jsp" class="logo">TODO</a>
        <div class="nav-links">
            <a href="listTasks">View Tasks</a>
            <a href="add-task-auth.jsp">Add New Task</a>
            <a href="pastTasks">Past Tasks</a>
            <a href="logout">Logout</a>
        </div>
    </nav>
    
    <div class="container">
        <h2>My Pending Tasks</h2>
        
        <% if (tasks != null && !tasks.isEmpty()) { %>
            <table>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Task task : tasks) { %>
                    <tr>
                        <td><%= task.getTitle() %></td>
                        <td><%= task.getDescription() %></td>
                        <td><%= task.getTaskDate() %></td>
                        <td><%= task.getTaskTime() %></td>
                        <td><%= task.getStatus() %></td>
                        <td>
                            <form style="display:inline" action="toggleStatus" method="post">
                                <input type="hidden" name="id" value="<%= task.getId() %>">
                                <button type="submit" class="btn-complete">Complete</button>
                            </form>
                            <form style="display:inline" action="deleteTask" method="post">
                                <input type="hidden" name="id" value="<%= task.getId() %>">
                                <button type="submit" class="btn-delete">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <div style="text-align: center; padding: 3rem;">
                <h3>No pending tasks found!</h3>
                <p>Start by adding a new task.</p>
                <a href="add-task-auth.jsp" class="btn-primary">Add Your First Task</a>
            </div>
        <% } %>
    </div>
</body>
</html>
