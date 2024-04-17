<%@tag description="Base page layout" pageEncoding="UTF-8" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="head" fragment="true" %>

<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>

<!DOCTYPE html>
<html>
<head>
    <title><jsp:invoke fragment="title"/></title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@1.0.0/css/bulma.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    <style>
        :root {
            --bulma-primary-h: 0;
            --bulma-primary-s: 70%;
            --bulma-primary-l: 35%;
        }
    </style>

    <jsp:invoke fragment="head"/>
</head>
<body>
<component:navbar/>

<jsp:doBody/>

<component:footer/>
</body>
</html>