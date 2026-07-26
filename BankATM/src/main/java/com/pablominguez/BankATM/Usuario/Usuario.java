package com.pablominguez.BankATM.Usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "bank_database")
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    //private Account[] accounts;  with id of account, money in that acocunt, comisions to remove at each year of certain period
    private String name;
    private String apellidos;
    @Column(name = "dni", unique = true, nullable = false)
    private String dni;
    private String email;
    private String phone;
    private double money;
    private String location;
}
