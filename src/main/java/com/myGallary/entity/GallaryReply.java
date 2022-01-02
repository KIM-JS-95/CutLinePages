package com.myGallary.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GallaryReply {
    @Id
    @GeneratedValue
    private Long id;


    @Column(length =1000, nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GALLARY_ID")
    private Gallary gallary;

}
