<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Register</title>
</head>
<body>

<main>
  <h1>Login</h1>
  <form action="register" method="post">
    <div class="field">
      <label for="username">Username:</label>
      <input type="text" id="username" name="username" required>
    </div>

    <div class="field">
      <label for="password">Password:</label>
      <input type="password" id="password" name="password" required>
    </div>

    <div class="field">
      <label for="password">RePassword:</label>
      <input type="password" id="repassword" name="password" required>
    </div>

    <input type="submit" value="Login">

    <p>Already have an account? <a href="login.jsp">Login</a></p>
  </form>
</main>

</body>
</html>