const nbColorsElement = document.getElementById("nbColors");
const nbValuesElement = document.getElementById("nbValues");
const nbRoundsElement = document.getElementById("nbRounds")

/**
 * Mise à jour du nombre de rounds max en fonction du nombre de couleurs et de valeurs séléctionnés
 */
function updateOnChange() {
  nbRoundsElement.max = nbColorsElement.value * nbValuesElement.value;

  nbRoundsElement.value =
    (nbRoundsElement.value > nbRoundsElement.max)
      ? nbRoundsElement.max
      : nbRoundsElement.value
  ;
}

nbColorsElement.addEventListener("change", updateOnChange);
nbValuesElement.addEventListener("change", updateOnChange);
