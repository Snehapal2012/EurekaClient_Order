package com.example.order.service;

import com.example.order.exception.OrderException;
import com.example.order.model.Book;
import com.example.order.model.User;
import com.example.order.repository.OrderRepo;
import com.example.order.util.EmailSenderService;
import com.example.order.model.Order;
import com.example.order.dto.OrderDTO;
import com.example.order.util.TokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSenderService;
    //Apply logic for Insert order details in the database
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Order insert(OrderDTO orderDTO) {
        User userObject = restTemplate.getForObject("http://localhost:8081/user/id/"+orderDTO.getUser(), User.class);
        Book bookObject = restTemplate.getForObject("http://localhost:8082/book/id/"+orderDTO.getBook(), Book.class);
        System.out.println(userObject.getFirstName());
        System.out.println(bookObject.getBookName());
        if (userObject.equals(null) && bookObject.equals(null)) {
            throw new OrderException("User or Book Id is not present in database!");
        } else {
            Order order = new Order(orderDTO);
            orderRepo.save(order);
            String token = tokenUtil.createToken(order.getOrderId());
            emailSenderService.sendEmail(userObject.getEmail(), "Order placed!", "Hii...."+userObject.getFirstName()+" ! \n\n Your order has been placed successfully! Order details are below: \n\n Order id:  " + order.getOrderId() + "\n Order date:  " + order.getDate() + "\n Order Price:  " + order.getPrice() + "\n Order quantity:  " + order.getQuantity() + "\n Order address:  " + order.getAddress() + "\n Order user id:  " + order.getUser() + "\n Order book id:  " + order.getBook() + "\n Order cancellation status:  " + order.isCancel()+"\n Token:  " + token);
            return order;
        }
    }
    //Apply logic for Getting all order details present in the database
    @Override
    public List<Order> getAll(){
        List<Order> orderList=orderRepo.findAll();
        return orderList;
    }
    //Apply logic for Getting particular order details which will be found by id
    @Override
    public Order getById(long id){
        Optional<Order> order=orderRepo.findById(id);
        if(order.isPresent()){
            Order order1=orderRepo.findById(id).get();
            return order1;
        }else {
            throw new OrderException("Sorry! We can not find order id: "+id);
        }
    }
    //Apply logic for Deleting particular order details which will be found by id
    @Override
    public String deleteById(long orderId, long userId){
        OrderDTO orderDTO=new OrderDTO();
        Optional<Order> order=orderRepo.findById(orderId);
        User userObject = restTemplate.getForObject("http://localhost:8081/user/id/"+userId, User.class);
        Book bookObject = restTemplate.getForObject("http://localhost:8082/book/id/"+orderDTO.getBook(), Book.class);
        if((order.isPresent()) && userObject.equals(null) && bookObject.equals(null)){
            throw new OrderException("Sorry! We can not find order id, user or book id!");
        }else {
            orderRepo.deleteById(orderId);
            emailSenderService.sendEmail(userObject.getEmail(), "Order is deleted!","Hii.... "+userObject.getFirstName()+"! \n\n Your order has been deleted successfully! Order id: "+order.get().getOrderId());
            return "Details has been deleted!";
        }
    }
    //Apply logic for Updating particular order which will be found by id
    @Override
    public Order updateById(long id, OrderDTO orderDTO){
        User userObject = restTemplate.getForObject("http://localhost:8081/user/id/"+orderDTO.getUser(), User.class);
        Book bookObject = restTemplate.getForObject("http://localhost:8082/book/id/"+orderDTO.getBook(), Book.class);
        System.out.println(userObject.getFirstName());
        System.out.println(bookObject.getBookName());
        Order order=orderRepo.findById(id).get();
        if ((orderRepo.findById(id).isPresent()) && userObject.equals(null) && bookObject.equals(null)) {
            throw new OrderException("Order id, user id or book id did not match! Please check and try again!");
        }else {
        order.setDate(orderDTO.getDate());
        order.setPrice(orderDTO.getPrice());
        order.setQuantity(orderDTO.getQuantity());
        order.setAddress(orderDTO.getAddress());
        order.setUser(orderDTO.getUser());
        order.setBook(orderDTO.getBook());
        order.setCancel(orderDTO.getCancel());
        orderRepo.save(order);
        String token=tokenUtil.createToken(order.getOrderId());
        emailSenderService.sendEmail(userObject.getEmail(), "Order is updated!","Hii.... "+userObject.getFirstName()+"! \n\n Your order has been updated successfully! Order details are below: \n\n Order id:  "+order.getOrderId()+"\n Order date:  "+order.getDate()+"\n Order Price:  "+order.getPrice()+"\n Order quantity:  "+order.getQuantity()+"\n Order address:  "+order.getAddress()+"\n Order user id:  "+order.getUser()+"\n Order book id:  "+order.getBook()+"\n Order cancellation status:  "+order.isCancel()+ "\n Token:  "+token);
        return order;
        }
    }
}
