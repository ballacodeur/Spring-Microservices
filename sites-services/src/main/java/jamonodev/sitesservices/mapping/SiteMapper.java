package jamonodev.sitesservices.mapping;
import jamonodev.sitesservices.dto.SiteDto;
import jamonodev.sitesservices.entities.Site;
import org.mapstruct.Mapper;

@Mapper
public interface SiteMapper {
    SiteDto toDto(Site site);
    Site toEntity(SiteDto siteDto);
}