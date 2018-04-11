package com.webcerebrium.binance.websocket;

/* ============================================================
 * java-binance-api
 * https://github.com/webcerebrium/java-binance-api
 * ============================================================
 * Copyright 2017-, Viktor Lopata, Web Cerebrium OÜ
 * Released under the MIT License
 * ============================================================ */


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceEventDepthUpdate;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;


@Slf4j
public abstract class BinanceWebSocketAdapterDepth implements BinanceWebSocketAdapterInterface {
	
	@Override
	public void onOpen( ServerHandshake handshakedata ) {
        log.debug("onWebSocketConnect: {}", handshakedata);
    }

	@Override
	public void onError( Exception ex ) {
        log.error("onWebSocketError: {}", ex);
    }

	@Override
	public void onMessage( String message ) {
        log.debug("onWebSocketText message={}", message);
        JsonObject operation = (new Gson()).fromJson(message, JsonObject.class);
        try {
            onMessage(new BinanceEventDepthUpdate(operation));
        } catch ( BinanceApiException e ) {
            log.error("Error in websocket message {}", e.getMessage());
        }
    }

    public abstract void onMessage(BinanceEventDepthUpdate event) throws BinanceApiException;
}
