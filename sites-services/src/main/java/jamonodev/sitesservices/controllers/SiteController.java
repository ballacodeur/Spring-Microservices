package jamonodev.sitesservices.controllers;

import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import jamonodev.sitesservices.dto.SiteDto;
import jamonodev.sitesservices.exception.EntityNotFoundException;
import jamonodev.sitesservices.services.SitesServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/sites")
@AllArgsConstructor
public class SiteController {
    private static final String RESULT = "data";
    private static final String MESSAGE = "message";
    private static final String STATUS = "responseCode";
    private final SitesServices siteService;

    @GetMapping("/{siteID}")
    public ResponseEntity<?> getSiteById(@PathVariable("siteID") String siteID) {
        Map<String, Object> output = new HashMap<>();
        try {
            output.put(RESULT, siteService.getSiteById(UUID.fromString(siteID)));
            output.put(MESSAGE, "Site recherché trouvé avec succès");
            output.put(STATUS, 200);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            output.put(MESSAGE, e.getMessage());
            output.put(STATUS, 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(output);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSites() {
        Map<String, Object> output = new HashMap<>();
        try {
            output.put(RESULT, siteService.getAllSites());
            output.put(MESSAGE, "Liste des sites récupérée avec succès");
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
    public ResponseEntity<?> saveSite(@Valid @RequestBody SiteDto siteDto) {
        Map<String, Object> output = new HashMap<>();
        try {
            output.put(RESULT, siteService.saveSite(siteDto));
            output.put(MESSAGE, "Site ajouté avec succès");
            output.put(STATUS, 200);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            output.put(MESSAGE, e.getMessage());
            output.put(STATUS, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(output);
        }
    }

    @PutMapping("/{siteId}")
    public ResponseEntity<?> updateSite(@PathVariable("siteId") String siteId, @RequestBody SiteDto updateSiteDto) {
        Map<String, Object> output = new HashMap<>();
        try {
            output.put(MESSAGE, siteService.updateSite(siteId, updateSiteDto));
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

    @DeleteMapping("/{siteID}")
    public ResponseEntity<?> deleteSite(@PathVariable("siteID") String siteId) {
        Map<String, Object> output = new HashMap<>();
        try {
            output.put(MESSAGE, siteService.deleteSite(siteId));
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
