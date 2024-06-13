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

async function getUserFlag(token) {
    if (!token) {
        throw new Error('Token não encontrado.');
    }

    const response = await fetch('http://localhost:8080/user/flagUser', { // Substitua pelo seu endpoint real
        method: 'GET',
        headers: {
            'Authorization': token // Adiciona o token ao cabeçalho da requisição
        }
    });

    if (response.ok) {
        const userDetails = await response.json(); // Obtém o JSON da resposta
        return userDetails;
    } else {
        throw new Error('Erro ao obter os detalhes do usuário amante de musica.');
    }
}
  

async function getUserLoverDetails(token) {
        if (!token) {
            throw new Error('Token não encontrado.');
        }
    
        const response = await fetch('http://localhost:8080/user/lover/profileLover', { // Substitua pelo seu endpoint real
            method: 'GET',
            headers: {
                'Authorization': token // Adiciona o token ao cabeçalho da requisição
            }
        });
    
        if (response.ok) {
            const userDetails = await response.json(); // Obtém o JSON da resposta
            return userDetails;
        } else {
            throw new Error('Erro ao obter os detalhes do usuário musico.');
        }
    }


async function loginUser() {
    let email = document.getElementById("email").value;
    let password = document.getElementById("senha").value;

    console.log(email, password);
    const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: new Headers({
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

        try {
           
            const userDetailsToken = await getUserFlag(token);
           

            const userType = userDetailsToken;
            console.log('Tipo de usuário:', userType);

            if (userType === 1) {
                const portfolioResponse = await fetch('http://localhost:8080/user/profile/musician', {
                    headers: new Headers({
                        "Authorization": token
                    })
                });

                if (portfolioResponse.status === 204) { // No Content
                  
                    window.setTimeout(function () {
                        window.location.href = '../attMUSICO/attMUSICO.html';
                    }, 2000);
                } else if (portfolioResponse.ok) {
                
                    window.setTimeout(function () {
                        window.location.href = '../portMUSICO/portifolio-musico.html';
                    }, 2000);
                } else {
                    throw new Error('Erro ao verificar o portfólio do músico');
                }
            }if (userType === 2) {

                
                  
                  const userDetails = await getUserLoverDetails(token);
                  const comment = userDetails.comment;

                if(comment){
                        alert('Redirecionando para a página de perfil amante musico...');
                    window.setTimeout(function () {
                        window.location.href = '../portAM/portifolio-AM.html';
                    }, 2000);    
                }else{
                    alert('Perfil amante muisco Incompleto. Redirecionando para a página de Criação de perfil...');
                    window.setTimeout(function () {
                        window.location.href = '../attAM/attAM.html';
                    }, 2000);
                }
                    
            }
        } catch (error) {
            console.error('Erro ao obter tipo de usuário:', error);
            showToast("#errorToast");
        }
    } else {
        showToast("#errorToast");
    }
}

function showToast(id) {
    var toastElList = [].slice.call(document.querySelectorAll(id));
    var toastList = toastElList.map(function (toastEl) {
        return new bootstrap.Toast(toastEl);
    });
    toastList.forEach((toast) => toast.show());
}
