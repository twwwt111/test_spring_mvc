<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Match detail</title>
</head>
<body>
	<%@ include file="../templates/header.jsp"%>

	<div class="container">
		<h2 class="text-uppercase mt-3">Tổng kết</h2>
		<div style="border-bottom: solid 1px red;" class="mb-3"></div>
		<div class="row mt-3 justify-content-center">
			<div class="card">
				<h5 class="card-header">${match.firstTeam.teamName}
					${match.firstTeamScore} : ${match.secondTeamScore}
					${match.secondTeam.teamName}</h5>
				<div class="card-body">
					<h5 class="card-title">Vòng đấu: ${match.stage.stageName}</h5>
					<p class="card-text">Giải: ${match.league.leagueName}</p>
				</div>
				<div class="card-body">
					<h5 class="card-title">Sự kiện trong trận</h5>
					<c:forEach items="${eventMatch}" var="events">
						<p class="card-text">${events.player.playerName }</p>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="row mt-3 justify-content-center">
			<div class="col-3">
				<form
					action="${pageContext.request.contextPath}/match/addEvent/${match.matchId}"
					method="post">
					<label>Player team 1</label> <select name="playerId" id="players1">
						<c:forEach items="${players1}" var="p">
							<option value="${p.playerId}">${p.playerName}</option>
						</c:forEach>
					</select> <br> <label>Sự kiện</label> <select name="eventId">
						<c:forEach items="${events}" var="e">
							<option value="${e.eventId}">${e.eventName}</option>
						</c:forEach>
					</select> <br> <label>Vào lúc</label> <br> <input type="number"
						name="minute" id="minute"> <br> <label>Mô tả</label>
					<br> <input type="text" name="description" id="description">
					<button type="submit">Thêm sự kiện</button>
				</form>
			</div>

			<div class="col-3">
				<form
					action="${pageContext.request.contextPath}/match/addEvent/${match.matchId}"
					method="post">
					<label>Player team 2</label> <select name="playerId" id="players1">
						<c:forEach items="${players2}" var="p">
							<option value="${p.playerId}">${p.playerName}</option>
						</c:forEach>
					</select> <br> <label>Sự kiện</label> <select name="eventId">
						<c:forEach items="${events}" var="e">
							<option value="${e.eventId}">${e.eventName}</option>
						</c:forEach>
					</select> <br> <label>Vào lúc</label> <br> <input type="number"
						name="minute" id="minute"> <br> <label>Mô tả</label>
					<br> <input type="text" name="description" id="description">

					<button type="submit">Thêm sự kiện</button>
				</form>
			</div>
		</div>

		<%@ include file="../templates/footer.jsp"%>
</body>
</html>