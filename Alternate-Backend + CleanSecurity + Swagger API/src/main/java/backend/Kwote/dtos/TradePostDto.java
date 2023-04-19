package backend.Kwote.dtos;

import lombok.*;

@Builder
@Getter
@Setter
public class TradePostDto {
    Integer tradeId;
    String trade;
}
