<%@ tag import="uppa.project.database.pojo.Deck" %>
<%@ tag import="uppa.project.database.pojo.Game" %>
<%@tag description="form/newGame" pageEncoding="UTF-8" %>

<form id="newGame-form" action="${pageContext.request.contextPath}/main-menu" method="post">

    <h2 class="title is-5">Paramètres Généraux</h2>

    <div class="field">
        <label class="label">Difficulté</label>
        <div class="control columns">
            <div class="column">
                <label class="radio button is-fullwidth is-primary is-light">
                    <input type="radio" name="difficulty" checked/>
                    Facile
                </label>
            </div>
            <div class="column">
                <label class="radio button is-fullwidth is-light">
                    <input type="radio" name="difficulty"/>
                    Difficile
                </label>
            </div>
        </div>
    </div>

    <hr/>

    <h2 class="title is-5">Paramètres des Manches</h2>

    <div class="columns">
        <div class="column">
            <div class="field">
                <label class="label" for="nbRounds">Nombre de manche</label>
                <input class="input" type="number" id="nbRounds" name="nbRounds" value="<%= Deck.NB_COLORS_MAX * Deck.NB_VALUES_PER_COLOR_MAX %>" min="<%= Deck.NB_COLORS_MIN * Deck.NB_VALUES_PER_COLOR_MIN %>" max="<%= Deck.NB_COLORS_MAX * Deck.NB_VALUES_PER_COLOR_MAX %>">
            </div>
        </div>
        <div class="column">
            <div class="field">
                <label class="label" for="timer">Durée d'une manche</label>
                <input class="input" type="number" id="timer" name="timer" value="<%= Game.TIMER_MIN %>" min="<%= Game.TIMER_MIN %>" max="<%= Game.TIMER_MAX %>">
            </div>
        </div>
    </div>

    <hr/>

    <h2 class="title is-5">Paramètres du Deck</h2>

    <div class="columns">
        <div class="column">
            <div class="field">
                <label class="label" for="nbValues">Nombre de valeur <span id="tootltip-values"> <%= Deck.NB_VALUES_PER_COLOR_MAX %></span> </label>
                <div class="range-field">
                    <div class="value left"><%= Deck.NB_VALUES_PER_COLOR_MIN %></div>
                    <input type="range" id="nbValues" name="nbValues" value="<%= Deck.NB_VALUES_PER_COLOR_MAX %>" min="<%= Deck.NB_VALUES_PER_COLOR_MIN %>" max="<%= Deck.NB_VALUES_PER_COLOR_MAX %>">
                    <div class="value right"><%= Deck.NB_VALUES_PER_COLOR_MAX %></div>
                </div>
                </div>
        </div>
        <div class="column">
            <div class="field">
                <label class="label" for="nbColors">Nombre de couleur <span id="tootltip-colors"> <%= Deck.NB_COLORS_MAX %></span> </label>
                <div class="range-field">
                    <div class="value left"><%= Deck.NB_COLORS_MIN %></div>
                    <input type="range" id="nbColors" name="nbColors" value="<%= Deck.NB_COLORS_MAX %>" min="<%= Deck.NB_COLORS_MIN %>" max="<%= Deck.NB_COLORS_MAX %>">
                    <div class="value right"><%= Deck.NB_COLORS_MAX %></div>
                </div>
            </div>
        </div>
    </div>
</form>

<style>
    label.radio.button > input[type="radio"] {
        display: none;
    }

    .notification {
        position: absolute;
        bottom: 0;
        right: 0;
        margin: 1em !important;

        transform: translateY(100%);
        opacity: 0;
        animation: toast 5s ease forwards;
    }

    @keyframes toast {
        0% {
            opacity: 0;
            transform: translateY(100%);
        }
        5% {
            opacity: 1;
            transform: translateY(0);
        }
        95% {
            opacity: 1;
            transform: translateY(0);
        }
        100% {
            opacity: 0;
            transform: translateY(100%);
        }
    }

    @keyframes shake {
        0%, 100% {
            transform: translateX(0);
        }
        10%, 30%, 50%, 70%, 90% {
            transform: translateX(-5px);
        }
        20%, 40%, 60%, 80% {
            transform: translateX(5px);
        }
    }

    .range-field {
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: center;
        height: 100%;
        position: relative;
        gap: 5px;
    }
    .range-field .value {

        font-size: 18px;
        color: #045fa4;
        font-weight: 600;
    }
    .left {
        left: 0px;
    }
    .right {
        right: 0px;
    }
    .field input {
        flex: 1 1 auto;
    }

    .sliderValue {
        position: relative;
        width: 100%;
    }

    .sliderValue span {
        position: absolute;
        height: 45px;
        width: 45px;
        transform: translateX(-70%) scale(0);
        font-weight: 500;
        top: -40px;
        line-height: 55px;
        z-index: 2;
        color: #fff;
        transform-origin: bottom;
        transition: transform 0.3s ease-in-out;
    }

    .sliderValue span:after {
        position: absolute;
        content: "";
        height: 100%;
        width: 100%;
        background: #045fa4;
        border: 3px solid #fff;
        z-index: -1;
        left: 50%;
        transform: translateX(-50%) rotate(45deg);
        border-bottom-left-radius: 50%;
        box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.1);
        border-top-left-radius: 50%;
        border-top-right-radius: 50%;
    }

    .sliderValue span.show {
        transform: translateX(-70%) scale(1);
    }
</style>

<script defer type="module">
  const nbColorsElement = document.getElementById("nbColors");
  const nbColorsInputValue=document.querySelector("span[id='tootltip-colors']");
  const nbValuesElement = document.getElementById("nbValues");
  const nbValueInputValue=document.querySelector("span[id='tootltip-values']");
  const nbRoundsElement = document.getElementById("nbRounds")

  /**
   * Mise à jour de la valeur du slider
   */
    nbColorsElement.addEventListener("input", () => {
        nbColorsInputValue.textContent = nbColorsElement.value;
        nbColorsInputValue.style.left = 0.5 * (nbColorsElement.value) + "%";
    });


      nbValuesElement.addEventListener("input", () => {
        nbValueInputValue.textContent = nbValuesElement.value;
        nbValueInputValue.style.left = 0.5 * (nbValuesElement.value) + "%";
      });

  // nbValueInputValue.style.left = 0.5 * (nbValuesElement.value) + "%";
  // nbValueInputValue.style.left = 0.5 * (nbValuesElement.value) + "%";


  /**
   * Mise à jour du nombre de rounds max en fonction du nombre de couleurs et de valeurs séléctionnés
   */
  function updateOnChange() {
    nbRoundsElement.max = nbColorsElement.value * nbValuesElement.value;

    nbRoundsElement.value =
      (nbRoundsElement.value > nbRoundsElement.max)
        ? nbRoundsElement.max
        : nbRoundsElement.value
    ;
  }

  nbColorsElement.addEventListener("change", updateOnChange);
  nbValuesElement.addEventListener("change", updateOnChange);

  const radioButtons = document.querySelectorAll('input[type="radio"]');

    // if radio is checked, add class 'is-primary' to the parent label
    radioButtons.forEach(radio => {
        radio.addEventListener('change', () => {
            radioButtons.forEach(radio => {
                radio.parentElement.classList.remove('is-primary');
            });
            radio.parentElement.classList.add('is-primary');
        });
    });
</script>
