<%@ tag import="uppa.project.database.pojo.User" %>
<%@tag description="form/profile" pageEncoding="UTF-8" %>
<%@ tag import="uppa.project.web.translation.Translator" %>
<%@ tag import="java.util.Date" %>
<%@ tag import="java.text.SimpleDateFormat" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>

<form id="profile-form" action="${pageContext.request.contextPath}/profile" method="post">
    <jsp:useBean id="user" class="uppa.project.database.pojo.User" scope="session"/>
    <input id="user-id" type="hidden" name="user-id" value="${user.id}"/>
    <div class="field">
        <label class="label" for="username">${translator.translate('user_username')}</label>
        <div class="control has-icons-left">
            <input id="username" name="username" value="${user.username}" type="text" class="input is-fullwidth" disabled/>
            <span class="icon is-left"><i class="fas fa-user"></i></span>
        </div>
    </div>
    <div class="field">
        <label class="label" for="email">${translator.translate('user_email')}</label>
        <div class="control has-icons-left">
            <input id="old-email" name="oldEmail" type="hidden" value="${user.email}" class="input is-fullwidth" required>
            <input id="email" name="email" type="email" value="${user.email}" class="input is-fullwidth" required>
            <span class="icon is-left"><i class="fas fa-envelope"></i></span>
        </div>
    </div>
    <div class="field">
        <label class="label">${translator.translate('user_password')}</label>
        <a id="change-password" href="#">${translator.translate('user_update_password')}</a>
    </div>
    <div id="old-password-field" class="field" style="display: none">
        <div class="control has-icons-left">
            <input id="old-password" name="oldPassword" placeholder="${translator.translate('user_old_password')}" type="password" class="input is-fullwidth">
            <span class="icon is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>
    <div id="password-field" class="field" style="display: none">
        <div class="control has-icons-left">
            <input id="password" name="password" placeholder="${translator.translate('user_new_password')}" type="password" class="input is-fullwidth">
            <span class="icon is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>
    <div id="repeat-password-field" class="field" style="display: none">
        <div class="control has-icons-left">
            <input id="repeat-password" name="repeat-password" placeholder="${translator.translate('user_confirm_password')}" type="password" class="input is-fullwidth">
            <span class="icon is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>
    <div class="field">
        <label class="label">${translator.translate('user_birthdate')}</label>
        <% Date date = user.getBirth();
            String language = translator.getLanguage();
            SimpleDateFormat sdfDay;
            SimpleDateFormat sdfHour;
            if (language.equals("EN")) {
                sdfDay = new SimpleDateFormat("MM/dd/yyyy");
            } else {
                sdfDay = new SimpleDateFormat("dd/MM/yyyy");
            }

            String day = sdfDay.format(date);
        %>
        <input class="input" type="text" value="<%=day%>" disabled>
    </div>
    <div class="field">
        <label class="label" for="gender">${translator.translate('user_gender')}</label>
        <div class="control has-icons-left">
            <div class="select is-fullwidth">
                <input id="old-gender" name="oldGender" type="hidden" value="${user.gender.name()}" class="input is-fullwidth" required>
                <select name="gender" id="gender" required>
                    <% if (user.getGender() == null) { %>
                        <option selected value="">-- ${translator.translate('user_gender_select_option')} --</option>
                    <% } else { %>
                        <option value="">-- ${translator.translate('user_gender_select_option')} --</option>
                    <% } %>
                    <% if (user.getGender().equals(User.Gender.MALE)) {%>
                        <option selected value="MALE">${translator.translate('user_gender_male')}</option>
                    <% } else { %>
                    <option value="MALE">${translator.translate('user_gender_male')}</option>
                    <% } %>
                    <% if (user.getGender().equals(User.Gender.FEMALE)) {%>
                        <option selected value="FEMALE">${translator.translate('user_gender_female')}</option>
                    <% } else { %>
                        <option value="FEMALE">${translator.translate('user_gender_female')}</option>
                    <% } %>
                    <% if (user.getGender().equals(User.Gender.OTHER)) {%>
                        <option selected value="OTHER">${translator.translate('user_gender_other')}</option>
                    <% } else { %>
                    <option value="OTHER">${translator.translate('user_gender_other')}</option>
                    <% } %>
                </select>
            </div>
            <span class="icon is-left"><i class="fa-solid fa-venus-mars"></i></span>
        </div>
    </div>

    <div class="field">
        <div class="buttons is-justify-content-space-between">
            <a href="${pageContext.request.contextPath}/lobby" class="button is-light is-right">
                    <span class="icon">
                        <i class="fa-solid fa-arrow-left"></i>
                    </span>
            <span>${translator.translate('back')}</span>
            </a>
            <input type="submit" id="modify" class="button is-primary is-light" value="${translator.translate('profile_edit')}">
        </div>
    </div>
</form>
