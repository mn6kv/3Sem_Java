<%--
  Created by IntelliJ IDEA.
  User: under
  Date: 04.11.2020
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    function checkEmail() {
        const email = document.getElementById("email")
        const xhr = new XMLHttpRequest()

        if (email.value.length > 4) {
            document.getElementById("email").disable = false
            document.getElementById("email-error").style.display = "none"
        } else {
            document.getElementById("email").disable = true
            document.getElementById("email-error").style.display = "block"
        }

        xhr.open("GET", "http://localhost:8080//emailCheck?email=" + email.value, false)
        xhr.send()

        const result = xhr.responseText

        if (result === "true") {
            document.getElementById("email").disable = false
            document.getElementById("email-error").style.display = "none"
        }

        if (xhr.status !== 200) {
            document.getElementById("email").disable = true
            document.getElementById("email-error").style.display = "block"
        } else {
            if (result === "false") {
                document.getElementById("email").disable = true
                document.getElementById("email-error").style.display = "block"
            }
        }
    }
</script>
<form action="/signUp" method="post">
    <input type="email" name="email" placeholder="Email" onkeyup="checkEmail()">
    <span class="error" id="email-error">Почтовый адрес уже занят</span>
    <input type="text" name="name" placeholder="Name">
    <input type="password" name="password" placeholder="Password">
    <input type="submit">
</form>

</body>
</html>
