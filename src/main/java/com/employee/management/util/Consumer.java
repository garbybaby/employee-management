package com.employee.management.util;

import com.employee.management.controller.EmployeeController;
import com.employee.management.dto.EmployeeDto;
import com.employee.management.manager.EmpManager;
import com.employee.management.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    EmpManager empManager;
    private static final Logger logger = LogManager.getLogger(Consumer.class);

    private final String MSG_PRfX="Consumer#";
   @RabbitListener(queues="${spring.rabbitmq.queue}")
    public void receivedMessage(EmployeeDto msg) throws Exception{
       String mtd_pstfx="receivedMessage()";
       logger.info("Enter "+MSG_PRfX+mtd_pstfx);
       logger.info("Enter Consumer#recievedMessage()");
       logger.info("*****************************************");
       logger.info("Consumer Received Message From Queue  : " + msg);
       logger.info("*****************************************");
        EmployeeDto employee=empManager.createOrUpdateEmployee(msg);

       logger.info("employee Added "+employee.getId());
       logger.info("Exit Consumer#recievedMessage()");

    }
}
