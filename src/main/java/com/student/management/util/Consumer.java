package com.student.management.util;

import com.student.management.manager.EmpManager;
import com.student.management.model.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    EmpManager empManager;

   @RabbitListener(queues="${spring.rabbitmq.queue}")
    public void receivedMessage(Employee msg) throws Exception{

        System.out.println("Enter Consumer#recievedMessage()");
        System.out.println("*****************************************");
        System.out.println("Consumer Received Message From Queue  : " + msg);
        System.out.println("*****************************************");
        Employee employee=empManager.createOrUpdateEmployee(msg);

        System.out.println("employee Added "+employee.getId());
        System.out.println("Exit Consumer#recievedMessage()");

    }
}
