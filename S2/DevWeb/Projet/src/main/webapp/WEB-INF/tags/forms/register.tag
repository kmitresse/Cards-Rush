<%@tag description="form/register" pageEncoding="UTF-8" %>

<form id="register-form" action="${pageContext.request.contextPath}/register" method="post">
    <div class="field">
        <label class="label" for="username">Nom d'utilisateur</label>
        <input id="username" name="username" placeholder="John Doe" type="text" class="input is-fullwidth" required>
    </div>

    <div class="field">
        <label class="label" for="email">Email</label>
        <input id="email" name="email" type="email" placeholder="john.doe@mail.com" class="input is-fullwidth" required>
    </div>

    <div class="field">
        <label class="label" for="password">Mot de passe</label>
        <input id="password" name="password" type="password" class="input is-fullwidth" required>
    </div>

    <div class="field">
        <label class="label" for="repassword">Confirmez le mot de passe</label>
        <input id="repassword" name="repassword" type="password" class="input is-fullwidth" required>
    </div>

    <div class="field">
        <label class="label" for="birth">Date de naissance</label>
        <input class="input is-fullwidth" type="date" id="birth" name="birth" required>
    </div>

    <div class="field">
        <label class="label" for="gender">Genre</label>
        <div class="select is-fullwidth">
            <select name="gender" id="gender" required>
                <option selected value="">--Choisissez une option--</option>
                <option value="MALE">Homme</option>
                <option value="FEMALE">Femme</option>
                <option value="OTHER">Autre</option>
            </select>
        </div>
    </div>

    <input type="submit" class="button has-text-white is-primary is-fullwidth" value="S'inscrire">
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
    const registerForm = document.querySelector("form#register-form");

    // Form fields
    const usernameInput = document.querySelector("input#username");
    const emailInput = document.querySelector("input#email");
    const passwordInput = document.querySelector("input#password");
    const repasswordInput = document.querySelector("input#repassword");
    const birthInput = document.querySelector("input#birth");
    const genderInput = document.querySelector("select#gender");
    const submitButton = document.querySelector("input[type=submit]");

    // Add event listener to the form submission
    registerForm.addEventListener("submit", onSubmit)

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

        const {action, method} = registerForm;

        const url = new URL(action);
        url.searchParams.append("username", usernameInput.value);
        url.searchParams.append("email", emailInput.value);
        url.searchParams.append("password", passwordInput.value);
        url.searchParams.append("birth", birthInput.value);
        url.searchParams.append("gender", genderInput.value);

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
        usernameInput.classList.add("is-danger");
        emailInput.classList.add("is-danger");
        passwordInput.classList.add("is-danger");
        repasswordInput.classList.add("is-danger");
        birthInput.classList.add("is-danger");
        genderInput.classList.add("is-danger");

        // Notification
        const notification = document.createElement("div");
        notification.classList.add("notification", "is-danger");
        notification.innerHTML = error.message;
        document.body.appendChild(notification);
        setTimeout(() => notification.remove(), 5010);
    }
</script>

