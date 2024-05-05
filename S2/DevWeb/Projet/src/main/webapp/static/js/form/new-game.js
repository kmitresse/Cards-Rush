import {onError} from "../notification/error.js";

const nbRound = document.querySelector("input[name='nbRounds']");
const nbValues = document.querySelector("input[name='nbValues']");
const nbColors = document.querySelector("input[name='nbColors']");
const rangeInputs = document.querySelectorAll("input[type='range']");
rangeInputs.forEach(input => updateMaxRound(input));

const radioButtons = document.querySelectorAll('input[type="radio"]');
radioButtons.forEach(radio => updateRadio(radio));

const form = document.getElementById('new-game-form');

form.addEventListener('submit', evt => {
  evt.preventDefault();

  const {action, method} = form;

  const url = new URL(action);
  const contextPath = url.href.substring(0, url.href.lastIndexOf("/") + 1);

  const formData = new FormData(form);
  for (const [key, value] of formData.entries()) {
    url.searchParams.append(key, value);
  }

  fetch(url, {headers: {"Content-Type": "application/json"}, method})
    .then(res => res.json())
    .then(data => {
      if (data.code !== 200) throw new Error(data.message);

      // Redirection vers la page de jeu
      window.location.href = contextPath + "game?id=" + data.message;
    })
    .catch((error) => onError(error.message, inputs))
});

function updateMaxRound(input) {
  const tooltip = document.querySelector(input.dataset.tooltip);
  input.addEventListener("input", () => {
    tooltip.innerHTML = input.value
    nbRound.max = nbValues.value * nbColors.value;
    nbRound.value = parseInt(nbRound.value) > parseInt(nbRound.max) ? nbRound.max : nbRound.value;
  })
}

function updateRadio(radio) {
  radio.addEventListener('change', () => {
    radioButtons.forEach(radio => radio.parentElement.classList.remove('is-primary'));
    radio.parentElement.classList.add('is-primary');
  });
}
