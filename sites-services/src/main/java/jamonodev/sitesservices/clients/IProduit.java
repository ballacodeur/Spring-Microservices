package jamonodev.sitesservices.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produits-services" , url = "http://localhost:2020/api/produits")
public interface IProduit {
    @GetMapping("/site/{siteID}/produits")
    ResponseEntity<?> getAllProduitsBySites(@PathVariable String siteID);
}
