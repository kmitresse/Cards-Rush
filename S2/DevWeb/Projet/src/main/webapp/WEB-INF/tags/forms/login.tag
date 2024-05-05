<%@tag description="form/login" pageEncoding="UTF-8" %>

<form id="login-form" action="${pageContext.request.contextPath}/login" method="post">

    <div class="field">
        <label class="label" for="username">Nom d'utilisateur</label>
        <div class="control has-icons-left">
            <input id="username" name="username" placeholder="John Doe" type="text" class="input is-fullwidth" required/>
            <span class="icon is-left">
                <i class="fas fa-user"></i>
            </span>
        </div>
    </div>

    <div class="field">
        <label class="label" for="password">Mot de passe</label>
        <div class="control has-icons-left">
            <input id="password" name="password" placeholder="Mot de passe" class="input is-fullwidth" type="password" required/>
            <span class="icon is-left">
                <i class="fas fa-lock"></i>
            </span>
        </div>
    </div>

    <p class="content has-text-right">
        <a href="${pageContext.request.contextPath}/forgotten-password" class="link">Mot de passe oublié ?</a>
    </p>

    <input type="submit" class="button is-primary has-text-white is-fullwidth" value="Connexion">
</form>
