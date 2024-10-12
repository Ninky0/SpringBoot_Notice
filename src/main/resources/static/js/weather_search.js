let regions = [];

// 페이지 로드 시 CSV 데이터를 서버에서 받아옴
window.onload = function () {
    fetch('/weather/regions')
        .then(response => response.json())
        .then(data => {
            regions = data;
            populateRegions();
        })
        .catch(error => console.error('Error fetching regions:', error));
};

// 대범위 지역을 <select> 태그에 추가하고 기본값 설정
function populateRegions() {
    const regionSelect = document.getElementById('region');
    const uniqueRegions = [...new Set(regions.map(item => item.regionParent))]; // 대범위 지역 중복 제거

    uniqueRegions.forEach(region => {
        const option = document.createElement('option');
        option.value = region;
        option.textContent = region;

        // "서울특별시"를 기본값으로 설정
        if (region === "서울특별시") {
            option.selected = true;
        }
        regionSelect.appendChild(option);
    });

    // "서울특별시" 선택 시 소범위 지역도 기본값으로 설정
    populateSubRegions();
}

// 대범위 지역 선택 시 소범위 지역을 <select> 태그에 추가하고 기본값 설정
function populateSubRegions() {
    const selectedRegion = document.getElementById('region').value;
    const region2Select = document.getElementById('region2');
    region2Select.innerHTML = ''; // 기존 옵션 제거

    const subRegions = regions.filter(item => item.regionParent === selectedRegion);
    subRegions.forEach(region => {
        const option = document.createElement('option');
        option.value = region.regionChild;
        option.textContent = region.regionChild;

        // "종로구"를 기본값으로 설정
        if (region.regionChild === "종로구") {
            option.selected = true;
        }
        region2Select.appendChild(option);
    });
}

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('searchButton').addEventListener('click', submitSearchForm);
});

// 날씨 API 요청
function submitSearchForm() {
    const selectedRegion = document.getElementById('region').value;
    const selectedSubRegion = document.getElementById('region2').value;

    const selectedRegionData = regions.find(item => item.regionParent === selectedRegion && item.regionChild === selectedSubRegion);

    if (selectedRegionData) {
        const nx = selectedRegionData.nx;
        const ny = selectedRegionData.ny;

        alert(nx);
        alert(ny);
        console.log(nx, ny);

        // 날씨 API 요청
        fetch(`/weather/search?nx=${nx}&ny=${ny}`)
            .then(response => response.json())
            .then(data => {
                alert('잠깐')
                document.getElementById('weather-text').textContent = `Weather: ${JSON.stringify(data)}`;
            })
            .catch(error => console.error('Error fetching weather:', error));
    }
}
