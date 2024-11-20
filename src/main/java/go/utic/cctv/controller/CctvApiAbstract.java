package go.utic.cctv.controller;

import go.utic.cctv.controller.result.eCctvSsipApiErrorCode;
import go.utic.cctv.service.CctvApiService;
import go.utic.cctv.vo.VoApiInvoke;
import go.utic.cctv.vo.VoAuthorized;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@RequiredArgsConstructor
public abstract class CctvApiAbstract {

    @Autowired
    protected CctvApiService cctvApiService;

    static final String CCTV_API_URL       = "/api";
    static final String CCTV_API_CCTV_INFO = "/cctvInfo/{apiToken}";

    protected int getAuthorizedInfo(String apiId, String apiToken, String remoteIP) throws Exception {

        VoAuthorized authorized = cctvApiService.getApiAuthorizedInfo(apiId, apiToken);
        if (authorized == null) {
            // 등록되지 않은 사용자
            return eCctvSsipApiErrorCode.UNREGISTERED_APITOKEN.getValue();
        }
        else {
            if (authorized.getIsAuthorized() == 0) {
                // 키 유효기간이 지남
                return eCctvSsipApiErrorCode.APITOKEN_EXPIRED.getValue();
            }
            else {
                // ip address 체크
                String[] ipAddrs = authorized.getAccessIpAddrs().replaceAll(" ", "").split(",");
                Set<String> setIpAddrs = new HashSet<String>(Arrays.asList(ipAddrs));
                //for (String ipAddr : ipAddrs)
                //    System.out.println(ipAddr);
                if (setIpAddrs.contains("*") || setIpAddrs.contains(remoteIP)) {
                    return eCctvSsipApiErrorCode.SUCCESS.getValue();
                }
            }
        }
        return eCctvSsipApiErrorCode.DENIED_IPADDR.getValue();
    }

    protected void insertInvokeHs(String apiId,String apiToken,String remoteIP,int isAuthorized) {
        VoApiInvoke voAI = new VoApiInvoke(apiId,apiToken,remoteIP,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),eCctvSsipApiErrorCode.getValue(isAuthorized).getValue());
        cctvApiService.insertInvokeHs(voAI);
    }

    protected String getRemoteIP(HttpServletRequest request) {
        String ipAddr = request.getHeader("X-FORWARDED-FOR");

        //proxy 환경일 경우
        if (ipAddr == null || ipAddr.length() == 0) {
            ipAddr = request.getHeader("Proxy-Client-IP");
        }

        //웹로직 서버일 경우
        if (ipAddr == null || ipAddr.length() == 0) {
            ipAddr = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddr == null || ipAddr.length() == 0) {
            ipAddr = request.getRemoteAddr() ;
        }

        //-Djava.net.preferIPv4Stack=true
        if (ipAddr.equals("0:0:0:0:0:0:0:1"))   //==> ipv6 <== default
            ipAddr = "127.0.0.1";   //==> localhost

        return ipAddr;
    }
}