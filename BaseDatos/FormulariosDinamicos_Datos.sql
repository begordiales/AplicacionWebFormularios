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
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (12,'Departamento 1','2022-05-18',NULL);
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `plantillas`
--

LOCK TABLES `plantillas` WRITE;
/*!40000 ALTER TABLE `plantillas` DISABLE KEYS */;
INSERT INTO `plantillas` VALUES (1,'PLANTILLA TITULO','Plantilla que mostrará el titulo del formulario','01',0,'2022-05-19',NULL),(2,'PLANTILLA SUBTITULO','Plantilla que mostrara el subtitulo del formulario','02',0,'2022-05-19',NULL),(3,'DATOS PERSONALES','','03',2,'2022-05-19',NULL),(4,'CLAÚSULA DE PROTECCIÓN DE DATOS',NULL,'04',0,'2022-05-19',NULL),(5,'PLANTILLA ENCUESTA DE SATISFACCIÓN',NULL,'05',0,'2022-05-19',NULL),(6,'ADJUNTAR DOCUMENTACIÓN',NULL,'08',1,'2022-05-19',NULL);
/*!40000 ALTER TABLE `plantillas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `plantillas_campos`
--

LOCK TABLES `plantillas_campos` WRITE;
/*!40000 ALTER TABLE `plantillas_campos` DISABLE KEYS */;
INSERT INTO `plantillas_campos` VALUES (1,1,'TITULO','Título',0,250,NULL,1,6,NULL,NULL,NULL,NULL,NULL,'2022-05-19',NULL),(2,3,'SEXO','Sexo',1,1,NULL,4,8,NULL,NULL,NULL,NULL,NULL,'2022-05-19',NULL),(3,3,'PERMISO','Permiso conducir',0,1,NULL,5,9,NULL,NULL,NULL,NULL,NULL,'2022-05-19',NULL),(4,3,'NOMBRE','Nombre',1,250,NULL,3,3,NULL,NULL,NULL,NULL,NULL,'2022-05-19',NULL),(5,3,'TIPODOC','Tipo documento',1,1,NULL,1,7,NULL,NULL,NULL,NULL,NULL,'2022-05-19',NULL),(6,3,'DOCUMENTO','NIF/NIE',1,9,NULL,2,3,1,NULL,NULL,NULL,NULL,'2022-05-19',NULL),(10,4,'RESPONSABLE','RESPONSABLE del tratamiento',1,4000,NULL,1,6,NULL,'El respnsable del tratamiento es el grupo de empresas....',NULL,NULL,NULL,'2022-05-21',NULL),(11,4,'FINALIDAD','FINALIDAD del tratamiento',1,4000,NULL,2,6,NULL,'La razón principal por la que recabamos su información personal es para...',NULL,NULL,NULL,'2022-05-21',NULL),(12,4,'LEGITIMACION','LEGITIMACION del tratamiento',1,4000,NULL,3,6,NULL,'Ejecución de un contrato, Interés legítimo del Responsable o Consentimiento del interesado.',NULL,NULL,NULL,'2022-05-21',NULL),(13,5,'CONTENIDO','3. ¿Cómo de comprensible y claro es el contenido de la web?',1,1,NULL,3,8,NULL,NULL,NULL,NULL,NULL,'2022-05-21',NULL),(14,5,'NAVEGACION','1. ¿Cómo encuentras la navegación por la web cuando te mueves por ella?',1,1,NULL,1,8,NULL,NULL,NULL,NULL,NULL,'2022-05-21',NULL),(15,5,'DIFICULTAD','2. ¿Cómo de difícil te resultar encontrar en nuestra web la información que buscas?',1,1,NULL,2,8,NULL,NULL,NULL,NULL,NULL,'2022-05-21',NULL),(16,5,'SATISFACCION','4. ¿Cómo de satisfecho/a estás con nuestra web?',1,1,NULL,4,8,NULL,NULL,NULL,NULL,NULL,'2022-05-21',NULL),(17,5,'RECOMENDACION','5. ¿Recomendarías nuestro sitio web a otras personas?',1,1,NULL,5,8,NULL,NULL,NULL,NULL,NULL,'2022-05-21',NULL),(18,6,'Documento1','Documento',0,1,NULL,1,10,NULL,NULL,NULL,NULL,NULL,'2022-09-06',NULL),(19,6,'Documento2','Documento',0,1,NULL,2,10,NULL,NULL,NULL,NULL,NULL,'2022-09-06',NULL);
/*!40000 ALTER TABLE `plantillas_campos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `plantillas_campos_opciones`
--

LOCK TABLES `plantillas_campos_opciones` WRITE;
/*!40000 ALTER TABLE `plantillas_campos_opciones` DISABLE KEYS */;
INSERT INTO `plantillas_campos_opciones` VALUES (1,2,1,'Masculino',1,'2022-05-19',NULL),(2,2,2,'Femenino',2,'2022-05-19',NULL),(3,3,1,'A',1,'2022-05-19',NULL),(4,3,2,'B',2,'2022-05-19',NULL),(5,3,3,'C1',2,'2022-05-19',NULL),(6,5,1,'NIF',1,'2022-05-19',NULL),(7,5,2,'NIE',2,'2022-05-19',NULL),(8,14,1,'Muy sencilla',1,'2022-05-21',NULL),(9,14,2,'Relativamente sencilla',2,'2022-05-21',NULL),(10,14,3,'Normal',3,'2022-05-21',NULL),(11,14,4,'Algo compleja',4,'2022-05-21',NULL),(12,14,5,'Muy compleja',5,'2022-05-21',NULL),(13,15,1,'Muy fácil',1,'2022-05-21',NULL),(14,15,2,'Relativamente fácil',2,'2022-05-21',NULL),(15,15,3,'Normal',3,'2022-05-21',NULL),(16,15,4,'Relativamente difícil',4,'2022-05-22',NULL),(17,15,5,'Muy difícil',5,'2022-05-21',NULL),(18,13,1,'Totalmente comprensible y claro',1,'2022-05-21',NULL),(19,13,2,'Comprensible',2,'2022-05-21',NULL),(20,13,3,'Relativamente comprensible',3,'2022-05-21',NULL),(21,13,4,'Poco comprensible',4,'2022-05-21',NULL),(22,13,5,'Incomprensible',5,'2022-05-21',NULL),(23,16,1,'Muy satisfecho/a',1,'2022-05-21',NULL),(24,16,2,'Satisfecho/a',2,'2022-05-21',NULL),(25,16,3,'Medianamente satisfecho/a',3,'2022-05-21',NULL),(26,16,4,'Insatisfecho/a',4,'2022-05-21',NULL),(27,16,5,'Muy insatisfecho/a',5,'2022-05-21',NULL),(28,17,1,'Sí, definitivamente',1,'2022-05-21',NULL),(29,17,2,'Probablemente sí',2,'2022-05-21',NULL),(30,17,3,'No lo sé',3,'2022-05-21',NULL),(31,17,4,'Probablemente no',4,'2022-05-21',NULL),(32,17,5,'No, para nada',5,'2022-05-21',NULL);
/*!40000 ALTER TABLE `plantillas_campos_opciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipos`
--

LOCK TABLES `tipos` WRITE;
/*!40000 ALTER TABLE `tipos` DISABLE KEYS */;
INSERT INTO `tipos` VALUES (1,'01','TIPOS FORMULARIOS',NULL,'2022-05-19',NULL),(2,'02','TIPOS CAMPOS',NULL,'2022-05-19',NULL);
/*!40000 ALTER TABLE `tipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `validaciones`
--

LOCK TABLES `validaciones` WRITE;
/*!40000 ALTER TABLE `validaciones` DISABLE KEYS */;
INSERT INTO `validaciones` VALUES (1,'validarNIF',NULL,'validarNIF','01');
/*!40000 ALTER TABLE `validaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `valores_tipos`
--

LOCK TABLES `valores_tipos` WRITE;
/*!40000 ALTER TABLE `valores_tipos` DISABLE KEYS */;
INSERT INTO `valores_tipos` VALUES (1,'01',1,'FORMULARIO GENERAL',NULL,'2022-05-19',NULL),(2,'02',1,'ENCUESTAS',NULL,'2022-05-19',NULL),(3,'01',2,'TEXTO',NULL,'2022-05-19',NULL),(4,'02',2,'NUMERICO',NULL,'2022-05-19',NULL),(5,'03',2,'FECHA',NULL,'2022-05-19',NULL),(6,'04',2,'LITERAL',NULL,'2022-05-19',NULL),(7,'05',2,'SELECT',NULL,'2022-05-19',NULL),(8,'06',2,'RADIO',NULL,'2022-05-19',NULL),(9,'07',2,'CHECK',NULL,'2022-05-19',NULL),(10,'08',2,'FILE',NULL,'2022-05-19',NULL);
/*!40000 ALTER TABLE `valores_tipos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-12  9:29:09
