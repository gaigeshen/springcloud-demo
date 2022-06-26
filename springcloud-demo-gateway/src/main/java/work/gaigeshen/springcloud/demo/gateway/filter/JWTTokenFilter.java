package work.gaigeshen.springcloud.demo.gateway.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import work.gaigeshen.springcloud.demo.commons.json.JsonCodec;
import work.gaigeshen.springcloud.demo.commons.web.HttpStatusResultCode;
import work.gaigeshen.springcloud.demo.commons.web.Result;
import work.gaigeshen.springcloud.demo.commons.web.ResultCreator;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
@Component
public class JWTTokenFilter implements GlobalFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpResponse response = exchange.getResponse();

    String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");

    if (Objects.isNull(authorization)) {
      return renderResult(response, ResultCreator.create(HttpStatusResultCode.UNAUTHORIZED));
    }

    JWTVerifier verifier = JWT.require(Algorithm.HMAC256("")).build();
    try {
      verifier.verify(authorization);
    } catch (JWTVerificationException e) {
      return renderResult(response, ResultCreator.create(HttpStatusResultCode.FORBIDDEN));
    }
    return chain.filter(exchange);
  }

  private Mono<Void> renderResult(ServerHttpResponse response, Result<?> result) {
    response.setStatusCode(HttpStatus.OK);
    response.getHeaders().add("Content-Type", "application/json;charset=utf-8");

    String encodedResult = JsonCodec.instance().encode(result);

    DataBuffer buffer = response.bufferFactory().wrap(encodedResult.getBytes(StandardCharsets.UTF_8));

    return response.writeWith(Flux.just(buffer));
  }
}
