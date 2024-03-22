const forgottenPasswordForm = document.getElementById("forgottenPasswordForm");

forgottenPasswordForm.addEventListener("submit", (event) => {
    event.preventDefault();
    //Recuperer les données du formulaire
    const formData = new FormData(forgottenPasswordForm);
    const data = {};
    formData.forEach((value, key) => data[key] = value);
    //Recuperer l'URL de l'action et la methode
    const action = forgottenPasswordForm.getAttribute("action");
    const method = forgottenPasswordForm.getAttribute("method");
    //Redirection vers le servlet ForgottenPasswordServlet
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

});

