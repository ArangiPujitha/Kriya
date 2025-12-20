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
        <div class="dashboard-welcome">
            <h2>Welcome, <%= user.getUsername() %>!</h2>
            <p>Manage your tasks efficiently and stay productive.</p>
        </div>
        
        <div class="dashboard-stats">
            <div class="stat-card">
                <h3>Tasks</h3>
                <p>Quick Add Task</p>
                <a href="add-task-auth.jsp" class="btn-primary">Add Task</a>
            </div>
            
            <div class="stat-card">
                <h3>View</h3>
                <p>View All Tasks</p>
                <a href="listTasks" class="btn-secondary">View Tasks</a>
            </div>
            
            <div class="stat-card">
                <h3>History</h3>
                <p>Past Tasks</p>
                <a href="pastTasks" class="btn-accent">Past Tasks</a>
            </div>
        </div>
    </div>
</body>
</html>