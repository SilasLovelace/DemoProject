package backend.Kwote.controllers;

import backend.Kwote.dtos.TradePostDto;
import backend.Kwote.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/trade/")
@EnableMethodSecurity
public class TradeController {
    @Autowired
    TradeService tradeService;
    @PostMapping
    ResponseEntity<?> postTrade (@RequestBody TradePostDto tradePostDto){
        return ResponseEntity.ok(tradeService.postTrade(tradePostDto));
    }
}
