<%@tag description="form/newGame" pageEncoding="UTF-8" %>

<form id="newGame-form" action="${pageContext.request.contextPath}/main-menu" method="post">
    <div>

    </div>
</form>

<style>
    .notification {
        position: absolute;
        bottom: 0;
        right: 0;
        margin: 1em !important;

        transform: translateY(100%);
        opacity: 0;
        animation: toast 5s ease forwards;
    }

    @keyframes toast {
        0% {
            opacity: 0;
            transform: translateY(100%);
        }
        5% {
            opacity: 1;
            transform: translateY(0);
        }
        95% {
            opacity: 1;
            transform: translateY(0);
        }
        100% {
            opacity: 0;
            transform: translateY(100%);
        }
    }

    @keyframes shake {
        0%, 100% {
            transform: translateX(0);
        }
        10%, 30%, 50%, 70%, 90% {
            transform: translateX(-5px);
        }
        20%, 40%, 60%, 80% {
            transform: translateX(5px);
        }
    }
</style>

<script defer type="module">

</script>

