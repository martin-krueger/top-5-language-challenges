import re as re


class Aircraft(object):
    def __init__(self, direction_from_airport=0):
        self.direction_from_airport = direction_from_airport


class Runway(object):
    RUNWAY_RE = "(\d{2})[A-Z]{0,1}/(\d{2})[A-Z]{0,1}"
    RUNWAY_PATTERN = re.compile(RUNWAY_RE)

    def __init__(self, runway=""):
        self.validate_runway(runway)
        self.directions = []
        self.runway = runway
        self.names = self.runway.split("/")

        for i, runway_direction in enumerate(self.names):
            direction_str = runway_direction[0:2] if len(runway_direction) == 3 else runway_direction
            direction = int(direction_str) * 10
            self.directions.append(direction)

    def validate_runway(self, runway=""):
        result = self.RUNWAY_PATTERN.match(runway)

        if result is None:
            raise ValueError("Runway does not match pattern")

        direction1 = int(result.group(1))
        direction2 = int(result.group(2))

        if direction1 > direction2:
            raise ValueError("First runway direction must be less than second runway direction")

        if direction1 + 18 != direction2:
            raise ValueError("Runway directions must be opposite to each other")

    def get_runway_with_minimum_angle(self, direction: int):
        angle = abs(direction - self.directions[0])
        angle = 360 - angle if angle > 180 else angle

        if angle <= 90:
            return angle, self.names[0]
        else:
            return 180 - angle, self.names[1]


class Airport(object):

    def __init__(self, name="", icao="", iata="", runways=None, wind_speed=0, wind_direction=None):
        self.runways = []

        if wind_speed < 0:
            raise ValueError("Wind speed must be greater than zero")

        if wind_direction is not None and (wind_direction < 0 or wind_direction > 359):
            raise ValueError("Wind direction must be in between 0 and 359")

        for r in runways:
            self.runways.append(Runway(r))

        self.name = name
        self.icao = icao
        self.iata = iata
        self.wind_speed = wind_speed
        self.wind_direction = wind_direction

    def get_best_runway(self, aircraft=Aircraft):
        if self.wind_speed > 5:
            aircraft_direction = self.wind_direction
        else:
            aircraft_direction = (aircraft.direction_from_airport + 180) % 360

        min_angle = 360
        min_runway = ""
        for r in self.runways:
            angle, direction = r.get_runway_with_minimum_angle(aircraft_direction)

            if angle < min_angle:
                min_angle = angle
                min_runway = direction

        return min_runway


if __name__ == '__main__':
    runway = input("Enter runway, e.g. 09/27: ")
    wind_speed_input = input("Enter wind speed (>0): ")
    wind_direction_input = input("Enter wind direction (0-359): ")
    aircraft_direction_input = input("Aircraft direction (0-359): ")

    try:
        wind_speed = int(wind_speed_input)
    except ValueError:
        print("Cannot convert wind speed value '" + wind_speed_input + "' to int")

    try:
        wind_direction = int(wind_direction_input)
    except ValueError:
        print("Cannot convert wind direction value '" + wind_speed_input + "' to int")

    try:
        aircraft_direction = int(aircraft_direction_input)
    except ValueError:
        print("Cannot convert aircraft direction value '" + aircraft_direction_input + "' to int")

    try:
        airport = Airport("", "", "", [runway], wind_speed, wind_direction)
    except ValueError as e:
        print(e)

    try:
        aircraft = Aircraft(aircraft_direction)
    except ValueError as e:
        print(e)

    runway = airport.get_best_runway(aircraft)

    print()
    print("Land on runway: " + runway)
