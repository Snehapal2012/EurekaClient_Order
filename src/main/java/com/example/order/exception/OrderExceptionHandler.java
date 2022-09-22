package com.example.order.exception;

import com.example.order.dto.ResponseOrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class OrderExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseOrderDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> errorList=exception.getBindingResult().getAllErrors();
        List<String> errMsg=errorList.stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseOrderDTO responseOrderDTO =new ResponseOrderDTO("Exception while processing REST request", errMsg.toString());
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ResponseOrderDTO> handlePayrollException(OrderException exception){
        ResponseOrderDTO responseOrderDTO =new ResponseOrderDTO("Exception while processing REST request",exception.getMessage());
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.BAD_GATEWAY);
    }
}
