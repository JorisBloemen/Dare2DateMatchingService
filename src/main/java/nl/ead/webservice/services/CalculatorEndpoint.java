package nl.ead.webservice.services;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import nl.ead.webservice.CalculateRequest;
import nl.ead.webservice.CalculateResponse;
import nl.ead.webservice.ComparedMember;
import nl.ead.webservice.ResultList;
import nl.ead.webservice.dao.IMemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.HashMap;

@Endpoint
public class CalculatorEndpoint {
    private MatchingService matchingService;

    @Autowired
    public CalculatorEndpoint() {
    }

    @PayloadRoot(localPart = "CalculateRequest", namespace = "http://www.han.nl/schemas/messages")
    @ResponsePayload
    public CalculateResponse calculateSumForName(@RequestPayload CalculateRequest req) {
        this.matchingService = new MatchingService(req.getId());
        ResultList rl = new ResultList();
        HashMap<Long, Number> spotifyMatchCount = this.matchingService.spotifyMatches();
        for(Long id :spotifyMatchCount.keySet()){
            ComparedMember cm = new ComparedMember();
            cm.setId(id);
            cm.setSpotifyMatchCount(spotifyMatchCount.get(id).intValue());
            cm.setYoutubeMatchCount(0);
            rl.getComparedMember().add(cm);
        }
        CalculateResponse resp = new CalculateResponse();
        resp.setResultList(rl);
        return resp;
    }
}



//import java.util.List;
//
//@Endpoint
//public class CalculatorEndpoint {
//    private final ICalculationDao calculationDao;
//    private final IMoviePrinter moviePrinter;
//
//    @Autowired
//    public CalculatorEndpoint(IMoviePrinter moviePrinter, ICalculationDao calculationDao) {
//        this.moviePrinter = moviePrinter;
//        this.calculationDao = calculationDao;
//    }
//
//    @PayloadRoot(localPart = "CalculateRequest", namespace = "http://www.han.nl/schemas/messages")
//    @ResponsePayload
//    public CalculateResponse calculateSumForName(@RequestPayload CalculateRequest req) {
//        // a sequence of a minimum of 1 and unbounded max is generated as a
//        // List<>
//        List<Integer> paramList = req.getInput().getParamlist().getParam();
//        CalculateOperation op = req.getInput().getOperation();
//        int retValue = paramList.get(0);
//        StringBuffer calculationInput = new StringBuffer();
//        calculationInput.append(paramList.get(0));
//
//        for (int i = 1; i < paramList.size(); i++) {
//            // CalculateOperation is generated as an enum
//            if (op.equals(CalculateOperation.ADD)) {
//                retValue += paramList.get(i).intValue();
//                calculationInput.append(" + " + paramList.get(i).intValue());
//            } else if (op.equals(CalculateOperation.SUBTRACT)) {
//                retValue -= paramList.get(i).intValue();
//                calculationInput.append(" -" + paramList.get(i).intValue());
//            } else if (op.equals(CalculateOperation.MULTIPLY)) {
//                retValue *= paramList.get(i).intValue();
//                calculationInput.append(" * " + paramList.get(i).intValue());
//            } else if (op.equals(CalculateOperation.DIVIDE)) {
//                retValue /= paramList.get(i).intValue();
//                calculationInput.append(" / " + paramList.get(i).intValue());
//            }
//        }
//
//        CalculateResult result = new CalculateResult();
//        result.setMessage("Here are the results of the jury for the calculation " + calculationInput);
//        result.setValue(retValue);
//        CalculateResponse resp = new CalculateResponse();
//        resp.setResult(result);
//
//        // OK, I know this isn't the best example of an external service for a calculator....
//        moviePrinter.printMovieDetails("Bond");
//
//        Calculation calculation = new Calculation(calculationInput.toString(), retValue + "");
//        calculationDao.save(calculation);
//
//        return resp;
//    }
//}
