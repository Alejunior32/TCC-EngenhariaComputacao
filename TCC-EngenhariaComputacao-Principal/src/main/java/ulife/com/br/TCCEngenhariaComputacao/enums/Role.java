package ulife.com.br.TCCEngenhariaComputacao.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN(),
    ROLE_MEDICO(),
    ROLE_PACIENTE();

    @Override
    public String getAuthority() {
        return name();
    }
}
