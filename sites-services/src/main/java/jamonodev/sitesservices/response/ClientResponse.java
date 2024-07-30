package jamonodev.sitesservices.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientResponse {
    private UUID clientID;
    private String nom;
    private String adresse;
    private String siteID;
}
