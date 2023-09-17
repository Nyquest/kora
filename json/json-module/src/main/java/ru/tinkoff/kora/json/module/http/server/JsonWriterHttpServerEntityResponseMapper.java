package ru.tinkoff.kora.json.module.http.server;

import ru.tinkoff.kora.common.Context;
import ru.tinkoff.kora.http.server.common.HttpServerRequest;
import ru.tinkoff.kora.http.server.common.HttpServerResponse;
import ru.tinkoff.kora.http.server.common.HttpServerResponseEntity;
import ru.tinkoff.kora.http.server.common.handler.HttpServerResponseMapper;
import ru.tinkoff.kora.json.common.JsonWriter;
import ru.tinkoff.kora.json.module.http.JsonHttpOutBody;

public final class JsonWriterHttpServerEntityResponseMapper<T> implements HttpServerResponseMapper<HttpServerResponseEntity<T>> {
    private final JsonWriter<T> writer;

    public JsonWriterHttpServerEntityResponseMapper(JsonWriter<T> writer) {
        this.writer = writer;
    }

    @Override
    public HttpServerResponse apply(Context ctx, HttpServerRequest request, HttpServerResponseEntity<T> value) {
        return HttpServerResponse.of(value.code(), value.headers(), new JsonHttpOutBody<>(this.writer, ctx, value.body()));
    }
}
