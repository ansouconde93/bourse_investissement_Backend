package bourse.bourse.project.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Transactions {
    @Id
    private String id;
    private String description;
    private double unitPrice;
    private int quantity;
    private Instant date;
    private Societe societe;
}
