package jamonodev.sitesservices.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clients-services" , url = "http://localhost:2020/api/clients")
public interface IClient {
    @GetMapping("/site/{siteID}/clients")
    ResponseEntity<?> getAllClientsBySite(@PathVariable String siteID);
}
