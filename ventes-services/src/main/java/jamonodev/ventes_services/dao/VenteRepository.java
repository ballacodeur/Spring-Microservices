package jamonodev.ventes_services.dao;


import jamonodev.ventes_services.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VenteRepository extends JpaRepository<Vente, UUID> {
}
