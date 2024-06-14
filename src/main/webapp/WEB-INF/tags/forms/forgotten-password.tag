<%@tag description="form/forgotten-password" pageEncoding="UTF-8" %>
<%@ tag import="uppa.project.web.translation.Translator" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>

<form id="forgotten-password-form" action="${pageContext.request.contextPath}/forgotten-password" method="post">
    <div class="field">
        <label class="label" for="email">${translator.translate('forgotten_title')}</label>
        <div class="control has-icons-left">
            <input id="email" name="email" placeholder="johndoe@exemple.com" class="input is-fullwidth" required/>
            <span class="icon is-small is-left">
                <i class="fas fa-envelope"></i>
            </span>
        </div>
        <div class="help">${translator.translate('forgotten_email_description')}</div>
    </div>
    <input type="submit" class="button is-primary is-fullwidth has-text-white" value="${translator.translate('forgotten_submit')}">
</form>

