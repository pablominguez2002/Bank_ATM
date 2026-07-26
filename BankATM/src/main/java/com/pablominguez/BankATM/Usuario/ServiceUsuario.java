package com.pablominguez.BankATM.Usuario;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

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

    public Optional<DTOUsuario> findByDNI(String dni)
    {
        Optional<Usuario> user = repoUsuario.findByDni(dni);

        DTOUsuario dtouser = new DTOUsuario(user.get().getName(), user.get().getApellidos(), user.get().getMoney());

        Optional<DTOUsuario> dto = Optional.of(dtouser);

        return dto;
    }

    public Optional<DTOUsuario> findByNames(String name, String apellidos)
    {
        Optional<Usuario> user = repoUsuario.findByNameAndApellidos(name, apellidos);

        DTOUsuario dtouser = new DTOUsuario(user.get().getName(), user.get().getApellidos(), user.get().getMoney());

        Optional<DTOUsuario> dto = Optional.ofNullable(dtouser);

        return dto;
    }

    public Optional<DTOUsuario> filterByMoreMoney(long limit)
    {
        Optional<Usuario> user = repoUsuario.findByMoneyGreaterThan(limit);

        Optional<DTOUsuario> dtouser = Optional.ofNullable(new DTOUsuario(user.get().getName(), user.get().getApellidos(), user.get().getMoney()));

        return dtouser;
    }

    public Optional<DTOUsuario> findByEmail(String email)
    {
        Optional<Usuario> user = repoUsuario.findByEmail(email);

        Optional<DTOUsuario> dtouser = Optional.ofNullable(new DTOUsuario(user.get().getName(), user.get().getApellidos(), user.get().getMoney()));

        return dtouser;
    }

    public List<Optional<DTOUsuario>> findByProvince(String province)
    {
        List<Optional<Usuario>> list = repoUsuario.findByProvince(province);

        List<DTOUsuario> dtolist = list.stream().map((usuario) -> new DTOUsuario(usuario.get().getName(), usuario.get().getApellidos(), usuario.get().getMoney())).toList();

        Iterator<DTOUsuario> it = dtolist.iterator();

        List<Optional<DTOUsuario>> dto = new ArrayList<>();

        while(it.hasNext())
        {
            dto.add(Optional.ofNullable(it.next()));
        }

        return dto;
    }

    @Transactional
    public DTOUsuario deleteByDni(String dni)
    {
        Optional<Usuario> user = repoUsuario.deleteByDni(dni);

        DTOUsuario dtouser = new DTOUsuario(user.get().getName(), user.get().getApellidos(), user.get().getMoney());

        return dtouser;
    }

    @Transactional
    public DTOUsuario updateLocationByDni(String dni, String location)
    {
        repoUsuario.updateLocationByDni(dni, location);

        Optional<Usuario> user = repoUsuario.findByDni(dni);

        DTOUsuario dto = new DTOUsuario(user.get().getName(), user.get().getApellidos(), user.get().getMoney());

        return dto;
    }

    @Transactional
    public DTOUsuario updatePhoneByDni(String dni, String phone)
    {
        repoUsuario.updatePhoneByDni(dni, phone);

        Optional<Usuario> user = repoUsuario.findByDni(dni);

        DTOUsuario dto = new DTOUsuario(user.get().getName(), user.get().getApellidos(), user.get().getMoney());

        return dto;
    }

    @Transactional
    public DTOUsuario ingresaMoneyByDni(String dni, Double money)
    {
        Optional<Usuario> user = repoUsuario.findByDni(dni);

        money += user.get().getMoney();

        repoUsuario.updateMoneyByDni(dni, money);

        DTOUsuario dto = new DTOUsuario(user.get().getName(), user.get().getApellidos(), money);

        return dto;
    }

    @Transactional
    public DTOUsuario retireMoneyByDni(String dni, Double money)
    {
        Optional<Usuario> user = repoUsuario.findByDni(dni);

        if(money<=user.get().getMoney())
            money = user.get().getMoney() - money;
        else
            throw new RuntimeException("No hay Suficiente Dinero en la Cuenta");

        repoUsuario.updateMoneyByDni(dni, money);

        DTOUsuario dto = new DTOUsuario(user.get().getName(), user.get().getApellidos(), money);

        return dto;
    }
}
