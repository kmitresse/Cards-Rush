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

//Récupération de mot de passe réussie = redirection vers la page de connexion + message d'alerte
const urlParams = new URLSearchParams(window.location.search);
const succes = urlParams.get('succes');
if (succes != null) {
    window.alert(succes);
}
