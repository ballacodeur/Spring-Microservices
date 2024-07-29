package jamonodev.ventes_services.services;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.InternalServerErrorException;

import jamonodev.produits_services.exception.EntityNotFoundException;
import jamonodev.ventes_services.dao.VenteRepository;
import jamonodev.ventes_services.dto.VenteDto;
import jamonodev.ventes_services.mapping.VenteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class VenteService {
    private final VenteRepository venteRepository;
    private final VenteMapper venteMapper;

    public VenteService(VenteRepository venteRepository, VenteMapper venteMapper) {
        this.venteRepository = venteRepository;
        this.venteMapper = venteMapper;
    }

    public List<VenteDto> getAllVentes() {
        try {
            return venteRepository.findAll().stream()
                    .map(venteMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Une erreur est survenue lors de la récupération des ventes", e);
            throw new InternalServerErrorException("Une erreur est survenue lors de la récupération des ventes");
        }
    }

    public VenteDto getVenteById(UUID venteID) {
        try {
            return venteMapper.toDto(venteRepository.findById(venteID)
                    .orElseThrow(() -> new EntityNotFoundException("La vente est introuvable")));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Vente Introuvable");
        } catch (Exception e) {
            log.error("Une erreur est survenue lors de la récupération de la vente avec l'ID {}", venteID, e);
            throw new InternalServerErrorException("Une erreur est survenue lors de la récupération de la vente");
        }
    }

    public VenteDto saveVente(VenteDto venteDto) {
        try {
            return venteMapper.toDto(venteRepository.save(venteMapper.toEntity(venteDto)));
        } catch (Exception e) {
            log.error("Une erreur est survenue lors de la sauvegarde de la vente", e);
            throw new InternalServerErrorException("Une erreur est survenue lors de la sauvegarde de la vente");
        }
    }

    public String updateVente(String venteID, VenteDto updateVente) {
        try {
            VenteDto existingVente = getVenteById(UUID.fromString(venteID));
            updateVente.setVenteID(existingVente.getVenteID());
            venteRepository.save(venteMapper.toEntity(updateVente));
            return "Modifications effectuées avec succès";
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Une erreur est survenue lors de la mise à jour de la vente avec l'ID {}", venteID, e);
            throw new InternalServerErrorException("Une erreur est survenue lors de la mise à jour de la vente");
        }
    }

    public String deleteVente(String venteId) {
        try {
            VenteDto existingVente = getVenteById(UUID.fromString(venteId));
            venteRepository.delete(venteMapper.toEntity(existingVente));
            return "La vente a été bien supprimée";
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Une erreur est survenue lors de la suppression de la vente avec l'ID {}", venteId, e);
            throw new InternalServerErrorException("Une erreur est survenue lors de la suppression de la vente");
        }
    }
}
