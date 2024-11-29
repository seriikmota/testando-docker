package br.ueg.acervodigital.config;

import br.ueg.acervodigitalarquitetura.config.ApiSecurityConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig extends ApiSecurityConfig {

    @Value("${api.version}")
    private String apiBase;

    @Override
    protected void configureHttpSecurity(HttpSecurity http) throws Exception {

    }

    @Override
    protected List<String> getCustomFreeAccess() {
        List<String> urls = new ArrayList<>();
        urls.add(apiBase.concat("/post/list"));
        urls.add(apiBase.concat("/item/list"));
        urls.add(apiBase.concat("/item/pdf"));
        return urls;
    }

    @Override
    protected List<String> getCustomFreeAccessGet() {
        return List.of();
    }
}
