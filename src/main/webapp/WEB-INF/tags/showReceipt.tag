<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="receipt"  required="true" rtexprvalue="true" type="dto.ReceiptDto" %>

<td><c:out value="${receipt.getId()}"/></td>
<td><c:out value="${receipt.getUser().getId()}"/></td>
<td><c:out value="${receipt.getUser().getName()}"/></td>
<td><c:out value="${receipt.getUser().getSurname()}"/></td>
<td><c:out value="${receipt.getBook().getId()}"/></td>
<td><c:out value="${receipt.getBook().getName()}"/></td>
<td><c:out value="${receipt.getBook().getAuthor()}"/></td>
<td><c:out value="${receipt.getStatus()}"/></td>
