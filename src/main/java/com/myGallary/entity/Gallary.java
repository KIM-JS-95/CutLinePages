package com.myGallary.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gallary extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String link;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="USER_ID")
//    private Account user;

}
