<header class="bg-white shadow-sm border-b">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between items-center py-4">
            <div class="flex items-center">
                <i class="fas fa-tint text-red-500 text-2xl mr-3"></i>
                <h1 class="text-2xl font-bold text-gray-900">B-Apheresis</h1>
            </div>
            <nav class="flex space-x-8">
                <a href="${pageContext.request.contextPath}/"
                   class="text-gray-500 hover:text-gray-900 px-3 py-2 text-sm font-medium">Accueil</a>
                <a href="${pageContext.request.contextPath}/donors?action=list"
                   class="text-red-600 border-b-2 border-red-600 px-3 py-2 text-sm font-medium">Donneurs</a>
                <a href="${pageContext.request.contextPath}/recipients?action=list"
                   class="text-gray-500 hover:text-gray-900 px-3 py-2 text-sm font-medium">Receveurs</a>
            </nav>
        </div>
    </div>
</header>