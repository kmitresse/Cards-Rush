import {onError} from "../notification/error.js";

const registerForm = document.querySelector("form#register-form");

const inputs = registerForm.querySelectorAll("input, select");

registerForm.addEventListener("submit", onSubmit)

function onSubmit(event) {
  event.preventDefault();

  const password = registerForm.querySelector("input[name='password']");
  const repassword = registerForm.querySelector("input[name='repassword']");

  // Check if the password and the confirmation password are the same
  if (password.value !== repassword.value) {
    onError( new Error("Les mots de passe ne correspondent pas"),[password, repassword]);
    return;
  }

  const {action, method} = registerForm;

  const url = new URL(action);
  const contextPath = url.href.substring(0, url.href.lastIndexOf("/") + 1);
  inputs.forEach(input => url.searchParams.append(input.name, input.value));

  fetch(url, {headers: {"Content-Type": "application/json"}, method})
    .then(res => res.json())
    .then(data => {
      if (data.code !== 200) {
        throw new Error(data.message);
      }
    })
    .then(() => window.location.href = contextPath+"login?success=create-account")
    .catch((error) => onError(error, inputs)
    )
}
