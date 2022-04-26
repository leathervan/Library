<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="sub"  required="true" rtexprvalue="true" type="dto.SubscriptionDto" %>

<td><c:out value="${sub.getId()}"/></td>
<td><c:out value="${sub.getStart()}"/></td>
<td><c:out value="${sub.getEnd()}"/></td>
<td><c:out value="${sub.getDebt()}"/></td>
<td><c:out value="${sub.getUser().getId()}"/></td>
<td><c:out value="${sub.getUser().getName()}"/></td>
<td><c:out value="${sub.getUser().getSurname()}"/></td>
<td><c:out value="${sub.getBook().getId()}"/></td>
<td><c:out value="${sub.getBook().getName()}"/></td>
<td><c:out value="${sub.getBook().getAuthor()}"/></td>
