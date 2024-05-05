<%@tag description="form/forgotten-password" pageEncoding="UTF-8" %>

<form id="forgotten-password-form" action="${pageContext.request.contextPath}/forgotten-password" method="post">
    <div class="field">
        <label class="label" for="email">Email</label>
        <div class="control has-icons-left">
            <input id="email" name="email" placeholder="johndoe@exemple.com" class="input is-fullwidth" required/>
            <span class="icon is-small is-left">
                <i class="fas fa-envelope"></i>
            </span>
        </div>
        <div class="help">Veuillez entrer votre email pour obtenir un lien de récupération</div>
    </div>
    <input type="submit" class="button is-primary is-fullwidth has-text-white" value="Envoyer">
</form>

<%--<script defer type="module">--%>
<%--  const forgottenPasswordForm = document.querySelector("form#forgotten-password-form");--%>

<%--  // Champ email--%>
<%--  const inputs = [document.querySelector("input#email")];--%>

<%--  // Ajout de l'écouteur d'événement sur la soumission du formulaire--%>
<%--  forgottenPasswordForm.addEventListener("submit", onSubmit)--%>

<%--  /**--%>
<%--   * Gestion de la soumission du formulaire--%>
<%--   * @param event {Event} - Événement de soumission du formulaire--%>
<%--   */--%>
<%--  function onSubmit(event) {--%>
<%--    event.preventDefault();--%>

<%--    const {action, method} = forgottenPasswordForm;--%>

<%--    const url = new URL(action);--%>
<%--    url.searchParams.append("email", emailInput.value);--%>

<%--    fetch(url, {headers: {"Content-Type": "application/json"}, method})--%>
<%--      .then(res => res.json())--%>
<%--      .then(data => {--%>
<%--        if (data.code !== 200) throw new Error(data.message);--%>
<%--      })--%>
<%--      .then(() => window.location.href = "${pageContext.request.contextPath}/login")--%>
<%--      .catch(onError);--%>
<%--  }--%>
<%--</script>--%>

