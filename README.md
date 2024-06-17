# Cards Rush - Projet Développement Web - 2024

English version available [here](README_EN.md)

## Auteurs :
- [Kevin MITRESSÉ](http://kmitresse.free.fr)
- [Lucàs VABRE](https://portfolio-lucasvbr.vercel.app/)

## Installation

Pour pouvoir lancer le projet, il vous faut avoir docker et maven d'installé sur votre machine.

### Installation de docker desktop

Pour installer docker desktop, rendez-vous sur le site officiel de docker et téléchargez la version correspondant à votre système d'exploitation.

Lien : [Docker Desktop](https://www.docker.com/products/docker-desktop)

### Installation de maven

Pour installer maven, rendez-vous sur le site officiel de maven et téléchargez la version correspondant à votre système d'exploitation.

Lien vers un tutoriel d'installation : [Maven](https://www.baeldung.com/install-maven-on-windows-linux-mac)

### Prérequis supplémentaires (uniquement pour windows)
Sur windows, il est nécessaire d'avoir installé un système de terminal bash comme git bash ou windows terminal.

`Recommandé` Lien vers git bash (Facile d'utilisation et regroupe toutes les commandes bash) : [Git Bash](https://git-scm.com/downloads)

Lien vers windows terminal : [Windows Terminal](https://www.microsoft.com/fr-fr/p/windows-terminal/9n0dx20hk701?activetab=pivot:overviewtab)

### Installation du projet
Une fois tous les outils installés. 
Lancez les commandes suivantes pour installer le projet :

Depuis un terminal bash (ou git bash), se positionner à la racine du projet puis lancez les commandes suivantes:

```` bash
# Création des scripts .sql
./mysql/build.sh

# Lancement du container docker contenant la base de données
# project_devweb est le nom du projet, vous pouvez le changer à votre guise
# retirer la commande -p project_devweb si vous ne souhaitez pas nommer le projet, il portera par défaut le nom du dossier contenant le projet
docker-compose -p cardsRush up -d

# Installation des dépendances maven
mvn clean package
mvn install
````

## Lancement du projet
Depuis IntelliJ, ouvrir l'onglet `file > project structure` et vérifier que les artefacts suivants sont bien présents :
- project: war
- project: war exploded

![Onglet project_structure.png](readmeTools/project_structure.png)

Nous utilisons tomcat pour lancer notre projet. 

Sur IntelliJ, vous pouvez ajouter une configuration Tomcat depuis l'onglet "edit run configurations", puis en cliquant sur le bouton "+" en haut à gauche de la fenêtre de lancement. 

Editez la configuration comme suit (le port utilisé pour Tomcat conseillé est 8080, mais vous pouvez le changer si vous le souhaitez ou s'il est déjà utilisé par un autre service sur votre machine) :
![Configuration Tomcat.png](readmeTools/tomcat_configuration.png)
![Configuration Tomcat2.png](readmeTools/tomcat_deployment.png)

Enfin, allumez votre container docker contenant la base de données puis lancez l'application depuis votre navigateur.

## Parties multijoueurs

Cards Rush est un jeu multijoueurs. Pour joueur avec vos amis en local, vous devez être connecté au même réseau (même wifi, même partage de connexion, etc...).

Le lien a utiliser pour accéder au jeu est le suivant : `http://ip:8080/cardsrush_war_exploded/` (ip étant l'adresse ip de la machine hébergeant le serveur Tomcat).

L'application n'étant pas encore hébérgée en ligne, il est nécessaire de lancer l'application sur une machine et de partager l'adresse ip de cette machine pour que les autres joueurs puissent se connecter.

## Potentielles améliorations à prévoir:

- Hebergement du jeu en ligne.
- Ajout d'un chat pour les parties multijoueurs lorsque l'application sera hébergée en ligne.
