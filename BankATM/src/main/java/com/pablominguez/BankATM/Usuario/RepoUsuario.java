package com.pablominguez.BankATM.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario, Long>
{
    public Optional<Usuario> findByDni(String dni);

    public Optional<Usuario> findByNameAndApellidos(String name, String apellidos);

    public Optional<Usuario> findByMoneyGreaterThan(long limit);

    public Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE REPLACE(u.location, ' ','') LIKE CONCAT('%,', TRIM(:province), ',%')")
    public List<Optional<Usuario>> findByProvince(String province);

    @Modifying
    public Optional<Usuario> deleteByDni(String dni);

    @Query("UPDATE Usuario u SET u.location = :location WHERE u.dni = :dni")
    @Modifying
    public void updateLocationByDni(String dni, String location);

    @Modifying
    @Query("UPDATE Usuario u SET u.phone = :phone WHERE u.dni = :dni")
    public void updatePhoneByDni(String dni, String phone);

    @Modifying
    @Query("UPDATE Usuario u SET u.money = :money WHERE u.dni = :dni")
    public void updateMoneyByDni(String dni, Double money);
}
