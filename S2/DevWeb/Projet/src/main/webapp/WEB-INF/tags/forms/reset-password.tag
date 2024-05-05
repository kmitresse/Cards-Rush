<%@tag description="form/register" pageEncoding="UTF-8" %>

<form id="reset-password-form" action="${pageContext.request.contextPath}/reset-password" method="post">
    <input type="hidden" id="token" name="token" value="${pageContext.request.getParameter("token")}">
    <div class="field">
        <label class="label" for="password">Mot de passe</label>
        <div class="control has-icons-left">
            <input id="password" name="password" type="password" class="input is-fullwidth" required>
            <span class="icon is-small is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>

    <div class="field">
        <label class="label" for="repassword">Confirmez le mot de passe</label>
        <div class="control has-icons-left">
            <input id="repassword" name="repassword" type="password" class="input is-fullwidth" required>
            <span class="icon is-small is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>

    <input type="submit" class="button is-primary has-text-white is-fullwidth" value="Envoyer">
</form>

