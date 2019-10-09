package eg;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Configuration
public class FiltersConfiguration {

    public static final String TX_ID = "txId";

    //    @Bean
    public FilterRegistrationBean registerLoggerFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

                String txId = UUID.randomUUID().toString();

                String rqTxId = request.getHeader(TX_ID);
                if (!StringUtils.isEmpty(rqTxId)) {
                    txId = rqTxId + " -> " + txId;
                }
                request.setAttribute(TX_ID, txId);

                response.addHeader(TX_ID, txId);

                MDC.put(TX_ID, txId);

                log.info("...before... txId: {}", txId);

                filterChain.doFilter(request, response);

                log.info("...after... txId: {}", txId);
            }
        });
        return filterRegistrationBean;
    }
}
