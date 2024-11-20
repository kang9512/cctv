package go.utic.cctv.vo;

import lombok.Data;

@Data
public class VoApiInvoke {
    String apiId;
    String apiToken;
    String ipAddr;
    String eventDt;
    int error;

    public VoApiInvoke(String apiId, String apiToken, String ipAddr, String eventDt, int error) {
        this.apiId = apiId;
        this.apiToken = apiToken;
        this.ipAddr = ipAddr;
        this.eventDt = eventDt;
        this.error = error;
    }
}
