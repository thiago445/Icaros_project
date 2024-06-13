document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('CadastroForm');
    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const user = {
                name: document.getElementById('name').value,
                email: document.getElementById('email').value,
                password: document.getElementById('password').value,
                birthDate: document.getElementById('birthDate').value,
                gender: document.getElementById('gender').value,
                flagUserType: parseInt(document.getElementById('flagUserType').value, 10),
                telephone: document.getElementById('cel').value,
                musicalGenre: document.getElementById('generoMusical').value
            };

            let specificUserKey = '';
            let specificUser = {};
            const userType = user.flagUserType;

            if (userType == 2) {
                specificUser = {
                    cpf: document.getElementById('cpf').value,
                };
                specificUserKey = 'userLover';
                enviarDados(user, specificUserKey, specificUser);
            } else if (userType == 1) {
                specificUser = {
                    cpf: document.getElementById('cpf').value,
                };
                specificUserKey = 'userMusician';
                enviarDados(user, specificUserKey, specificUser);
            } else if (userType == 3) {
                const cnpj = document.getElementById('cnpj').value;
                console.log('CNPJ:', cnpj); // Log para depuração

                obterNomeEmpresa(cnpj).then(fantasyName => {
                    console.log('Nome da Empresa:', fantasyName); // Log para depuração
                    specificUser = {
                        cnpj: cnpj,
                        fantasyName: fantasyName,
                    };
                    specificUserKey = 'producerUser';
                    enviarDados(user, specificUserKey, specificUser);
                }).catch(error => {
                    console.error('Erro ao obter nome da empresa:', error);
                    alert('Erro ao obter nome da empresa. Tente novamente.');
                });
            }
                
         
            
        });
    } else {
        console.error('Form with ID "CadastroForm" not found.');
    }
});

// Função para realizar a requisição à API da BrasilAPI
async function obterNomeEmpresa(cnpj) {
    const url = `https://brasilapi.com.br/api/cnpj/v1/${cnpj}`;

    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('Erro na requisição');
        }
        const data = await response.json();

        if (data.error) {
            throw new Error(data.message);
        }

        return data.razao_social; // Retorna o nome da empresa
    } catch (error) {
        console.error("Erro ao obter nome da empresa:", error);
        return null;
    }
}

// Função para enviar dados ao backend
function enviarDados(user, specificUserKey, specificUser) {
    const payload = { user: user };
    payload[specificUserKey] = specificUser;

    console.log('Payload being sent:', payload); // Log para depuração

    fetch('http://localhost:8080/user/tipe', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        console.log('Success:', data);
        window.location.href = '../LOGIN/login.html';
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

// Função para mostrar opções de CPF/CNPJ
document.addEventListener('DOMContentLoaded', function() {
    const select = document.getElementById('flagTipoUsuario');
    const cpfField = document.getElementById('cpfField');
    const cnpjField = document.getElementById('cnpjField');
    const cpfInput = document.getElementById('cpf');
    const cnpjInput = document.getElementById('cnpj');

    function mostrarOpcao() {
        cpfField.style.display = 'none';
        cnpjField.style.display = 'none';
        cpfInput.required = false;
        cnpjInput.required = false;

        if (select.value == 1 || select.value == 2) {
            cpfField.style.display = 'block';
            cpfInput.required = true;
        } else if (select.value == 3) {
            cnpjField.style.display = 'block';
            cnpjInput.required = true;
        }
    }

    select.addEventListener('change', mostrarOpcao);
    mostrarOpcao();
});

$(document).ready(function() {
    $('#cel').mask('(00) 00000-0000'); // Máscara para números de celular brasileiros
    $('#cpf').mask('000.000.000-00', {reverse: true}); // Máscara para CPF
    $('#cnpj').mask('00.000.000/0000-00', {reverse: true}); // Máscara para CNPJ
});
