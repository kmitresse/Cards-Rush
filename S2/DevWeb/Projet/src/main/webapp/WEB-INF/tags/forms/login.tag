<%@tag description="form/login" pageEncoding="UTF-8" %>

<form id="login-form" action="${pageContext.request.contextPath}/login" method="post">
    <div class="field"> <!-- Field Username -->
        <label class="label" for="username">Nom d'utilisateur</label>
        <input id="username" placeholder="John Doe" class="input is-fullwidth" required/>
    </div>
    <div class="field"> <!-- Field Password -->
        <label class="label" for="password">Mot de passe</label>
        <input id="password" class="input is-fullwidth" type="password" required/>
    </div>
    <p class="content has-text-right"><a href="${pageContext.request.contextPath}/forgotten-password" class="link">Mot de passe oublié ?</a>
    </p>
    <input type="submit" class="button is-primary has-text-white is-fullwidth" value="Connexion">
    <hr/>
    <p class="content has-text-centered"><a href="${pageContext.request.contextPath}/register">S'inscrire</a></p>

</form>

<style>
    .notification {
        position: absolute;
        bottom: 0;
        right: 0;
        margin: 1em !important;

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

    @keyframes shake {
        0%, 100% {
            transform: translateX(0);
        }
        10%, 30%, 50%, 70%, 90% {
            transform: translateX(-5px);
        }
        20%, 40%, 60%, 80% {
            transform: translateX(5px);
        }
    }
</style>

<script defer type="module">
    const loginForm = document.querySelector("form#login-form");

    // Form fields
    const usernameInput = document.querySelector("input#username");
    const passwordInput = document.querySelector("input#password");

    // Add event listener to the form submission
    loginForm.addEventListener("submit", onSubmit)

    /**
     * Handle the form submission with Ajax request
     * @param event {Event} - Event of the form submission
     */
    function onSubmit(event) {
        event.preventDefault();

        const {action, method} = loginForm;

        const url = new URL(action);
        url.searchParams.append("username", usernameInput.value);
        url.searchParams.append("password", passwordInput.value);

        fetch(url, {headers: {"Content-Type": "application/json"}, method})
            .then(res => res.json())
            .then(data => {
                if (data.code !== 200) throw new Error(data.message);
            })
            .then(() => window.location.href = "${pageContext.request.contextPath}/main-menu")
            .catch(onError);
    }

    /**
     * Handle the error of the form submission
     * @param error {Error} - Error of the form submission
     */
    function onError(error) {
        console.error("Error:", error)

        // Input fields in red
        usernameInput.classList.add("is-danger");
        passwordInput.classList.add("is-danger");

        // Shake the inputs
        usernameInput.style.animation = "shake 0.5s ease-in-out";
        passwordInput.style.animation = "shake 0.5s ease-in-out";

        // Notification
        const notification = document.createElement("div");
        notification.classList.add("notification", "is-danger");
        notification.innerHTML = error.message;
        document.body.appendChild(notification);
        setTimeout(() => notification.remove(), 5010);
    }

    // Remove the shake animation at the end of animation
    usernameInput.addEventListener("animationend", () => usernameInput.style.animation = "");
    passwordInput.addEventListener("animationend", () => passwordInput.style.animation = "");
</script>

