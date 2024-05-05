<%@ tag import="uppa.project.database.pojo.User" %>
<%@tag description="form/profile" pageEncoding="UTF-8" %>

<form id="profile-form" action="${pageContext.request.contextPath}/profile" method="post">
    <jsp:useBean id="user" class="uppa.project.database.pojo.User" scope="session"/>
    <div class="field">
        <label class="label" for="username">Nom d'utilisateur</label>
        <div class="control has-icons-left">
            <input id="username" name="username" value="${user.username}" type="text" class="input is-fullwidth" disabled/>
            <span class="icon is-left"><i class="fas fa-user"></i></span>
        </div>
    </div>
    <div class="field">
        <label class="label" for="email">Email</label>
        <div class="control has-icons-left">
            <input id="old-email" name="oldEmail" type="hidden" value="${user.email}" class="input is-fullwidth" required>
            <input id="email" name="email" type="email" value="${user.email}" class="input is-fullwidth" required>
            <span class="icon is-left"><i class="fas fa-envelope"></i></span>
        </div>
    </div>
    <div class="field">
        <label class="label">Mot de passe</label>
        <a id="change-password" href="#">Changer le mot de passe</a>
    </div>
    <div id="old-password-field" class="field" style="display: none">
        <div class="control has-icons-left">
            <input id="old-password" name="oldPassword" placeholder="Ancien mot de passe" type="password" class="input is-fullwidth">
            <span class="icon is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>
    <div id="password-field" class="field" style="display: none">
        <div class="control has-icons-left">
            <input id="password" name="password" placeholder="Nouveau mot de passe" type="password" class="input is-fullwidth">
            <span class="icon is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>
    <div id="repeat-password-field" class="field" style="display: none">
        <div class="control has-icons-left">
            <input id="repeat-password" name="repeat-password" placeholder="Confirmer le mot de passe" type="password" class="input is-fullwidth">
            <span class="icon is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>
    <div class="field">
        <label class="label">Date de naissance</label>
        <input class="input" type="text" value="${user.birth.toLocaleString()}" disabled>
    </div>
    <div class="field">
        <label class="label" for="gender">Genre</label>
        <div class="control has-icons-left">
            <div class="select is-fullwidth">
                <input id="old-gender" name="oldGender" type="hidden" value="${user.gender.name()}" class="input is-fullwidth" required>
                <select name="gender" id="gender" required>
                    <% if (user.getGender() == null) { %>
                        <option selected value="">- Choisissez une option -</option>
                    <% } else { %>
                        <option value="">- Choisissez une option -</option>
                    <% } %>
                    <% if (user.getGender().equals(User.Gender.MALE)) {%>
                        <option selected value="MALE">Homme</option>
                    <% } else { %>
                    <option value="MALE">Homme</option>
                    <% } %>
                    <% if (user.getGender().equals(User.Gender.FEMALE)) {%>
                        <option selected value="FEMALE">Femme</option>
                    <% } else { %>
                        <option value="FEMALE">Femme</option>
                    <% } %>
                    <% if (user.getGender().equals(User.Gender.OTHER)) {%>
                        <option selected value="OTHER">Autre</option>
                    <% } else { %>
                    <option value="OTHER">Autre</option>
                    <% } %>
                </select>
            </div>
            <span class="icon is-left"><i class="fa-solid fa-venus-mars"></i></span>
        </div>
    </div>

    <div class="field">
        <div class="buttons is-justify-content-space-between">
            <a href="${pageContext.request.contextPath}/lobby" class="button is-light is-right">
                    <span class="icon">
                        <i class="fa-solid fa-arrow-left"></i>
                    </span>
            <span>Retour</span>
            </a>
            <input type="submit" id="modify" class="button is-primary is-light" value="Modifier">
        </div>
    </div>
</form>
