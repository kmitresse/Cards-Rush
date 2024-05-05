import {onError} from "../notification/error.js";
const forgottenPasswordForm = document.querySelector("form#forgotten-password-form");

// Champ email
const inputs = [document.querySelector("input#email")];

// Ajout de l'écouteur d'événement sur la soumission du formulaire
forgottenPasswordForm.addEventListener("submit", onSubmit)

/**
 * Gestion de la soumission du formulaire
 * @param event {Event} - Événement de soumission du formulaire
 */
function onSubmit(event) {
  event.preventDefault();

  const {action, method} = forgottenPasswordForm;

  const url = new URL(action);
  const contextPath = url.href.substring(0, url.href.lastIndexOf("/") + 1);
  inputs.forEach(input => url.searchParams.append(input.getAttribute("name"), input.value));

  fetch(url, {headers: {"Content-Type": "application/json"}, method})
    .then(res => res.json())
    .then(data => {
      if (data.code !== 200) throw new Error(data.message);
    })
    .then(() => window.location.href = contextPath+"login?success=forgotten-password")
    .catch((error) => onError(error, inputs));
}
