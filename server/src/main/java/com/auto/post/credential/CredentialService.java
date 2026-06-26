package com.auto.post.credential;

import java.util.List;

/**
 * 平台凭证管理服务。
 */
public interface CredentialService {

    /**
     * 查询平台凭证列表，可按平台过滤。
     *
     * @param platform 平台编码，可为空
     * @return 平台凭证列表
     */
    List<CredentialDTO> list(String platform);

    /**
     * 保存平台凭证边界信息。
     *
     * @param request 凭证保存参数
     * @return 保存后的凭证信息
     */
    CredentialDTO save(CredentialDTO request);
}
