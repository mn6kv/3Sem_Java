<%@ page import="models.User" %>
<%@ page import="java.util.List" %>

<%--
  Created by IntelliJ IDEA.
  User: under
  Date: 30.09.2020
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>password</th>
        <th>age</th>
    </tr>

    <% List<User> users = (List<User>)request.getAttribute("UsersForJSP");
        for (int i = 0; i < users.size(); i++){%>
    <tr>
        <td><%=users.get(i).getId()%></>
        <td><%=users.get(i).getName()%></>
        <td><%=users.get(i).getPassword()%></>
        <td><%=users.get(i).getAge()%></>
    </tr>
    <%}%>

</table>

</body>
</html>