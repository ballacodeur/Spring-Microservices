package jamonodev.sitesservices.services;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jamonodev.sitesservices.clients.IClient;
import jamonodev.sitesservices.clients.IProduit;
import jamonodev.sitesservices.response.ClientResponse;
import jamonodev.sitesservices.response.ProduitResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Slf4j
public class ClientApiService {
    private ObjectMapper objectMapper = new ObjectMapper();
    private IClient iClient;

    private IProduit iProduit;

    public ClientApiService(IClient iClient, IProduit iProduit) {
        this.iClient = iClient;
        this.iProduit = iProduit;
    }

    public List<ClientResponse> getAllClientsBySite(String siteID) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ResponseEntity<?> clientResponse = iClient.getAllClientsBySite(siteID);
        LinkedHashMap<?, ?> responseBody = (LinkedHashMap<?, ?>) clientResponse.getBody();

        JsonNode responseJson = objectMapper.valueToTree(responseBody);
        JsonNode dataNode = responseJson.get("data");


        if (dataNode.isArray()) {
            return objectMapper.readValue(dataNode.traverse(), objectMapper.getTypeFactory().constructCollectionType(List.class, ClientResponse.class));
        } else {
            return Collections.singletonList(objectMapper.treeToValue(dataNode, ClientResponse.class));
        }
    }


    public List<ProduitResponse> getAllProduitsBySite(String siteID) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ResponseEntity<?> produitResponse = iProduit.getAllProduitsBySites(siteID);
        LinkedHashMap<?, ?> responseBody = (LinkedHashMap<?, ?>) produitResponse.getBody();

        JsonNode responseJson = objectMapper.valueToTree(responseBody);
        JsonNode dataNode = responseJson.get("data");


        if (dataNode.isArray()) {
            return objectMapper.readValue(dataNode.traverse(), objectMapper.getTypeFactory().constructCollectionType(List.class, ProduitResponse.class));
        } else {
            return Collections.singletonList(objectMapper.treeToValue(dataNode, ProduitResponse.class));
        }
    }
}
