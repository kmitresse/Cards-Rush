const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const formData = new FormData(loginForm);
    const data = {};
    formData.forEach((value, key) => data[key] = value);

    const action = loginForm.getAttribute("action")
    const endpoint = loginForm.getAttribute("data-login-endpoint");
    const method = loginForm.getAttribute("method")

    fetch(endpoint, {
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data),
        method,
    })
        .then(res => res.json())
        .then(data => {
            if (data)
            window.location.href = action;
        })
        .catch(error => console.error("Error:", error))
    ;
});