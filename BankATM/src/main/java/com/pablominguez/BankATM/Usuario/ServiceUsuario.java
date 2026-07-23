package com.pablominguez.BankATM.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class ServiceUsuario
{
    @Autowired
    RepoUsuario repoUsuario;

    public HashSet<DTOUsuario> findAllUsers()
    {
        Set<Usuario> set = new HashSet<>();

        set = Set.copyOf(repoUsuario.findAll());

        Set<DTOUsuario> dtoset = new HashSet<>();

        Iterator<Usuario> it = set.iterator();

        while(it.hasNext())
        {
            Usuario user = it.next();
            DTOUsuario dtoUsuario = new DTOUsuario();

            dtoUsuario.setName(user.getName());
            dtoUsuario.setApellidos(user.getApellidos());
            dtoUsuario.setMoney(user.getMoney());

            dtoset.add(dtoUsuario);
        }

        return (HashSet<DTOUsuario>) dtoset;
    }

    public Usuario createUser(Usuario usuario)
    {
        return repoUsuario.save(usuario);
    }
}
