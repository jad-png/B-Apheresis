<div class="mb-8">
    <div class="flex justify-between items-center">
        <div>
            <h2 class="text-3xl font-bold text-gray-900">
                <%= request.getAttribute("pageTitle") != null ? request.getAttribute("pageTitle") : "Page Title" %>
            </h2>
            <p class="mt-2 text-sm text-gray-600">
                <%= request.getAttribute("pageSubtitle") != null ? request.getAttribute("pageSubtitle") : "Page subtitle" %>
            </p>
        </div>
        <% if (request.getAttribute("actionButton") != null) { %>
        <%= request.getAttribute("actionButton") %>
        <% } %>
    </div>
</div>