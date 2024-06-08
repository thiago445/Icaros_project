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
                telephone: document.getElementById('cel').value
                
            };

            let specificUserKey = '';
            
            let specificUser = {};
            const userType = user.flagUserType;

            if (userType == 2) {
                specificUser = {
                    cpf: document.getElementById('cpf').value,
                    name: document.getElementById('name').value,
                    musicalGenre: document.getElementById('generoMusical').value
                     // Assuming same name as user
                };
                specificUserKey = 'userLover';
            } else if (userType == 1) {
                specificUser = {
                    cpf: document.getElementById('cpf').value,
                    musicalGenre: document.getElementById('generoMusical').value

                };
                specificUserKey = 'userMusician';
            } else if (userType == 3) {
                specificUser = {
                    cnpj: document.getElementById('cnpj').value,
                    musicalGenre: document.getElementById('generoMusical').value
                };
                specificUserKey = 'producerUser';
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
    
                if (select.value === 'musico' || select.value === 'AM') {
                    cpfField.style.display = 'block';
                    cpfInput.required = true;
                } else if (select.value === 'produtor') {
                    cnpjField.style.display = 'block';
                    cnpjInput.required = true;
                }
            }
    
            // Call mostrarOpcao() when the select value changes
            select.addEventListener('change', mostrarOpcao);
    
            // Call mostrarOpcao() initially
            mostrarOpcao();
        });
        
            $(document).ready(function(){
            $('#cel').mask('(00) 00000-0000'); // Máscara para números de celular brasileiros
            $('#cpf').mask('000.000.000-00', {reverse: true}); // Máscara para CPF
            $('#cnpj').mask('00.000.000/0000-00', {reverse: true}); // Máscara para CNPJ
        });