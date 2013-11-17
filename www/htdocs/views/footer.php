		<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>
		<script src="https://code.jquery.com/jquery-2.0.0.min.js"></script>
		<?php if($page == 'upload'){ ?>
		<script src="<?php echo url('js/upload.js'); ?>"></script>
		<?php } ?>
		<?php if($page = 'view'){ ?>
		<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBvHJzUT6MfhjvUlyjo6bYhDV-EV8i-Dc0&sensor=false"></script>
		<script type="text/javascript">
			google.maps.event.addDomListener(window, 'load', initialize);
			var postcodeStrings = [
				"",
				"Manchester,London",
				"Oxford,London",
				"LE126UW,NG104AH,B112RJ,London"
			];
			function initialize(){
				makeMap(1);
				makeMap(2);
				makeMap(3);
			}
			function makeMap(mapNo) {
				var directionDisplay;
				var directionsService = new google.maps.DirectionsService();
				var map;
				var directionsDisplay = new google.maps.DirectionsRenderer();

				var london = new google.maps.LatLng(51.5072, 0.1275);
				var myOptions = {
					zoom: 6,
					mapTypeId: google.maps.MapTypeId.ROADMAP,
					center: london
				}

				var waypoints = [];
				var postcodes = [];

				postcodes = postcodeStrings[mapNo].split(",");

				var length = postcodes.length;
				if (length > 2) {
					for (i = 1; i < (length - 1); i++) {
						waypoints.push({
							location: String(postcodes[i]),
							stopover: true
						});
					}
				}

				var request = {
					origin: String(postcodes[0]),
					destination: String(postcodes[length - 1]),
					waypoints: waypoints,
					optimizeWaypoints: false,//set this to true to get optimized path else it will plot as the given input.
					travelMode: google.maps.DirectionsTravelMode.DRIVING//set your travel mode here (walking,driving..)
				};


				directionsService.route(request, function (response, status) {
					if (status == google.maps.DirectionsStatus.OK) {
						map = new google.maps.Map(document.getElementById("map-canvas-" + mapNo), myOptions);
						directionsDisplay.setMap(map);
						directionsDisplay.setDirections(response);
						var route = response.routes[0];
						var total = 0;
						var numberLegs = route.legs.length;
					}else{
						alert("Could not load data: " + status);
					}
				});
			}		  
		</script>
		<?php } ?>
	</body>
</html>
