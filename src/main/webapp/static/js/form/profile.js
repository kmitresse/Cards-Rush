import {onError} from "../notification/error.js";
import {onSuccess} from "../notification/success.js";
import {Success} from "../notification/success.js";
const languageSelector = document.getElementById('language-select');

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

  const {action, method} = profileForm;

  const url = new URL(action);
  const formData = new FormData(profileForm);
  for (const [key, value] of formData.entries()) {
    url.searchParams.append(key, value);
  }
  url.searchParams.append("username", username.value);

  fetch(url, {headers: {"Content-Type": "application/json"}, method})
    .then(res => res.json())
    .then(data => {
      if (data.code !== 200) throw new Error(data.message);
      onSuccess(Success.PROFILE_UPDATED)
    })
    .catch((error) => onError(error, inputs))
}
