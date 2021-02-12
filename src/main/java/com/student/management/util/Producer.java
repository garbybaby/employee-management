package com.student.management.util;


import com.student.management.model.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;


    @Autowired
    private AmqpTemplate amqpTemplate;

    public void produceMsg(Employee msg)
    {
        try {
            amqpTemplate.convertAndSend(exchange, routingKey, msg);
        }catch (Exception ex){
            System.out.println("Exception "+ex);
        }
    }
}
