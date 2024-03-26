window.onload = function (){
    const urlParams = new URLSearchParams(window.location.search);
    let error = null;
    if (urlParams.has('error')) {
        error = urlParams.get('error');
    }
    console.log(error);
    if (error != null && error === "expired-token") {
        window.alert("Lien expiré, veuillez recommencer la procédure de récupération de mot de passe.");
    }
    if (error != null && error === "invalid-token") {
        window.alert("Lien invalide, veuillez recommencer la procédure de récupération de mot de passe.");
    }
}
