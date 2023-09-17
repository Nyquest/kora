package ru.tinkoff.kora.http.client.common.form;

import ru.tinkoff.kora.common.Context;
import ru.tinkoff.kora.http.client.common.request.HttpClientRequestBuilder;
import ru.tinkoff.kora.http.client.common.request.HttpClientRequestMapper;
import ru.tinkoff.kora.http.common.form.FormUrlEncoded;

public final class FormUrlEncodedClientRequestMapper implements HttpClientRequestMapper<FormUrlEncoded> {
    @Override
    public HttpClientRequestBuilder apply(Context ctx, HttpClientRequestBuilder builder, FormUrlEncoded form) {
        var writer = new UrlEncodedWriter();
        for (var part : form) {
            for (var value : part.values()) {
                writer.add(part.name(), value);
            }
        }
        return writer.write(builder);
    }
}
