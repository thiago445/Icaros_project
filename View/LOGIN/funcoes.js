

function entrar(){
    let email = document.querySelector('#email')
    let emailLabel = document.querySelector('#emailLabel')

    let senha = document.querySelector('#senha')
    let senhaLabel = document.querySelector('#senhaLabel')

    let msgError = document.querySelector('#msgError')
    let listaUser = []

    let userValid = {
        nome: '',
        email: '',
        senha: '',
        csenha: '',
        nasc: '',
        cel: ''
    }

    listaUser = JSON.parse(localStorage.getItem(listaUser))

    listaUser.forEach((item) => {
        if(email.value == item.email && senha.value == item.senha){
            nome: item.nome;
            email: item.email;
            senha: item.senha;
            csenha: item.csenha;
            nasc: item.nasc;
            cel: item.cel;
        }
    });
}