// Mise à jour du nombre de rounds max en fonction du nombre de couleurs et de valeurs sélécetionnés
document.getElementById("nbColors").addEventListener("change", function () {
  let nbColors = document.getElementById("nbColors").value;
  let nbValues = document.getElementById("nbValues").value;
  let nbRounds = document.getElementById("nbRounds");
  nbRounds.max = nbColors * nbValues;
  nbRounds.value = nbRounds.value > nbRounds.max ? nbRounds.max : nbRounds.value;
});
document.getElementById("nbValues").addEventListener("change", function () {
  let nbColors = document.getElementById("nbColors").value;
  let nbValues = document.getElementById("nbValues").value;
  let nbRounds = document.getElementById("nbRounds");
  nbRounds.max = nbColors * nbValues;
  nbRounds.value = nbRounds.value > nbRounds.max ? nbRounds.max : nbRounds.value;
});
