<%@ page import="static jdk.internal.net.http.common.Utils.dump" %>
<%@ page import="uppa.project.pojo.User" %>
<%@ page import="uppa.project.dao.jpa.Game_JPA_DAO_Factory" %>
<%@ page import="uppa.project.dao.DAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  User user = (User) (request.getAttribute("session"));
%>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
  <% if (user == null) { %>
  <%-- Pas de user connecté -> On envoi sur la page de connection --%>
    <jsp:include page="login.jsp"></jsp:include>
  <% } else {
    session.setAttribute("sessionUser", session);
  %>
    <jsp:include page="dashboard.jsp"></jsp:include>
  <% } %>
</body>
</html>
