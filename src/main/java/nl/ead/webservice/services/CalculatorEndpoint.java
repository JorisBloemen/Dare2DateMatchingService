package nl.ead.webservice.services;

import nl.ead.webservice.CalculateRequest;
import nl.ead.webservice.CalculateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CalculatorEndpoint {

    @Autowired
    private IMatchService matchService;

    public CalculatorEndpoint() {
    }

    @PayloadRoot(localPart = "CalculateRequest", namespace = "http://www.han.nl/schemas/messages")
    @ResponsePayload
    public CalculateResponse calculateSumForName(@RequestPayload CalculateRequest req) {
        CalculateResponse resp = new CalculateResponse();
        resp.setResultList(this.matchService.getMatches(req.getId()));
        return resp;
    }
}
