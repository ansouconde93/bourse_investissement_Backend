package bourse.bourse.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Autority {
    @Id
    private String id;
    private String nom;
    private boolean susppendu = false; //susppendu = false => cette autory n'est pas suppendu donc fonctionne.
    private List<Transactions> transactions = new ArrayList<>();
}
