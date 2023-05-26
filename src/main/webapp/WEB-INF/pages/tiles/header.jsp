<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--
	ƒанный код импортируетс€ в файл baseLayout.jsp где он был вызван (в верхний колонтитул).

	¬ данном коде есть кнопки с переключением €зыка ру\англ которые хран€т на пустые ссылки (пока что) и код, обернутый в 2-ва условных оператора:

		- если текущий пользователь имеет статус "не активный", то дл€ него будет отображатьс€ форма с пол€ми дл€ ввода дл€ авторизации, котора€ отправл€ет данные
		 с методом POST на FrontController с командой = do_sign_in, а также имеетс€ дополнительна€ клавиша, котора€ переадресует на форму дл€ регистрации.

		- если текущий пользователь имеет статус "активный", то дл€ него будет отображена единственна€ кнопка, котора€ отправл€ет данные
		 с методом POST на FrontController с командой = do_sign_out
--%>

<div class="wrapper">

	<div class="newstitle">News management</div>
	<div class="local-link">

		<div align="right">

			<a href=""> en </a> &nbsp;&nbsp; 
			<a	href=""> ru </a> <br /> <br />

		</div>

		<c:if test="${not (sessionScope.user eq 'active')}">
			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_in" /> 
					Enter login: <input type="text" name="login" value="" /><br /> 
					Enter password: <input type="password" name="password" value="" /><br />

					<c:if test="${not (requestScope.AuthenticationError eq null)}">
						<font color="red"> 
						   <c:out value="${requestScope.AuthenticationError}" />
						</font> 
					</c:if>
					
					<a href="">Registration</a> <input type="submit" value="Sign In" /><br />
				</form>
			</div>
		</c:if>
		
		<c:if test="${sessionScope.user eq 'active'}">

			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_out" /> 
					<input type="submit" value="Sign Out" /><br />
				</form>
			</div>

		</c:if>
	</div>
</div>
