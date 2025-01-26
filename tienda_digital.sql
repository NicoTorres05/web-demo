-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: tienda_digital
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idCategoria` int NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Electrónica','Productos electrónicos como teléfonos, computadoras, etc.'),(2,'Hogar','Artículos para el hogar, muebles, decoración, etc.'),(3,'Ropa','Ropa para hombres, mujeres y niños.');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `idProducto` int NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` text,
  `precio` decimal(10,2) NOT NULL,
  `idCategoria` int DEFAULT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `idCategoria` (`idCategoria`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Smartphone Samsung Galaxy','Teléfono móvil con pantalla AMOLED y 128 GB de almacenamiento.',499.99,1),(2,'Laptop HP','Computadora portátil con procesador Intel i5 y 8 GB de RAM.',799.99,1),(3,'Silla de oficina','Silla ergonómica para oficina con ajuste de altura.',89.99,2),(4,'Camiseta Nike','Camiseta deportiva de algodón para hombres.',19.99,3),(5,'Pantalones Levi\'s','Pantalones de mezclilla para hombres, talla 32.',49.99,3);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` varchar(50) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Juan Pérez','juan@example.com','password123','admin'),(2,'Maria González','maria@example.com','password456','cliente'),(3,'Carlos López','carlos@example.com','password789','cliente');
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

-- Dump completed on 2025-01-26 21:19:38
