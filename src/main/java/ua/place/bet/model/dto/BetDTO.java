package ua.place.bet.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BetDTO {
    private Long money;
    private Double coefficient;
}
