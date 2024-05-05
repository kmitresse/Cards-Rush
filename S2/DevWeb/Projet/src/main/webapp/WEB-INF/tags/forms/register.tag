<%@tag description="form/register" pageEncoding="UTF-8" %>

<form id="register-form" action="${pageContext.request.contextPath}/register" method="post">
    <div class="field">
        <label class="label" for="username">Nom d'utilisateur</label>
        <div class="control has-icons-left">
            <input id="username" name="username" placeholder="John Doe" type="text" class="input is-fullwidth" required>
            <span class="icon is-left"><i class="fas fa-user"></i></span>
        </div>
    </div>

    <div class="field">
        <label class="label" for="email">Email</label>
        <div class="control has-icons-left">
            <input id="email" name="email" type="email" placeholder="johndoe@exemple.com" class="input is-fullwidth" required>
            <span class="icon is-left"><i class="fas fa-envelope"></i></span>
        </div>
    </div>

    <div class="field">
        <label class="label" for="password">Mot de passe</label>
        <div class="control has-icons-left">
            <input id="password" name="password" placeholder="Mot de passe" type="password" class="input is-fullwidth" required>
            <span class="icon is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>

    <div class="field">
        <label class="label" for="repassword">Confirmez le mot de passe</label>
        <div class="control has-icons-left">
            <input id="repassword" name="repassword" placeholder="Répétez le mot de passe" type="password" class="input is-fullwidth" required>
            <span class="icon is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>

    <div class="field">
        <label class="label" for="birth">Date de naissance</label>
        <input class="input is-fullwidth" type="date" id="birth" name="birth" required>
    </div>

    <div class="field">
        <label class="label" for="gender">Genre</label>
        <div class="control has-icons-left">
            <div class="select is-fullwidth">
                <select name="gender" id="gender" required>
                    <option selected value="">- Choisissez une option -</option>
                    <option value="MALE">Homme</option>
                    <option value="FEMALE">Femme</option>
                    <option value="OTHER">Autre</option>
                </select>
            </div>
            <span class="icon is-left"><i class="fa-solid fa-venus-mars"></i></span>
        </div>
    </div>

    <input type="submit" class="button has-text-white is-primary is-fullwidth" value="S'inscrire">
</form>

