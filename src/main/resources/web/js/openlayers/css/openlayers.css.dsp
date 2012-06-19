<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<c:include page="~./js/openlayers/ext/theme/default/style.css.dsp"/>
<c:include page="~./js/openlayers/ext/theme/default/google.css.dsp"/>
<c:if test="${zk.ie == 6}">
<c:include page="~./js/openlayers/ext/theme/default/ie6-style.css.dsp"/>
</c:if>