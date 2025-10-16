<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= request.getAttribute("pageTitle") != null ? request.getAttribute("pageTitle") : "B-Apheresis" %></title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <% if (request.getAttribute("additionalStyles") != null) { %>
    <style><%= request.getAttribute("additionalStyles") %></style>
    <% } %>
</head>
<body class="bg-gray-50">
<jsp:include page="../common/header.jsp"/>

<main class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
    <jsp:include page="../common/page-header.jsp"/>

    <% if (request.getAttribute("successMessage") != null) { %>
    <jsp:include page="../common/alert-success.jsp"/>
    <% } %>

    <% if (request.getAttribute("errorMessage") != null) { %>
    <jsp:include page="../common/alert-error.jsp"/>
    <% } %>

    <!-- Main content will be inserted here -->
    <%= request.getAttribute("content") != null ? request.getAttribute("content") : "" %>
</main>

<jsp:include page="../common/footer.jsp"/>

<!-- Scripts -->
<script src="${pageContext.request.contextPath}/resources/js/utils.js"></script>
<% if (request.getAttribute("pageScript") != null) { %>
<script><%= request.getAttribute("pageScript") %></script>
<% } %>
<script src="${pageContext.request.contextPath}/resources/js/donor-create.js"></script>
</body>
</html>