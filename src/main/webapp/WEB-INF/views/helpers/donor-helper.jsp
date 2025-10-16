<%@ page import="dto.DonorDTO" %>
<%!
    // Helper method to format blood type from "O_POSITIVE" to "O+"
    private String formatBloodType(String bloodType) {
        if (bloodType == null) return "N/A";

        switch(bloodType) {
            case "O_POSITIVE": return "O+";
            case "O_NEGATIVE": return "O-";
            case "A_POSITIVE": return "A+";
            case "A_NEGATIVE": return "A-";
            case "B_POSITIVE": return "B+";
            case "B_NEGATIVE": return "B-";
            case "AB_POSITIVE": return "AB+";
            case "AB_NEGATIVE": return "AB-";
            default: return bloodType;
        }
    }

    // Helper method to build donor table row
    private String buildDonorRow(DonorDTO donor) {
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

        return "<tr class='hover:bg-gray-50 transition-colors duration-150'>" +
                "<td class='px-6 py-4 whitespace-nowrap text-sm font-mono text-gray-900'>#" + donor.getId() + "</td>" +
                "<td class='px-6 py-4 whitespace-nowrap'><div class='text-sm font-medium text-gray-900 truncate max-w-[150px]' title='" + donor.getFirstName() + " " + donor.getLastName() + "'>" + donor.getFirstName() + " " + donor.getLastName() + "</div></td>" +
                "<td class='px-6 py-4 whitespace-nowrap'><div class='text-sm text-gray-900 font-mono'>" + donor.getCin() + "</div></td>" +
                "<td class='px-6 py-4 whitespace-nowrap'><span class='inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-red-100 text-red-800'>" + formatBloodType(donor.getBloodType().toString()) + "</span></td>" +
                "<td class='px-6 py-4 whitespace-nowrap'><span class='inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium " + statusColor + "'>" + statusText + "</span></td>" +
                "<td class='px-6 py-4 whitespace-nowrap text-sm text-gray-900'>" + (donor.getAge() != null ? donor.getAge() + " ans" : "N/A") + "</td>" +
                "<td class='px-6- py-4 whitespace-nowrap'>" + (donor.getEligible() != null && donor.getEligible() ? "<i class='fas fa-check-circle text-green-500 text-lg' title='Éligible'></i>" : "<i class='fas fa-times-circle text-red-500 text-lg' title='Non éligible'></i>") + "</td>" +
                "<td class='px-6 py-4 whitespace-nowrap'>" + (donor.getCanDonate() != null && donor.getCanDonate() ? "<i class='fas fa-check-circle text-green-500 text-lg' title='Peut donner'></i>" : "<i class='fas fa-times-circle text-red-500 text-lg' title='Ne peut pas donner'></i>") + "</td>" +
                "<td class='px-6 py-4 whitespace-nowrap text-sm font-medium'><div class='flex space-x-2'>" +
                "<a href='" + request.getContextPath() + "/donors?action=edit&id=" + donor.getId() + "' class='text-blue-600 hover:text-blue-900 transition-colors duration-200' title='Modifier'><i class='fas fa-edit'></i></a>" +
                "<a href='" + request.getContextPath() + "/donors?action=delete&id=" + donor.getId() + "' class='text-red-600 hover:text-red-900 transition-colors duration-200' onclick='return utils.confirmAction(\\\"Êtes-vous sûr de vouloir supprimer ce donneur ?\\\")' title='Supprimer'><i class='fas fa-trash'></i></a>" +
                "</div></td></tr>";
    }
%>