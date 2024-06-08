function togglePasswordVisibility() {
    var passwordField = document.getElementById("senha");
    var passwordToggle = document.getElementById("toggleSenha");
    
    if (passwordField.type === "password") {
        passwordField.type = "text";
        passwordToggle.textContent = "Esconder Senha";
    } else {
        passwordField.type = "password";
        passwordToggle.textContent = "Mostrar Senha";
    }
}

async function loginUser() {
    let email = document.getElementById("email").value;
    let password = document.getElementById("senha").value;

    console.log(email, password);
    const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: new Headers ({
            "Content-Type": "application/json; charset=utf8",
            accept: "application/json"
        }),
        body: JSON.stringify({ 
            email: email, 
            password: password 
        }),
    });

    let key = "Authorization";
    let token = response.headers.get(key);
    window.localStorage.setItem(key, token);

    if (response.ok) {
        showToast("#okToast");

        // Adicionar o token no cabeçalho da segunda requisição
        const portfolioResponse = await fetch('http://localhost:8080/user/profile/musician', {
            headers: new Headers({
                "Authorization": token
            })
        });

        if (portfolioResponse.status === 204) { // No Content
            alert('Perfil incompleto. Redirecionando para a página de cadastro de perfil...');
            window.setTimeout(function() {
                window.location.href = '../attMUSICO/attMUSICO.html';
            }, 2000);
        } else if (portfolioResponse.ok) {
            alert('Login bem-sucedido. Redirecionando...');
            window.setTimeout(function() {
                window.location.href = '../portMUSICO/portifolio-musico.html';
            }, 2000);
        } else {
            throw new Error('Erro ao verificar o portfólio do músico');
        }
    } else {
        showToast("#errorToast");
    }

    function showToast(id) {
        var toastElList = [].slice.call(document.querySelectorAll(id));
        var toastList = toastElList.map(function(toastEl) {
            return new bootstrap.Toast(toastEl);
        });
        toastList.forEach((toast) => toast.show());
    }
}
