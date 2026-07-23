package com.pablominguez.BankATM.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;

@RestController
public class ControllerUsuario
{
    @Autowired
    ServiceUsuario serviceUsuario;

    @GetMapping("users")
    public HashSet<DTOUsuario> getUsers()
    {
        return serviceUsuario.findAllUsers();
    }

    @PostMapping("user")
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario)
    {
        Usuario user = serviceUsuario.createUser(usuario);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // Takes current request path (/api/users)
                .path("/{id}")        // Appends /{id}
                .buildAndExpand(user.getId()) // Replaces {id} with savedUser.getId()
                .toUri();

        return ResponseEntity.created(location).body(user);
    }
}
