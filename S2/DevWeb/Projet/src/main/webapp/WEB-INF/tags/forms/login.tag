<%@ tag import="uppa.project.web.translation.Translator" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>
<%@tag description="form/login" pageEncoding="UTF-8" %>

<form id="login-form" action="${pageContext.request.contextPath}/login" method="post">

    <div class="field">
        <label class="label" for="username"><%= translator.translate("user_username") %></label>
        <div class="control has-icons-left">
            <input id="username" name="username" placeholder="John Doe" type="text" class="input is-fullwidth" required/>
            <span class="icon is-left">
                <i class="fas fa-user"></i>
            </span>
        </div>
    </div>

    <div class="field">
        <label class="label" for="password"><%= translator.translate("user_password") %></label>
        <div class="control has-icons-left">
            <input id="password" name="password" placeholder="<%= translator.translate("user_password") %>" class="input is-fullwidth" type="password" required/>
            <span class="icon is-left">
                <i class="fas fa-lock"></i>
            </span>
        </div>
    </div>

    <p class="content has-text-right">
        <a href="${pageContext.request.contextPath}/forgotten-password" class="link"><%= translator.translate("login_forgotten_password") %></a>
    </p>

    <input type="submit" class="button is-primary has-text-white is-fullwidth" value="<%= translator.translate("login_submit") %>">
</form>
