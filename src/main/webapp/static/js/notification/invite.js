export function onInvite(from, contextPath, game_id) {
  const languageSelector = document.getElementById('language-select');

  //Translatables variables
  let inviteTitle;
  let inviteMsg;
  let acceptButtonLabel;
  let declineButtonLabel;
  if (languageSelector.value === "EN") {
    inviteTitle = "Invitation from " + from + " !";
    inviteMsg = from + " invited you to join his game";
    acceptButtonLabel = "Accept";
    declineButtonLabel = "Decline";
  } else {
    inviteTitle = "Invitation de " + from + " !";
    inviteMsg = from + " vous a invité à rejoindre sa partie";
    acceptButtonLabel = "Accepter";
    declineButtonLabel = "Refuser";
  }

  // Notification
  const notification = document.createElement("div");
  notification.classList.add("notification", "invite-notification", "is-info", "is-light");

  const notificationTitle = document.createElement("p");
  notificationTitle.classList.add("title", "is-6");
  notificationTitle.innerHTML = inviteTitle;

  const notificationIcon = document.createElement("span");
  notificationIcon.classList.add("icon");
  notificationIcon.innerHTML = "<i class='fa-solid fa-envelope fa-beat-fade' style='color: #74C0FC;'></i>";

  const notificationSubtitle = document.createElement("div");
  notificationSubtitle.classList.add("subtitle", "is-6");
  const notificationMessage = document.createElement("p");
  notificationMessage.classList.add("is-6");
  notificationMessage.innerHTML = inviteMsg;

  const buttons = document.createElement("div");
  buttons.classList.add("buttons", "is-flex", "is-flex-direction-row");

  const acceptButton = document.createElement("a");
  acceptButton.classList.add("button", "is-success");
  acceptButton.textContent = acceptButtonLabel;
  acceptButton.addEventListener("click", () => {
    window.location.href = contextPath + "game?id=" + game_id;
  });

  const declineButton = document.createElement("a");
  declineButton.classList.add("button", "is-danger");
  declineButton.textContent = declineButtonLabel;
  declineButton.addEventListener("click", () => {
    notification.remove();
  });

  buttons.appendChild(acceptButton);
  buttons.appendChild(declineButton);
  notificationSubtitle.appendChild(notificationMessage);
  notificationSubtitle.appendChild(buttons);

  notificationTitle.appendChild(notificationIcon);
  notification.appendChild(notificationTitle);
  notification.appendChild(notificationSubtitle);
  notification.appendChild(buttons);
  document.body.appendChild(notification);
  // Retirer la notification et les animations après 5 secondes
};
