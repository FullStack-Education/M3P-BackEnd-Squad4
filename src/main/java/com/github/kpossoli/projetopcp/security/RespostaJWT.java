package com.github.kpossoli.projetopcp.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespostaJWT {

    private String accessToken;

}
