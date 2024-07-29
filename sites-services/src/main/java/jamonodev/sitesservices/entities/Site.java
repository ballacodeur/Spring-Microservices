package jamonodev.sitesservices.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter  @Getter
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID siteID;
    private String nom;
    private String adresse;
}
