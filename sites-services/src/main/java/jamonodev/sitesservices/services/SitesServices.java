package jamonodev.sitesservices.services;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.InternalServerErrorException;
import jamonodev.sitesservices.dao.SiteRepository;
import jamonodev.sitesservices.dto.SiteDto;
import jamonodev.sitesservices.exception.EntityNotFoundException;
import jamonodev.sitesservices.mapping.SiteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class SitesServices {
    private final SiteRepository siteRepository;
    private final SiteMapper siteMapper;

    public SitesServices(SiteRepository siteRepository, SiteMapper siteMapper) {
        this.siteRepository = siteRepository;
        this.siteMapper = siteMapper;
    }

    public List<SiteDto> getAllSites() {
        try {
            return siteRepository.findAll().stream()
                    .map(siteMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Une erreur est survenue lors de la récupération des sites", e);
            throw new InternalServerErrorException("Une erreur est survenue lors de la récupération des sites");
        }
    }

    public SiteDto getSiteById(UUID siteID) {
        try {
            return siteMapper.toDto(siteRepository.findById(siteID)
                    .orElseThrow(() -> new EntityNotFoundException("Le site est introuvable")));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Site Introuvable");
        } catch (Exception e) {
            log.error("Une erreur est survenue lors de la récupération du site avec l'ID {}", siteID, e);
            throw new InternalServerErrorException("Une erreur est survenue lors de la récupération du site");
        }
    }

    public SiteDto saveSite(SiteDto siteDto) {
        try {
            return siteMapper.toDto(siteRepository.save(siteMapper.toEntity(siteDto)));
        } catch (Exception e) {
            log.error("Une erreur est survenue lors de la sauvegarde du site", e);
            throw new InternalServerErrorException("Une erreur est survenue lors de la sauvegarde du site");
        }
    }

    public String updateSite(String siteID, SiteDto updateSite) {
        try {
            SiteDto existingSite = getSiteById(UUID.fromString(siteID));
            updateSite.setSiteID(existingSite.getSiteID());
            siteRepository.save(siteMapper.toEntity(updateSite));
            return "Modifications effectuées avec succès";
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Une erreur est survenue lors de la mise à jour du site avec l'ID {}", siteID, e);
            throw new InternalServerErrorException("Une erreur est survenue lors de la mise à jour du site");
        }
    }

    public String deleteSite(String siteId) {
        try {
            SiteDto existingSite = getSiteById(UUID.fromString(siteId));
            siteRepository.delete(siteMapper.toEntity(existingSite));
            return "Le site a été bien supprimé";
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Une erreur est survenue lors de la suppression du site avec l'ID {}", siteId, e);
            throw new InternalServerErrorException("Une erreur est survenue lors de la suppression du site");
        }
    }
}
