document.addEventListener("DOMContentLoaded", function() {
    const token = window.localStorage.getItem('Authorization'); // Obtém o token do localStorage

    if (!token) {
        console.error('Token não encontrado.'); // Verifica se o token está disponível
        return;
    }

    // Função para buscar os dados do usuário
    async function fetchUserProfile() {
        try {
            const response = await fetch('http://localhost:8080/user/musician/profileMusician', {
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
        const emailElement = document.getElementById('email');
        const imagemElement = document.getElementById('imagem');

        if (nomeElement) {
            nomeElement.innerText = userProfile.user.name;
        }
        
        if (descricaoElement && userProfile.comment !== null) {
            descricaoElement.innerText = userProfile.comment;
        }
        if (opcoesElement) {
            opcoesElement.innerText = userProfile.user.musicalGenre;
        }
        if (emailElement) {
            emailElement.innerText = userProfile.user.email;
        }
        if (imagemElement && userProfile.image) {
            const byteCharacters = atob(userProfile.image);
            const byteNumbers = new Array(byteCharacters.length);
            for (let i = 0; i < byteCharacters.length; i++) {
                byteNumbers[i] = byteCharacters.charCodeAt(i);
            }
            const byteArray = new Uint8Array(byteNumbers);
            const blob = new Blob([byteArray], {type: 'image/jpeg'}); // Substitua 'image/jpeg' pelo tipo correto se necessário
            const imageUrl = URL.createObjectURL(blob);
            imagemElement.src = imageUrl;
        }

    }
    fetchUserProfile();
});