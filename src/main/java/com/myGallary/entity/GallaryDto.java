package com.myGallary.entity;

import lombok.*;


import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GallaryDto {

    private Long id;

    private String title;

    private String content;

    private String type;

    private LocalDateTime createDate;

}
