var defaultLevel;
var oMap;

function fn_godsoftMblDatagokrSuwon_onload_suwon() {
	var w = $(window).width() - 95;
	var h = $(window).height() - 50;

	$(window).resize(function() {
		var w = $(window).width() - 95;
		varh = $(window).height() - 50;
		oMap.setSize(new nhn.api.map.Size(w, h));
	});

	var oSeoulCityPoint = new nhn.api.map.LatLng(37.5675451, 126.9773356);
	// var defaultLevel = 11;
	defaultLevel = 11;
	// var oMap = new nhn.api.map.Map(document.getElementById('map'), {
	oMap = new nhn.api.map.Map(document.getElementById('map'), {
		point : oSeoulCityPoint,
		zoom : defaultLevel,
		enableWheelZoom : true,
		enableDragPan : true,
		enableDblClickZoom : false,
		mapMode : 0,
		activateTrafficMap : false,
		activateBicycleMap : false,
		minMaxLevel : [ 1, 14 ],
		// size : new nhn.api.map.Size(800, 480)
		size : new nhn.api.map.Size(w, h)
	// size : new nhn.api.map.Size(window.innerWidth - 300,
	// window.innerHeight - 300)
	});
	var oSlider = new nhn.api.map.ZoomControl();
	oMap.addControl(oSlider);
	oSlider.setPosition({
		top : 10,
		left : 10
	});

	var oMapTypeBtn = new nhn.api.map.MapTypeBtn();
	oMap.addControl(oMapTypeBtn);
	oMapTypeBtn.setPosition({
		bottom : 10,
		right : 80
	});

	var oThemeMapBtn = new nhn.api.map.ThemeMapBtn();
	oThemeMapBtn.setPosition({
		bottom : 10,
		right : 10
	});
	oMap.addControl(oThemeMapBtn);

	var oBicycleGuide = new nhn.api.map.BicycleGuide(); // - 자전거 범례 선언
	oBicycleGuide.setPosition({
		top : 10,
		right : 10
	}); // - 자전거 범례 위치 지정
	oMap.addControl(oBicycleGuide);// - 자전거 범례를 지도에 추가.

	var oTrafficGuide = new nhn.api.map.TrafficGuide(); // - 교통 범례 선언
	oTrafficGuide.setPosition({
		bottom : 30,
		left : 10
	}); // - 교통 범례 위치 지정.
	oMap.addControl(oTrafficGuide); // - 교통 범례를 지도에 추가.

	var trafficButton = new nhn.api.map.TrafficMapBtn(); // - 실시간 교통지도 버튼 선언
	trafficButton.setPosition({
		bottom : 10,
		right : 150
	}); // - 실시간 교통지도 버튼 위치 지정
	oMap.addControl(trafficButton);

	var oSize = new nhn.api.map.Size(28, 37);
	var oOffset = new nhn.api.map.Size(14, 37);
	var oIcon = new nhn.api.map.Icon(
			'http://static.naver.com/maps2/icons/pin_spot2.png', oSize, oOffset);

	var oInfoWnd = new nhn.api.map.InfoWindow();
	oInfoWnd.setVisible(false);
	oMap.addOverlay(oInfoWnd);

	oInfoWnd.setPosition({
		top : 20,
		left : 20
	});

	var oLabel = new nhn.api.map.MarkerLabel(); // - 마커 라벨 선언.
	oMap.addOverlay(oLabel); // - 마커 라벨 지도에 추가. 기본은 라벨이 보이지 않는 상태로 추가됨.

	oInfoWnd.attach('changeVisible', function(oCustomEvent) {
		if (oCustomEvent.visible) {
			oLabel.setVisible(false);
		}
	});

	var oPolyline = new nhn.api.map.Polyline([], {
		strokeColor : '#f00', // - 선의 색깔
		strokeWidth : 5, // - 선의 두께
		strokeOpacity : 0.5
	// - 선의 투명도
	}); // - polyline 선언, 첫번째 인자는 선이 그려질 점의 위치. 현재는 없음.
	oMap.addOverlay(oPolyline); // - 지도에 선을 추가함.

	oMap.attach('mouseenter', function(oCustomEvent) {

		var oTarget = oCustomEvent.target;
		// 마커위에 마우스 올라간거면
		if (oTarget instanceof nhn.api.map.Marker) {
			var oMarker = oTarget;
			oLabel.setVisible(true, oMarker); // - 특정 마커를 지정하여 해당 마커의 title을
			// 보여준다.
		}
	});

	oMap.attach('mouseleave', function(oCustomEvent) {

		var oTarget = oCustomEvent.target;
		// 마커위에서 마우스 나간거면
		if (oTarget instanceof nhn.api.map.Marker) {
			oLabel.setVisible(false);
		}
	});

	oMap
			.attach(
					'click',
					function(oCustomEvent) {
						var oPoint = oCustomEvent.point;
						var oTarget = oCustomEvent.target;
						oInfoWnd.setVisible(false);
						// 마커 클릭하면
						if (oTarget instanceof nhn.api.map.Marker) {
							// 겹침 마커 클릭한거면
							if (oCustomEvent.clickCoveredMarker) {
								return;
							}
							// - InfoWindow 에 들어갈 내용은 setContent 로 자유롭게 넣을 수
							// 있습니다.
							// 외부 css를 이용할 수 있으며,
							// - 외부 css에 선언된 class를 이용하면 해당 class의 스타일을 바로 적용할 수
							// 있습니다.
							// - 단, DIV 의 position style 은 absolute 가 되면 안되며,
							// - absolute 의 경우 autoPosition 이 동작하지 않습니다.
							oInfoWnd
									.setContent('<DIV style="border-top:1px solid; border-bottom:2px groove black; border-left:1px solid; border-right:2px groove black;margin-bottom:1px;color:black;background-color:white; width:auto; height:auto;">'
											+ '<span style="color: #000000 !important;display: inline-block;font-size: 12px !important;font-weight: bold !important;letter-spacing: -1px !important;white-space: nowrap !important; padding: 2px 5px 2px 2px !important">'
											+ 'Hello World <br /> '
											+ oTarget.getPoint()
											+ '<span></div>');
							oInfoWnd.setPoint(oTarget.getPoint());
							oInfoWnd.setPosition({
								right : 15,
								top : 30
							});
							oInfoWnd.setVisible(true);
							oInfoWnd.autoPosition();
							return;
						}
						var oMarker = new nhn.api.map.Marker(oIcon, {
							title : '마커 : ' + oPoint.toString()
						});
						oMarker.setPoint(oPoint);
						oMap.addOverlay(oMarker);

						var aPoints = oPolyline.getPoints(); // - 현재 폴리라인을
						// 이루는 점을
						// 가져와서 배열에 저장.
						aPoints.push(oPoint); // - 추가하고자 하는 점을 추가하여 배열로 저장함.
						oPolyline.setPoints(aPoints); // - 해당 폴리라인에 배열에 저장된 점을
						// 추가함
					});
}

function fn_godsoftMblDatagokrSuwon_geocode_suwon() {
	var url = "";
	// url += contextPath + "/proxy.jsp?";
	url += "http://openapi.map.naver.com/api/geocode";
	url += "?key=acadc19d58314c61c3ce90f95eca41fc";
	url += "&encoding=utf-8";
	url += "&coord=latlng";
	url += "&output=json";
	url += "&query=%EA%B2%BD%EA%B8%B0%EB%8F%84%20%EC%84%B1%EB%82%A8%EC%8B%9C%20%EB%B6%84%EB%8B%B9%EA%B5%AC%20%EC%A0%95%EC%9E%90%EB%8F%99%20178-1";

	var url2 = contextPath + "/url.mdo?spec=" + encodeURIComponent(url);

	var data = {};

	function success(data, textStatus, jqXHR) {
		console.debug(data.result.userquery);
		console.debug(data.result.items[0].point.x);
		console.debug(data.result.items[0].point.y);

		// setCenterAndLevel(p : Coord, level : Number, options : ZoomOptions)

		// etCenter(p : Coord, options : ZoomOptions) 지도 중심점의 좌표를 변경한다.
		// setCenterBy(x_pixels : Number, y_pixels : Number, options :
		// ZoomOptions) 지도 중심점을 픽셀 단위의 상대 좌표만큼 변경한다.
		// setCenterAndLevel(p : Coord, level : Number, options : ZoomOptions)
		// 지도의 중심점을 특정 좌표로 변경하며 지도의 축척 레벨을 변경한다.
		// setCenterAndLevelBy(p : Coord, deltaLevel : Number, options :
		// ZoomOptions) 지도의 중심점을 특정 좌표로 변경하며 지도의 축척 레벨을 deltaLevel 값만큼 변경한다.

		// setPointLevel(p : Coord, level : Number, options : ZoomOptions) 특정
		// 좌표의
		// 지도 상 위치를 유지하며 지도의 축척 레벨을 변경한다.
		// setPointLevelBy(p : Coord, deltaLevel : Number, options :
		// ZoomOptions) 특정 좌표의 지도 상 위치를 유지하며 지도의 축척 레벨을 deltaLevel 값만큼 변경한다.

		// var oSeoulCityPoint = new nhn.api.map.LatLng(
		// data.result.items[0].point.y, data.result.items[0].point.x,
		// defaultLevel);
		var oSeoulCityPoint = new nhn.api.map.LatLng(
				data.result.items[0].point.y, data.result.items[0].point.x);

		console.debug("oSeoulCityPoint=" + oSeoulCityPoint);
		console.debug("defaultLevel=" + defaultLevel);

		oMap.setCenter(oSeoulCityPoint); // - 지도 생성시 지정한 중심점으로 중심점을 설정한다.
	}

	var dataType = "json";

	$.get(url2, data, success, dataType);
}