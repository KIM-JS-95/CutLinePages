package com.myGallary.entity;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gallary")
public class Gallary extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ColumnDefault("0")
    private int count;

    @NotBlank
    @Column(length =1000, nullable = false)
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @NotNull
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @OneToMany(mappedBy = "gallary",cascade = CascadeType.ALL)
    private List<GallaryReply> gallaryReply = new ArrayList<>();

    public void setAccount(Account account){
        this.account=account;

        if(!account.getGallaries().contains(this)){
            account.getGallaries().add(this);
        }
    }
}
