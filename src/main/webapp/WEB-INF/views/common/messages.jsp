<%
    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<% if (successMessage != null) { %>
<div class="max-w-7xl mx-auto my-4 p-4 bg-[#c1121f] text-[#fdf0d5] rounded shadow">
    <p><%= successMessage %></p>
</div>
<% } %>

<% if (errorMessage != null) { %>
<div class="max-w-7xl mx-auto my-4 p-4 bg-[#780000] text-[#fdf0d5] rounded shadow">
    <p><%= errorMessage %></p>
</div>
<% } %>