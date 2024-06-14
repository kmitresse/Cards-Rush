
import WebsocketToolkit from "../WebsocketToolkit.js";

let users = [];
const languageSelector = document.getElementById('language-select');
let inviteLabel;
if (languageSelector.value === 'EN') {
  inviteLabel = 'Invite';
}
else {
  inviteLabel = 'Inviter';

}
const game = document.querySelector('#game-id');
const from_id = document.querySelector('#user-id');
const from_username = document.querySelector('#user-username');

function updateUsers(users) {
  const $tbody = document.querySelector('#user-list-modal tbody');
  $tbody.innerHTML = '';
  users.forEach(user => {
    const $tr = document.createElement('tr');
    const $tdUsername = document.createElement('td');
    const $tdNbPlayedGame = document.createElement('td');
    const $tdNbWins = document.createElement('td');
    const $tdScore = document.createElement('td');
    const $tdRightClick = document.createElement('td');
    const $tdRapidClick = document.createElement('td');
    const $tdAction = document.createElement('td');
    const $button = document.createElement('button');

    $button.classList.add('button', 'is-primary', 'is-light');
    $button.textContent = inviteLabel;
    $button.addEventListener('click', () => {
      const data = {
        from: {
          id: from_id.value,
          username: from_username.value
        },
        to: {...user},
        game_id: game.value
      };
      const message = {
        type: "invite",
        data: JSON.stringify(data)
      };

      ws.ws.send(JSON.stringify(message));
    })


    $tdUsername.textContent = user.username;
    $tdNbPlayedGame.textContent = user.nbPlayedGames;
    $tdNbWins.textContent = user.nbWin;
    $tdScore.textContent = user.scoreRate + "%";
    $tdRightClick.textContent = user.rigthClickPercentRate + "%";
    $tdRapidClick.textContent = user.rapidClickPercentRate + "%";
    $tdAction.appendChild($button);

    $tr.appendChild($tdUsername);
    $tr.appendChild($tdNbPlayedGame);
    $tr.appendChild($tdNbWins);
    $tr.appendChild($tdScore);
    $tr.appendChild($tdRightClick);
    $tr.appendChild($tdRapidClick);
    $tr.appendChild($tdAction);

    $tbody.appendChild($tr);
  });
}

// Websocket for users in the lobby

const url = new URL(window.location.href);
const contextPath = url.pathname.substring(0, url.pathname.indexOf("/", 1) + 1)
url.pathname = contextPath + "ws/users/0";
url.protocol = "ws:"

const ws = new WebsocketToolkit(url);
ws.onOpen(_ => console.log("Connected to the server"));
ws.onMessage("init", (data) => {
  users = data;
  updateUsers(users);
});
ws.onMessage("addUser", (data) => {
  users.push(data);
  updateUsers(users);
});
ws.onMessage("removeUser", (data) => {
  users = users.filter(user => user.id !== data.id);
  updateUsers(users);
});
ws.onMessage("invite", (data) => {
  const {from, to, game} = data;
})
ws.onError((error) => console.error(error));
ws.onClose(() => console.log("Disconnected from the server"));
