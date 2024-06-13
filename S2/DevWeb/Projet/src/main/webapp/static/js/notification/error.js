export function onError(error, inputs = []) {
  // Animations des champs
  const languageSelector = document.getElementById('language-select');

  inputs.forEach(input => {
    input.classList.add("is-danger");
    input.style.animation = "shake 0.5s ease-in-out"
  });

  // Notification
  const notification = document.createElement("div");
  notification.classList.add("notification", "is-danger");

  const notificationTitle = document.createElement("p");
  notificationTitle.classList.add("title", "is-6");
  if (languageSelector.value === "EN") {
    notificationTitle.innerHTML = "Error";
  } else {
    notificationTitle.innerHTML = "Erreur";
  }

  const notificationIcon = document.createElement("span");
  notificationIcon.classList.add("icon");
  notificationIcon.innerHTML = "<i class='fas fa-exclamation-triangle'></i>";

  const notificationMessage = document.createElement("p");
  notificationMessage.classList.add("subtitle", "is-6");
  console.log(error.message)
  notificationMessage.innerHTML = error.message;

  notificationTitle.appendChild(notificationIcon);
  notification.appendChild(notificationTitle);
  notification.appendChild(notificationMessage);
  document.body.appendChild(notification);

  // Retirer la notification et les animations après 5 secondes
  setTimeout(() => {
    notification.remove()
    inputs.forEach(input => input.classList.remove("is-danger"));
  }, 5010);
  inputs.forEach(input => input.addEventListener("animationend", () => input.style.animation = ""));
}
