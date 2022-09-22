package com.epam.library.delivery.email;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
class DeliverByEmailService {


    boolean validateEmail(String email){
        Pattern pattern = Pattern.compile("^(.+)@(\\\\S+)$");
        return pattern.matcher(email).matches();
    }

}
