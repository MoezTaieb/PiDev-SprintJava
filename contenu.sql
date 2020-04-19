-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 13 avr. 2020 à 18:47
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pidev2020`
--





CREATE TABLE `mauvais_mots` (
  `id` int(11) NOT NULL,
  `mots` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `mauvais_mots`
--

INSERT INTO `mauvais_mots` (`id`, `mots`) VALUES
(1, 'f**k');

-- --------------------------------------------------------
-- --------------------------------------------------------

--
-- Structure de la table `msg`
--

CREATE TABLE `msg` (
  `id` int(11) NOT NULL,
  `emetteur_id` int(11) DEFAULT NULL,
  `recepteur_id` int(11) DEFAULT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `body` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `piece` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lu` int(11) NOT NULL,
  `DateEnvoi` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `msg`
--



-- --------------------------------------------------------


-- --------------------------------------------------------

--
-- Structure de la table `affectation`
--

DROP TABLE IF EXISTS `affectation`;
CREATE TABLE IF NOT EXISTS `affectation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `camp_id` int(11) DEFAULT NULL,
  `service_id` int(11) DEFAULT NULL,
  `remarqueAffectation` longtext COLLATE utf8_unicode_ci NOT NULL,
  `dateAffectation` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_F4DD61D377075ABB` (`camp_id`),
  KEY `IDX_F4DD61D3ED5CA9E6` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `affectations`
--

DROP TABLE IF EXISTS `affectations`;
CREATE TABLE IF NOT EXISTS `affectations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `remarque` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `affectations`
--

INSERT INTO `affectations` (`id`, `remarque`, `date`) VALUES
(2, 'aaaaa', '2015-01-01'),
(3, 'mm', '2015-01-01');

-- --------------------------------------------------------

--
-- Structure de la table `annonce`
--

DROP TABLE IF EXISTS `annonce`;
CREATE TABLE IF NOT EXISTS `annonce` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `posteur_id` int(11) DEFAULT NULL,
  `titreAnnonce` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descriptionAnnonce` longtext COLLATE utf8_unicode_ci NOT NULL,
  `imageUrlAnnonce` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateAnnonce` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_F65593E59DDC44B3` (`posteur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `annonce`
--

INSERT INTO `annonce` (`id`, `posteur_id`, `titreAnnonce`, `descriptionAnnonce`, `imageUrlAnnonce`, `dateAnnonce`) VALUES
(1, 4, 'edzedzedzdzdzd', 'dzedzedzedezdzedz', '10861192.jpeg', '2020-02-25 19:28:46'),
(2, 13, 'lk', 'lk', '76962500-hand-writing-conclusion-with-marker-concept-background.jpeg', '2020-02-25 21:59:18'),
(3, 5, 'deded', 'dedzdzedzedzdzdezd', NULL, '2020-02-26 15:27:11'),
(4, 16, 'dzdadzdzadza', 'azdzadd', NULL, '2020-02-27 10:59:02'),
(5, 16, 'zadza', 'dazddadazd', NULL, '2020-02-27 10:59:30'),
(6, 16, 'adzad', 'adadada', NULL, '2020-02-27 10:59:56');

-- --------------------------------------------------------

--
-- Structure de la table `argent`
--

DROP TABLE IF EXISTS `argent`;
CREATE TABLE IF NOT EXISTS `argent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `affectation_id` int(11) DEFAULT NULL,
  `montant` double NOT NULL,
  `demande_id` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_B583FB8E6D0ABA22` (`affectation_id`),
  KEY `IDX_B583FB8E80E95E18` (`demande_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `camp`
--

DROP TABLE IF EXISTS `camp`;
CREATE TABLE IF NOT EXISTS `camp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `responsable_id` int(11) DEFAULT NULL,
  `nomCamp` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adresseCamp` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nbrefugier` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_C194423053C59D72` (`responsable_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `camp`
--

INSERT INTO `camp` (`id`, `responsable_id`, `nomCamp`, `adresseCamp`, `nbrefugier`) VALUES
(2, 4, 'a', 'llzl', 6),
(3, 5, 'sfax', 'sfax', 69),
(4, 7, 'sousse', 'sousse', 65),
(5, 6, 'kairouan', 'kairouan', 45),
(8, 8, 'wwww', 'wwww', 96),
(9, 9, 'xcxcx', 'xcxxc', 63),
(10, 10, 'gffggf', 'fgfgfgf', 70);

-- --------------------------------------------------------

--
-- Structure de la table `categorie_equipment`
--

DROP TABLE IF EXISTS `categorie_equipment`;
CREATE TABLE IF NOT EXISTS `categorie_equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomCategorie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `categorie_equipment`
--

INSERT INTO `categorie_equipment` (`id`, `nomCategorie`) VALUES
(99, 'categorie 2'),
(111, 'Catégorie 1'),
(112, 'categorie 4');

-- --------------------------------------------------------

--
-- Structure de la table `categorie_produit`
--

DROP TABLE IF EXISTS `categorie_produit`;
CREATE TABLE IF NOT EXISTS `categorie_produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomCategorie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `categorie_produit`
--

INSERT INTO `categorie_produit` (`id`, `nomCategorie`) VALUES
(1, 'msd:'),
(2, 'al'),
(3, 'Catégorie 3');

-- --------------------------------------------------------

--
-- Structure de la table `categorie_service`
--

DROP TABLE IF EXISTS `categorie_service`;
CREATE TABLE IF NOT EXISTS `categorie_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomCategorie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `categorie_service`
--

INSERT INTO `categorie_service` (`id`, `nomCategorie`) VALUES
(3, 'coiffeur'),
(4, 'dentiste'),
(5, 'host'),
(7, 'danseur');

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nbModules` int(11) NOT NULL,
  `nbEtudiants` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`id`, `nom`, `nbModules`, `nbEtudiants`) VALUES
(1, '3a', 10, 31),
(3, '2a', 4, 30),
(22, '4 infi', 9, 21);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commentateur_id` int(11) DEFAULT NULL,
  `annonce_id` int(11) DEFAULT NULL,
  `contenuCommentaire` longtext COLLATE utf8_unicode_ci NOT NULL,
  `dateCommentaire` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_67F068BCD7428D7A` (`commentateur_id`),
  KEY `IDX_67F068BC8805AB2F` (`annonce_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

DROP TABLE IF EXISTS `demande`;
CREATE TABLE IF NOT EXISTS `demande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `remarque` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `cas` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `demande`
--

INSERT INTO `demande` (`id`, `remarque`, `etat`, `cas`, `date`) VALUES
(1, 'tedt', 'test', 'test', '2015-01-01');

-- --------------------------------------------------------

--
-- Structure de la table `demandes`
--

DROP TABLE IF EXISTS `demandes`;
CREATE TABLE IF NOT EXISTS `demandes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `camp_id` int(11) DEFAULT NULL,
  `equipment_id` int(11) DEFAULT NULL,
  `service_id` int(11) DEFAULT NULL,
  `argent_id` int(11) DEFAULT NULL,
  `remarqueDemande` longtext COLLATE utf8_unicode_ci NOT NULL,
  `etatDemande` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `casDemande` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateDemande` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_BD940CBB77075ABB` (`camp_id`),
  KEY `IDX_BD940CBB517FE9FE` (`equipment_id`),
  KEY `IDX_BD940CBBED5CA9E6` (`service_id`),
  KEY `IDX_BD940CBBE1319E3` (`argent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
CREATE TABLE IF NOT EXISTS `equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `affectation_id` int(11) DEFAULT NULL,
  `evenement_id` int(11) DEFAULT NULL,
  `nomEquipment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `etatEquipment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateDonEquipment` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `CategorieEquipment_id` int(11) DEFAULT NULL,
  `nbEquipment` int(11) NOT NULL,
  `demandes_id` int(11) DEFAULT NULL,
  `idU` int(11) DEFAULT NULL,
  `nomCategorie` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_D338D583FD02F13` (`evenement_id`),
  KEY `IDX_D338D5835FC58329` (`CategorieEquipment_id`),
  KEY `IDX_D338D5836D0ABA22` (`affectation_id`),
  KEY `IDX_D338D583F49DCC2D` (`demandes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `equipment`
--

INSERT INTO `equipment` (`id`, `affectation_id`, `evenement_id`, `nomEquipment`, `etatEquipment`, `dateDonEquipment`, `CategorieEquipment_id`, `nbEquipment`, `demandes_id`, `idU`, `nomCategorie`) VALUES
(1, NULL, NULL, 'tetsttt', 'recuu', '2020-03-31 22:00:00', 112, 3, NULL, NULL, 'categorie 3'),
(3, NULL, NULL, 'equipment 2', 'recu', '2020-04-01 22:00:00', 112, 3, NULL, NULL, 'categorie 4'),
(144, NULL, NULL, 'table', 'En Cours', '2020-04-03 00:14:30', 112, 3, NULL, NULL, 'categorie 3'),
(145, NULL, NULL, 'mekla', 'En Cours', '2020-04-03 00:28:36', 99, 5, NULL, NULL, 'categorie 2'),
(151, NULL, NULL, 'equipment 5', 'En Cours', '2020-04-11 04:26:34', 111, 3, NULL, NULL, 'Catégorie 1'),
(152, NULL, NULL, 'user', 'En Cours', '2020-04-12 23:13:10', 99, 5, NULL, 1, 'categorie 2'),
(153, NULL, NULL, 'uu', 'En Cours', '2020-04-12 23:25:39', 99, 5, NULL, 1, 'categorie 2');

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomEvenement` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lieuEvenement` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateEvenement` datetime NOT NULL,
  `nombreMaxParticipant` int(11) NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_produit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_B26681EF7384557` (`id_produit`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id`, `nomEvenement`, `lieuEvenement`, `dateEvenement`, `nombreMaxParticipant`, `image`, `id_produit`) VALUES
(2, 'mmm', 'kk', '2021-03-01 00:12:00', 12, 'ef82a924f5b660b228a53b854f3b2486.jpeg', NULL),
(3, 'ertretret', 'ertret', '2015-01-01 00:00:00', 25, '0d770857a5950226ecc0033cec3bd7fd.jpeg', 5),
(4, 'moez', 'kdqjkqdjkds', '2021-01-01 00:00:00', 52, 'abc3969d8bd4f36f0030a506ab6cbaf8.png', 5);

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

DROP TABLE IF EXISTS `fos_user`;
CREATE TABLE IF NOT EXISTS `fos_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
   `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `charge_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cinUser` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nomUser` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prenomUser` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresseUser` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telUser` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `imageUrlUser` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeUser` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `resetCode` int(11) NOT NULL DEFAULT 0,
  `resetNumAttempts` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `cinUser`, `nomUser`, `prenomUser`, `adresseUser`, `telUser`, `imageUrlUser`, `typeUser`, `charge_id`) VALUES
(4, 'admin', 'admin', 'mohamedmibe.louhichi@esprit.tn', 'mohamedmibe.louhichi@esprit.tn', 1, NULL, '$2y$13$8H3uDGiDq9b.N02Y9ZvMUOk4pcnE5O7uysaLThsOiW.5NdXxOVAaC', '2020-03-05 10:22:50', NULL, NULL, 'a:2:{i:0;s:10:\" ROLE_USER\";i:1;s:10:\"ROLE_ADMIN\";}', '118022222', 'admin', 'admin', 'admin', '53418774', NULL, NULL, NULL),
(5, 'user', 'user', 'amin@amin.com', 'amin@amin.com', 1, NULL, '$2y$13$dnRvqXKzBHCzCa9UQHbN8uM3gKj9dGVUihStHDjEZ1ASLaDc5sxve', '2020-02-27 08:50:00', NULL, NULL, 'a:1:{i:0;s:10:\" ROLE_USER\";}', 'user', 'user', 'user', 'user', 'user', NULL, NULL, NULL),
(6, 'usera', 'usera', 'amin@amin.zcom', 'amin@amin.zcom', 1, NULL, '$2y$13$vfrxLVZw8EtfwVfZB.W2l.Mclli6c3cr/BYyXRxpUZ.oNjpSME4eW', '2020-02-16 21:05:35', NULL, NULL, 'a:1:{i:0;s:10:\" ROLE_USER\";}', 'aa', 'users', 'users', 'aaa', 'aa', NULL, NULL, NULL),
(7, 'achref', 'achref', 'acherf@hj.vom', 'acherf@hj.vom', 1, NULL, '$2y$13$B2dBYueAkOEFzmNNnW8PIuiNje0fQx0DepC3x1/MD4.nZNgnlWGCO', '2020-02-19 23:05:58', NULL, NULL, 'a:1:{i:0;s:10:\" ROLE_USER\";}', 'achref', 'achref', 'achref', 'achref', 'achref', NULL, NULL, NULL),
(8, 'wassim1', 'wassim1', 'wassim1@wassim1.com', 'wassim1@wassim1.com', 1, NULL, '$2y$13$1QTA9VQ/TftD/Fns6kKqPueJyTrAZvuBOsoBssU6tgiyRo7h7IuUO', '2020-02-25 20:32:46', NULL, NULL, 'a:1:{i:0;s:10:\" ROLE_USER\";}', '9656', 'wassim1', 'wassim1', 'wassim1', '1245', NULL, NULL, NULL),
(9, 'wassim2', 'wassim2', 'wassim2@wassim2.com', 'wassim2@wassim2.com', 1, NULL, '$2y$13$MCu9Tw20BozobPT8zjp.h.5iC.s0Bxtsuj3SXSfTKAcM7Z.vIajBi', '2020-02-25 20:34:04', NULL, NULL, 'a:1:{i:0;s:10:\" ROLE_USER\";}', '78962', 'wassim2', 'wassim2', 'wassim2', '963', NULL, NULL, NULL),
(10, 'wassim3', 'wassim3', 'wassim3@wassim2.com', 'wassim3@wassim2.com', 1, NULL, '$2y$13$xd0cxzS/Z1f5jvKd09UKh.mEkI0wTr6gqw3smlUsJgGBnWpd/mvbu', '2020-02-25 20:35:07', NULL, NULL, 'a:1:{i:0;s:10:\" ROLE_USER\";}', '746332', 'wassim3', 'wassim3', 'wassim3', '1023', NULL, NULL, NULL),
(11, 'wassim4', 'wassim4', 'wassim4@wassim2.com', 'wassim4@wassim2.com', 1, NULL, '$2y$13$es0PVi.r3ZOWE0g0eBU28OFxuKRyYAQ/6696XJqiGGoGwzzRwBVxq', '2020-02-25 20:36:54', NULL, NULL, 'a:1:{i:0;s:10:\" ROLE_USER\";}', '204', 'wassim4', 'wassim4', 'wassim4', '798922', NULL, NULL, NULL),
(12, 'wassim5', 'wassim5', 'wassim5@wassim2.com', 'wassim5@wassim2.com', 1, NULL, '$2y$13$UYdWxp2zGgY2yW2FfJRGmOuYnSfmruvKlW4CbIsHqGitKb7On67Bm', '2020-02-25 20:38:37', NULL, NULL, 'a:1:{i:0;s:10:\" ROLE_USER\";}', '45522', 'wassim5', 'wassim5', 'wassim5', '6655', NULL, NULL, NULL),
(13, 'wassim6', 'wassim6', 'wassim6@wassim2.com', 'wassim6@wassim2.com', 1, NULL, '$2y$13$gH5B8tPDkOdPnD3oeD385eIexs/Pk0BVQpuBFDChUe2hz7ojci4mm', '2020-02-25 20:39:30', NULL, NULL, 'a:1:{i:0;s:10:\" ROLE_USER\";}', '459695', 'wassim6', 'wassim6', 'wassim6', '79892212', NULL, NULL, NULL),
(14, 'amin', 'amin', 'mohamedamine.louhichi@esprit.tn', 'mohamedamine.louhichi@esprit.tn', 1, NULL, '$2y$13$abLjOq4YIvsxIFSjUUsQRuP5xvLAn/rmmTul/4Rk7hzq.LiqKcw5K', '2020-02-25 23:53:37', NULL, NULL, 'a:1:{i:0;s:10:\" ROLE_USER\";}', '459695', 'amin', 'amin', 'dsd', '2222', NULL, NULL, NULL),
(15, 'fedi', 'fedi', 'fedi@fedi.com', 'fedi@fedi.com', 1, NULL, '$2y$13$BB7inNS/b6qqrsZBo.mr/uHPrcPbMOUil/JsX4Ph1zox.g9wlvVGm', '2020-02-27 10:20:24', NULL, NULL, 'a:1:{i:0;s:10:\" ROLE_USER\";}', 'fedi', 'fedi', 'fedi', 'fedi', 'fedi', NULL, NULL, NULL),
(16, 'amine', 'amine', 'aminlouhichi13@gmail.com', 'aminlouhichi13@gmail.com', 1, NULL, '$2y$13$xp36dzqX7/XdJXDee7FS8OTbOAsg3fLjqCN11TnWF0cDRoFgPKVfG', '2020-02-27 10:31:56', NULL, NULL, 'a:2:{i:0;s:10:\" ROLE_USER\";i:1;s:5:\"ADMIN\";}', '55555', 'amine', 'amine', 'amine', '53418774', NULL, NULL, NULL),
(17, 'admin1', 'admin1', 'fedi.lahbib1@esprit.tn', 'fedi.lahbib1@esprit.tn', 1, NULL, '$2y$13$MRzNZxtggFvE/jkslTLnnOBCQXDR9ukRb3F0EA88AjxJd6JlylQx2', '2020-02-27 10:41:13', NULL, NULL, 'a:2:{i:0;s:11:\" ROLE_ADMIN\";i:1;s:10:\"ROLE_ADMIN\";}', '53418774', 'admin1', 'admin1', 'admin1', '53418774', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `invite`
--

DROP TABLE IF EXISTS `invite`;
CREATE TABLE IF NOT EXISTS `invite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `evenement_id` int(11) DEFAULT NULL,
  `nomInvite` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenomInvite` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_C7E210D7FD02F13` (`evenement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emetteur_id` int(11) DEFAULT NULL,
  `destinataire_id` int(11) DEFAULT NULL,
  `contenuMessage` longtext COLLATE utf8_unicode_ci NOT NULL,
  `dateEnvoiMessage` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_B6BD307F79E92E8C` (`emetteur_id`),
  KEY `IDX_B6BD307FA4F84F6E` (`destinataire_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id`, `emetteur_id`, `destinataire_id`, `contenuMessage`, `dateEnvoiMessage`) VALUES
(1, 15, NULL, 'tel:+21652291826 contenu:Avenger 3A20', '2020-02-26 14:02:21');

-- --------------------------------------------------------

--
-- Structure de la table `messagemetadata`
--

DROP TABLE IF EXISTS `messagemetadata`;
CREATE TABLE IF NOT EXISTS `messagemetadata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_read` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_DA67B3AD537A1329` (`message_id`),
  KEY `IDX_DA67B3AD9D1C3019` (`participant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `messagemetadata`
--

INSERT INTO `messagemetadata` (`id`, `message_id`, `participant_id`, `is_read`) VALUES
(1, 1, 6, 0),
(2, 1, 4, 1),
(3, 2, 4, 0),
(4, 2, 5, 1);

-- --------------------------------------------------------

--
-- Structure de la table `message_user`
--

DROP TABLE IF EXISTS `message_user`;
CREATE TABLE IF NOT EXISTS `message_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_3C379668E2904019` (`thread_id`),
  KEY `IDX_3C379668F624B39D` (`sender_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `message_user`
--

INSERT INTO `message_user` (`id`, `thread_id`, `sender_id`, `body`, `created_at`) VALUES
(1, 1, 4, 'frfrefrefrefrefrfrefer', '2020-02-25 19:21:10'),
(2, 2, 5, 'ssssss', '2020-02-26 15:30:48');

-- --------------------------------------------------------

--
-- Structure de la table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
CREATE TABLE IF NOT EXISTS `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contenu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `vue` int(11) NOT NULL DEFAULT '0',
  `userNotifier_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_6000B0D3A7EB6860` (`userNotifier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `notifications`
--

INSERT INTO `notifications` (`id`, `contenu`, `vue`, `userNotifier_id`) VALUES
(1, 'rfrfrfrfrfrfrfrf', 1, 4),
(2, 'rfrfrfrfrfrfrfrf', 1, 5),
(3, 'rfrfrfrfrfrfrfrf', 0, 6),
(4, 'rfrfrfrfrfrfrfrf', 0, 7),
(5, 'ssssssss', 0, 4),
(6, 'ssssssss', 1, 5),
(7, 'ssssssss', 0, 6),
(8, 'ssssssss', 0, 7),
(9, 'deded', 0, 4),
(10, 'deded', 1, 5),
(11, 'deded', 0, 6),
(12, 'deded', 0, 7),
(13, '20/37', 0, 4),
(14, '20/37', 1, 5),
(15, '20/37', 0, 6),
(16, '20/37', 0, 7),
(17, 'frfrfrfref', 0, 4),
(18, 'frfrfrfref', 1, 5),
(19, 'frfrfrfref', 0, 6),
(20, 'frfrfrfref', 0, 7),
(21, 'sssssss', 1, 5),
(22, 'sssss', 0, 4),
(23, 'sssss', 1, 5),
(24, 'sssss', 0, 6),
(25, 'sssss', 0, 7),
(26, 'ssss', 0, 4),
(27, 'ssss', 1, 5),
(28, 'ssss', 0, 6),
(29, 'ssss', 0, 7),
(30, 'xdededzedzedzeddzdze', 0, 4),
(31, 'xdededzedzedzeddzdze', 1, 5),
(32, 'xdededzedzedzeddzdze', 0, 6),
(33, 'xdededzedzedzeddzdze', 0, 7),
(34, 'xdededzedzedzeddzdze', 0, 8),
(35, 'xdededzedzedzeddzdze', 0, 9),
(36, 'xdededzedzedzeddzdze', 0, 10),
(37, 'xdededzedzedzeddzdze', 0, 11),
(38, 'xdededzedzedzeddzdze', 0, 12),
(39, 'xdededzedzedzeddzdze', 0, 13),
(40, 'xdededzedzedzeddzdze', 0, 14),
(41, 'xdededzedzedzeddzdze', 0, 15),
(42, 'dedzdezdzdzdz', 0, 4),
(43, 'dedzdezdzdzdz', 1, 5),
(44, 'dedzdezdzdzdz', 0, 6),
(45, 'dedzdezdzdzdz', 0, 7),
(46, 'dedzdezdzdzdz', 0, 8),
(47, 'dedzdezdzdzdz', 0, 9),
(48, 'dedzdezdzdzdz', 0, 10),
(49, 'dedzdezdzdzdz', 0, 11),
(50, 'dedzdezdzdzdz', 0, 12),
(51, 'dedzdezdzdzdz', 0, 13),
(52, 'dedzdezdzdzdz', 0, 14),
(53, 'dedzdezdzdzdz', 0, 15),
(54, 'ssssssssssssssss', 0, 4),
(55, 'ssssssssssssssss', 0, 5),
(56, 'ssssssssssssssss', 0, 6),
(57, 'ssssssssssssssss', 0, 7),
(58, 'ssssssssssssssss', 0, 8),
(59, 'ssssssssssssssss', 0, 9),
(60, 'ssssssssssssssss', 0, 10),
(61, 'ssssssssssssssss', 0, 11),
(62, 'ssssssssssssssss', 0, 12),
(63, 'ssssssssssssssss', 0, 13),
(64, 'ssssssssssssssss', 0, 14),
(65, 'ssssssssssssssss', 0, 15),
(66, 'ssssssssssssssss', 0, 16),
(67, 'ssssssssssssssss', 0, 17);

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

DROP TABLE IF EXISTS `participation`;
CREATE TABLE IF NOT EXISTS `participation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `participant_id` int(11) DEFAULT NULL,
  `evenement_id` int(11) DEFAULT NULL,
  `dateParticipation` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_AB55E24F9D1C3019` (`participant_id`),
  KEY `IDX_AB55E24FFD02F13` (`evenement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `participation`
--

INSERT INTO `participation` (`id`, `participant_id`, `evenement_id`, `dateParticipation`) VALUES
(9, 17, 3, '2020-02-27 10:49:45');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categorie_id` int(11) DEFAULT NULL,
  `nomProduit` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prixProduit` double NOT NULL,
  `qteProduit` int(11) NOT NULL,
  `descriptionProduit` longtext COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_29A5EC27BCF5E72D` (`categorie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `categorie_id`, `nomProduit`, `prixProduit`, `qteProduit`, `descriptionProduit`, `photo`) VALUES
(3, 1, 'Produit 2', 2, 3, 'aeaze', '00404ec50efcc070c1bf3df362848452.jpeg'),
(4, 2, 'P', 23, 2, 'zae', '02ae8258ef6fb3d88c4e12b62248e227.jpeg'),
(5, 3, 'llll', 32, 20, 'zaeaze', 'd106f97ff7aeb1842b1400cab3c73059.jpeg'),
(7, 2, 'T-shirt', 20, 20, 'eazeaze', '48927bde585588b4ffbf196b0a9eccc2.png'),
(8, 1, 'ezaeaze', 23, 23, 'zaeaze', '5eff5021fc7d40a0ceab7b3ceb51522d.png'),
(9, 1, 'pazeaze', 23, 23, 'eazeaz', '5bc47e08c23ae22029eaaefa2a923557.png'),
(10, 1, 'papap', 111, 11, 'llz', '2d2567b774e2a84ac1a4254ee9f70ee8.jpeg'),
(11, 1, 'lalal', 458, 7, 'aùmek ok k', 'd2599f17596f3ff1a440a3ae128696fd.jpeg'),
(12, 1, 'aeaze', 23, 32, 'eaeaze', '94a8e0bf8741d1073ebeab05d2c1ccc5.png'),
(13, 1, 'Paaezea', 22, 0, 'eazea', '2a9d9eb891e6a3e322c5fda70d9a6571.png');

-- --------------------------------------------------------

--
-- Structure de la table `produitcomment`
--

DROP TABLE IF EXISTS `produitcomment`;
CREATE TABLE IF NOT EXISTS `produitcomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `produit_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` longtext COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_9A157ED5F347EFB` (`produit_id`),
  KEY `IDX_9A157ED5A76ED395` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `produitcomment`
--

INSERT INTO `produitcomment` (`id`, `produit_id`, `user_id`, `content`) VALUES
(1, NULL, 14, 'kjlkjlj'),
(2, 3, 15, 'test pour le user fedi\r\n'),
(3, 3, 16, 'test pour le user amine'),
(4, 3, 16, 'kekz');

-- --------------------------------------------------------

--
-- Structure de la table `produitlike`
--

DROP TABLE IF EXISTS `produitlike`;
CREATE TABLE IF NOT EXISTS `produitlike` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `produit_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_A0BC2E30F347EFB` (`produit_id`),
  KEY `IDX_A0BC2E30A76ED395` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `produitlike`
--

INSERT INTO `produitlike` (`id`, `produit_id`, `user_id`) VALUES
(2, 3, 15),
(4, 3, 16);

-- --------------------------------------------------------

--
-- Structure de la table `recommandation`
--

DROP TABLE IF EXISTS `recommandation`;
CREATE TABLE IF NOT EXISTS `recommandation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recommandeur_id` int(11) DEFAULT NULL,
  `annonce_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_C7782A28E76C5273` (`recommandeur_id`),
  KEY `IDX_C7782A288805AB2F` (`annonce_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `refugie`
--

DROP TABLE IF EXISTS `refugie`;
CREATE TABLE IF NOT EXISTS `refugie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `camp_id` int(11) DEFAULT NULL,
  `nomRefugie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenomRefugie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adresseRefugie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `telRefugie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `numassportRefugie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nationaliteRefugie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_304ECA4A77075ABB` (`camp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `refugie`
--

INSERT INTO `refugie` (`id`, `camp_id`, `nomRefugie`, `prenomRefugie`, `adresseRefugie`, `telRefugie`, `numassportRefugie`, `nationaliteRefugie`, `image`) VALUES
(1, NULL, 'zolml', 'llzml', 'lzmlmzl', '245', '545', 'kkd', '5d388299238cb85ff7907b9f5728b2ef.jpeg'),
(2, 2, 'bilel', 'z', 'z', '5', '5', 'ezr', '1d550fb449c9452fea49fdf9beda1a25.jpeg'),
(3, 2, 'achref', 'achref', 'lzmlmzl', '245', '5', 'tunis', '19f2b276982bb88e549efda8058aac55.jpeg'),
(4, 3, 'amin', 'ami', 'z', '445', '3223', 'tunis', '5e244caeb05df28727e8c2e5b5992473.jpeg'),
(5, 4, 'wassim6', 'wassim6', 'wwq', '5546', '546564', 'tunis', 'b7d337b47683dc986916a567506a4260.jpeg'),
(6, 5, 'wassim4', 'wassim4', 'wqshg', '5445', '45546', 'tunis', '038cd378bb8f5bc73910cb0ce7088d86.jpeg'),
(7, 5, 'wassim2', 'wassim2', 'wawwa', '0133', '96321', 'tunis', '949c5bcc40ee26e9664b09222d17783c.jpeg'),
(8, 3, 'aymen', 'aymen', 'aymen', '35652', '96', 'tunis', 'fbb1198114759d94979349eb6c107dca.jpeg'),
(9, 3, '5', '5', '5', '55', '55', '55', 'e854a8db40146040ee20e4b110b9884d.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomService` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descriptionService` longtext COLLATE utf8_unicode_ci NOT NULL,
  `dateDon` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `categorieService_id` int(11) DEFAULT NULL,
  `affectation_id` int(11) DEFAULT NULL,
  `nomCategorie` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idU` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_E19D9AD242C2042C` (`categorieService_id`),
  KEY `IDX_E19D9AD26D0ABA22` (`affectation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`id`, `nomService`, `descriptionService`, `dateDon`, `categorieService_id`, `affectation_id`, `nomCategorie`, `idU`) VALUES
(81, 'amin', 'mamam', '2020-04-02 22:00:00', 4, NULL, 'dentiste', 1),
(82, 'service 2', 'description du servce 2', '2020-04-11 03:34:10', 7, NULL, 'danseur', NULL),
(83, 'user', 'zlzlz', '2020-04-13 02:53:33', 4, NULL, 'dentiste', 1);

-- --------------------------------------------------------

--
-- Structure de la table `thread`
--

DROP TABLE IF EXISTS `thread`;
CREATE TABLE IF NOT EXISTS `thread` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by_id` int(11) DEFAULT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `is_spam` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_368C49B5B03A8386` (`created_by_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `thread`
--

INSERT INTO `thread` (`id`, `created_by_id`, `subject`, `created_at`, `is_spam`) VALUES
(1, 4, 'usera', '2020-02-25 19:21:10', 0),
(2, 5, 'admin', '2020-02-26 15:30:48', 0);

-- --------------------------------------------------------

--
-- Structure de la table `threadmetadata`
--

DROP TABLE IF EXISTS `threadmetadata`;
CREATE TABLE IF NOT EXISTS `threadmetadata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `last_participant_message_date` datetime DEFAULT NULL,
  `last_message_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_BEF427AEE2904019` (`thread_id`),
  KEY `IDX_BEF427AE9D1C3019` (`participant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `threadmetadata`
--

INSERT INTO `threadmetadata` (`id`, `thread_id`, `participant_id`, `is_deleted`, `last_participant_message_date`, `last_message_date`) VALUES
(1, 1, 6, 0, NULL, '2020-02-25 19:21:10'),
(2, 1, 4, 0, '2020-02-25 19:21:10', NULL),
(3, 2, 4, 0, NULL, '2020-02-26 15:30:48'),
(4, 2, 5, 0, '2020-02-26 15:30:48', NULL);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `affectation`
--
ALTER TABLE `affectation`
  ADD CONSTRAINT `FK_F4DD61D377075ABB` FOREIGN KEY (`camp_id`) REFERENCES `camp` (`id`),
  ADD CONSTRAINT `FK_F4DD61D3ED5CA9E6` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`);

--
-- Contraintes pour la table `annonce`
--
ALTER TABLE `annonce`
  ADD CONSTRAINT `FK_F65593E59DDC44B3` FOREIGN KEY (`posteur_id`) REFERENCES `fos_user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `argent`
--
ALTER TABLE `argent`
  ADD CONSTRAINT `FK_B583FB8E6D0ABA22` FOREIGN KEY (`affectation_id`) REFERENCES `affectations` (`id`),
  ADD CONSTRAINT `FK_B583FB8E80E95E18` FOREIGN KEY (`demande_id`) REFERENCES `demande` (`id`);

--
-- Contraintes pour la table `camp`
--
ALTER TABLE `camp`
  ADD CONSTRAINT `FK_C194423053C59D72` FOREIGN KEY (`responsable_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FK_67F068BC8805AB2F` FOREIGN KEY (`annonce_id`) REFERENCES `annonce` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_67F068BCD7428D7A` FOREIGN KEY (`commentateur_id`) REFERENCES `fos_user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `demandes`
--
ALTER TABLE `demandes`
  ADD CONSTRAINT `FK_BD940CBB517FE9FE` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`id`),
  ADD CONSTRAINT `FK_BD940CBB77075ABB` FOREIGN KEY (`camp_id`) REFERENCES `camp` (`id`),
  ADD CONSTRAINT `FK_BD940CBBE1319E3` FOREIGN KEY (`argent_id`) REFERENCES `argent` (`id`),
  ADD CONSTRAINT `FK_BD940CBBED5CA9E6` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`);

--
-- Contraintes pour la table `equipment`
--
ALTER TABLE `equipment`
  ADD CONSTRAINT `FK_D338D5835FC58329` FOREIGN KEY (`CategorieEquipment_id`) REFERENCES `categorie_equipment` (`id`),
  ADD CONSTRAINT `FK_D338D5836D0ABA22` FOREIGN KEY (`affectation_id`) REFERENCES `affectations` (`id`),
  ADD CONSTRAINT `FK_D338D583F49DCC2D` FOREIGN KEY (`demandes_id`) REFERENCES `demande` (`id`),
  ADD CONSTRAINT `FK_D338D583FD02F13` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id`);

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `FK_B26681EF7384557` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id`);

--
-- Contraintes pour la table `invite`
--
ALTER TABLE `invite`
  ADD CONSTRAINT `FK_C7E210D7FD02F13` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_B6BD307F79E92E8C` FOREIGN KEY (`emetteur_id`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_B6BD307FA4F84F6E` FOREIGN KEY (`destinataire_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `messagemetadata`
--
ALTER TABLE `messagemetadata`
  ADD CONSTRAINT `FK_DA67B3AD537A1329` FOREIGN KEY (`message_id`) REFERENCES `message_user` (`id`),
  ADD CONSTRAINT `FK_DA67B3AD9D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `message_user`
--
ALTER TABLE `message_user`
  ADD CONSTRAINT `FK_3C379668E2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  ADD CONSTRAINT `FK_3C379668F624B39D` FOREIGN KEY (`sender_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `FK_6000B0D3A7EB6860` FOREIGN KEY (`userNotifier_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `FK_AB55E24F9D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_AB55E24FFD02F13` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_29A5EC27BCF5E72D` FOREIGN KEY (`categorie_id`) REFERENCES `categorie_produit` (`id`);

--
-- Contraintes pour la table `produitcomment`
--
ALTER TABLE `produitcomment`
  ADD CONSTRAINT `FK_9A157ED5A76ED395` FOREIGN KEY (`user_id`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_9A157ED5F347EFB` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`);

--
-- Contraintes pour la table `produitlike`
--
ALTER TABLE `produitlike`
  ADD CONSTRAINT `FK_A0BC2E30A76ED395` FOREIGN KEY (`user_id`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_A0BC2E30F347EFB` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`);

--
-- Contraintes pour la table `recommandation`
--
ALTER TABLE `recommandation`
  ADD CONSTRAINT `FK_C7782A288805AB2F` FOREIGN KEY (`annonce_id`) REFERENCES `annonce` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_C7782A28E76C5273` FOREIGN KEY (`recommandeur_id`) REFERENCES `fos_user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `refugie`
--
ALTER TABLE `refugie`
  ADD CONSTRAINT `FK_304ECA4A77075ABB` FOREIGN KEY (`camp_id`) REFERENCES `camp` (`id`);

--
-- Contraintes pour la table `service`
--
ALTER TABLE `service`
  ADD CONSTRAINT `FK_E19D9AD242C2042C` FOREIGN KEY (`categorieService_id`) REFERENCES `categorie_service` (`id`),
  ADD CONSTRAINT `FK_E19D9AD26D0ABA22` FOREIGN KEY (`affectation_id`) REFERENCES `affectations` (`id`);

--
-- Contraintes pour la table `thread`
--
ALTER TABLE `thread`
  ADD CONSTRAINT `FK_368C49B5B03A8386` FOREIGN KEY (`created_by_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `threadmetadata`
--
ALTER TABLE `threadmetadata`
  ADD CONSTRAINT `FK_BEF427AE9D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_BEF427AEE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`);
COMMIT;





CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `reporteur_id` int(11) DEFAULT NULL,
  `annonce_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



--
ALTER TABLE `report`
  ADD CONSTRAINT `FK_C42F778420DBCF25` FOREIGN KEY (`reporteur_id`) REFERENCES `fos_user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_C42F77848805AB2F` FOREIGN KEY (`annonce_id`) REFERENCES `annonce` (`id`) ON DELETE CASCADE;
-- Index pour la table `report`

--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_C42F778420DBCF25` (`reporteur_id`),
  ADD KEY `IDX_C42F77848805AB2F` (`annonce_id`);


ALTER TABLE `report`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=0;

--
-- Index pour la table `msg`
--
ALTER TABLE `msg`
  ADD PRIMARY KEY (`id`),
  ADD KEY `emeteur` (`emetteur_id`),
  ADD KEY `recepeteur` (`recepteur_id`);
--
-- AUTO_INCREMENT pour la table `msg`
--
ALTER TABLE `msg`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Contraintes pour la table `msg`
--
ALTER TABLE `msg`
  ADD CONSTRAINT `emeteur` FOREIGN KEY (`emetteur_id`) REFERENCES `fos_user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `recepeteur` FOREIGN KEY (`recepteur_id`) REFERENCES `fos_user` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
