package campusequipmentloan.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class LoanRequestDTO {
    @NotNull
    private Long studentId;

    @NotNull
    private Long equipmentId;

    private LocalDate startDate;

    // getters and setters
}
