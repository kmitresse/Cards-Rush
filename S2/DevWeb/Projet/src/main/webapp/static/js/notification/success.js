export class Success {
  static successLabels = {
    EN: "Success",
    FR: "Succès",
  }

  static successKeys = {
    FORGOTTEN_PASSWORD: "forgotten-password",
    RESET_PASSWORD: "reset-password",
    UPDATE_PROFILE: "update-profile",
    CREATE_ACCOUNT: "create-account",
  }

  static successValues = {
    EN: {
      FORGOTTEN_PASSWORD: "An email has been sent to you to reset your password",
      RESET_PASSWORD: "Password successfully recovered",
      UPDATE_PROFILE: "Profile successfully modified",
      CREATE_ACCOUNT: "Account successfully created",
    },
    FR: {
      FORGOTTEN_PASSWORD: "Un email vous a été envoyé pour réinitialiser votre mot de passe",
      RESET_PASSWORD: "Mot de passe récupéré avec succès",
      UPDATE_PROFILE: "Profil modifié avec succès",
      CREATE_ACCOUNT: "Compte créé avec succès",
    }
  }
}

export function onSuccess(title, message) {
  console.log("Succès:", "Modifications effectuées avec succès")

  // Notification
  const notification = document.createElement("div");
  notification.classList.add("notification", "is-success");

  const notificationTitle = document.createElement("p");
  notificationTitle.classList.add("title", "is-6");
  notificationTitle.innerHTML = title;

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

export function verifSuccess() {
  const languageSelector = document.getElementById('language-select');

  const url = new URL(window.location.href);
  if (url.searchParams.get("success") !== undefined && url.searchParams.get("success") === Success.successKeys.FORGOTTEN_PASSWORD) {
    if (languageSelector.value === "EN") {
      onSuccess(Success.successLabels.EN, Success.successValues.EN.FORGOTTEN_PASSWORD)
    } else {
      onSuccess(Success.successLabels.FR, Success.successValues.FR.FORGOTTEN_PASSWORD)
    }
  }
  if (url.searchParams.get("success") !== undefined && url.searchParams.get("success") === Success.successKeys.RESET_PASSWORD) {
    if (languageSelector.value === "EN") {
      onSuccess(Success.successLabels.EN, Success.successValues.EN.RESET_PASSWORD)
    } else {
      onSuccess(Success.successLabels.FR, Success.successValues.FR.RESET_PASSWORD)
    }
    if (url.searchParams.get("success") !== undefined && url.searchParams.get("success") === Success.successKeys.CREATE_ACCOUNT) {
      if (languageSelector.value === "EN") {
        onSuccess(Success.successLabels.EN, Success.successValues.EN.CREATE_ACCOUNT)
      } else {
        onSuccess(Success.successLabels.FR, Success.successValues.FR.CREATE_ACCOUNT)
      }
    }
    if (url.searchParams.get("success") !== undefined && url.searchParams.get("success") === Success.successKeys.UPDATE_PROFILE) {
      if (languageSelector.value === "EN") {
        onSuccess(Success.successLabels.EN, Success.successValues.EN.UPDATE_PROFILE)
      } else {
        onSuccess(Success.successLabels.FR, Success.successValues.FR.UPDATE_PROFILE)
      }
    }
  }
}
