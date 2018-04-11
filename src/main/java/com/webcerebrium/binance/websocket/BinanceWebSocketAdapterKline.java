package com.webcerebrium.binance.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceEventKline;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;


@Slf4j
public abstract class BinanceWebSocketAdapterKline implements BinanceWebSocketAdapterInterface {
	
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
            onMessage(new BinanceEventKline(operation));
        } catch (BinanceApiException e) {
            log.error("Error in websocket message {}", e.getMessage());
        }
    }

    public abstract void onMessage(BinanceEventKline event) throws BinanceApiException;

}
