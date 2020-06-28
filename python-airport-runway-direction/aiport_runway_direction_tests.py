import unittest

from aiport_runway_direction import Aircraft, Airport

class InputValidation(unittest.TestCase):

    def __test_pattern(self, runway, expected_error_message):
        try:
            Airport("", "", "", [runway])
        except ValueError as e:
            self.assertEqual(expected_error_message, e.args[0])
            return
        self.fail()

    def test_valid_pattern_00_18(self):
        Airport("", "", "", ["00/18"])

    def test_valid_pattern_09C_27C(self):
        Airport("", "", "", ["09C/27C"])

    def test_wrong_pattern(self):
        self.__test_pattern("1111", 'Runway does not match pattern')

    def test_not_opposite(self):
        self.__test_pattern("11/11", 'Runway directions must be opposite to each other')

    def test_first_direction_not_less_than_second(self):
        self.__test_pattern("27/09", 'First runway direction must be less than second runway direction')

    def test_wind_speed_less_than_zero(self):
        try:
            Airport("", "", "", [], -1)
        except ValueError as e:
            self.assertEqual('Wind speed must be greater than zero', e.args[0])

    def test_wind_direction_is_minus_1(self):
        try:
            Airport("", "", "", [], 0, -1)
        except ValueError as e:
            self.assertEqual('Wind direction must be in between 0 and 359', e.args[0])
            return
        self.fail()

    def test_wind_direction_is_360(self):
        try:
            Airport("", "", "", [], 0, 360)
        except ValueError as e:
            self.assertEqual('Wind direction must be in between 0 and 359', e.args[0])
            return
        self.fail()

class TestScenario1_DresdenAirport(unittest.TestCase):

    def runway_04_22(self, aircraft_direction: int, expected_runway: str):
        aircraft = Aircraft(aircraft_direction)
        airport = Airport("Dresden", "EDDC", "DRS", ["04/22"])

        best_runway = airport.get_best_runway(aircraft)

        self.assertEqual(expected_runway, best_runway,
                         "best runway for direction " + str(aircraft_direction) + " and runway 04/22 should be " + expected_runway)

    def test_aircraft_12_for_runway_04_22(self):
        self.runway_04_22(12, "22")

    def test_aircraft_0_to_359(self):
        for aircraft_direction in range(0, 360):
            expected_runway = "22"

            if 130 <= aircraft_direction <= 310:
                expected_runway = "04"

            self.runway_04_22(aircraft_direction, expected_runway)


class TestScenario2_FankfurtAirport(unittest.TestCase):

    def __best_runway_test(self, aircraft_direction: int, expected_runway: str):
        aircraft = Aircraft(aircraft_direction)
        airport = Airport("Fankfurt", "EDDF", "FRA", ["07R/25L", "07C/25C", "07L/25R", "18/36"])

        best_runway = airport.get_best_runway(aircraft)

        self.assertEqual(expected_runway, best_runway,
                         "best runway for direction " + str(aircraft_direction) + " and runway 04/22 should be " + expected_runway)

    def test_aircraft_0_to_359(self):
        for aircraft_direction in range(0, 360):
            if 0 <= aircraft_direction < 35:
                expected_runway = "18"

            if 35 <= aircraft_direction <= 125:
                expected_runway = "25L"

            if 125 < aircraft_direction < 215:
                expected_runway = "36"

            if 215 <= aircraft_direction <= 305:
                expected_runway = "07R"

            if 305 < aircraft_direction <= 360:
                expected_runway = "18"

            self.__best_runway_test(aircraft_direction, expected_runway)


class TestScenario2_FankfurtAirport_Windy(unittest.TestCase):

    def best_runway_test(self, aircraft_direction: int, wind_direction: int, expected_runway: str):
        aircraft = Aircraft(aircraft_direction)
        airport = Airport("Fankfurt", "EDDF", "FRA", ["07R/25L", "07C/25C", "07L/25R", "18/36"], 6, wind_direction)

        best_runway = airport.get_best_runway(aircraft)

        self.assertEqual(expected_runway, best_runway,
                         "best runway for direction " + str(aircraft_direction) + " and runway 04/22 should be " + expected_runway)

    def test_aircraft_0_to_359_wind_230(self):
        for aircraft_direction in range(0, 360):
            self.best_runway_test(aircraft_direction, 230, "25L")

    def test_aircraft_0_to_359_wind_90(self):
        for aircraft_direction in range(0, 360):
            self.best_runway_test(aircraft_direction, 90, "07R")