// Filter table based on search input
function filterTable() {
    const input = document.getElementById('searchInput');
    const filter = input.value.toLowerCase();
    const rows = document.querySelectorAll('#donorTableBody tr');
    let visibleCount = 0;

    rows.forEach(row => {
        const text = row.textContent.toLowerCase();
        if (text.includes(filter)) {
            row.style.display = '';
            visibleCount++;
        } else {
            row.style.display = 'none';
        }
    });

    document.getElementById('resultCount').textContent = visibleCount + ' donneur(s)';
}

// Show/hide blood type filter
function applyFilter() {
    const filterSelect = document.getElementById('filterSelect');
    const bloodTypeFilter = document.getElementById('bloodTypeFilter');
    const value = filterSelect.value;

    bloodTypeFilter.classList.add('hidden');

    if (value === 'bloodType') {
        bloodTypeFilter.classList.remove('hidden');
    } else if (value === 'available') {
        window.location.href = 'donors?action=filter&filterType=available';
    } else if (value === 'eligible') {
        window.location.href = 'donors?action=filter&filterType=eligible';
    } else if (value === 'all') {
        window.location.href = 'donors?action=list';
    }
}

// Filter by blood type
function filterByBloodType() {
    const bloodType = document.getElementById('bloodTypeSelect').value;
    window.location.href = 'donors?action=filter&filterType=bloodType&bloodType=' + bloodType;
}

// Initialize event listeners
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    const filterSelect = document.getElementById('filterSelect');
    const bloodTypeSelect = document.getElementById('bloodTypeSelect');

    if (searchInput) searchInput.addEventListener('input', filterTable);
    if (filterSelect) filterSelect.addEventListener('change', applyFilter);
    if (bloodTypeSelect) bloodTypeSelect.addEventListener('change', filterByBloodType);
});