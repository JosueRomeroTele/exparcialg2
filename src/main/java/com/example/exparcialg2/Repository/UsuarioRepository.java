package com.example.exparcialg2.Repository;

import com.example.exparcialg2.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.query.Procedure;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    Usuario findByCorreo(String correo);

    Usuario findByIdRol(int idrol);

    @Query(value = "select u.* from usuario u where u.Rol_idRol = 6", nativeQuery = true)
    List<Usuario> listargestores();

    @Transactional
    @Modifying
    @Query(value="UPDATE usuario u SET u.contrasena = :pass WHERE (u.idUsuario = :usuarioUd)",nativeQuery=true)
    void actualizarContrasenaUsuario(@Param("pass") String pass, @Param("usuarioUd") int idusuario );


    @Procedure(name = "crearusuario")
    void crearusuariosp(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("dni") Integer dni, @Param("correo") String correo,
                         @Param("contrasena") String contrasena, @Param("rol") Integer rol, @Param("enable") boolean enable);



}
