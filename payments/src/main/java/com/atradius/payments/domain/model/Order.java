package com.atradius.payments.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Order {
    @JsonProperty("user")
    private String user;
    @JsonProperty("drink")
    private String drink;
    @JsonProperty("size")
    private String size;
}
