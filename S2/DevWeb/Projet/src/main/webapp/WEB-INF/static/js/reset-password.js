const ResetPasswordForm = document.getElementById("resetPasswordForm");

ResetPasswordForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const formData = new FormData(ResetPasswordForm);
    const data = {};
    formData.forEach((value, key) => data[key] = value);

    const action = loginForm.getAttribute("action")
    const method = loginForm.getAttribute("method")


    fetch("/reset-password", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (response.ok) {
            window.location.href = "/login";
        } else {
            response.json().then(data => {
                alert(data.message);
            });
        }
    }).catch(error => {
        console.error("Error:", error);
    });


});

