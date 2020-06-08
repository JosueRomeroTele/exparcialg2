DELIMITER $$
create procedure crearusuario(in nombre	varchar(40),in apellido varchar(40),in dni varchar(8),in correo varchar(50),in contrasena varchar(100),in Rol_idRol int,in ena tinyint)
begin
INSERT INTO `mydb`.`usuario` (`nombre`, `apellido`, `dni`, `correo`, `contrasena`, `Rol_idRol`, `enable`) VALUES (nombre, apellido, dni, correo, contrasena, Rol_idRol, ena);
end $$

DELIMITER ;;