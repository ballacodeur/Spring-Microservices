package jamonodev.sitesservices.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProduitResponse {
    private UUID produitID;
    private String nom;
    private String description;
    private double quantite;
    private double seuil;
    private String siteID;
}
