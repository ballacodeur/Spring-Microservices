package jamonodev.ventes_services.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter  @Getter
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID venteID;
    private double quantite;
    private double prix;
    private Date dateVente;
    @Column(nullable = false)
    private String siteID;
}
