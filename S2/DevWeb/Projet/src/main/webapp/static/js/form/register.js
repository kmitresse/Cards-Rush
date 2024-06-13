import {onError} from "../notification/error.js";
const languageSelector = document.getElementById('language-select');

const registerForm = document.querySelector("form#register-form");

const inputs = registerForm.querySelectorAll("input, select");

registerForm.addEventListener("submit", onSubmit)

function onSubmit(event) {
  event.preventDefault();

  const {action, method} = registerForm;

  const url = new URL(action);
  const contextPath = url.href.substring(0, url.href.lastIndexOf("/") + 1);
  inputs.forEach(input => url.searchParams.append(input.name, input.value));
  console.log(url.href);

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
