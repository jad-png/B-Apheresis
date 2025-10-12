package dto;

import

public class DonationDTO {
    private Long id;
    private Long donorId;
    private Long recipientId;

    public DonationDTO() {}

    public Long getId() { return id;}
    public void setId(Long id) {  this.id = id; }

    public Long getDonorId() { return donorId; }
    public void setDonorId(Long donorId) { this.donorId = donorId; }

    public Long getRecipientId() { return recipientId; }
    public Long setRecipientId(Long recipientId) { this.recipientId = recipientId; return this.id; }
}
