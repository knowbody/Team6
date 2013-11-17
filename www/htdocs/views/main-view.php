<content>
	<container>
		<div class="inner">
			<?php
				foreach($expandedRoutes as $route){
					?>
					<div class="route">
						<h2>Route <?php echo $route['id']; ?></h2>
						<strong>Driver:</strong> <?php echo $route['driver']; ?><br>
						<strong>Kitchen:</strong> <?php echo $route['kitchen_name']; ?><br>
						<div id="map-canvas-<?php echo $route['id']; ?>" style="height:200px;"></div>
					</div>
					<?php
				}
			?>
		</div>
	</container>
</content>
