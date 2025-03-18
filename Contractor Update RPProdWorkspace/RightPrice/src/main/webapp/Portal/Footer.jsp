<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 <meta name="viewport" content="width-device-width, initial-scale=1">
<script src="${contextPath}/resources/js/jquery.3.5.1.js"></script>
<script src="${contextPath}/resources/js/bootstrap.messanger.js"></script>
  <div id='messenger'>
</div>
<%-- <script>
  var id = "<%=session.getAttribute("dasid")%>";
$('#messenger').append("<df-messenger chat-icon='https://storage.googleapis.com/sandwich-maker-wkakde.appspot.com/ChatBot%20Image/Chatbot%20.png' intent='WELCOME' chat-title='Atos-Syntel bot' agent-id='1b0ab59c-faa8-4875-808b-17c0059dcd51' language-code='en' user-id = '"+id+"'></df-messenger>")
</script>  --%>
<footer class="footer">
	<div>
	  <img src="${contextPath}/resources/Images/evidenfooterpic.PNG" width="100%" class="img-responsive" />
	</div>
</footer>