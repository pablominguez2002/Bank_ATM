package com.pablominguez.BankATM.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DTOUsuario
{
    private String name;
    private String apellidos;
    private double money;
}
