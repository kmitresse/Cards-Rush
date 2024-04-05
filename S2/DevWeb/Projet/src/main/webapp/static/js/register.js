const registerForm = document.getElementById("register-form");
const confirmPassword = document.getElementById("confirmPassword");

registerForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const formData = new FormData(registerForm);

    const data = {};
    formData.forEach((value, key) => data[key] = value);

    fetch(registerForm.getAttribute("action"), {
        method: registerForm.getAttribute("method"),
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
        .then(res => res.json())
        .then(_ => {
            window.location.href = "./login"
        })
        .catch(error => console.error("Error: " + error))
});
