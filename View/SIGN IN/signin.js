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
                flagTipoUsuario: document.getElementById('flagTipoUsuario').value
            };

            let specificUserKey = '';
            
            let specificUser = {};
            const userType = user.flagTipoUsuario;

            if (userType === 'AM') {
                specificUser = {
                    cpf: document.getElementById('cpf').value,
                    name: document.getElementById('name').value
                     // Assuming same name as user
                };
                specificUserKey = 'userLover';
            } else if (userType === 'musico') {
                specificUser = {
                    cpf: document.getElementById('cpf').value
                };
            } else if (userType === 'produtor') {
                specificUser = {
                    cnpj: document.getElementById('cnpj').value
                };
            }

            const payload = {
                user: user
            };

            payload[specificUserKey] = specificUser;

            console.log('Payload being sent:', payload);

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
        });
    } else {
        console.error('Form with ID "CadastroForm" not found.');
    }
});