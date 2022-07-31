package com.ckm.store.rest;

import com.ckm.store.service.Checkout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CheckoutController {

    @Autowired
    private Checkout checkout;

    @RequestMapping(path = "/checkout", method = RequestMethod.GET)
    public double getTotalAmount(){
        List<String> list = new ArrayList<>();
        list.add("Moby Dick");
        list.add("The Terrible Privacy of Maxwell Sim");
        //list.add("Sleeping Murder");

        return checkout.checkout(list);
    }
}
