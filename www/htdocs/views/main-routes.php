<?php
	foreach($expandedRoutes as $route){
		?>
		<content>
			<container>
				<div class="inner">
					<div class="route">
						<h2>Route <?php echo $route['id']; ?></h2>
						<p>
							<strong>Driver:</strong> <?php echo $route['driver']; ?><br>
							<strong>Kitchen:</strong> <?php echo $route['kitchen_name']; ?><br>
						</p>
						<div id="map-canvas-<?php echo $route['id']; ?>" style="height:200px;"
						postcodes="<?php echo implode(',', $route['postcodes']); ?>"></div>
					</div>
				</div>
			</container>
		</content>
		<?php
	}
?>
