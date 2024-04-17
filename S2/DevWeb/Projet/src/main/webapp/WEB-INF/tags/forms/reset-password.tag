<%@tag description="form/register" pageEncoding="UTF-8" %>

<form id="reset-password-form" action="${pageContext.request.contextPath}/reset-password" method="post">
    <input type="hidden" id="token" name="token" value="${pageContext.request.getParameter("token")}">
    <div class="field">
        <label class="label" for="password">Mot de passe</label>
        <input id="password" name="password" type="password" class="input is-fullwidth" required>
    </div>

    <div class="field">
        <label class="label" for="repassword">Confirmez le mot de passe</label>
        <input id="repassword" name="repassword" type="password" class="input is-fullwidth" required>
    </div>

    <input type="submit" class="button is-primary is-fullwidth" value="Envoyer">
    <hr/>
    <p class="content has-text-centered">Déjà inscrit ? <a href="${pageContext.request.contextPath}/login">Se connecter</a></p>
</form>

<style>
    .notification {
        position: absolute;
        bottom: 0;
        right: 0;
        margin: 1em !important;
        max-width: 40em;

        transform: translateY(100%);
        opacity: 0;
        animation: toast 5s ease forwards;
    }

    @keyframes toast {
        0% {
            opacity: 0;
            transform: translateY(100%);
        }
        5% {
            opacity: 1;
            transform: translateY(0);
        }
        95% {
            opacity: 1;
            transform: translateY(0);
        }
        100% {
            opacity: 0;
            transform: translateY(100%);
        }
    }
</style>

<script defer type="module">
  const resetPasswordForm = document.querySelector("form#reset-password-form");
  const submitButton = document.querySelector("input[type=submit]");

  // Form fields
  const tokenInput = document.querySelector("input#token");
  const passwordInput = document.querySelector("input#password");
  const repasswordInput = document.querySelector("input#repassword");

  // Add event listener to the form submission
  resetPasswordForm.addEventListener("submit", onSubmit)

  /**
   * Handle the form submission with Ajax request
   * @param event {Event} - Event of the form submission
   */
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

  /**
   * Handle the error of the form submission
   * @param error {Error} - Error of the form submission
   */
  function onError(error) {
    console.error("Error:", error)

    // Input fields in red
    passwordInput.classList.add("is-danger");
    repasswordInput.classList.add("is-danger");

    // Notification
    const notification = document.createElement("div");
    notification.classList.add("notification", "is-danger");
    notification.innerHTML = error.message;
    document.body.appendChild(notification);
    setTimeout(() => notification.remove(), 5010);
  }
</script>

