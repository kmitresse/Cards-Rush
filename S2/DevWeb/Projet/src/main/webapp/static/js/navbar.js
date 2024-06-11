// Récupération de tous les éléments de classe "navbar-burger"
const $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

// Ajout d'un enventListener sur chaque élément
$navbarBurgers.forEach(el => {
  el.addEventListener('click', () => {

    // Récupere la valeur de l'attribut "data-target"
    const target = el.dataset.target;
    const $target = document.getElementById(target);

    // Ajoute ou supprime la classe "is-active" sur les éléments
    el.classList.toggle('is-active');
    $target.classList.toggle('is-active');
  });
});

let languageSelector = document.getElementById('language-select');

languageSelector.addEventListener('change', function() {
  const contextPath = window.location.href.substring(0,  window.location.href.lastIndexOf("/") + 1);
  const action = contextPath + "/translate";
  console.log(action);
  const method = "PUT";

  const url = new URL(action);
  url.searchParams.append("language", languageSelector.value);

  fetch(url, {headers: {"Content-Type": "application/json"}, method})
    .then(() => window.location.href = window.location.href)

});
