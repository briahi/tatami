<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<footer>
    <div id="footer" class="navbar navbar-fixed-bottom">
        <a href="http://www.ippon.fr" id="footer_ippon"><fmt:message key="tatami.copyright"/> <fmt:message key="tatami.ippon.technologies"/></a> |
        <a href="https://github.com/ippontech/tatami" id="footer_github"><fmt:message key="tatami.github.fork"/></a> |
        <a href="http://blog.ippon.fr" id="footer_blog"><fmt:message key="tatami.ippon.blog"/></a> |
        <a href="https://twitter.com/#!/ippontech" id="footer_twitter"><fmt:message key="tatami.ippon.twitter.follow"/></a>
    </div>
</footer>

<!-- Le javascript -->

<!-- Placed at the end of the document so the pages load faster -->
<script src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
<script>!window.jQuery && document.write(unescape('%3Cscript src="/assets/js/jquery/jquery-1.7.2.min.js"%3E%3C/script%3E'))</script>

<c:if test="${wro4jEnabled eq false}">
    <script src="/assets/js/bootstrap/2.0.4/bootstrap-dropdown.js"></script>
    <script src="/assets/js/bootstrap/2.0.4/bootstrap-tab.js"></script>
    <script src="/assets/js/bootstrap/2.0.4/bootstrap-tooltip.js"></script>
    <script src="/assets/js/bootstrap/2.0.4/bootstrap-popover.js"></script>
    <script src="/assets/js/bootstrap/2.0.4/bootstrap-typeahead.js"></script>
    <script src="/assets/js/bootstrap/2.0.4/bootstrap-collapse.js"></script>
    <script src="/assets/js/tatami/standard/shortcut.js"></script>
    <script src="/assets/js/raphael/2.1.0/raphael-min.js"></script>
    <script src="/assets/js/mustache/mustache.js"></script>
    <script src="/assets/js/tatami/standard/tatami.utils.js"></script>
    <script src="/assets/js/tatami/standard/tatami.status.js"></script>
    <script src="/assets/js/tatami/standard/tatami.users.js"></script>
    <script src="/assets/js/tatami/standard/tatami.ajax.js"></script>
    <script src="/assets/js/tatami/standard/tatami.charts.js"></script>
    <script src="/assets/js/tatami/standard/tatami.js"></script>
</c:if>
<c:if test="${wro4jEnabled eq true}">
    <script src="/tatami/static/${version}/all.js"></script>
</c:if>

<!-- Mustache templates -->
<div id="mustache"></div>
