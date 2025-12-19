<%@ page import="com.todo.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Todo App</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Welcome, <%= user.getUsername() %>!</h2>
        
        <div class="menu">
            <a href="listTasks">View My Tasks</a> |
            <a href="add-task-auth.jsp">Add New Task</a> |
            <a href="pastTasks">Past Tasks</a> |
            <a href="logout">Logout</a>
        </div>
    </div>
</body>
</html>