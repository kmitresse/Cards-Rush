<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:base>
  <jsp:attribute name="title">Cards Rush - Inscription</jsp:attribute>
  <jsp:body>
    <div class="hero is-light is-fullheight">
      <div class="hero-body">
        <div class="container">
          <div class="columns is-centered">
            <div class="column is-5-tablet is-5-desktop is-5-widescreen">
              <div class="box">
                <h1 class="title has-text-centered">S'inscrire</h1>
                <form:register/>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </jsp:body>
</layout:base>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--  <title>Register</title>--%>
<%--  <link href="${pageContext.request.contextPath}/static/css/global.css" rel="stylesheet">--%>
<%--  <link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet">--%>
<%--  <script src="${pageContext.request.contextPath}/static/js/register.js" defer></script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<%@include file="../components/navbar.jsp"%>--%>
<%--<main>--%>
<%--    <div class="flex-column register-gap">--%>
<%--      <div>--%>
<%--        <h1>S'inscrire</h1>--%>
<%--        <form id="register-form" action="${pageContext.request.contextPath}/api/auth/register" method="POST">--%>

<%--          <label for="email">Email :</label>--%>
<%--          <input type="email" id="email" name="email" required>--%>

<%--          <label for="username">Nom d'utilisateur :</label>--%>
<%--          <input type="text" id="username" name="username" required>--%>

<%--          <label for="password">Mot de passe :</label>--%>
<%--          <input type="password" id="password" name="password" required>--%>

<%--          <label for="confirmPassword">Confirmez le mot de passe :</label>--%>
<%--          <input type="password" id="confirmPassword" name="confirmPassword" required>--%>

<%--          <label for="birthdate">Date de naissance :</label>--%>
<%--          <input type="date" id="birthdate" name="birthdate" required>--%>

<%--          <label for="gender">Genre :</label>--%>
<%--          <select name="gender" id="gender" required>--%>
<%--            <option value="">--Choisissez une option--</option>--%>
<%--            <option value="MALE">Homme</option>--%>
<%--            <option value="FEMALE">Femme</option>--%>
<%--            <option value="OTHER">Autre</option>--%>
<%--          </select>--%>

<%--          <input class="button" id="register-submit" type="submit" value="Register">--%>
<%--        </form>--%>
<%--      </div>--%>
<%--      <div>--%>
<%--        <hr>--%>
<%--        <p>Déjà un compte ? <a href="${pageContext.request.contextPath}/login">Se connecter</a></p>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--</main>--%>

<%--</body>--%>
<%--</html>--%>
