use std::char;

#[allow(dead_code)]
fn sample_map() -> [[char; 3]; 4] {
    let mut map = [['0'; 3]; 4];

    map[0] = ['1', '0', '0'];
    map[1] = ['1', '0', '1'];
    map[2] = ['0', '0', '1'];
    map[3] = ['0', '1', '0'];

    map
}

#[cfg(test)]
mod tests {
    use crate::stage1::sample_map;

    #[test]
    fn print_sample_map() {
        let map = sample_map();

        for i in 0..4 {
            for j in 0..3 {
                if map[i][j] == '0' {
                    print!("{}", '□')
                } else if map[i][j] == '1' {
                    print!("{}", '■')
                } else {
                    print!("?")
                }
                print!(" ")
            }

            println!()
        }
    }

    #[test]
    fn test_each_column_contains_at_least_one_land_mark() {
        let map = sample_map();

        for row in 0..4 {
            let mut found = false;

            for col in 0..3 {
                if map[row][col] == '1' {
                    found = true
                }
            }

            assert_eq!(true, found, "row={} does not contain land", row)
        }
    }
}
