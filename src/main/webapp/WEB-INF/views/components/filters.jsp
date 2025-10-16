<div class="bg-white rounded-lg shadow-sm border p-6 mb-6">
    <div class="flex flex-wrap gap-4 items-center">
        <!-- Filter Dropdown -->
        <div class="relative">
            <select id="filterSelect"
                    class="appearance-none bg-white border border-gray-300 rounded-lg px-4 py-2 pr-8 focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-red-500 text-sm">
                <option value="all">Tous les donneurs</option>
                <option value="available">Donneurs disponibles</option>
                <option value="eligible">Donneurs éligibles</option>
                <option value="bloodType">Par groupe sanguin</option>
            </select>
            <i class="fas fa-chevron-down absolute right-3 top-3 text-gray-400 pointer-events-none"></i>
        </div>

        <!-- Blood Type Filter -->
        <div id="bloodTypeFilter" class="hidden">
            <select id="bloodTypeSelect"
                    class="bg-white border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-red-500 text-sm">
                <option value="O_POSITIVE">O+</option>
                <option value="O_NEGATIVE">O-</option>
                <option value="A_POSITIVE">A+</option>
                <option value="A_NEGATIVE">A-</option>
                <option value="B_POSITIVE">B+</option>
                <option value="B_NEGATIVE">B-</option>
                <option value="AB_POSITIVE">AB+</option>
                <option value="AB_NEGATIVE">AB-</option>
            </select>
        </div>

        <!-- Search -->
        <div class="flex-1 min-w-[300px]">
            <div class="relative">
                <input type="text" id="searchInput" placeholder="Rechercher par nom ou CIN..."
                       class="w-full bg-white border border-gray-300 rounded-lg px-4 py-2 pl-10 focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-red-500 text-sm">
                <i class="fas fa-search absolute left-3 top-3 text-gray-400"></i>
            </div>
        </div>

        <!-- Results Count -->
        <div class="text-sm text-gray-600">
            <span id="resultCount">
                <%= request.getAttribute("donorCount") != null ? request.getAttribute("donorCount") : "0" %> donneur(s)
            </span>
        </div>
    </div>
</div>