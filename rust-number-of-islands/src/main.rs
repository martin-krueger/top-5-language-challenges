use crate::scenario::{scenario_7, scenario_6, Scenario, scenario_1, scenario_5, scenario_4, scenario_3, scenario_2};
use crate::stage2::Algorithm;

mod stage1;
mod stage2;
mod scenario;

fn main() {
    print_scenario(scenario_1());
    print_scenario(scenario_2());
    print_scenario(scenario_3());
    print_scenario(scenario_4());
    print_scenario(scenario_5());
    print_scenario(scenario_6());
    print_scenario(scenario_7());
}

fn print_scenario(scenario: Scenario) {
    let algo = Algorithm{};

    println!("Scenario: {}", scenario.name);

    println!("Map:");
    scenario.print_map();
    println!();

    let (number, island_map) = algo.number_of_islands(scenario.map);
    println!("Number of islands: {}", number);
    println!();

    println!("Island map:");
    print_island_map(island_map);
    println!();
}

fn print_island_map(island_map: Vec<Vec<i32>>) {
    for row in island_map.iter() {
        for value in row.iter() {
            print!("{} ", value)
        }

        println!()
    }
}