package bourse.bourse.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String specialite;
    @Indexed(unique = true)
    private String matricule;
    private String adresse;
    @Indexed(unique = true)
    private long telephone;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String password;
    private boolean susppendu = false; //susppendu = false => cette autory n'est pas suppendu donc fonctionne.
    private List<Roles> roles = new ArrayList<>();
}
