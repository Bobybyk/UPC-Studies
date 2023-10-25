function register() {
    const username = $("#register-username").val()
    const email = $("#register-email").val()
    const password = $("#register-password").val()

    $.post('api/auth/register', { username: username, password: password, email: email }, (res) => {
        if(res.success) {
            location.reload();
        } else {
            window.alert("Nom d'utilisateur ou email déjà utilisé.")
        }
    })
}

function login() {
    const username = $("#login-username").val()
    const password = $("#login-password").val()
    
    $.post('api/auth/login', { username: username, password: password }, (res) => {
        if(res.success) {
            location.reload();
        } else {
            window.alert("Nom d'utilisateur ou mot de passe erroné.")
        }
    })
}