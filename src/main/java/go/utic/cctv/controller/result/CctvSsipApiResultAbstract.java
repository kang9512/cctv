package go.utic.cctv.controller.result;

import lombok.Data;

@Data
public abstract class CctvSsipApiResultAbstract {
    private int status;
    private String message;

    public CctvSsipApiResultAbstract(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
