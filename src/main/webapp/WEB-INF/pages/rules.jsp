<%@ page import="uppa.project.web.translation.Translator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>


<layout:base title="${translator.translate('rules_title')}">
    <component:hero>
        <div class="columns">
            <div class="column">
                <component:card title="${translator.translate('rules_title')}">
                    <div class="content">
                        <div class="is-flex is-justify-content-center">
                            <img class="py-5" src="${pageContext.request.contextPath}/static/img/CardsRushLogoBlack.svg"/>
                        </div>
                        <p>${translator.translate('rules_description')}</p>
                        <ul>
                            <li>${translator.translate('rules_choice_1')}</li>
                            <li>${translator.translate('rules_choice_2')}</li>
                            <li>${translator.translate('rules_choice_3')}</li>
                            <li>${translator.translate('rules_choice_4')}</li>
                        </ul>
                        <h2>${translator.translate('rules_difficulty_title')}</h2>
                        <p>${translator.translate('rules_difficulty_description')}</p>
                        <ul>
                            <li>
                                <p>${translator.translate('rules_difficulty_easy_description')}</p>
                                <p>${translator.translate('rules_difficulty_easy_example')}</p>
                            </li>
                            <li>
                                <p>${translator.translate('rules_difficulty_hard_description')} </p>
                                <p class="has-text-weight-bold is-centered"> ${translator.translate('rules_difficulty_hard_priorities')}</p>
                                <p>${translator.translate('rules_difficulty_hard_priorities_explanation')}</p>
                                <p>${translator.translate('rules_difficulty_hard_example')}</p>
                            </li>
                        </ul>
                        <h2>${translator.translate('rules_score_management_title')}</h2>
                        <p>${translator.translate('rules_score_management_description')}</p>
                        <ul>
                            <li>${translator.translate('rules_score_management_1')}</li>
                            <li>${translator.translate('rules_score_management_2')}</li>
                            <li>${translator.translate('rules_score_management_3')}</li>
                            <li>${translator.translate('rules_score_management_4')}</li>
                            <li>${translator.translate('rules_score_management_5')}</li>
                        </ul>
                        <h2>${translator.translate('rules_winner_title')}</h2>
                        <p>${translator.translate('rules_winner_description')}</p>
                        <h3 class="is-justify-content-centered"> ${translator.translate('rules_end')}</h3>
                        <a href="${pageContext.request.contextPath}/lobby" class="button is-light is-right">
                            <span class="icon">
                                <i class="fa-solid fa-arrow-left"></i>
                            </span>
                            <span>${translator.translate('back')}</span>
                        </a>
                    </div>

                </component:card>
            </div>
        </div>
    </component:hero>
</layout:base>
