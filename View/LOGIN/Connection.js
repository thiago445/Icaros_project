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
    let key= "Authorization";
    let token = response.headers.get(key);
    window.localStorage.setItem(key, token);

    if(response.ok){
        showToast("#okToast");
    }else{
        showToast("#errorToast");
    }

    window.setTimeout(function(){
        window.location = "../portMUSICO/portifolio-musico.html";
    }, 2000);

    function showToast(id){
        var toastElist = [].slice.call(document.querySelectorAll(id));
        var toastList = toastElist.map(function (toastx1){
        return new bootstrap.toaet(toastx1);
        });
        toastList.forEach((toaet)  =>toastElist.show());
    };
    

}