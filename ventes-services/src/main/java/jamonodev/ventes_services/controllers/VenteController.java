package jamonodev.ventes_services.controllers;

import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import jamonodev.produits_services.exception.EntityNotFoundException;
import jamonodev.ventes_services.dto.VenteDto;
import jamonodev.ventes_services.services.VenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/ventes")
@AllArgsConstructor
public class VenteController {
    private static final String RESULT = "data";
    private static final String MESSAGE = "message";
    private static final String STATUS = "responseCode";
    private final VenteService venteService;

    @GetMapping("/{venteID}")
    public ResponseEntity<?> getVenteById(@PathVariable("venteID") String venteID) {
        Map<String, Object> output = new HashMap<>();
        try {
            output.put(RESULT, venteService.getVenteById(UUID.fromString(venteID)));
            output.put(MESSAGE, "Vente recherchée trouvée avec succès");
            output.put(STATUS, 200);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            output.put(MESSAGE, e.getMessage());
            output.put(STATUS, 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(output);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllVentes() {
        Map<String, Object> output = new HashMap<>();
        try {
            output.put(RESULT, venteService.getAllVentes());
            output.put(MESSAGE, "Liste des ventes récupérée avec succès");
            output.put(STATUS, 200);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            output.put(MESSAGE, e.getMessage());
            output.put(STATUS, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(output);
        }
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> saveVente(@Valid @RequestBody VenteDto venteDto) {
        Map<String, Object> output = new HashMap<>();
        try {
            output.put(RESULT, venteService.saveVente(venteDto));
            output.put(MESSAGE, "Vente ajoutée avec succès");
            output.put(STATUS, 200);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            output.put(MESSAGE, e.getMessage());
            output.put(STATUS, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(output);
        }
    }

    @PutMapping("/{venteId}")
    public ResponseEntity<?> updateVente(@PathVariable("venteId") String venteId, @RequestBody VenteDto updateVenteDto) {
        Map<String, Object> output = new HashMap<>();
        try {
            output.put(MESSAGE, venteService.updateVente(venteId, updateVenteDto));
            output.put(STATUS, 200);
            return ResponseEntity.ok(output);
        } catch (EntityNotFoundException e) {
            output.put(MESSAGE, e.getMessage());
            output.put(STATUS, 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(output);
        } catch (Exception e) {
            output.put(MESSAGE, e.getMessage());
            output.put(STATUS, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(output);
        }
    }

    @DeleteMapping("/{venteID}")
    public ResponseEntity<?> deleteVente(@PathVariable("venteID") String venteId) {
        Map<String, Object> output = new HashMap<>();
        try {
            output.put(MESSAGE, venteService.deleteVente(venteId));
            output.put(STATUS, 200);
            return ResponseEntity.ok(output);
        } catch (NotFoundException e) {
            output.put(MESSAGE, e.getMessage());
            output.put(STATUS, 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(output);
        } catch (Exception e) {
            output.put(MESSAGE, e.getMessage());
            output.put(STATUS, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(output);
        }
    }
}
