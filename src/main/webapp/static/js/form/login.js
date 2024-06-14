import {onError} from "../notification/error.js";
import {verifSuccess} from "../notification/success.js";

//Vérifier si un formulaire a été soumis avec succès
verifSuccess();

const form = document.querySelector("form#login-form");
const inputs = form.querySelectorAll("input[type='text'], input[type='password']");

form.addEventListener("submit", event => {
  event.preventDefault();

  const {action, method} = form;

  const url = new URL(action);
  const contextPath = url.href.substring(0, url.href.lastIndexOf("/") + 1);
  inputs.forEach(input => url.searchParams.append(input.getAttribute("name"), input.value));

  fetch(url, {headers: {"Content-Type": "application/json"}, method})
    .then(res => res.json())
    .then(data => {
      if (data.code !== 200) throw new Error(data.message);
    })
    .then(() => window.location.href = contextPath + "lobby")
    .catch((error) => onError(error, inputs))
});

// Retirer les animations des champs après la fin de l'animation
inputs.forEach(input => input.addEventListener("animationend", () => input.style.animation = ""));
