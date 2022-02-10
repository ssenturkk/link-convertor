package com.ssenturk.trendyollinkconvertor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ConvertedLink {

    @Id
    @GeneratedValue
    private int id;
    private String operation;
    private String url;
    private String deeplink;
    private LocalDateTime date = LocalDateTime.now();

    public ConvertedLink(String operation, String url, String deeplink) {
        this.operation = operation;
        this.url = url;
        this.deeplink = deeplink;
    }
}
