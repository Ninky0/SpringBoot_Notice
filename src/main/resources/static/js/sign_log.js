console.clear();

const loginBtn = document.getElementById('login');
const signupBtn = document.getElementById('signup');

loginBtn.addEventListener('click', (e) => {
    let parent = e.target.parentNode.parentNode;
    Array.from(e.target.parentNode.parentNode.classList).find((element) => {
        if(element !== "slide-up") {
            parent.classList.add('slide-up')
        }else{
            signupBtn.parentNode.classList.add('slide-up')
            parent.classList.remove('slide-up')
        }
    });
});

signupBtn.addEventListener('click', (e) => {
    let parent = e.target.parentNode;
    Array.from(e.target.parentNode.classList).find((element) => {
        if(element !== "slide-up") {
            parent.classList.add('slide-up')
        }else{
            loginBtn.parentNode.parentNode.classList.add('slide-up')
            parent.classList.remove('slide-up')
        }
    });
});

function submitForm() {
    const formData = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        phone: document.getElementById('phone').value,
        userid: document.getElementById('userid').value,
        password: document.getElementById('password').value
    };

    fetch('/users/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => response.text().then(text => ({ status: response.status, body: text })))
        .then(result => {
            if (result.status === 200) {
                alert(result.body); // Success message from server
                window.location.href = '/users'; // Redirect to the home page or user dashboard
            } else {
                throw new Error(result.body); // Error message from server
            }
        })
        .catch(error => {
            console.error('Error:', error.message);
            alert('요청 처리 중 오류가 발생했습니다: ' + error.message);
        });
}

function submitLoginForm() {
    const loginData = {
        userid: document.getElementById('login-userid').value,
        password: document.getElementById('login-password').value
    };
    console.log(loginData.userid, loginData.password);
    fetch('/users/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('로그인 성공!');
                window.location.href = '/articles';
            } else {
                alert('로그인 실패: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Login Error:', error);
            alert('로그인 처리 중 오류가 발생했습니다.');
        });
}