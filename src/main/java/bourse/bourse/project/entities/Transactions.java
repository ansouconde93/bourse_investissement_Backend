package bourse.bourse.project.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    @DBRef
    private Societe societe;
}
