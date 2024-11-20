package go.utic.cctv.controller.result;

import go.utic.cctv.vo.VoCctv;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CctvApiResultCctvInfo extends CctvSsipApiResultAbstract {

    private static final long serialVersionUID = 1L;

    private List<VoCctv> cctvInfo;
    private int count;
    private String timestamp;


    public CctvApiResultCctvInfo(int status, String message) {
        super(status, message);
        cctvInfo = null;
    }

    public CctvApiResultCctvInfo(int status, String message, List<VoCctv> cctvInfoList) {
        super(status, message);
        this.cctvInfo = cctvInfoList;
    }
}
