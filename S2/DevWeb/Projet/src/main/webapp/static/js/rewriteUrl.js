
function updateUrl(url) {
  // Mettre à jour l'URL sans recharger la page
  history.replaceState(null, null, url);
}
let contextPath = window.location.href.substring(0, window.location.href.lastIndexOf("/") + 1);
console.log(contextPath);

// Mettre à jour l'URL initiale au chargement de la page
updateUrl(contextPath);

