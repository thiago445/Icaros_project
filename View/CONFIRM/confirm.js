document.getElementById('sendTokenForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const email = document.getElementById('email').value;
    fetch('/api/sendToken', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({ email }),
    })
    .then(response => response.text())
    .then(data => alert(data));
});

document.getElementById('verifyTokenForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const email = document.getElementById('email').value;
    const token = document.getElementById('token').value;
    fetch('/api/verifyToken', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({ email, token }),
    })
    .then(response => response.text())
    .then(data => alert(data));
});