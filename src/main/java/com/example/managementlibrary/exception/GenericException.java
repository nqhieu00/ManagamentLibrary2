package com.example.managementlibrary.exception;


public class GenericException extends RuntimeException {

    private String message;

    public GenericException(String message) {
        super(message);
        if(message.contains("UK")){
            if(message.contains("UK_or6k6jmywerxbme223c988bmg")){
                message="Tác giả đã tồn tại";
            }
            else if(message.contains("UK_46ccwnsi9409t36lurvtyljak")){
                message="Thể loại đã tồn tại";
            }
            else if(message.contains("UK_h9trv4xhmh6s68vbw9ba6to70")){
                message="Nhà xuất bản đã tồn tại";
            }
            else if(message.contains("UK_ob8kqyqqgmefl0aco34akdtpe")){
                message="Email đã tồn tại";
            }
            else if(message.contains("UK_589idila9li6a4arw1t8ht1gx")){
                message="Phone number đã tồn tại";
            }
        }
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
