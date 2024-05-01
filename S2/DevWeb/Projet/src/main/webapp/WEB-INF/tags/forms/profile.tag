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
<script defer type="module">
    const profileForm = document.querySelector("form#profile-form");
    const changePassword = profileForm.querySelector("a#change-password");
    const passwordFields = profileForm.querySelectorAll("div#old-password-field, div#password-field, div#repeat-password-field");
    const inputs = profileForm.querySelectorAll("input[type='text'], input[type='password']");

    // Afficher les champs de mot de passe si le lien est cliqué
    changePassword.addEventListener("click", (e) => {
        e.preventDefault();
        passwordFields.forEach(field => {
          console.log(field)
            field.style.display = "block";
        });
    });

    profileForm.addEventListener("submit", onSubmit);

    function onSubmit(event) {
      event.preventDefault();

      const oldPassword = profileForm.querySelector("input[name='oldPassword']");
      const password = profileForm.querySelector("input[name='password']");
      const repassword = profileForm.querySelector("input[name='repeat-password']");
      // Check if the password and the confirmation password are the same
      if (oldPassword.value !== "" && password.value !== repassword.value) {
        onError(new Error("Les mots de passe ne correspondent pas"));
        return;
      }

      const {action, method} = profileForm;

      const url = new URL(action);
      const formData = new FormData(profileForm);
      for (const [key, value] of formData.entries()) {
        console.log(key, value);
        url.searchParams.append(key, value);
      }
      url.searchParams.append("id", ${user.id})

      fetch(url, {headers: {"Content-Type": "application/json"}, method})
        .then(res => res.json())
        .then(data => {
          console.log(data)
          if (data.code !== 200) throw new Error(data.message);
          onSuccess()
        })
        .catch(onError)
    }

    function onError(error) {
      console.log(error)

      // Input fields in red
      inputs.forEach(input => input.classList.add("is-danger"));

      // Notification
      const notification = document.createElement("div");
      notification.classList.add("notification", "is-danger");

      const notificationTitle = document.createElement("p");
      notificationTitle.classList.add("title", "is-6");
      notificationTitle.innerHTML = "Erreur";

      const notificationIcon = document.createElement("span");
      notificationIcon.classList.add("icon");
      notificationIcon.innerHTML = "<i class='fas fa-exclamation-triangle'></i>";

      const notificationMessage = document.createElement("p");
      notificationMessage.classList.add("subtitle", "is-6");
      notificationMessage.innerHTML = error.message;

      notificationTitle.appendChild(notificationIcon);
      notification.appendChild(notificationTitle);
      notification.appendChild(notificationMessage);
      document.body.appendChild(notification);
      console.log("je suis bien dans la fonction mais la notification ne s'affiche pas")
      setTimeout(() => {
        notification.remove()
        inputs.forEach(input => input.classList.remove("is-danger"));
      }, 5010);
    }

    function onSuccess() {
      console.log("Succès:", "Modifications effectuées avec succès")

      // Notification
      const notification = document.createElement("div");
      notification.classList.add("notification", "is-success");

      const notificationTitle = document.createElement("p");
      notificationTitle.classList.add("title", "is-6");
      notificationTitle.innerHTML = "Succès";

      const notificationIcon = document.createElement("span");
      notificationIcon.classList.add("icon");
      notificationIcon.innerHTML = "<i class='fa-solid fa-check'></i>";

      const notificationMessage = document.createElement("p");
      notificationMessage.classList.add("subtitle", "is-6");
      notificationMessage.innerHTML = "Le profil a été modifié avec succès";

      notificationTitle.appendChild(notificationIcon);
      notification.appendChild(notificationTitle);
      notification.appendChild(notificationMessage);
      document.body.appendChild(notification);
      console.log("je suis bien dans la fonction mais la notification ne s'affiche pas")

      setTimeout(() => notification.remove(), 5010);
    }
    inputs.forEach(input => input.addEventListener("animationend", () => input.style.animation = ""));

</script>
