import {onError} from "../notification/error.js";
import {onSuccess} from "../notification/success.js";
import {Success} from "../notification/success.js";

const profileForm = document.querySelector("form#profile-form");
const username = profileForm.querySelector("input[name='username']");
const changePassword = profileForm.querySelector("a#change-password");
const passwordFields = profileForm.querySelectorAll("div#old-password-field, div#password-field, div#repeat-password-field");
const inputs = profileForm.querySelectorAll("input[type='text'], input[type='email'], input[type='password']");

// Afficher les champs de mot de passe si le lien est cliqué
changePassword.addEventListener("click", (e) => {
  e.preventDefault();
  passwordFields.forEach(field => {
    field.style.display = "block";
  });
});

profileForm.addEventListener("submit", onSubmit);

function onSubmit(event) {
  event.preventDefault();

  const oldPassword = profileForm.querySelector("input[name='oldPassword']");
  const password = profileForm.querySelector("input[name='password']");
  const repassword = profileForm.querySelector("input[name='repeat-password']");
  // Check if the password and the confirmation password are the same
  if (oldPassword.value !== "" && password.value !== repassword.value) {
    onError(new Error("Les mots de passe ne correspondent pas"), [oldPassword, password, repassword]);
    return;
  }

  const {action, method} = profileForm;

  const url = new URL(action);
  const contextPath = url.href.substring(0, url.href.lastIndexOf("/") + 1);

  const formData = new FormData(profileForm);
  for (const [key, value] of formData.entries()) {
    url.searchParams.append(key, value);
  }
  url.searchParams.append("username", username.value);

  url.searchParams.forEach((value, key) => console.log(key, value));
  fetch(url, {headers: {"Content-Type": "application/json"}, method})
    .then(res => res.json())
    .then(data => {
      if (data.code !== 200) throw new Error(data.message);
      onSuccess(Success.PROFILE_UPDATED)
    })
    .catch((error) => onError(error, inputs))
}
