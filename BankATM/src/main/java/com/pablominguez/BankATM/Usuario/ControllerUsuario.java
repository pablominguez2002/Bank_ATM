//Corregir con If's para no siempre enviar el mismo Status Code


package com.pablominguez.BankATM.Usuario;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("user/dni/{dni}")
    public ResponseEntity<Optional<DTOUsuario>> getUserByDNI(@PathVariable String dni)
    {
        //ResponseEntity.ok(serviceUsuario.findByDNI(dni));
        return ResponseEntity.ok().body(serviceUsuario.findByDNI(dni));
    }

    @GetMapping("user/{name}/{apellidos}")
    public ResponseEntity<DTOUsuario> getUserByNames(@PathVariable String name, @PathVariable String apellidos)
    {
        return ResponseEntity.ofNullable(serviceUsuario.findByNames(name, apellidos).orElseThrow(() -> new EntityNotFoundException("Usuario no Encontrado con Nombre: " + name + " y con Apellidos: " + apellidos)));
    }

    @GetMapping("user/correo/{email}")
    public ResponseEntity<DTOUsuario> getUserByEmail(@PathVariable String email)
    {
        return ResponseEntity.ofNullable(serviceUsuario.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Usuario no Encontrado con E-Mail: " + email)));
    }

    @GetMapping("users/province/{province}")
    public ResponseEntity<List<Optional<DTOUsuario>>> getUsersByProvince(@PathVariable String province)
    {
        return ResponseEntity.ok().body(serviceUsuario.findByProvince(province));
    }

    @DeleteMapping("user/dni/{dni}")
    public ResponseEntity<DTOUsuario> deleteUserByDNI(@PathVariable String dni)
    {
        return ResponseEntity.ok().body(serviceUsuario.deleteByDni(dni));
    }

    @PutMapping("user/locationByDni/{dni}")
    public ResponseEntity<HashMap<String, String>> updateLocationByDni(@PathVariable String dni, @RequestBody String location)
    {
        HashMap<String, String> map = new HashMap<>();

        DTOUsuario dto = serviceUsuario.updateLocationByDni(dni, location);

        map.put("name:", dto.getName());
        map.put("apellidos:", dto.getApellidos());
        map.put("money:", String.valueOf(dto.getMoney()));
        map.put("location:",location);

        return ResponseEntity.ok().body(map);
    }

    @PutMapping("user/phoneByDni/{dni}")
    public ResponseEntity<DTOUsuario> updatePhoneByDni(@PathVariable String dni, @RequestBody String phone)
    {
        return ResponseEntity.ok().body(serviceUsuario.updatePhoneByDni(dni, phone));
    }

    @PutMapping("user/insertByDni/{dni}")
    public ResponseEntity<HashMap<String, String>> updateMoneyByDni(@PathVariable String dni, @RequestBody Double money)
    {
        HashMap<String, String> map = new HashMap<>();

        DTOUsuario dto = serviceUsuario.updateMoneyByDni(dni,money);

        map.put("name:", dto.getName());
        map.put("apellidos:",dto.getApellidos());
        map.put("money:",String.valueOf(dto.getMoney()));
        map.put("INFO:", "Su Cuenta ha incrementado en una cantidad de: " +money);

        return ResponseEntity.ok().body(map);
    }

}
