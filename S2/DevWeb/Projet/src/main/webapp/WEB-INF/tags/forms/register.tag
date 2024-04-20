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

<script defer type="module">
    const registerForm = document.querySelector("form#register-form");

    // Form fields
    const inputs = registerForm.querySelectorAll("input, select");

    // Add event listener to the form submission
    registerForm.addEventListener("submit", onSubmit)

    /**
     * Handle the form submission with Ajax request
     * @param event {Event} - Event of the form submission
     */
    function onSubmit(event) {
        event.preventDefault();

        const password = registerForm.querySelector("input[name='password']");
        const repassword = registerForm.querySelector("input[name='repassword']");

        // Check if the password and the confirmation password are the same
        if (password.value !== repassword.value) {
            onError(new Error("Les mots de passe ne correspondent pas"));
            return;
        }

        const {action, method} = registerForm;

        const url = new URL(action);
        inputs.forEach(input => url.searchParams.append(input.name, input.value));

        fetch(url, {headers: {"Content-Type": "application/json"}, method})
            .then(res => res.json())
            .then(data => {
                if (data.code !== 200) throw new Error(data.message);
            })
            .then(() => window.location.href = "${pageContext.request.contextPath}/login")
            .catch(onError)
    }

    /**
     * Handle the error of the form submission
     * @param error {Error} - Error of the form submission
     */
    function onError(error) {
        console.error("Error:", error)

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

        setTimeout(() => notification.remove(), 5010);
    }
</script>

