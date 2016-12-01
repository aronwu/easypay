package com.wuswoo.easypay.service.misc.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/** MQ 消息发送器 */
@Component
public class MQSender
{
	private AmqpTemplate template;
	
	public void send(Object message) throws AmqpException
	{
		template.convertAndSend(message);
	}
	
	public void send(String routingKey, Object message) throws AmqpException
	{
		template.convertAndSend(routingKey, message);
	}
	
	public void send(String exchange, String routingKey, Object message) throws AmqpException
	{
		template.convertAndSend(exchange, routingKey, message);
	}
	
	public void send(Object message, MessagePostProcessor messagePostProcessor) throws AmqpException
	{
		template.convertAndSend(message, messagePostProcessor);
	}
	
	public void send(String routingKey, Object message, MessagePostProcessor messagePostProcessor) throws
		AmqpException
	{
		template.convertAndSend(routingKey, message, messagePostProcessor);
	}
	
	public void send(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor) throws
		AmqpException
	{
		template.convertAndSend(exchange, routingKey, message, messagePostProcessor);
	}

	@Async
	public void sendAsync(Object message) throws AmqpException
	{
		template.convertAndSend(message);
	}
	
	@Async
	public void sendAsync(String routingKey, Object message) throws AmqpException
	{
		template.convertAndSend(routingKey, message);
	}
	
	@Async
	public void sendAsync(String exchange, String routingKey, Object message) throws AmqpException
	{
		template.convertAndSend(exchange, routingKey, message);
	}
	
	@Async
	public void sendAsync(Object message, MessagePostProcessor messagePostProcessor) throws
		AmqpException
	{
		template.convertAndSend(message, messagePostProcessor);
	}
	
	@Async
	public void sendAsync(String routingKey, Object message, MessagePostProcessor messagePostProcessor) throws
		AmqpException
	{
		template.convertAndSend(routingKey, message, messagePostProcessor);
	}
	
	@Async
	public void sendAsync(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor) throws
		AmqpException
	{
		template.convertAndSend(exchange, routingKey, message, messagePostProcessor);
	}

	public AmqpTemplate getTemplate()
	{
		return template;
	}

	public void setTemplate(AmqpTemplate template)
	{
		this.template = template;
	}

}
