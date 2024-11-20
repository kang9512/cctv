package go.utic.cctv.vo;

import lombok.Data;

@Data
public class VoAuthorized {
    private String accessIpAddrs;
    private int isAuthorized;
}
