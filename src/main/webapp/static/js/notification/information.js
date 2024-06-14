export function onInfo(title, message) {

  // Notification
  const notification = document.createElement("div");
  notification.classList.add("notification", "is-info", "is-light");

  const notificationTitle = document.createElement("p");
  notificationTitle.classList.add("title", "is-6");
  notificationTitle.innerHTML = title;

  const notificationIcon = document.createElement("span");
  notificationIcon.classList.add("icon");
  notificationIcon.innerHTML = "<i class='fa-solid fa-envelope fa-beat-fade' style='color: #74C0FC;'></i>";

  const notificationMessage = document.createElement("p");
  notificationMessage.classList.add("subtitle", "is-6");
  notificationMessage.innerHTML = message;

  notificationTitle.appendChild(notificationIcon);
  notification.appendChild(notificationTitle);
  notification.appendChild(notificationMessage);
  document.body.appendChild(notification);

  setTimeout(() => notification.remove(), 5010);
}
