package jamonodev.ventes_services.mapping;

import jamonodev.ventes_services.dto.VenteDto;
import jamonodev.ventes_services.entities.Vente;
import org.mapstruct.Mapper;

@Mapper
public interface VenteMapper {
    VenteDto toDto(Vente vente);
    Vente toEntity(VenteDto venteDto);
}
