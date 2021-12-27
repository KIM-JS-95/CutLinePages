package com.myGallary.entity;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GallaryDto {

    private Long id;

    private String title;

    private String content;

    private String username;

    private LocalDateTime createDate;

    private  int count;

    private String link;

    private Gallary gallary;

}
