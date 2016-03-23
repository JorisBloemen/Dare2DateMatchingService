package nl.ead.webservice.services;

import nl.ead.webservice.CalculateRequest;
import nl.ead.webservice.CalculateResponse;
import nl.ead.webservice.ComparedMember;
import nl.ead.webservice.ResultList;
import nl.ead.webservice.dao.ICalculationDao;
import nl.ead.webservice.dao.IMemberDao;
import nl.ead.webservice.model.Calculation;
import nl.ead.webservice.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class CalculatorEndpoint {
    private final IMemberDao memberDao;

    @Autowired
    public CalculatorEndpoint(IMemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @PayloadRoot(localPart = "CalculateRequest", namespace = "http://www.han.nl/schemas/messages")
    @ResponsePayload
    public CalculateResponse calculateSumForName(@RequestPayload CalculateRequest req) {
        ComparedMember cm = new ComparedMember();
        cm.setId(new Long(10));
        cm.setSpotifyMatchCount(14);
        cm.setYoutubeMatchCount(85);

        ResultList rl = new ResultList();
        rl.getComparedMember().add(cm);
        rl.getComparedMember().add(cm);

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
