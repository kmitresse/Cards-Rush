<%@ tag import="uppa.project.database.pojo.Deck" %>
<%@ tag import="uppa.project.database.pojo.Game" %>
<%@tag description="form/new-game" pageEncoding="UTF-8" %>

<form id="new-game-form" action="${pageContext.request.contextPath}/main-menu" method="post">

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
                <label class="label" for="nbRounds">Nombre de manches</label>
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
                <label class="label" for="nbValues">Nombre de valeurs <span id="tootltip-values"> <%= Deck.NB_VALUES_PER_COLOR_MAX %></span> </label>
                <div class="range-field">
                    <div class="value left"><%= Deck.NB_VALUES_PER_COLOR_MIN %></div>
                    <input type="range" id="nbValues" name="nbValues" value="<%= Deck.NB_VALUES_PER_COLOR_MAX %>" min="<%= Deck.NB_VALUES_PER_COLOR_MIN %>" max="<%= Deck.NB_VALUES_PER_COLOR_MAX %>">
                    <div class="value right"><%= Deck.NB_VALUES_PER_COLOR_MAX %></div>
                </div>
                </div>
        </div>
        <div class="column">
            <div class="field">
                <label class="label" for="nbColors">Nombre de couleurs <span id="tootltip-colors"> <%= Deck.NB_COLORS_MAX %></span> </label>
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

    .range-field .value {

        font-size: 18px;
        color: #045fa4;
        font-weight: 600;
    }
    .left {
        left: 0;
    }
    .right {
        right: 0;
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
