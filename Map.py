class MapTile:
	def __init__(self, elevation, moisture, temperature):
		self.elevation = elevation
		self.moisture = moisture
		self.temperature = temperature

		elev_color_val = int(2.55 * elevation)
		self.elev_color = (elev_color_val, elev_color_val, elev_color_val)

		self.moist_color = (0, 0, 255) if moisture <= 40 else (int(255 - 1.4 * moisture),
			                                                   int(240 - 1.55 * moisture),
			                                                   int(130 + 1.25 * moisture))

		temp_color_r = int(abs(255 - 5.1 * temperature))
		self.temp_color = (temp_color_r, 255, int(255 - 5.1 * temperature)) if temperature <= 50 else 
		                  (temp_color_r, int(340 - 1.7 * temperature), 0)

		avg_three = lambda a, b, c: (a + b + c)
		self.color = tuple(map(avg_three, self.elev_color, self.moist_color, self.temp_color))


class MapCreator:
	WIDTH = 40
	HEIGHT = 20
	RANDOMNESS_FACTOR = 15

	def __init__():

	def new_tile()