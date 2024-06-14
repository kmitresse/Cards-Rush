<%@tag description="form/register" pageEncoding="UTF-8" %>
<%@ tag import="uppa.project.web.translation.Translator" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>

<form id="reset-password-form" action="${pageContext.request.contextPath}/reset-password" method="post">
    <input type="hidden" id="token" name="token" value="${pageContext.request.getParameter("token")}">
    <div class="field">
        <label class="label" for="password">${translator.translate('user_password')}</label>
        <div class="control has-icons-left">
            <input id="password" name="password" type="password" placeholder="${translator.translate('user_password')}" class="input is-fullwidth" required>
            <span class="icon is-small is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>

    <div class="field">
        <label class="label" for="repassword">${translator.translate('user_confirm_password')}</label>
        <div class="control has-icons-left">
            <input id="repassword" name="repassword" placeholder="${translator.translate('user_confirm_password_input')}" type="password" class="input is-fullwidth" required>
            <span class="icon is-small is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>

    <input type="submit" class="button is-primary has-text-white is-fullwidth" value="${translator.translate('reset_submit')}">
</form>

