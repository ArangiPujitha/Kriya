<%@ page import="java.util.List" %>
<%@ page import="com.todo.model.Task" %>
<%
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
%>
<html>
<head>
    <title>Pending Tasks</title>
</head>
<body>
<h1>Pending Tasks</h1>
<a href="index.html">Back to Home</a><br><br>
<table border="1" cellpadding="5">
<tr>
    <th>Title</th>
    <th>Description</th>
    <th>Date</th>
    <th>Time</th>
    <th>Status</th>
    <th>Actions</th>
</tr>
<% for(Task task : tasks) { %>
<tr>
    <td><%= task.getTitle() %></td>
    <td><%= task.getDescription() %></td>
    <td><%= task.getTaskDate() %></td>
    <td><%= task.getTaskTime() %></td>
    <td><%= task.getStatus() %></td>
    <td>
        <form style="display:inline" action="tasks" method="post">
            <input type="hidden" name="action" value="toggle">
            <input type="hidden" name="id" value="<%= task.getId() %>">
            <button type="submit">Mark Completed</button>
        </form>
        <form style="display:inline" action="tasks" method="post">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="id" value="<%= task.getId() %>">
            <button type="submit">Delete</button>
        </form>
    </td>
</tr>
<% } %>
</table>
</body>
</html>
