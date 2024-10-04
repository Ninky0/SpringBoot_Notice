document.getElementById('deleteForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 폼 기본 제출 막기
    submitForm();
});

function submitForm() {
    const id = document.getElementById('id').value;
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

    fetch(`/articles/erase/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (!response.ok) {
                // 에러 응답 처리
                return response.json().then(data => {
                    throw new Error(data.message || "Unknown error"); // 서버에서 설정된 에러 메시지 사용
                });
            }
            return response.text(); // 성공 응답 처리
        })
        .then(data => {
            alert(data); // 성공 메시지 출력
            window.location.href = '/articles'; // 성공 시, 글 목록 페이지로 리다이렉트
        })
        .catch(error => {
            console.error('Error:', error);
            alert('요청 처리 중 오류가 발생했습니다: ' + error.message); // 에러 메시지를 표시
            window.location.href = `/articles/erase/${id}`; // 에러 발생 시, 비밀번호 다시 입력
        });
}
