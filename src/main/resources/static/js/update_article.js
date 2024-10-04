function submitUpdateForm(event) {
    event.preventDefault();  // 폼의 기본 제출 동작을 방지

    const formData = {
        id: document.getElementById('id').value,
        title: document.getElementById('title').value,
        content: document.getElementById('content').value,
    };

    fetch('/articles/update/'+formData.id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => response.text().then(text => ({ status: response.status, body: text })))
        .then(result => {
            if (result.status === 200) {
                alert(result.body); // Success message from server
                window.location.href = '/articles'; // Redirect to the articles list
            } else {
                throw new Error(result.body); // Error message from server
            }
        })
        .catch(error => {
            console.error('Error:', error.message);
            alert('요청 처리 중 오류가 발생했습니다: ' + error.message);
        });
}
