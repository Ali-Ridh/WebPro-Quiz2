<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Form</title>
</head>
<body>
<h1>${user == null ? "Add" : "Edit"} User</h1>
<form action="user" method="post">
    <input type="hidden" name="action" value="${user == null ? 'insert' : 'update'}">
    <input type="hidden" name="userId" value="${user.userId}">
    <label>Username:</label>
    <input type="text" name="username" value="${user.username}">
    <br>
    <label>Password:</label>
    <input type="password" name="password" value="${user.password}">
    <br>
    <label>Full Name:</label>
    <input type="text" name="fullName" value="${user.fullName}">
    <br>
    <label>Role:</label>
    <select name="role">
        <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>Admin</option>
        <option value="student" ${user.role == 'student' ? 'selected' : ''}>Student</option>
    </select>
    <br>
    <button type="submit">Save</button>
</form>
</body>
</html>
