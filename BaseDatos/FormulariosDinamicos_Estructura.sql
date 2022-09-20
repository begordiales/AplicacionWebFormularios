-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: formulariosdinamicos
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamentos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formularios`
--

DROP TABLE IF EXISTS `formularios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formularios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  `iddepartamento` int DEFAULT NULL,
  `idtipoform` int DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_FORM_DPTO_idx` (`iddepartamento`),
  KEY `FK_TIPOFORM_idx` (`idtipoform`),
  CONSTRAINT `FK_FORM_DPTO` FOREIGN KEY (`iddepartamento`) REFERENCES `departamentos` (`id`),
  CONSTRAINT `FK_TIPOFORM` FOREIGN KEY (`idtipoform`) REFERENCES `valores_tipos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formularios_bloques`
--

DROP TABLE IF EXISTS `formularios_bloques`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formularios_bloques` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idform` int DEFAULT NULL,
  `idplantilla` int DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `orden` int DEFAULT NULL,
  `numcolumnas` int NOT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_FB_FORM_idx` (`idform`),
  KEY `FK_FB_PLAN_idx` (`idplantilla`),
  CONSTRAINT `FK_FB_FORM` FOREIGN KEY (`idform`) REFERENCES `formularios` (`id`),
  CONSTRAINT `FK_FB_PLAN` FOREIGN KEY (`idplantilla`) REFERENCES `plantillas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formularios_bloques_campos`
--

DROP TABLE IF EXISTS `formularios_bloques_campos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formularios_bloques_campos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idbloque` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `etiqueta` varchar(100) NOT NULL,
  `idtipocampo` int NOT NULL,
  `obligatorio` tinyint DEFAULT '0',
  `tamano` int DEFAULT NULL,
  `ancho` int DEFAULT NULL,
  `orden` int DEFAULT NULL,
  `idvalidacion` int DEFAULT NULL,
  `valor` varchar(4000) DEFAULT NULL,
  `tamanofichero` int DEFAULT NULL,
  `tiposfichero` varchar(100) DEFAULT NULL,
  `numficheros` int DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_baja` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_BC_BLOQUE_idx` (`idbloque`),
  KEY `FK_BC_VALIDACION_idx` (`idvalidacion`),
  KEY `FK_BC_VT_idx` (`idtipocampo`),
  CONSTRAINT `FK_BC_BLOQUE` FOREIGN KEY (`idbloque`) REFERENCES `formularios_bloques` (`id`),
  CONSTRAINT `FK_BC_VALIDACION` FOREIGN KEY (`idvalidacion`) REFERENCES `validaciones` (`id`),
  CONSTRAINT `FK_BC_VT` FOREIGN KEY (`idtipocampo`) REFERENCES `valores_tipos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formularios_campos_opciones`
--

DROP TABLE IF EXISTS `formularios_campos_opciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formularios_campos_opciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idcampo` int NOT NULL,
  `valor` int NOT NULL,
  `descripcion` varchar(250) NOT NULL,
  `orden` int DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CO_CAMPOS_idx` (`idcampo`),
  CONSTRAINT `FK_CO_CAMPOS` FOREIGN KEY (`idcampo`) REFERENCES `formularios_bloques_campos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formularios_envios`
--

DROP TABLE IF EXISTS `formularios_envios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formularios_envios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idform` int NOT NULL,
  `fecha_envio` datetime NOT NULL,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ENVIOS_FORM_idx` (`idform`),
  CONSTRAINT `FK_ENVIOS_FORM` FOREIGN KEY (`idform`) REFERENCES `formularios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formularios_respuestas`
--

DROP TABLE IF EXISTS `formularios_respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formularios_respuestas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idenvio` int NOT NULL,
  `idcampo` int NOT NULL,
  `respuesta` varchar(4000) DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_RESP_CAMPOS_idx` (`idcampo`),
  KEY `FK_RESP_ENVIOS_idx` (`idenvio`),
  CONSTRAINT `FK_RESP_CAMPOS` FOREIGN KEY (`idcampo`) REFERENCES `formularios_bloques_campos` (`id`),
  CONSTRAINT `FK_RESP_ENVIOS` FOREIGN KEY (`idenvio`) REFERENCES `formularios_envios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plantillas`
--

DROP TABLE IF EXISTS `plantillas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plantillas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `codigo` varchar(10) DEFAULT NULL,
  `num_columnas` int DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plantillas_campos`
--

DROP TABLE IF EXISTS `plantillas_campos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plantillas_campos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idplantilla` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `etiqueta` varchar(250) NOT NULL,
  `obligatorio` tinyint DEFAULT '0',
  `tamano` int DEFAULT NULL,
  `ancho` int DEFAULT NULL,
  `orden` int DEFAULT NULL,
  `idtipocampo` int NOT NULL,
  `idvalidacion` int DEFAULT NULL,
  `valor` varchar(4000) DEFAULT NULL,
  `tamanofichero` int DEFAULT NULL,
  `tiposfichero` varchar(100) DEFAULT NULL,
  `numficheros` int DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PCAMPOS_PLAN_idx` (`idplantilla`),
  KEY `FK_PCAMPOS_VALIDACION_idx` (`idvalidacion`),
  KEY `FK_PCAMPOS_VT_idx` (`idtipocampo`),
  CONSTRAINT `FK_PCAMPOS_PLAN` FOREIGN KEY (`idplantilla`) REFERENCES `plantillas` (`id`),
  CONSTRAINT `FK_PCAMPOS_VALIDACION` FOREIGN KEY (`idvalidacion`) REFERENCES `validaciones` (`id`),
  CONSTRAINT `FK_PCAMPOS_VT` FOREIGN KEY (`idtipocampo`) REFERENCES `valores_tipos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plantillas_campos_opciones`
--

DROP TABLE IF EXISTS `plantillas_campos_opciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plantillas_campos_opciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idplantillacampo` int NOT NULL,
  `valor` int NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `orden` int DEFAULT NULL,
  `fecha_alta` date DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PCAMPOS_OPCIONES_idx` (`idplantillacampo`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos`
--

DROP TABLE IF EXISTS `tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `validaciones`
--

DROP TABLE IF EXISTS `validaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `validaciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `funcion` varchar(200) NOT NULL,
  `codigo` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `valores_tipos`
--

DROP TABLE IF EXISTS `valores_tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valores_tipos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) NOT NULL,
  `idtipo` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_VT_TIPOS_idx` (`idtipo`),
  CONSTRAINT `FK_VT_TIPOS` FOREIGN KEY (`idtipo`) REFERENCES `tipos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-12  9:27:58
