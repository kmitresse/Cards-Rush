const ERROR_MESSAGE = {
    "expired-token": "Lien expiré, veuillez recommencer la procédure de récupération de mot de passe.",
    "invalid-token": "Lien invalide, veuillez recommencer la procédure de récupération de mot de passe.",
};

const urlParams = new URLSearchParams(window.location.search);
const error = urlParams.get('error');

if (error) {
    const errorMessage = ERROR_MESSAGE[error];
    console.error(errorMessage);
    window.alert(errorMessage);
}
