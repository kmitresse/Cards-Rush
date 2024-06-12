<%@tag description="layout/base" pageEncoding="UTF-8" %>
<%@tag import="uppa.project.Global" %>
<%@ tag import="uppa.project.web.translation.Translator" %>

<%@attribute name="title"%>
<%@attribute name="head" fragment="true" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>

<% Translator translator =(Translator) session.getAttribute("translator"); %>

<!DOCTYPE html>
<html class="has-navbar-fixed-top">
<head>
    <title>${Global.APP_NAME} | ${title}</title>
    <link rel="icon" type="image/svg" href="${pageContext.request.contextPath}/static/icon.svg">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@1.0.0/css/bulma.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/notification.css">
    <script defer type="module" src="${pageContext.request.contextPath}/static/js/navbar.js"></script>
    <script defer type="module" src="${pageContext.request.contextPath}/static/js/notification/error.js"></script>
    <script defer type="module" src="${pageContext.request.contextPath}/static/js/notification/success.js"></script>
    <script defer type="module" src="${pageContext.request.contextPath}/static/js/notification/invite.js"></script>
    <jsp:invoke fragment="head"/>
</head>
<body>
<component:navbar/>

<jsp:doBody/>

<component:footer/>
</body>
</html>
