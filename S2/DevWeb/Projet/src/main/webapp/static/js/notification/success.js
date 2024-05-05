export class Success {
  static successKeys = {
    FORGOTTEN_PASSWORD: "forgotten-password",
    RESET_PASSWORD: "reset-password",
    UPDATE_PROFILE: "update-profile",
    CREATE_ACCOUNT: "create-account",
  }

  static successValues = {
    FORGOTTEN_PASSWORD: "Un email vous a été envoyé pour réinitialiser votre mot de passe",
    RESET_PASSWORD: "Mot de passe récupéré avec succès",
    UPDATE_PROFILE: "Profil modifié avec succès",
    CREATE_ACCOUNT: "Compte créé avec succès",
  }
}
export function onSuccess(message) {
  console.log("Succès:", "Modifications effectuées avec succès")

  // Notification
  const notification = document.createElement("div");
  notification.classList.add("notification", "is-success");

  const notificationTitle = document.createElement("p");
  notificationTitle.classList.add("title", "is-6");
  notificationTitle.innerHTML = "Succès";

  const notificationIcon = document.createElement("span");
  notificationIcon.classList.add("icon");
  notificationIcon.innerHTML = "<i class='fa-solid fa-check'></i>";

  const notificationMessage = document.createElement("p");
  notificationMessage.classList.add("subtitle", "is-6");
  notificationMessage.innerHTML = message;

  notificationTitle.appendChild(notificationIcon);
  notification.appendChild(notificationTitle);
  notification.appendChild(notificationMessage);
  document.body.appendChild(notification);

  setTimeout(() => notification.remove(), 5010);
}

export function verifSuccess(){
  const url = new URL(window.location.href);
  if (url.searchParams.get("success")!=undefined && url.searchParams.get("success") === Success.successKeys.FORGOTTEN_PASSWORD) {
    onSuccess(Success.successValues.FORGOTTEN_PASSWORD)
  }
  if (url.searchParams.get("success")!=undefined && url.searchParams.get("success") === Success.successKeys.RESET_PASSWORD) {
    onSuccess(Success.successValues.RESET_PASSWORD)
  }
  if (url.searchParams.get("success")!=undefined && url.searchParams.get("success") === Success.successKeys.CREATE_ACCOUNT) {
    onSuccess(Success.successValues.CREATE_ACCOUNT)
  }
  if (url.searchParams.get("success")!=undefined && url.searchParams.get("success") === Success.successKeys.UPDATE_PROFILE) {
    onSuccess(Success.successValues.UPDATE_PROFILE)
  }
}
