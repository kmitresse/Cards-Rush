<%@tag description="form/register" pageEncoding="UTF-8" %>
<%@ tag import="uppa.project.web.translation.Translator" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>

<form id="register-form" action="${pageContext.request.contextPath}/register" method="post">
    <div class="field">
        <label class="label" for="username">${translator.translate("user_username")}</label>
        <div class="control has-icons-left">
            <input id="username" name="username" placeholder="John Doe" type="text" class="input is-fullwidth" required>
            <span class="icon is-left"><i class="fas fa-user"></i></span>
        </div>
    </div>

    <div class="field">
        <label class="label" for="email">${translator.translate("user_email")}</label>
        <div class="control has-icons-left">
            <input id="email" name="email" type="email" placeholder="johndoe@exemple.com" class="input is-fullwidth" required>
            <span class="icon is-left"><i class="fas fa-envelope"></i></span>
        </div>
    </div>

    <div class="field">
        <label class="label" for="password">${translator.translate("user_password")}</label>
        <div class="control has-icons-left">
            <input id="password" name="password" placeholder="${translator.translate('user_password')}" type="password" class="input is-fullwidth" required>
            <span class="icon is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>

    <div class="field">
        <label class="label" for="repassword">${translator.translate("user_confirm_password")}</label>
        <div class="control has-icons-left">
            <input id="repassword" name="repassword" placeholder="${translator.translate('user_confirm_password_input')}" type="password" class="input is-fullwidth" required>
            <span class="icon is-left"><i class="fas fa-lock"></i></span>
        </div>
    </div>

    <div class="field">
        <label class="label" for="birth">${translator.translate("user_birthdate")}</label>
        <input class="input is-fullwidth" type="date" id="birth" name="birth" required>
    </div>

    <div class="field">
        <label class="label" for="gender">${translator.translate("user_gender")}</label>
        <div class="control has-icons-left">
            <div class="select is-fullwidth">
                <select name="gender" id="gender" required>
                    <option selected value="">-- ${translator.translate("user_gender_select_option")} --</option>
                    <option value="MALE">${translator.translate("user_gender_male")}</option>
                    <option value="FEMALE">${translator.translate("user_gender_female")}</option>
                    <option value="OTHER">${translator.translate("user_gender_other")}</option>
                </select>
            </div>
            <span class="icon is-left"><i class="fa-solid fa-venus-mars"></i></span>
        </div>
    </div>

    <input type="submit" class="button has-text-white is-primary is-fullwidth" value="${translator.translate("register_submit")}">
</form>

