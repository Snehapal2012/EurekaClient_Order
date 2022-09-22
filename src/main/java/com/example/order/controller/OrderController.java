package com.example.order.controller;

import com.example.order.dto.OrderDTO;
import com.example.order.dto.ResponseOrderDTO;
import com.example.order.model.Order;
import com.example.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order") //This is the global APi
public class OrderController {
    //Create dependency injection for orderService class
    @Autowired
    IOrderService orderService;
    //Create Api for Insert order details in the database
    @PostMapping("/insert")
    public ResponseEntity<ResponseOrderDTO> insert(@Valid @RequestBody OrderDTO orderDTO) throws Exception{
        Order order=orderService.insert(orderDTO);
        ResponseOrderDTO responseOrderDTO=new ResponseOrderDTO("Order details are submitted!",order);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.CREATED);
    }
    //Create Api for Getting all order details present in the database
    @GetMapping("/getAll")
    public ResponseEntity<ResponseOrderDTO> getAll(){
        List<Order> orderList=orderService.getAll();
        ResponseOrderDTO responseOrderDTO=new ResponseOrderDTO("All order details are found!",orderList);
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.FOUND);
    }
    //Create Api for Getting particular order details which will be found by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseOrderDTO> getById(@PathVariable long id){
        Order order=orderService.getById(id);
        ResponseOrderDTO responseOrderDTO=new ResponseOrderDTO("Searched order details by id is found!",order);
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.FOUND);
    }
    //Create Api for Deleting particular order details which will be found by id
    @DeleteMapping("/delete/{orderId}/{userId}")
    public ResponseEntity<ResponseOrderDTO> deleteById(@PathVariable long orderId, @PathVariable long userId){
        String result=orderService.deleteById(orderId,userId);
        ResponseOrderDTO responseOrderDTO=new ResponseOrderDTO("Cart details is deleted!","Deleted order id is: "+orderId);
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.GONE);
    }
    //Create Api for Updating particular order details which will be found by id
    @PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseOrderDTO> updateById(@Valid @PathVariable long id,@RequestBody OrderDTO orderDTO){
        Order order=orderService.updateById(id,orderDTO);
        ResponseOrderDTO responseOrderDTO=new ResponseOrderDTO("Searched details is updated!",order);
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.ACCEPTED);
    }
}
