package go.utic.cctv.service;

import go.utic.cctv.mapper.CctvApiMapper;
import go.utic.cctv.vo.VoApiInvoke;
import go.utic.cctv.vo.VoAuthorized;
import go.utic.cctv.vo.VoCctv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional(readOnly = false, rollbackFor = {Exception.class})
@RequiredArgsConstructor
public class CctvApiService {

    private final CctvApiMapper cctvApiMapper;

    @Transactional(readOnly = true)
    public List<VoCctv> getCctvInfoList() {
        return cctvApiMapper.getCctvInfoList();
    }

    @Transactional(readOnly = true)
    public VoAuthorized getApiAuthorizedInfo(String apiId, String apiToken) {
        return cctvApiMapper.getApiAuthorizedInfo(apiId, apiToken);
    }

    public void insertInvokeHs(VoApiInvoke voInvoke){
        cctvApiMapper.insertInvokeHs(voInvoke);
    }


}
