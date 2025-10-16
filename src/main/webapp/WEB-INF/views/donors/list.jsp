<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.DonorDTO" %>
<%@ page import="java.util.List" %>
<%
    // Set page attributes
    request.setAttribute("pageTitle", "Liste des Donneurs");
    request.setAttribute("pageSubtitle", "Gestion complète des donneurs de sang");

    // Action button
    String actionButton = "<a href=\"" + request.getContextPath() + "/donors?action=create\" " +
            "class=\"bg-red-600 hover:bg-red-700 text-white px-6 py-3 rounded-lg font-semibold transition-colors duration-200 flex items-center\">" +
            "<i class=\"fas fa-plus mr-2\"></i>Nouveau Donneur</a>";
    request.setAttribute("actionButton", actionButton);

    // Get donors list
    List<DonorDTO> donors = (List<DonorDTO>) request.getAttribute("donors");
    int donorCount = donors != null ? donors.size() : 0;
    request.setAttribute("donorCount", donorCount + "");

    // Build content
    StringBuilder content = new StringBuilder();
    content.append("<jsp:include page='../components/filters.jsp'/>");

    content.append("<div class=\"bg-white rounded-lg shadow-sm border overflow-hidden\">");
    content.append("<div class=\"overflow-x-auto\">");
    content.append("<table class=\"min-w-full divide-y divide-gray-200\">");
    content.append("<thead class=\"bg-gray-50\">");
    content.append("<tr>");
    content.append("<th class=\"px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider\">ID</th>");
    content.append("<th class=\"px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider\">Nom Complet</th>");
    content.append("<th class=\"px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider\">CIN</th>");
    content.append("<th class=\"px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider\">Groupe Sanguin</th>");
    content.append("<th class=\"px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider\">Statut</th>");
    content.append("<th class=\"px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider\">Âge</th>");
    content.append("<th class=\"px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider\">Éligible</th>");
    content.append("<th class=\"px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider\">Actions</th>");
    content.append("</tr>");
    content.append("</thead>");
    content.append("<tbody class=\"bg-white divide-y divide-gray-200\" id=\"donorTableBody\">");

    if (donors != null && !donors.isEmpty()) {
        for (DonorDTO donor : donors) {
            // Format blood type
            String bloodTypeFormatted = "N/A";
            if (donor.getBloodType() != null) {
                String bloodType = donor.getBloodType().toString();
                switch(bloodType) {
                    case "O_POSITIVE": bloodTypeFormatted = "O+"; break;
                    case "O_NEGATIVE": bloodTypeFormatted = "O-"; break;
                    case "A_POSITIVE": bloodTypeFormatted = "A+"; break;
                    case "A_NEGATIVE": bloodTypeFormatted = "A-"; break;
                    case "B_POSITIVE": bloodTypeFormatted = "B+"; break;
                    case "B_NEGATIVE": bloodTypeFormatted = "B-"; break;
                    case "AB_POSITIVE": bloodTypeFormatted = "AB+"; break;
                    case "AB_NEGATIVE": bloodTypeFormatted = "AB-"; break;
                    default: bloodTypeFormatted = bloodType;
                }
            }

            // Status styling
            String statusColor = "bg-gray-100 text-gray-800";
            String statusText = "INCONNU";
            if (donor.getStatus() != null) {
                switch(donor.getStatus().toString()) {
                    case "AVAILABLE":
                        statusColor = "bg-green-100 text-green-800";
                        statusText = "DISPONIBLE";
                        break;
                    case "UNAVAILABLE":
                        statusColor = "bg-yellow-100 text-yellow-800";
                        statusText = "INDISPONIBLE";
                        break;
                    case "INELIGIBLE":
                        statusColor = "bg-red-100 text-red-800";
                        statusText = "NON ÉLIGIBLE";
                        break;
                }
            }

            content.append("<tr class=\"hover:bg-gray-50\">");
            content.append("<td class=\"px-6 py-4 whitespace-nowrap text-sm font-mono text-gray-900\">#").append(donor.getId()).append("</td>");
            content.append("<td class=\"px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900\">").append(donor.getFirstName()).append(" ").append(donor.getLastName()).append("</td>");
            content.append("<td class=\"px-6 py-4 whitespace-nowrap text-sm text-gray-900 font-mono\">").append(donor.getCin()).append("</td>");
            content.append("<td class=\"px-6 py-4 whitespace-nowrap\"><span class=\"inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-red-100 text-red-800\">").append(bloodTypeFormatted).append("</span></td>");
            content.append("<td class=\"px-6 py-4 whitespace-nowrap\"><span class=\"inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium ").append(statusColor).append("\">").append(statusText).append("</span></td>");
            content.append("<td class=\"px-6 py-4 whitespace-nowrap text-sm text-gray-900\">").append(donor.getAge() != null ? donor.getAge() + " ans" : "N/A").append("</td>");
            content.append("<td class=\"px-6 py-4 whitespace-nowrap\">").append(donor.getEligible() != null && donor.getEligible() ? "<i class='fas fa-check-circle text-green-500 text-lg'></i>" : "<i class='fas fa-times-circle text-red-500 text-lg'></i>").append("</td>");
            content.append("<td class=\"px-6 py-4 whitespace-nowrap text-sm font-medium\">");
            content.append("<div class=\"flex space-x-2\">");
            content.append("<a href=\"").append(request.getContextPath()).append("/donors?action=edit&id=").append(donor.getId()).append("\" class=\"text-blue-600 hover:text-blue-900\"><i class=\"fas fa-edit\"></i></a>");
            content.append("<a href=\"").append(request.getContextPath()).append("/donors?action=delete&id=").append(donor.getId()).append("\" class=\"text-red-600 hover:text-red-900\" onclick=\"return confirm('Êtes-vous sûr?')\"><i class=\"fas fa-trash\"></i></a>");
            content.append("</div>");
            content.append("</td>");
            content.append("</tr>");
        }
    } else {
        content.append("<tr><td colspan=\"8\" class=\"px-6 py-12 text-center text-gray-500\">Aucun donneur trouvé</td></tr>");
    }

    content.append("</tbody>");
    content.append("</table>");
    content.append("</div>");
    content.append("</div>");

    request.setAttribute("content", content.toString());
%>

<jsp:include page="../layouts/main.jsp"/>