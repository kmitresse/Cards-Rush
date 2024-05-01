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

<script defer type="module">
    const form = document.querySelector("form#login-form");
    const inputs = form.querySelectorAll("input[type='text'], input[type='password']");

    form.addEventListener("submit", event => {
        event.preventDefault();

        const {action, method} = form;

        const url = new URL(action);
        inputs.forEach(input => url.searchParams.append(input.getAttribute("name"), input.value));

        fetch(url, {headers: {"Content-Type": "application/json"}, method})
            .then(res => res.json())
            .then(data => {
                if (data.code !== 200) throw new Error(data.message);
            })
            .then(() => window.location.href = "${pageContext.request.contextPath}/lobby")
            .catch((error) => {
                // Animations des champs
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

                // Retirer la notification et les animations après 5 secondes
                setTimeout(() => notification.remove(), 5010);
            });
    });

    // Retirer les animations des champs après la fin de l'animation
    inputs.forEach(input => input.addEventListener("animationend", () => input.style.animation = ""));
</script>

