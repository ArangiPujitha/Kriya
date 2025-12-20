<!DOCTYPE html>
<html>
<head>
    <title>Register - Todo App</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Create Your Account</h2>
        
        <% if (request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
        <% } %>
        
        <form action="register" method="post">
            <div class="form-group">
                <label>Username:</label>
                <input type="text" name="username" required maxlength="50" placeholder="Enter your username">
            </div>
            
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" required maxlength="100" placeholder="Enter your email">
            </div>
            
            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" required minlength="6" placeholder="Enter your password (min 6 characters)">
            </div>
            
            <button type="submit" class="btn-primary">Register</button>
        </form>
        
        <div style="text-align: center; margin-top: 2rem;">
            <p>Already have an account? <a href="login" style="color: #667eea; font-weight: 600;">Login here</a></p>
            <p><a href="index.html" style="color: #667eea;">Back to Home</a></p>
        </div>
    </div>
</body>
</html>