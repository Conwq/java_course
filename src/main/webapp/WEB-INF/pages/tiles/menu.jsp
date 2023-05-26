<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
	ƒанный код импортируетс€ на странцу baseLayout и отобразит дл€ активного пользовател€ кнопку с реферальной ссылкой на список всех новостей,
	а если данный пользователь имеет такжестатус админ, то добавит ему возможность добавлени€ новости,
	нажима€ на реферальную ссылку дл€ перехода на страницу добавлени€.
	Ёта часть кода импортируетс€ в baseLLayout лишь в случае, если пользователь будет активирован.
--%>

<div class="menu-wrapper">

	<div class="menu-title-wrapper">
		<div class="menu-title">
		       News
		</div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: right;">

			<ul style="list-style-image: url(images/img.jpg); text-align: left;">

				<li style="padding-left: 15px;">
					<a href="">news list</a>
					<br />
				</li>

				<c:if test="${sessionScope.role eq 'admin'}">

				   <li style="padding-left: 15px;">
				    	<a href="">add news </a>
                   		<br />
					</li>

				</c:if>

			</ul>
		</div>

		<div class="clear">

		</div>

	</div>
	<!--  grey free space at the bottom of menu -->

	<div style="height: 25px;"></div>
</div>

