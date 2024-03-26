const loginForm = document.getElementById("login-form");

loginForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const formData = new FormData(loginForm);
    const data = {};
    formData.forEach((value, key) => data[key] = value);

    const action = loginForm.getAttribute("action")
    const method = loginForm.getAttribute("method")

    fetch(action, {
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data),
        method,
    })
        .then(res => res.json())
        .then(data => {
            console.log(data);
            // if (data.status === 200) window.location.href = data.redirect;
        })
        .catch(error => console.error("Error:", error))
    ;
});


const urlParams = new URLSearchParams(window.location.search);
if (urlParams.has('success')) {
    if (urlParams.get('success') === "account-created") {
        window.alert("Compte créé avec succès.");
    }
    if (urlParams.get('success') === "password-reseted") {
        window.alert("Mot de passe réinitialisé avec succès.");
    }
}
