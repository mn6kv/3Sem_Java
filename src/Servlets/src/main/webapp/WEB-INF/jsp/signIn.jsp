<%--
  Created by IntelliJ IDEA.
  User: under
  Date: 06.10.2020
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body> Sign up/in
    <div>
        <form action="/signIn" method="post">
            <label for="name_input", for="password_input">Type your name and password</label>
            <br>
            <input id="name_input" name="name_input" placeholder="Your name">
            <br>
            <input id="password_input" name="password_input" placeholder="Your password">
            <br>
            <input id="age_input" name="age_input" placeholder="Age">
            <br>
            <input type="submit" value="Sign up!">
        </form>
    </div>
</body>
</html>
