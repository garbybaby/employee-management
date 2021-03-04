package com.employee.management.util;


import com.employee.management.dto.EmployeeDto;
import com.employee.management.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private static final Logger logger = LogManager.getLogger(Producer.class);
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;


    @Autowired
    private AmqpTemplate amqpTemplate;

    public void produceMsg(EmployeeDto msg)
    {
        try {
            logger.info("@@@ Employee Before Put to Queue. "+msg.toString());
            amqpTemplate.convertAndSend(exchange, routingKey, msg);
            logger.info("Put to queue. "+msg.toString());
        }catch (Exception ex){
            logger.info("Exception "+ex);
        }
    }
}
