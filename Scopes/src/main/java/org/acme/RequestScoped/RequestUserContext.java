package org.acme.RequestScoped;

import io.vertx.core.http.HttpServerRequest;

import java.util.Locale;

public class RequestUserContext {

    //it is lazy
    //Such a bean lives only within a chain used to process a single HTTP request.
    // A request-scoped bean encapsulates information of a request and shares them through multiple injection points

    private final Locale locale;

    public RequestUserContext(HttpServerRequest request) {
        String localeHeader = request.headers().get("X-Locale");
        locale = localeHeader != null ? new Locale(localeHeader) : Locale.ENGLISH;
    }

    Locale getLocale() {
        return locale;
    }

}
