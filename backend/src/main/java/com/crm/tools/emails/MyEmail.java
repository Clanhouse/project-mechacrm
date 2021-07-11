package com.crm.tools.emails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyEmail {

    private String subject;
    private String message;
    private String recipient;

}
