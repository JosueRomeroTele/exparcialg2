package com.example.exparcialg2.Repository;

import com.example.exparcialg2.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    Usuario findByCorreo(String correo);
    @Query(value = "select u.* from usuario u where u.Rol_idRol = 2", nativeQuery = true)
    List<Usuario> listargestores();
}
