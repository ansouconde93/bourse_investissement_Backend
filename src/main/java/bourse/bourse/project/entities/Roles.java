package bourse.bourse.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Roles {
    @Id
    private String id;
    @Indexed(unique = true)
    private String nom;
    private boolean susppendu = false; //susppendu = false => cette autory n'est pas suppendu donc fonctionne.

    private List<Autority> autorities = new ArrayList<>();
}
