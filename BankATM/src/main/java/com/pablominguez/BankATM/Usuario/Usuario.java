package com.pablominguez.BankATM.Usuario;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String apellidos;
    @Column(name = "dni", unique = true, nullable = false)
    private final String dni;
    private String email;
    private String phone;
    private double money;
    private String location;

}
