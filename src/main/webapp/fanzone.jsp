<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery.min.js">
	
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fanZone.js">
	
</script>
<title>FanZone</title>
</head>
<body>

	<div class="tab">
		<button class="tablinks" id="shop">Official Shop</button>
		<button class="tablinks" id="adshop">Ads</button>

	</div>
	<h1 id="name">${fanzone.name}</h1>
	<!-- Tab content -->
	<div id="officialShop" class="tabcontent">

		<div>
			<p>Official Ads<p>
		</div>
		<button id = "addAd">Add ad</button>
		<div id="OfficialAds"></div>
	</div>

	<div id="Ads" class="tabcontent" style="display: none">
		<div>
			<p>Unofficial Ads<p>
		</div>
		<button id = "addAdun">Add ad</button>
		<div id="UnofficialAds"></div>

	</div>
	<a href = myAds.jsp>Active Ads</a>
	<a href = "../bided_ads.jsp">Bided Ads</a>

</body>
</html>