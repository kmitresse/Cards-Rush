import WebsocketToolkit from "../WebsocketToolkit.js";
import PlayerHand from "../PlayerHand.js"
import Card from "../Card.js"
import {onInfo} from "../notification/information.js";
import {onError} from "../notification/error.js";
const languageSelector = document.getElementById('language-select');

const choice = document.querySelector('#choice');
// Game information
const gameId = document.querySelector('#game-id');
const gameTimer = document.querySelector('#game-timer');

//User session information
const userSessionId = document.querySelector('#user-id');
const userSessionUsername = document.querySelector('#user-username');

let gameAdminID = -1
const buttons = document.querySelectorAll('a#start-game-button, a#invite-player')

function updateButtons(user) {
  buttons.forEach(button => {
    if (user.id !== gameAdminID ) {
      console.log("not admin" + user.username)
      button.classList.add('is-disable');
      button.setAttribute('title', 'Accessible uniquement par l\'admin');
    } else {
      console.log("is admin" + user.username)
      button.classList.remove('is-disable');
      button.removeAttribute('title');
    }
  });
}

function updateAdmin(user) {
  if (gameAdminID !== -1) {
    let titleInfo;
    let messageInfo;
    if (languageSelector.value === "EN") {
      titleInfo = "The admin has leaved the game";
      messageInfo = "You are automatically select has thenew admin";
    } else {
      titleInfo = "L'admin a quitté la partie";
      messageInfo = "Vous êtes automatiquement désigné comme le nouvel admin";
    }
    onInfo(titleInfo, messageInfo)
  }
  console.log("admin:" + user.username)
  gameAdminID = user.id;
}
let havePlayed = false;

const timer = gameTimer.value;
let remainingTime = timer;
let timerInterval;

// Display timer
document.querySelector('#timer').innerText = remainingTime + "s";

const interval = () => {
  remainingTime--;

  document.querySelector('#timer').innerText = remainingTime + "s";

  if (remainingTime <= 0) clearInterval(timerInterval);
}

choice.querySelectorAll('button').forEach(button => {
  button.addEventListener('click', () => {
    const message = {
      type: "click",
      data: button.dataset.value
    }
    wsgame.ws.send(JSON.stringify(message));

    havePlayed = true;

    // Disable buttons
    choice.querySelectorAll('button').forEach(button => button.disabled = true);
  });
});


const url = new URL(window.location.href);
const contextPath = url.pathname.substring(0, url.pathname.indexOf("/", 1) + 1)
url.pathname = contextPath + "ws/game/"+gameId.value;
url.protocol = "ws:";
url.searchParams.delete("id");

const wsgame = new WebsocketToolkit(url);
wsgame.onOpen(() => {
  console.log("Connected to the server (GameWS)")

  const message = {
    type: "connection",
    data: JSON.stringify({
      id: userSessionId.value,
      username: userSessionUsername.value
    })
  }

  wsgame.ws.send(JSON.stringify(message))
});
wsgame.onMessage("updatePlayerList", (data) => {
  players = data;
  console.log(players)
  if (gameAdminID === -1 || players[0].user.id !== gameAdminID) {
    updateAdmin(players[0].user)

  }
  players.forEach((player) => {
    console.log("player:" + player.user.id)
    console.log("user:" + userSessionId.value)
    console.log( player.user.id.toString() == userSessionId.value)
    if (player.user.id.toString() === userSessionId.value) {
      updateButtons(player.user)
    }
  });
  updatePlayerList();
});
wsgame.onMessage("start", (game) => {
  currentGame = game;

  document.querySelector('#gameWaiting').style.display = 'none';
  document.querySelector('#gameStarted').style.display = 'block';

  timerInterval = setInterval(interval, 1000);

  const deck = document.querySelector('#deck'); // Column
  const myCard = document.querySelector('#myCard'); // Column
  const otherCards = document.querySelector('#otherCards'); // Columns
  const round = document.querySelector('#round');

  // Reset content
  deck.innerHTML = "";
  myCard.innerHTML = "";
  otherCards.innerHTML = "";
  round.innerText = "";

  // Show current round
  round.innerText = (currentGame.currentRound + 1)

  // Show other player cards
  game.players
    .filter(p => p.user.id !== userSessionId.value)
    .forEach(p => {
      const playerHand = new PlayerHand(p);
      otherCards.innerHTML += playerHand.render({
        textPosition: PlayerHand.TextPosition.TOP,
        className: "column"
      });
    });

  // Show my card
  const me = game.players.find(p => p.user.id === userSessionId.value);

  const playerHand = new PlayerHand(me);
  myCard.innerHTML += playerHand.render({
    textPosition: PlayerHand.TextPosition.BOTTOM,
    className: "column"
  });

  // Show deck
  const deckCard = new Card(game.currentCard.color, game.currentCard.value);
  deck.innerHTML = deckCard.render();
})
wsgame.onMessage("updatePlayer", (p) => {
  document.querySelector(".player-" + p.user.id + " .card-play").style.boxShadow = "inset 0px 0px 30px 10px orange";
})
wsgame.onMessage("timerEnd", (game) => {
  if (!havePlayed) {
    const message = {
      type: "click",
      data: "TIMER_END"
    }
    wsgame.ws.send(JSON.stringify(message));
  }
})

wsgame.onMessage("nextRound", (game) => {
  currentGame = game;

  document.querySelector('#gameWaiting').style.display = 'none';
  document.querySelector('#gameStarted').style.display = 'block';

  const deck = document.querySelector('#deck'); // Column
  const choice = document.querySelector('#choice');
  const myCard = document.querySelector('#myCard'); // Column
  const otherCards = document.querySelector('#otherCards'); // Columns
  const round = document.querySelector('#round');

  clearInterval(timerInterval);
  remainingTime = timer;
  document.querySelector('#timer').innerText = remainingTime + "s";
  timerInterval = setInterval(interval, 1000);

  // Reset content
  deck.innerHTML = "";
  myCard.innerHTML = "";
  otherCards.innerHTML = "";
  round.innerText = "";
  havePlayed = false;
  choice.querySelectorAll('button').forEach(button => button.disabled = false);

  // Show the current round
  round.innerText = (currentGame.currentRound + 1)

  // Show other player cards
  game.players
    .filter(p => p.user.id !== userSessionId.value)
    .forEach(p => {
      const playerHand = new PlayerHand(p);
      otherCards.innerHTML += playerHand.render({
        textPosition: PlayerHand.TextPosition.TOP,
        className: "column"
      });
    });

  // Show my card
  const me = game.players.find(p => p.user.id === userSessionId.value);

  const playerHand = new PlayerHand(me);
  myCard.innerHTML += playerHand.render({
    textPosition: PlayerHand.TextPosition.BOTTOM,
    className: "column"
  });

  // Show deck
  const deckCard = new Card(game.currentCard.color, game.currentCard.value);
  deck.innerHTML = deckCard.render();
})

wsgame.onMessage("end", (game) => {
  clearInterval(timerInterval);
  window.location.href = "${pageContext.request.contextPath}/game-statistics?id=${game.id}&endGame=true";
})

// Close handling
wsgame.onClose(() => console.log("Disconnected from the server (GameWS)"));

// Game
let currentGame;

// Player List
let players = [];
const playerList = document.querySelector('#playerList tbody');

function updatePlayerList() {
  playerList.innerHTML = '';
  players.forEach(player => {
    const tr = document.createElement('tr');
    const td = document.createElement('td');
    td.textContent = player.user.username;
    tr.appendChild(td);
    playerList.appendChild(tr);
  });
}

// Start Game Button
document.querySelector('#start-game-button').addEventListener('click', () => {
  if (players.length < 2 || players.length > 4) {
      if (languageSelector.value === "EN") {
        onError(new Error("You need between 2 and 4 players to start the game"));
      } else {
        onError(new Error("Il faut entre 2 et 4 joueurs pour démarrer la partie"));
      }
    return;
  }

  const message = {type: "start", data: ""}
  wsgame.ws.send(JSON.stringify(message));
});
