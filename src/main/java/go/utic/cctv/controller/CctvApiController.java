package go.utic.cctv.controller;

import go.utic.cctv.controller.result.CctvApiResultCctvInfo;
import go.utic.cctv.controller.result.CctvSsipApiResultError;
import go.utic.cctv.controller.result.eCctvSsipApiErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
@Controller
@RequestMapping(CctvApiAbstract.CCTV_API_URL)
public class CctvApiController extends CctvApiAbstract {

    @GetMapping(value = {CctvApiAbstract.CCTV_API_CCTV_INFO}, produces = {"application/json; charset=utf8"})
    public ResponseEntity<?> getCctvinfoList(@PathVariable String apiToken, HttpServletRequest request) throws Exception {
        log.info("mappingPath apiToken = {}", apiToken);

        String apiId = CCTV_API_URL + CCTV_API_CCTV_INFO;
        String remoteIP = getRemoteIP(request);

//        request.getParameterNames()

        log.info("remoteIPAddress = {}", remoteIP);

        int isAuthorized = getAuthorizedInfo(apiId, apiToken, remoteIP);

        if (eCctvSsipApiErrorCode.getValue(isAuthorized).getValue() != eCctvSsipApiErrorCode.SUCCESS.getValue()) {
            // api token 인즘 오류
            System.out.println("인증오류: " + eCctvSsipApiErrorCode.getValue(isAuthorized).getValue() + ", " + eCctvSsipApiErrorCode.getValue(isAuthorized).toString());
            CctvSsipApiResultError error = new CctvSsipApiResultError(eCctvSsipApiErrorCode.getValue(isAuthorized).getValue(), eCctvSsipApiErrorCode.getValue(isAuthorized).toString());

            // 이력저장
            // apiid                      | apitoken   | ipaddr    | eventdt     | error
            // /api/brokerInfo/{apiToken} | ssip-admin | 127.0.0.1 | 2020-11-01  | 0

            insertInvokeHs(apiId,apiToken,remoteIP,isAuthorized);

            return new ResponseEntity<CctvSsipApiResultError> (error, HttpStatus.UNAUTHORIZED);
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+9"));

        CctvApiResultCctvInfo result = new CctvApiResultCctvInfo(eCctvSsipApiErrorCode.SUCCESS.getValue(), eCctvSsipApiErrorCode.SUCCESS.toString());
        result.setCctvInfo(cctvApiService.getCctvInfoList());
        result.setCount(result.getCctvInfo().size());
        result.setTimestamp(sdf.format(date));
        //System.out.println("nodeInfo =======> " + result.toString());

        // 이력저장
        insertInvokeHs(apiId,apiToken,remoteIP,isAuthorized);
        return new ResponseEntity<CctvApiResultCctvInfo> (result, HttpStatus.OK);
    }
}
