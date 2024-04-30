<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>


<layout:base title="Règles du jeu">
    <component:hero>
        <div class="columns">
            <div class="column">
                <component:card title="Règles du jeu">
                    <div class="content">
                        <div class="is-flex is-justify-content-center">
                            <img class="py-5" src="${pageContext.request.contextPath}/static/img/CardsRushLogoBlack.svg"/>
                        </div>
                        <p>
                            Card Rush est un jeu de rapidité multijoueur en ligne.<br>
                            Chaque joueur possède un jeu de carte identique mélangé aléatoirement. Au centre du plateau un jeu de carte
                            similaire.<br>
                            Votre objectif si vous l'acceptez, identifier les similitudes entre votre main et celle du plateau le plus
                            rapidement possible.
                            Plusieurs choix sont possibles:
                        </p>
                        <ul>
                            <li>Les cartes comparées sont identiques</li>
                            <li>Les cartes comparées ont la même couleur mais pas la même valeur</li>
                            <li>Les cartes comparées ont la même valeur mais pas la même couleur</li>
                            <li>Les cartes comparées sont totalement différentes</li>
                        </ul>
                        <h2 class="title is-5">Modes de difficultés</h2>
                        <p>Deux modes de difficultés s'offrent à vous :</p>
                        <ul>
                            <li><p>Le mode <span class="tag is-light is-medium is-primary">facile</span>: Vous ne devez effectuer des
                                comparaisons uniquement entre voter main et celle du plateau.</p>
                                <p>
                                    Exemple : <br>
                                    Vous : 4 de pique <br>
                                    Plateau : 4 de trèfle <br>
                                    La réponse à selectionner est "Même valeur".
                                </p>
                            </li>
                            <li><p>Le mode <span class="tag is-light is-medium is-primary">difficile</span>: Vous devez effectuer des
                                comparaisons avec les mains de chaque joueur et opter pour la réponse correspondant aux plus grand nombre
                                de joueur</p>
                                <p>
                                    Exemple: (4 joueurs) <br>
                                    Joueur 1 : 4 de pique <br>
                                    Joueur 2 : Roi de carreau <br>
                                    Joueur 3 : 4 de coeur <br>
                                    Joueur 4 : As de pique <br>
                                    Plateau : Roi de pique <br>
                                    La réponse à selectionner est "Même couleur" car elle représente 2 joueurs sur les 4.
                                </p></li>
                        </ul>
                        <h2 >Gestion des scores</h2>
                        <p>Les scores sont définits comme suit:</p>
                        <ul>
                            <li>Bonne réponse : <span class="tag is-success">+2pts</span></li>
                            <li>Réponse partielle : <span class="tag is-success">+1pt</span> (Exemple: la carte est identique mais le joueur a identifié une similitude
                                uniquement sur la couleur (resp. la valeur)
                            </li>
                            <li>Aucune réponse : <span class="tag is-light">+0pt</span></li>
                            <li>Mauvaise réponse : <span class="tag is-danger">-1pt</span></li>
                        </ul>
                        <h2 class="title is-5">Vainqueur de la partie</h2>
                        <p>Le vainqueur d'une partie est celui qui aura accumulé le plus de points, en cas d'égalité, le joueur le plus rapide est déclaré vainqueur.<br>
                            Si l'égalité subsiste, des manches supplémentaires départagerons les joueurs.</p>
                        <h3 class="is-justify-content-centered"> Alors affûtez votre agilité et visez
                            la victoire!</h3>
                        <a href="${pageContext.request.contextPath}/lobby" class="button is-light is-right">
                    <span class="icon">
                        <i class="fa-solid fa-arrow-left"></i>
                    </span>
                            <span>Retour</span>
                        </a>
                    </div>

                </component:card>
            </div>
        </div>
    </component:hero>
</layout:base>
