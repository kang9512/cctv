package go.utic.cctv.mapper;

import go.utic.cctv.vo.VoApiInvoke;
import go.utic.cctv.vo.VoAuthorized;
import go.utic.cctv.vo.VoCctv;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CctvApiMapper {

    VoAuthorized getApiAuthorizedInfo(@Param("apiId1") String apiId, @Param("apiToken") String apiToken);
    List<VoCctv> getCctvInfoList();
    void insertInvokeHs(VoApiInvoke voInvoke);
}
