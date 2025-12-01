package com.green.pipeline_dummy.application.user.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserInfo {
    String countryCode;
    String currencyCode;
}
