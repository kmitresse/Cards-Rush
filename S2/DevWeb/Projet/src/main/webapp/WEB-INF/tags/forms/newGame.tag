<%@tag description="form/newGame" pageEncoding="UTF-8" %>

<form id="newGame-form" action="${pageContext.request.contextPath}/main-menu" method="post">

    <h2 class="title is-5">Paramètre Général</h2>

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

    <h2 class="title is-5">Paramètre des Manches</h2>

    <div class="columns">
        <div class="column">
            <div class="field">
                <label class="label">Nombre de manche</label>
            </div>
        </div>
        <div class="column">
            <div class="field">
                <label class="label">Durée d'une manche</label>
            </div>
        </div>
    </div>

    <hr/>

    <h2 class="title is-5">Paramètre du Deck</h2>

    <div class="columns">
        <div class="column">
            <div class="field">
                <label class="label">Nombre de valeur par deck</label>
            </div>
        </div>
        <div class="column">
            <div class="field">
                <label class="label">Nombre de couleur par deck</label>
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
</style>

<script defer type="module">
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

