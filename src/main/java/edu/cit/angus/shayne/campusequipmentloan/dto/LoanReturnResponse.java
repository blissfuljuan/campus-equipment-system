package edu.cit.angus.shayne.campusequipmentloan.dto;

import java.time.LocalDate;

public class LoanReturnResponse {
    private Long loanId;
    private String status;
    private LocalDate returnDate;
    private double penalty;

    public Long getLoanId() { return loanId; }
    public void setLoanId(Long loanId) { this.loanId = loanId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public double getPenalty() { return penalty; }
    public void setPenalty(double penalty) { this.penalty = penalty; }
}
