package go.utic.cctv.controller.result;

import java.util.HashMap;
import java.util.Map;

public enum eCctvSsipApiErrorCode {
    SUCCESS(0, "요청성공"),
    UNREGISTERED_APITOKEN(1, "등록되지 않은 API TOKEN"),
    APITOKEN_EXPIRED(2, "API TOKEN 유효기간 오류"),
    DENIED_IPADDR(3, "접근불가 IP Address"),
    NOTFOUND_BROKER_INFO(4, "접속 가능한 브로커 정보가 없음"),
    NOTFOUNT_URL(5,"잘못된 URL 경로");

    private final int intValue;
    private final String strValue;

    public int getValue() {
        return intValue;
    }

    public String toString() {
        return strValue;
    }

    private static final Map<Integer, eCctvSsipApiErrorCode> map;

    eCctvSsipApiErrorCode(int intValue, String strValue) {
        this.intValue = intValue;
        this.strValue = strValue;
    }

    static {
        map = new HashMap<>();
        for (eCctvSsipApiErrorCode e : values())
            map.put(Integer.valueOf(e.intValue), e);
    }

    public static eCctvSsipApiErrorCode getValue(int intValue) {

        return map.get(Integer.valueOf(intValue));
    }
}
