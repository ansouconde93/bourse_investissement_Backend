package bourse.bourse.project.model;

import bourse.bourse.project.entities.Societe;
import bourse.bourse.project.entities.Transactions;
import bourse.bourse.project.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Gestionneur {
    private User user;
    private List<Societe> societes;
    private List<Transactions> transactions;
}
