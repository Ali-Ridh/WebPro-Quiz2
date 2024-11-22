<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<h1>User List</h1>
<a href="user?action=insert">Add User</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Full Name</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.userId}</td>
            <td>${user.username}</td>
            <td>${user.fullName}</td>
            <td>${user.role}</td>
            <td>
                <a href="user?action=edit&id=${user.userId}">Edit</a> |
                <a href="user?action=delete&id=${user.userId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
