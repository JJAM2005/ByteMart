document.addEventListener("DOMContentLoaded", function() {
    const container = document.getElementById('container');
    const registerButton = document.getElementById('register');
    const loginButton = document.getElementById('login');

    registerButton.addEventListener('click', () => {
        container.classList.add("active");
    });

    loginButton.addEventListener('click', () => {
        container.classList.remove("active");
    });
});



document.getElementById('google').addEventListener('click', () => {
    const googleLoginUrl = 'https://accounts.google.com/o/oauth2/auth?' +
        'response_type=token&' +
        'client_id=272919508467-jvsfn8jvdqm3imoir9ppketpasn7babq.apps.googleusercontent.com&' +
        'redirect_uri=http://127.0.0.1:5500/src/main/resources/templates/Usuario/login.html#' +
        'scope=email%20profile';
    window.location.href = googleLoginUrl;
});

document.getElementById('facebook').addEventListener('click', () => {
    const facebookLoginUrl = 'https://www.facebook.com/v12.0/dialog/oauth?' +
        'response_type=token&' +
        'client_id=1764317554095812&' +
        'redirect_uri=http://127.0.0.1:5500/src/main/resources/templates/Usuario/login.html#' +
        'scope=email';
    window.location.href = facebookLoginUrl;
});

document.getElementById('google1').addEventListener('click', () => {
    const googleLoginUrl = 'https://accounts.google.com/o/oauth2/auth?' +
        'response_type=token&' +
        'client_id=272919508467-jvsfn8jvdqm3imoir9ppketpasn7babq.apps.googleusercontent.com&' +
        'redirect_uri=http://127.0.0.1:5500/src/main/resources/templates/Usuario/login.html#' +
        'scope=email%20profile';
    window.location.href = googleLoginUrl;
});

document.getElementById('facebook1').addEventListener('click', () => {
    const facebookLoginUrl = 'https://www.facebook.com/v12.0/dialog/oauth?' +
        'response_type=token&' +
        'client_id=1764317554095812&' +
        'redirect_uri=http://127.0.0.1:5500/src/main/resources/templates/Usuario/login.html#' +
        'scope=email';
    window.location.href = facebookLoginUrl;
});