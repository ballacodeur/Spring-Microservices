package jamonodev.ventes_services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VenteDto {
    private UUID venteID;


    @NotNull(message = "La quantite du produit est obligatoire")
    private double quantite;

    @NotNull(message = "Le quantite seuil du produit est obligatoire")
    private double prix;

    @NotBlank(message = "Le client doit etre associe a un site")
    private String siteID;


    private Date dateVente = new Date();
}
