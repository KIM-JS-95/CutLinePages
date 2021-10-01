package com.myGallary.entity;



import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "account", uniqueConstraints = {@UniqueConstraint(name = "NAME_EMAIL_UNIQUE", columnNames = {"USERNAME", "EMAIL"})})
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    // username -> usercode
    @Column(nullable = false)
    @NotBlank
    //@Length(min = 4)
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

    @CreationTimestamp
    private Date regDate;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

}
