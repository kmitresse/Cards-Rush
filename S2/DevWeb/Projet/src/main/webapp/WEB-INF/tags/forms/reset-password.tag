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

<script defer type="module">
    const resetPasswordForm = document.querySelector("form#reset-password-form");
    const submitButton = document.querySelector("input[type=submit]");
    const inputs = resetPasswordForm.querySelectorAll("input[type='text'], input[type='password']");

    const tokenInput = document.querySelector("input#token");
    const passwordInput = document.querySelector("input#password");
    const repasswordInput = document.querySelector("input#repassword");

    resetPasswordForm.addEventListener("submit", onSubmit)

    function onSubmit(event) {
        event.preventDefault();

        // Check if the password and the confirmation password are the same
        if (passwordInput.value !== repasswordInput.value) {
            onError(new Error("Les mots de passe ne correspondent pas"));
            return;
        }

        const {action, method} = resetPasswordForm;

        const url = new URL(action);
        url.searchParams.append("token", tokenInput.value);
        url.searchParams.append("password", passwordInput.value);

        submitButton.classList.add("is-loading");
        fetch(url, {headers: {"Content-Type": "application/json"}, method})
            .then(res => res.json())
            .then(data => {
                if (data.code !== 200) throw new Error(data.message);
            })
            .then(() => window.location.href = "${pageContext.request.contextPath}/login")
            .catch(onError)
            .finally(() => submitButton.classList.remove("is-loading"));
    }

    function onError(error) {
      console.error("Error:", error)

      inputs.forEach(input => {
        input.classList.add("is-danger");
        input.style.animation = "shake 0.5s ease-in-out"
      });


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

        setTimeout(() => notification.remove(), 5010);
      }
      inputs.forEach(input => input.addEventListener("animationend", () => input.style.animation = ""));

</script>

