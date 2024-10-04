document.getElementById('searchButton').addEventListener('click', function() {
    var searchValue = document.getElementById('searchInput').value.toLowerCase();
    var tableRows = document.querySelectorAll('table tbody tr');

    tableRows.forEach(function(row) {
        var titleText = row.cells[3].textContent.toLowerCase(); // 글 제목
        var authorText = row.cells[2].textContent.toLowerCase(); // 글쓴이
        if (titleText.includes(searchValue) || authorText.includes(searchValue)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
});
