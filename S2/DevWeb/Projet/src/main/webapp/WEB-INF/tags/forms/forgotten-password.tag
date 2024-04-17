<%@tag description="form/forgotten-password" pageEncoding="UTF-8" %>

<form id="forgotten-password-form" action="${pageContext.request.contextPath}/forgotten-password" method="post">
    <div class="field"> <!-- Field Username -->
        <label class="label" for="email">Email</label>
        <input id="email" placeholder="johndoe@exemple.com" class="input is-fullwidth" required/>
    </div>
    <input type="submit" class="button is-primary is-fullwidth" value="Envoyer">
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
  const forgottenPasswordForm = document.querySelector("form#forgotten-password-form");

  // Form fields
  const emailInput = document.querySelector("input#email");

  // Add event listener to the form submission
  forgottenPasswordForm.addEventListener("submit", onSubmit)

  /**
   * Handle the form submission with Ajax request
   * @param event {Event} - Event of the form submission
   */
  function onSubmit(event) {
    event.preventDefault();

    const {action, method} = forgottenPasswordForm;

    const url = new URL(action);
    url.searchParams.append("email", emailInput.value);

    fetch(url, {headers: {"Content-Type": "application/json"}, method})
      .then(res => res.json())
      .then(data => {
        if (data.code !== 200) throw new Error(data.message);
      })
      .then(() => window.location.href = "${pageContext.request.contextPath}/login")
      .catch(onError);
  }

  /**
   * Handle the error of the form submission
   * @param error {Error} - Error of the form submission
   */
  function onError(error) {
    console.error("Error:", error)

    // Input fields in red
    emailInput.classList.add("is-danger");

    // Shake the inputs
    emailInput.style.animation = "shake 0.5s ease-in-out";

    // Notification
    const notification = document.createElement("div");
    notification.classList.add("notification", "is-danger");
    notification.innerHTML = error.message;
    document.body.appendChild(notification);
    setTimeout(() => notification.remove(), 5010);
  }

  // Remove the shake animation at the end of animation
  emailInput.addEventListener("animationend", () => emailInput.style.animation = "");
</script>

