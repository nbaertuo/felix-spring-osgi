package org.ertuo.taoplugin.facade;


import java.util.Collection;

import org.ertuo.taoplugin.bean.Message;

public interface MessageFacade {
	
	void sendMessage(Message message);

	Collection<Message> listMessages();

}
