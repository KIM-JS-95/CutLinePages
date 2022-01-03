package com.myGallary.entity;



import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "account", uniqueConstraints = {@UniqueConstraint(name = "NAME_EMAIL_UNIQUE", columnNames = {"USERNAME", "EMAIL"})})
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String usercode;

    @Column(nullable = false)
    @NotBlank
    @Length(min = 4)
    private String username;

    @Column(nullable = false)
    @NotBlank
    @Length(min = 4)
    private String password;

    @Transient
    @NotBlank
    private String confirmPassword;

    @Column(nullable = false)
    @NotBlank
    @Email
    private String email;

    @Column(nullable = false)
    private Boolean isActive;

//    @CreationTimestamp
//    private Date regDate;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Gallary> gallaries = new ArrayList<>();


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;


    public void addGallary(Gallary gallary){
        this.gallaries.add(gallary);

        if(gallary.getAccount() != this){
            gallary.setAccount(this);
        }
    }

}
