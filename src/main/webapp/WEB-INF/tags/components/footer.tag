<%@ tag import="uppa.project.web.translation.Translator" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components"  %>
<%@ tag import="uppa.project.Global" %>
<%@ tag import="java.text.MessageFormat" %>

<%@ tag pageEncoding="UTF-8" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>
<footer class="footer">
    <div class="content has-text-centered">
        <p>
            ${MessageFormat.format(translator.translate('footer'), Global.APP_NAME)}
        </p>
    </div>
</footer>
