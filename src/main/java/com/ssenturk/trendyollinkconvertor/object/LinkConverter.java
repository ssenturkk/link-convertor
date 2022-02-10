package com.ssenturk.trendyollinkconvertor.object;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkConverter {

    @NotNull(message = "The link posted cannot be null!")
    @Length(min =1, message = "The link posted cannot be empty!")
    private String link;
}
