function submitForm() {
    const id = document.getElementById('id').value;
    const userid = document.getElementById('userid').value;
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;

    const formData = {
        userid: userid,
        name: name,
        email: email
    };

    fetch(`/users`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                return response.text().then(text => {
                    console.error('Error:', text);
                    alert('요청 처리 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.');
                });
            }
        })
        .then(data => {
            alert(data);
            // 서버로부터 성공 응답을 받으면 사용자를 리다이렉트
            window.location.href = '/users';
        })
        .catch(error => {
            console.error('Error:', error);
            alert('요청 처리 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.');
        });
}