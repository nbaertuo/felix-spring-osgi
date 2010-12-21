package org.ertuo.taoplugin.dao;


import java.util.Collection;

import org.ertuo.taoplugin.bean.Message;

public interface MessageDao {

	Collection<Message> list();

	void createMessage(Message message);

}
