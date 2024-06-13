import {onError} from "../notification/error.js";
const languageSelector = document.getElementById('language-select');

const resetPasswordForm = document.querySelector("form#reset-password-form");
const submitButton = document.querySelector("input[type=submit]");
const inputs = resetPasswordForm.querySelectorAll("input[type='password']");

const tokenInput = document.querySelector("input#token");
const passwordInput = document.querySelector("input#password");
const repasswordInput = document.querySelector("input#repassword");

resetPasswordForm.addEventListener("submit", onSubmit)

function onSubmit(event) {
  event.preventDefault();

  const {action, method} = resetPasswordForm;

  const url = new URL(action);
  const contextPath = url.href.substring(0, url.href.lastIndexOf("/") + 1);
  inputs.forEach(input => url.searchParams.append(input.getAttribute("name"), input.value));
  url.searchParams.append("token", tokenInput.value);
  submitButton.classList.add("is-loading");

  fetch(url, {headers: {"Content-Type": "application/json"}, method})
    .then(res => res.json())
    .then(data => {
      if (data.code !== 200) {
        throw new Error(data.message);
      }
    })
    .then(() => window.location.href = contextPath+"login?success=reset-password")
    .catch((error) => {
      console.log(inputs);
      onError(error, inputs);
      }
    )
    .finally(() => submitButton.classList.remove("is-loading"));
}
