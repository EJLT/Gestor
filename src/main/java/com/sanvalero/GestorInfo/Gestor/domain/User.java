package com.sanvalero.GestorInfo.Gestor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "gestor_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "El nombre de usuario no puede exceder los 50 caracteres")
    @NotBlank(message = "El nombre de usuario no puede estar en blanco")
    @Column
    private String username;

    @Size(min = 6, max = 50, message = "La contraseña debe tener entre 6 y 50 caracteres")
    @Column
    private String password;

    @Size(max = 100, message = "El nombre completo no puede exceder los 100 caracteres")
    @Column
    private String fullname;

    @Email(message = "El formato del correo electrónico no es válido")
    @Column
    private String email;

    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @PreUpdate
    protected void onUpdate() {
        this.registrationDate = new Date();
    }

    // atributos, getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}

