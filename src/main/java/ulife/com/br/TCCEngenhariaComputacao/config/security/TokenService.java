package ulife.com.br.TCCEngenhariaComputacao.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;

import java.util.Date;

@Service
public class TokenService {

    @Value("${tcc.jwt.secret}")
    private String secret;

    @Value("${tcc.jwt.expiration}")
    private Long expiration;

    public Algorithm algoritimo(){
        return Algorithm.HMAC256(secret);
    }

    public String gerarToken(Usuario usuario){
        Date dataAtual = new Date();
        Date dataDeExpiracao = new Date(dataAtual.getTime()+expiration);

        return JWT.create()
                .withIssuer("TCC Engenharia Computação")
                .withSubject(usuario.getLogin())
                .withClaim("id",usuario.getId())
                .withExpiresAt(dataDeExpiracao)
                .sign(algoritimo());
    }

    public String recuperarToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null)
            return authorizationHeader.replace("Bearer ","");

        return null;
    }

    public String getSubject(String tokenJWT){
        return JWT.require(algoritimo())
                .withIssuer("TCC Engenharia Computação")
                .build()
                .verify(tokenJWT)
                .getSubject();
    }

}
