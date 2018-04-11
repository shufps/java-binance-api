package com.webcerebrium.binance.websocket;

import org.java_websocket.handshake.ServerHandshake;

public interface BinanceWebSocketAdapterInterface {

	public abstract void onOpen( ServerHandshake handshakedata );
	public abstract void onMessage( String message );
	public abstract void onClose( int code, String reason, boolean remote );
	public abstract void onError( Exception ex );
}
