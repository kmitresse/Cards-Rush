const loginForm = document.getElementById("loginForm");

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
            if (data.status === 200 && data.nextUrl) window.location.href = data.nextUrl;
        })
        .catch(error => console.error("Error:", error))
    ;
});
