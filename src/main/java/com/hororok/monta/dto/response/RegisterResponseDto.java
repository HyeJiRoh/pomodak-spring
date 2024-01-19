package com.hororok.monta.dto.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDto {

    private int status;
    private Data data;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data{
        @JsonProperty("account_id")
        private UUID accountId;
    }

    public RegisterResponseDto(UUID accountId) {
        this.data = new Data(accountId);
        this.status = HttpStatus.CREATED.value();
    }
}