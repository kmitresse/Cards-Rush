<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Register</title>
  <link rel="stylesheet" href="css/login.css"/>
</head>
<body>

<main>
  <section id="title">Titre du jeu</section>
  <section id="form"><h1>Register</h1>
    <form id="registerForm"action="register" method="post">
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

      <div class="field">
        <label for="birthdate">Birthdate:</label>
        <input type="date" id="birthdate" name="birthdate" required>
      </div>


      <div class="field">
        <label for="gender">Gender:</label>
        <select name="gender" id="gender">
          <option value="">--Please choose an option--</option>
          <option value="male">Mâle</option>
          <option value="female">Female</option>
          <option value="non-binary">Non-binary</option>
        </select>
      </div>

      <input class="submit" type="submit" value="Login">

      <p>Already have an account? <a href="login.jsp">Login</a></p>
    </form>
  </section>
</main>

</body>
</html>
