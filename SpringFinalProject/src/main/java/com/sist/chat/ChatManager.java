package com.sist.chat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/chat-ws")
public class ChatManager {

	private static List<Session> users=new ArrayList<Session>();
	// NodeJS => react
	
	@OnOpen	// 연결 요청이 들어왔을 때 처리
	public void onOpen(Session session) {
		users.add(session);
		System.out.println("클라이언트 접속 :"+session.getId());
	}
	
	@OnClose // 연결 종료시 처리
	public void onclose(Session session) {
		users.remove(session);
		System.out.println("클라이언트 퇴장 :"+session.getId());
	}
	
	@OnMessage // 채팅 중
	public void onMessage(String message,Session session) throws Exception {
		System.out.println("수신 메세지 :"+message);
		
		/*
		 * for(session s:users) {
		 * 	s.getBasicRemote().sendText(message);
		 * 	System.out.println(session.getId()+"전송");
		 * }
		 */
		
		Iterator<Session> it=users.iterator();
		System.out.println("현재 인원 :"+users.size());
		
		while(it.hasNext()) {
			it.next().getBasicRemote().sendText(message);
			System.out.println(session.getId()+"전송");
		}
	}
	
}
