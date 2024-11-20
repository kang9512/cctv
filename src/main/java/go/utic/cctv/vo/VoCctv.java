package go.utic.cctv.vo;

import lombok.Data;

@Data
public class VoCctv {
    private String cctvid;
    private String cctvname;
    private String streamingsession;
    private String inner_ip;
    private String port;
    private String xcoord;
    private String ycoord;
}
