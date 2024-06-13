document.addEventListener("DOMContentLoaded", function() {
    const token = window.localStorage.getItem('Authorization'); // Obtém o token do localStorage

    if (!token) {
        console.error('Token não encontrado.'); // Verifica se o token está disponível
        return;
    }

    // Função para buscar os dados do usuário
    async function fetchUserProfile() {
        try {
            const response = await fetch('http://localhost:8080/user/lover/profileLover', {
                method: 'GET',
                headers: {
                    'Authorization': token,
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error('Erro ao buscar o perfil do usuário');
            }

            const userProfile = await response.json();
            displayUserProfile(userProfile);
        } catch (error) {
            console.error('Erro ao buscar o perfil do usuário:', error);
        }
    }

    // Função para exibir os dados do usuário na tela
    function displayUserProfile(userProfile) {
        const nomeElement = document.getElementById('nome');
        const descricaoElement = document.getElementById('descricao');
        const opcoesElement = document.getElementById('opcoes');

        if (nomeElement) {
            nomeElement.value = userProfile.user.name;
        }

        if (descricaoElement && userProfile.comment !== null) {
            descricaoElement.value = userProfile.comment;
        }

        if (opcoesElement) {
            opcoesElement.value = userProfile.user.musicalGenre;
        }
    }

    // Adiciona o evento de submissão para enviar os dados atualizados do usuário e a imagem
    const perfilForm = document.getElementById('perfilForm');
    
    if (perfilForm) {
        perfilForm.addEventListener('submit', function(event) {
            event.preventDefault(); // Impede o envio do formulário

            // Obtém os dados do formulário em JSON
            const comment = document.getElementById('descricao').value;
            const name = document.getElementById('nome').value;
            const musicalGenre = document.getElementById('opcoes').value;

            const userData = JSON.stringify({
                userLover: {
                    comment: comment
                },
                user: {
                    name: name,
                    musicalGenre: musicalGenre
                }
            });

            // Configura o cabeçalho da requisição para os dados do perfil
            const headers = new Headers();
            headers.append('Authorization', token);
            headers.append('Content-Type', 'application/json');

            // Envia a requisição PUT para atualizar os dados do perfil
            fetch('http://localhost:8080/user/lover', {
                method: 'PUT',
                headers: headers,
                body: userData
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao atualizar perfil');
                }
                return response.text(); // Use response.text() instead of response.json() to avoid parsing issues
            })
            .then(data => {
                console.log('Perfil atualizado com sucesso!', data);
                alert('Perfil atualizado com sucesso!');

                // Após atualizar o perfil, envia a imagem
                
                const imageInput = document.getElementById('imagem');
               //lembrar que tem que fazer oenvio mesmo sem foto
                    
                    const file = imageInput.files[0]; // Obtém o arquivo de imagem selecionado

                    const formData = new FormData();
                    formData.append('image', file); // Adiciona o arquivo de imagem ao FormData

                    // Configura o cabeçalho da requisição para enviar a imagem
                    const imageHeaders = new Headers();
                    imageHeaders.append('Authorization', token);

                    // Envia a requisição POST para enviar a imagem
                    fetch('http://localhost:8080/user/lover/upload', {
                        method: 'POST',
                        headers: imageHeaders,
                        body: formData
                    })
                    .then(imageResponse => {
                        if (!imageResponse.ok) {
                            throw new Error('Erro ao enviar imagem');
                        }
                        return imageResponse.text();
                    })
                    .then(imageData => {
                        console.log('Imagem enviada com sucesso!', imageData);
                        alert('Imagem enviada com sucesso!');

                        // Redireciona para outra página após o envio bem-sucedido
                        window.location.href = '../portAM/portifolio-AM.html'; // Substitua pela URL desejada
                    })
                    .catch(imageError => console.error('Erro ao enviar imagem:', imageError));
                
            })
            .catch(error => console.error('Erro ao atualizar perfil:', error));
        });
    }

    // Chama a função para buscar e exibir os dados do usuário
    fetchUserProfile();
});
