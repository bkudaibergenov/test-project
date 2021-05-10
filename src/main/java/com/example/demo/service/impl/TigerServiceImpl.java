package com.example.demo.service.impl;

import com.example.demo.service.CatService;
import org.springframework.stereotype.Service;

@Service("tigerService")
public class TigerServiceImpl implements CatService {

    @Override
    public void say() {
        System.out.println("Ррррррр");
    }
}
