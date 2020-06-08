-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `idPedido` int NOT NULL AUTO_INCREMENT,
  `totalpago` decimal(10,0) NOT NULL,
  `fechacompra` datetime NOT NULL,
  `codigopedido` varchar(45) NOT NULL,
  `Usuario_idUsuario` int NOT NULL,
  PRIMARY KEY (`idPedido`),
  KEY `fk_Pedido_Usuario1_idx` (`Usuario_idUsuario`),
  CONSTRAINT `fk_Pedido_Usuario1` FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,20,'2020-08-07 00:00:00','PE20',2),(2,30,'2020-08-09 00:00:00','PE21',2),(3,4,'2020-06-08 04:22:49','PE1512011',2),(7,4,'2020-06-08 04:36:30','PE1512011',2),(8,7,'2020-06-08 04:37:19','PE1512021',2);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_has_producto`
--

DROP TABLE IF EXISTS `pedido_has_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_has_producto` (
  `Pedido_idPedido` int NOT NULL,
  `Producto_idProducto` int NOT NULL,
  PRIMARY KEY (`Pedido_idPedido`,`Producto_idProducto`),
  KEY `fk_Pedido_has_Producto_Producto1_idx` (`Producto_idProducto`),
  KEY `fk_Pedido_has_Producto_Pedido1_idx` (`Pedido_idPedido`),
  CONSTRAINT `fk_Pedido_has_Producto_Pedido1` FOREIGN KEY (`Pedido_idPedido`) REFERENCES `pedido` (`idPedido`),
  CONSTRAINT `fk_Pedido_has_Producto_Producto1` FOREIGN KEY (`Producto_idProducto`) REFERENCES `producto` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_has_producto`
--

LOCK TABLES `pedido_has_producto` WRITE;
/*!40000 ALTER TABLE `pedido_has_producto` DISABLE KEYS */;
INSERT INTO `pedido_has_producto` VALUES (3,1),(7,1),(8,1),(8,5);
/*!40000 ALTER TABLE `pedido_has_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `idProducto` int NOT NULL AUTO_INCREMENT,
  `codigoproducto` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `precio` decimal(10,4) NOT NULL,
  `stock` int NOT NULL,
  `drescripcion` varchar(45) NOT NULL,
  `foto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'p4p','papas',4.0000,30,'grandes y peque√±as','papas.jpg'),(2,'t0m','tomate',4.0000,50,'chileno','tomates.jpg'),(3,'ve1','cerveza pilsen',2.0000,700,'fa','pilsen.jpg'),(4,'ce2','cerveza cristal',1.0000,200,'qwe','cristal.jpg'),(5,'ce3','cerveza brhama',3.2100,20,'gaaa','brahma.jpg'),(6,'g4a','gaseosa coca cola',4.0000,30,'solo heladas','cocacola.jpg'),(7,'g4k','gaseosa inka kola',5.3000,300,'solo para caseros','inkakola.jpg'),(8,'ron2','ron cartavio',411.0000,13,'ron fino','roncartavio.jpg'),(9,'vinoq','vino queirolo',33.3300,20,'se vende solo en caja','queirolo.jpg'),(10,'cheto','cheetos',0.5500,300,'4 m√°ximo por cliente','chetos.jpg'),(11,'cuaderno','cuaderno A4 Standford',4.5000,300,'maximo 2 por cliente','cuaderno1.jpg'),(12,'balon','pelota de futbol',33.4000,50,'balon original del mundial 2010','balon.jpg'),(13,'cam1AL','camiseta de alianza ',55.5400,300,'camisetas original del club','alianza.jpg'),(14,'cam1U','camista de universitario',12.2000,33,'camiseta casi original del club','u.jpg');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idRol` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (4,'invitado'),(5,'registrado'),(6,'gestor'),(7,'administrador');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
INSERT INTO `spring_session` VALUES ('b2796710-5482-4d30-befe-fbf534f3439a','aa1a956a-8c05-4312-a224-657e33530aef',1591609875907,1591612282861,1800,1591614082861,'l.rengifo@pucp.edu.pe');
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
INSERT INTO `spring_session_attributes` VALUES ('b2796710-5482-4d30-befe-fbf534f3439a','CarritoDeCompras',_binary '¨\Ì\0sr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0\0w\0\0\0\0x'),('b2796710-5482-4d30-befe-fbf534f3439a','org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN',_binary '¨\Ì\0sr\06org.springframework.security.web.csrf.DefaultCsrfTokenZ\Ô∑\»/¢˚\’\0L\0\nheaderNamet\0Ljava/lang/String;L\0\rparameterNameq\0~\0L\0tokenq\0~\0xpt\0X-CSRF-TOKENt\0_csrft\0$3d167014-6e25-44da-b6fd-344bea64e94f'),('b2796710-5482-4d30-befe-fbf534f3439a','SPRING_SECURITY_CONTEXT',_binary '¨\Ì\0sr\0=org.springframework.security.core.context.SecurityContextImpl\0\0\0\0\0\0\0L\0authenticationt\02Lorg/springframework/security/core/Authentication;xpsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0\0L\0credentialst\0Ljava/lang/Object;L\0	principalq\0~\0xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailsq\0~\0xpsr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0\0L\0rolet\0Ljava/lang/String;xpt\0\nregistradoxq\0~\0\rsr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\00:0:0:0:0:0:0:1t\0$b120fb4a-d068-4b2b-a0e8-853fda44af8cpsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiest\0Ljava/util/Set;L\0passwordq\0~\0L\0usernameq\0~\0xpsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0\nsr\0java.util.TreeSet›òPìï\Ìá[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0\0\0xpw\0\0\0q\0~\0xpt\0l.rengifo@pucp.edu.pe'),('b2796710-5482-4d30-befe-fbf534f3439a','usuario',_binary '¨\Ì\0sr\0&com.example.exparcialg2.Entity.Usuario∏\›‹§+	∂\0I\0dniZ\0enableI\0	idusuarioL\0apellidot\0Ljava/lang/String;L\0\ncontrasenaq\0~\0L\0correoq\0~\0L\0idRolt\0$Lcom/example/exparcialg2/Entity/Rol;L\0nombreq\0~\0xpè\Ì7\0\0\0t\0rengifot\0<$2a$10$WFKkrvmQe9fa8J6x2OXhT.JRFXLMO9vR3xqV1KgFQg5o05lnZtXfqt\0l.rengifo@pucp.edu.pesr\0\"com.example.exparcialg2.Entity.Rol/\„˛û]≈éˆ\0I\0idrolL\0nombreq\0~\0xp\0\0\0t\0\nregistradot\0lewis');
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  `apellido` varchar(40) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `contrasena` varchar(100) NOT NULL,
  `Rol_idRol` int DEFAULT NULL,
  `enable` tinyint NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_Usuario_Rol_idx` (`Rol_idRol`),
  CONSTRAINT `fk_Usuario_Rol` FOREIGN KEY (`Rol_idRol`) REFERENCES `rol` (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Cristiano','Ronald','66778899','admin','$2y$12$Xnn8mgT28z48PoMuHNWgSOwYooa61n2ZUuqgjiXEgacY4c7FkJCwu',7,1),(2,'eduardo','sanchez','76542309','a20122509@pucp.edu.pe','$2y$12$8Vih8MRddbqIMTsKhqMA8eAm7Q8n/9gWpbkFJ7pyhcamOJ2UMNFe2',5,1),(3,'lewis','hh','98765432','lewis','$2y$12$8Vih8MRddbqIMTsKhqMA8eAm7Q8n/9gWpbkFJ7pyhcamOJ2UMNFe2',6,1),(4,'lewis','rengifo','76541239','l.rengifo@pucp.edu.pe','$2a$10$WFKkrvmQe9fa8J6x2OXhT.JRFXLMO9vR3xqV1KgFQg5o05lnZtXfq',5,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-08  5:32:15
