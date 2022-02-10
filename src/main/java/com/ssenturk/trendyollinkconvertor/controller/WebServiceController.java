package com.ssenturk.trendyollinkconvertor.controller;

import com.ssenturk.trendyollinkconvertor.object.LinkConverter;
import com.ssenturk.trendyollinkconvertor.service.LinkConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/converter")
public class WebServiceController  {

    @Autowired
    private LinkConverterService converterService;

    @PostMapping("/webUrl_to_deeplink")
    public ResponseEntity<String> getDeepLink(@Valid @RequestBody LinkConverter url){
        String link =  converterService.convertFromWeburl(url);
        return ResponseEntity.ok(link);
    }

    @PostMapping("/deeplink_to_webUrl")
    public ResponseEntity<String>  getUrl(@Valid @RequestBody LinkConverter deeplink){
        String link =  converterService.convertFromDeepLink(deeplink);
        return ResponseEntity.ok(link);
    }

}
