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
    <title>Add Task - Todo App</title>
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
        <h2>Add New Task</h2>
        
        <form action="addTask" method="post" onsubmit="return validateDateTime()">
            <div class="form-group">
                <label>Title:</label>
                <input type="text" name="title" required maxlength="100" placeholder="Enter task title">
            </div>
            
            <div class="form-group">
                <label>Description:</label>
                <textarea name="description" required maxlength="500" placeholder="Enter task description" rows="4"></textarea>
            </div>
            
            <div class="form-group">
                <label>Date:</label>
                <input type="date" name="date" id="taskDate" required>
            </div>
            
            <div class="form-group">
                <label>Time:</label>
                <input type="time" name="time" id="taskTime" required>
            </div>
            
            <button type="submit" class="btn-primary">Add Task</button>
        </form>
        
        <div style="text-align: center; margin-top: 2rem;">
            <a href="dashboard.jsp" class="btn-secondary">Back to Dashboard</a>
        </div>
    </div>
    
    <script>
        // Set minimum date to today
        document.getElementById('taskDate').min = new Date().toISOString().split('T')[0];
        
        function validateDateTime() {
            const selectedDate = new Date(document.getElementById('taskDate').value);
            const selectedTime = document.getElementById('taskTime').value;
            const now = new Date();
            const today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
            
            // Check if date is in the past
            if (selectedDate < today) {
                alert('Please select a future date.');
                return false;
            }
            
            // If date is today, check if time is in the past
            if (selectedDate.getTime() === today.getTime()) {
                const currentTime = now.getHours().toString().padStart(2, '0') + ':' + now.getMinutes().toString().padStart(2, '0');
                if (selectedTime <= currentTime) {
                    alert('Please select a future time for today.');
                    return false;
                }
            }
            
            return true;
        }
    </script>
</body>
</html>