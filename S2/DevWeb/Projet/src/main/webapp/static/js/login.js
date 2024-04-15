const loginForm = document.getElementById("login-form");

loginForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const formData = new FormData(loginForm);
    const data = {};
    formData.forEach((value, key) => data[key] = value);

    fetch(loginForm.getAttribute("action"), {
        headers: {"Content-Type": "application/json"}, body: JSON.stringify(data), method: loginForm.getAttribute("method"),
    })
        .then(res => res.json())
        .then(user => sessionStorage.setItem("user", JSON.stringify(user)))
        .then(() => window.location.href = "./main-menu")
        .catch(error => console.error("Error:", error));
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
