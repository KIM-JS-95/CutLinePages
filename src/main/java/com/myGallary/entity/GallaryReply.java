package com.myGallary.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GallaryReply {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

   // @Column(length =1000, nullable = false)
    private String comment;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "GALLARY_ID")
    private Gallary gallary;

    public void setGallary(Gallary gallary){
        this.gallary=gallary;

        if(!gallary.getGallaryReply().contains(this)){
            gallary.getGallaryReply().add(this);
        }
    }
}
