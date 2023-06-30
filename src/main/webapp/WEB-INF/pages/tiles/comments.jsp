	
	
<body>

	<div align="center">
		<h3>Комментарии</h3>
	</div>
	
	<table>
    <thead>
      <tr>
        <th>Date</th>
        <th>User</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="row" items="${rows}">
        <tr>
          <td>${row.date}</td>
          <td>${row.user}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

</body>
	