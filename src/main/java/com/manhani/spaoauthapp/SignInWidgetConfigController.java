package com.manhani.spaoauthapp;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInWidgetConfigController {
    private final String issuerUrl;
    private final String clientId;

    public SignInWidgetConfigController(@Value("#{@environment['okta.oauth2.clientId']}") final String clientId,
            @Value("#{@environment['okta.oauth2.issuer']}") final String issuerUrl) {
        Assert.notNull(clientId, "Property 'okta.oauth2.clientId' is required.");
        Assert.notNull(issuerUrl, "Property 'okta.oauth2.issuer' is required.");
        this.clientId = clientId;
        this.issuerUrl = issuerUrl;
    }

    @GetMapping("/sign-in-widget-config")
    public WidgetConfig getWidgetConfig() {
        return new WidgetConfig(issuerUrl, clientId);
    }

    public static class WidgetConfig {
        public String baseUrl;
        public String clientId;
        public Map<String, Object> authParams = new LinkedHashMap<>();

        WidgetConfig(final String issuer, final String clientId) {
            this.clientId = clientId;
            this.authParams.put("issuer", issuer);
            this.baseUrl = issuer.replaceAll("/oauth2/.*", "");
        }
    }
}
