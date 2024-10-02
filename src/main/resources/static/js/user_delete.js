function submitForm() {
    const userid = document.getElementById('userid').value;
    const password = document.getElementById('password').value;

    if (!password) {
        alert('비밀번호를 입력하세요.');
        return;
    }

    const formData = {
        userid: userid,
        password: password
    };

    fetch(`/users/quit`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (!response.ok) {
                // 서버에서 에러를 반환한 경우 에러 메시지를 처리
                return response.text().then(text => { throw new Error(text); });
            }
            // 성공적으로 처리된 경우 리다이렉트
            return response.text();
        })
        .then(data => {
            alert(data);  // 성공 메시지 출력
            window.location.href = '/users';
        })
        .catch(error => {
            console.error('Error:', error.message);
            alert(error.message);  // 서버에서 받은 에러 메시지를 경고창에 출력
            window.location.href = '/users/quit';
        });
}