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
  <section id="form">
    <div class="border-left"></div>
    <div class="flex-column register-gap">
      <div>
        <h1>Register</h1>
        <form id="register-form" action="register" method="post">

          <label for="email">Email:</label>
          <input type="email" id="email" name="email" required>

          <label for="username">Username:</label>
          <input type="text" id="username" name="username" required>

          <label for="password">Password:</label>
          <input type="password" id="password" name="password" required>

          <label for="password">RePassword:</label>
          <input type="password" id="repassword" name="password" required>

          <label for="birthdate">Birthdate:</label>
          <input type="date" id="birthdate" name="birthdate" required>

          <label for="gender">Gender:</label>
          <select name="gender" id="gender">
            <option value="">--Please choose an option--</option>
            <option value="male">Mâle</option>
            <option value="female">Female</option>
            <option value="non-binary">Non-binary</option>
          </select>

          <input id="register-submit" type="submit" value="Register">
        </form>
      </div>
      <div>
        <hr>
        <p>Already have an account? <a href="login.jsp">Login</a></p>
      </div>
    </div>
  </section>
</main>

</body>
</html>
