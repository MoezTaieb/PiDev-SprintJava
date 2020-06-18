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
