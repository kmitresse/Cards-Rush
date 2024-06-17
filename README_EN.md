# Cards Rush - Web Development Project - 2024

Version française disponible [ici](README.md)

## Authors :

- [Kevin MITRESSÉ](http://kmitresse.free.fr)
- [Lucàs VABRE](https://portfolio-lucasvbr.vercel.app/)

## Installation

To be able to run the project, you need to have docker and maven installed on your machine.

### Installation of docker desktop

To install docker desktop, go to the official docker website and download the version corresponding to your operating system.

Link: [Docker Desktop](https://www.docker.com/products/docker-desktop)

### Installation of maven

To install maven, go to the official maven website and download the version corresponding to your operating system.

Link to an installation tutorial: [Maven](https://www.baeldung.com/install-maven-on-windows-linux-mac)

### Additional prerequisites (only for windows)

On windows, you need to have installed a bash terminal system like git bash or windows terminal.

`Recommended` Link to git bash (Easy to use and groups all bash commands): [Git Bash](https://git-scm.com/downloads)

Link to windows terminal: [Windows Terminal](https://www.microsoft.com/fr-fr/p/windows-terminal/9n0dx20hk701?activetab=pivot:overviewtab)

### Project installation

Once all the tools are installed.
Run the following commands to install the project:

From a bash terminal (or git bash), go to the root of the project and run the following commands:

```` bash

# Create the .sql scripts
./mysql/build.sh

# Launch the docker container containing the database
# project_devweb is the name of the project, you can change it as you wish
# remove the -p project_devweb command if you do not want to name the project, it will default to the name of the folder containing the project
docker-compose -p cardsRush up -d

# Install maven dependencies
mvn clean package
mvn install
````
## Launch of the project

From IntelliJ, open the `file > project structure` tab and check that the following artifacts are present:
- project: war
- project: war exploded

![Onglet project_structure.png](readmeTools/project_structure.png)

We use tomcat to launch our project.

On IntelliJ, you can add a Tomcat configuration from the "edit run configurations" tab, then by clicking on the "+" button at the top left of the launch window.

Edit the configuration as follows (the port used for Tomcat is 8080, but you can change it if you wish or if it is already used by another service on your machine):
![Configuration Tomcat.png](readmeTools/tomcat_configuration.png)
![Configuration Tomcat2.png](readmeTools/tomcat_deployment.png)

Finally, start your docker container containing the database and launch the application from your browser.

## Multiplayer games

Cards Rush is a multiplayer game. To play with your friends locally, you must be connected to the same network (same wifi, same connection sharing, etc...).

The link to use to access the game is as follows: `http://ip:8080/cardsrush_war_exploded/` (ip being the ip address of the machine hosting the Tomcat server).

As the application is not yet hosted online, it is necessary to launch the application on a machine and share the ip address of this machine so that other players can connect.


## Potential improvements to consider:

- Hosting the game online.
- Adding a chat for multiplayer games when the application is hosted online.


## Potentielles améliorations à prévoir:

- Hebergement du jeu en ligne.
- Ajout d'un chat pour les parties multijoueurs lorsque l'application sera hébergée en ligne.
