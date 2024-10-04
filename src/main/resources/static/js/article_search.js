document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('searchButton').addEventListener('click', submitSearchForm);
});

function submitSearchForm() {
    const keyword = document.getElementById('keyword').value;
    if (keyword.trim() !== '') {
        window.location.href = `/articles/search?keyword=${encodeURIComponent(keyword)}`;
    } else {
        alert('검색어를 입력하세요.');
    }
}
