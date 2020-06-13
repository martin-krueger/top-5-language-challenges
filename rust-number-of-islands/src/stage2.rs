use std::vec::Vec;

pub struct Algorithm {}

impl Algorithm {
    pub(crate) fn number_of_islands(&self, map: Vec<Vec<char>>) -> (u32, Vec<Vec<i32>>) {
        // state values:
        //  -1 : unknown, initial state
        //   0 : water
        // >=1 : n'th island
        let mut state = vec![vec![-1i32; map[0].len()]; map.len()];
        let mut number = 0u32;

        for (row, row_value) in map.iter().enumerate() {
            for (col, col_value) in row_value.iter().enumerate() {
                if state[row][col] != -1 {
                    continue;
                }

                if *col_value == '0' {
                    state[row][col] = 0
                } else {
                    number += 1;
                    self.explore_island(&map, &mut state, row, col, number as i32 )
                }
            }
        }

        return (number, state);
    }

    fn explore_island(
        &self, map: &Vec<Vec<char>>, state: &mut Vec<Vec<i32>>, row: usize, col: usize, island: i32) {
        if state[row][col] != -1 {
            return
        }

        state[row][col] = island;

        if row > 0 && map[row - 1][col] == '1' {
            self.explore_island(map, state, row - 1, col, island)
        }

        if col > 0 && map[row][col - 1] == '1' {
            self.explore_island(map, state, row, col - 1, island)
        }

        if row < map.len() - 1 && map[row + 1][col] == '1' {
            self.explore_island(map, state, row + 1, col, island)
        }

        if col < map[0].len() - 1 && map[row][col + 1] == '1' {
            self.explore_island(map, state, row, col + 1, island)
        }
    }
}


#[cfg(test)]
mod tests {
    use crate::scenario;
    use crate::stage2::Algorithm;
    use crate::scenario::Scenario;

    #[test]
    fn test_scenario_1() {
        test(scenario::scenario_1())
    }

    #[test]
    fn test_scenario_2() {
        test(scenario::scenario_2())
    }

    #[test]
    fn test_scenario_3() {
        test(scenario::scenario_3())
    }

    #[test]
    fn test_scenario_4() {
        test(scenario::scenario_4())
    }

    #[test]
    fn test_scenario_5() {
        test(scenario::scenario_5())
    }

    #[test]
    fn test_scenario_6() {
        test(scenario::scenario_6())
    }

    #[test]
    fn test_scenario_7() {
        test(scenario::scenario_7())
    }

    fn test(scenario: Scenario) {
        let algo = Algorithm {};

        let result = algo.number_of_islands(scenario.map);

        assert_eq!(scenario.islands, result.0, "expected {} island(s)", scenario.islands);

        let compare =
            result.1.iter()
                .zip(&scenario.island_map)
                .find(|&(p, q)| p != q);
        assert!(compare.is_none(), "\nexpected island map: {:?} \n            but was: {:?}", scenario.island_map, result.1)
    }

}