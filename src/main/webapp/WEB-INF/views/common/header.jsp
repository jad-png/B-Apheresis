<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!— Tailwind CSS CDN and meta —>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@3.3.2/dist/tailwind.min.css" rel="stylesheet">
</head>

<nav class="bg-[#c1121f] text-[#fdf0d5] shadow-md">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between h-16 items-center">

            <div class="flex-shrink-0 font-bold text-xl tracking-wide">
                <a href="${pageContext.request.contextPath}/" class="hover:text-[#dad7cd]">B-Apheresis</a>
            </div>

            <div class="hidden md:flex space-x-8">
                <a href="${pageContext.request.contextPath}/donors/list" class="hover:text-[#dad7cd] px-3 py-2 rounded-md font-medium">Donors</a>
                <a href="${pageContext.request.contextPath}/recipients/list" class="hover:text-[#dad7cd] px-3 py-2 rounded-md font-medium">Recipients</a>
                <a href="${pageContext.request.contextPath}/donations/list" class="hover:text-[#dad7cd] px-3 py-2 rounded-md font-medium">Donations</a>
            </div>

            <!-- Mobile menu button -->
            <div class="md:hidden">
                <button id="mobile-menu-btn" aria-label="Toggle menu" class="focus:outline-none focus:ring-2 focus:ring-inset focus:ring-[#fdf0d5]">
                    <svg class="h-6 w-6 text-[#fdf0d5]" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 8h16M4 16h16" />
                    </svg>
                </button>
            </div>

        </div>
    </div>

    <!-- Mobile menu -->
    <div id="mobile-menu" class="hidden md:hidden bg-[#780000]">
        <a href="${pageContext.request.contextPath}/donors/list" class="block px-4 py-2 text-[#fdf0d5] hover:bg-[#c1121f]">Donors</a>
        <a href="${pageContext.request.contextPath}/recipients/list" class="block px-4 py-2 text-[#fdf0d5] hover:bg-[#c1121f]">Recipients</a>
        <a href="${pageContext.request.contextPath}/donations/list" class="block px-4 py-2 text-[#fdf0d5] hover:bg-[#c1121f]">Donations</a>
    </div>

    <script>
        const btn = document.getElementById('mobile-menu-btn');
        const menu = document.getElementById('mobile-menu');

        btn.addEventListener('click', () => {
            menu.classList.toggle('hidden');
        });
    </script>
</nav>
