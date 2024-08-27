<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập Nhật Giải Đấu</title>
<style type="text/css">
.panel-heading {
	border-bottom: 1px solid #dfe4ea;
}

.panel-footer {
	border-top: 1px solid #dfe4ea;
}
</style>
</head>
<body>
	<%@ include file="../templates/header.jsp"%>

	<section class="match-section"
		style="background-image: url(${pageContext.request.contextPath }/resources/img/match/match-bg.jpg); padding-top: 60px;">
		<div class="container">
			<div class="col-md-10 offset-md-1 rounded"
				style="background-color: #f8f9fa;">
				<form>
					<div class="panel-heading text-center py-3">
						<h3>Cập Nhật Giải Đấu</h3>
					</div>
					<div class="panel-body py-2">
						<div class="row">
							<div class="col-md-10 offset-md-1">
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group mb-3">
											<label class="label mb-1" for="leagueName">Tên giải
												đấu</label> <input type="text" name="leagueName" id="leagueName"
												class="form-control" placeholder="Nhập tên giải đấu"
												required>
										</div>
										<div class="form-group mb-3">
											<label class="label mb-1" for="teamsize">Số lượng
												người mỗi đội</label> <select class="form-control" id="teamsize"
												name="teamsize">
												<option value="5" selected>5</option>
												<option value="7">7</option>
												<option value="9">9</option>
												<option value="11">11</option>
											</select>
										</div>
										<div class="form-group mb-3">
											<label class="label mb-1" for="startDate">Ngày bắt
												đầu</label> <input type="date" name="startDate" id="startDate"
												class="form-control" required>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group mb-3">
											<label class="label mb-1" for="teamNumber">Số đội
												tham gia</label> <input type="number" name="teamNumber"
												class="form-control" value="2" id="teamNumber" required>
										</div>
										<div class="form-group mb-3">
											<label class="label mb-1" for="format">Hình thức thi
												đấu</label> <select class="form-control" id="format" name="format">
												<option value="knockout" selected>Loại trực tiếp</option>
												<option value="mixed">Hỗn hợp</option>
											</select>
										</div>
										<div class="form-group mb-3">
											<label class="label mb-1" for="endDate">Ngày kết thúc</label>
											<input type="date" name="endDate" id="endDate"
												class="form-control" required>
										</div>
									</div>
								</div>
								<hr>
								<div class="form-group mb-3">
									<label class="label mb-1" for="stage">Trạng thái giải
										đấu</label> <select class="form-control" id="stage" name="stage">
										<option value="knockout" selected>Vòng bảng</option>
										<option value="mixed">Vòng 1/16</option>
									</select>
								</div>
								<div class="form-group mb-3">
									<label class="label mb-1" for="description">Mô tả</label>
									<textarea class="form-control" id="description"
										name="description" rows="5" required></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-footer py-3">
						<div class="row">
							<div class="col-md-3 mx-auto text-center">
								<div class="row">
									<button type="submit" class="btn btn-danger text-center">Cập
										Nhật</button>
									<a href="${pageContext.request.contextPath }/league/dashboard"
										class="btn btn-secondary text-center text-white ml-2">Hủy
										Bỏ</a>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>

	<%@ include file="../templates/footer.jsp"%>
</body>
</html>