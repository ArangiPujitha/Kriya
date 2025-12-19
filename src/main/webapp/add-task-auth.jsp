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
    <title>Add Task</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Add New Task</h2>
        <form action="addTask" method="post">
            <div class="form-group">
                <label>Title:</label>
                <input type="text" name="title" required>
            </div>
            
            <div class="form-group">
                <label>Description:</label>
                <textarea name="description" required></textarea>
            </div>
            
            <div class="form-group">
                <label>Date:</label>
                <input type="date" name="date" required>
            </div>
            
            <div class="form-group">
                <label>Time:</label>
                <input type="time" name="time" required>
            </div>
            
            <button type="submit">Add Task</button>
        </form>
        
        <p><a href="dashboard.jsp">Back to Dashboard</a></p>
    </div>
</body>
</html>