import WebsocketToolkit from "../WebsocketToolkit.js";

let users = [];
const languageSelector = document.getElementById('language-select');
function updateUsers() {
  const table = document.querySelector('#connected-user-list tbody');

  // Clear the table
  table.innerHTML = '';

  // Add the users to the table
  users.forEach(user => {
    const tr = document.createElement('tr');
    const tdUsername = document.createElement('td');
    tdUsername.dataset.id = user.id;
    tdUsername.textContent = user.username;
    const tdNbGames = document.createElement('td');
    tdNbGames.dataset.id = user.id;
    tdNbGames.textContent = user.nbPlayedGames;
    const tdNbWin = document.createElement('td');
    tdNbWin.dataset.id = user.id;
    tdNbWin.textContent = user.nbWin + " (" + user.winRate + "%)";
    const tdScoreRate = document.createElement('td');
    tdScoreRate.dataset.id = user.id;
    tdScoreRate.textContent = user.scoreRate + "%";


    tr.appendChild(tdUsername);
    tr.appendChild(tdNbGames);
    tr.appendChild(tdNbWin);
    tr.appendChild(tdScoreRate);
    table.appendChild(tr);
  });
}

// Get the user id
const user = document.querySelector("#user_id")

// Create a new WebSocket
const url = new URL(window.location.href);
const contextPath = url.pathname.substring(0, url.pathname.indexOf("/", 1) + 1)
console.log(url.pathname)
console.log(contextPath)
url.pathname = contextPath + "ws/users/"+user.value;
console.log(url.pathname)
url.protocol = "ws:"

const ws = new WebsocketToolkit(url);
ws.onOpen(() => console.log("Connected to the server"));
ws.onMessage("init", (data) => {
  users = data;
  updateUsers();
});
ws.onMessage("addUser", (data) => {
  users.push(data);
  updateUsers();
});
ws.onMessage("removeUser", (data) => {
  users = users.filter(user => user.id !== data.id);
  updateUsers();
});
ws.onMessage("invite", (data) => {
  const {from, to, game_id} = data;
  let inviteMsg ;
  if (languageSelector.value === "EN") {
    inviteMsg = from.username + " invited you to join his game";
  } else {
    inviteMsg = from.username + " vous a invité à rejoindre sa partie";
  }
  if (confirm(from.username + inviteMsg)) {
    window.location.href = contextPath+"game?id=" + game_id;
  }
})
ws.onError((error) => console.error(error));
ws.onClose(() => console.log("Disconnected from the server"));
