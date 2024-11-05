package com.erikssonherlo.common.application.response;

import lombok.*;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    String token;
}