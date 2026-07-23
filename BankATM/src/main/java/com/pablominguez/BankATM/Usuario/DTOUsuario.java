package com.pablominguez.BankATM.Usuario;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DTOUsuario
{
    private String name;
    private String apellidos;
    private double money;
}
