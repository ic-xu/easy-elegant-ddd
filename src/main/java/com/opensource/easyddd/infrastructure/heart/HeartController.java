package com.opensource.easyddd.infrastructure.heart;

import com.opensource.easyddd.infrastructure.dto.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HeartController {


    @GetMapping("/heart")
    public BaseResponse<String> heart(){

        return BaseResponse.ok("heart....");
    }

}
