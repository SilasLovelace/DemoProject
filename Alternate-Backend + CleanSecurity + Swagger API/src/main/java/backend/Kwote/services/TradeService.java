package backend.Kwote.services;

import backend.Kwote.dtos.TradePostDto;
import backend.Kwote.entities.TradeEntity;
import backend.Kwote.repositories.TradeCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {
    @Autowired
    TradeCrudRepository tradeCrudRepository;

    public TradePostDto postTrade (TradePostDto tradePostDto){
        TradeEntity tradeEntity = new TradeEntity()
                .trade(tradePostDto.getTrade());
        tradeCrudRepository.save(
                tradeEntity
        );
        return TradePostDto.builder()
                .trade(tradeEntity.trade())
                .tradeId(tradeEntity.tradeId())
                .build();
    }
}
