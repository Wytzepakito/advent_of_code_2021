use std::cmp::{min, max};

fn get_lines(string: &str) -> Vec<((u16, u16), (u16, u16))> {
    let mut lines: Vec<((u16, u16), (u16, u16))> = Vec::new();
    for line in string.lines() {
        let points: Vec<&str> = line.split("->").collect();
        let point_a_l: Vec<u16> = points[0]
            .split(",")
            .map(|x| x.trim().parse::<u16>().unwrap())
            .collect();
        let point_a = (point_a_l[0], point_a_l[1]);
        let point_b_l: Vec<u16> = points[1]
            .split(",")
            .map(|x| x.trim().parse().unwrap())
            .collect();
        let point_b = (point_b_l[0], point_b_l[1]);
        lines.push((point_a, point_b));
    }
    return (lines);
}

fn make_grid(n: i32) -> Vec<[u16; 1000]> {
    let mut grid = Vec::new();
    for i in 0..n {
        let mut row: [u16; 1000] = [0; 1000];

        grid.push(row.to_owned());
    }
    return (grid);
}

fn score_line(line: ((u16, u16), (u16, u16)), grid: &mut [[u16; 1000]], n: i32) -> u16 {
    let mut score_of_2: u16 = 0;
        for i in 0..grid.len()  {
        
            for j in 0..grid.len() {
                
                let min_x = min(line.0.0, line.1.0);
                let max_x = max(line.0.0, line.1.0);
                let min_y = min(line.0.1, line.1.1);
                let max_y = max(line.0.1, line.1.1);
                // checks horizontal lines
                if (line.0.0 == line.1.0 || line.0.1 == line.1.1 ) {
                    if (i >= min_y.into() && i <= max_y.into()) 
                    {        
                        if (j >= min_x.into() && j <= max_x.into())
                        {
                            let score = grid[i][j] + 1;

                            if (score == 2) {
                                score_of_2 +=1;
                            }
                            grid[i][j] = score;
                        } 
                }
            }
            // checks diagonal lines
            if (i32::from(line.0.0) - i32::from(line.1.0)).abs() == (i32::from(line.0.1) - i32::from(line.1.1)).abs() {
                    //println!("Diagonal line: {:?}, {:?}", line.0, line.1);
                    if (i >= min_y.into() && i <= max_y.into())
                    {        
                        if (j >= min_x.into() && j <= max_x.into())
                        {   
                            let i_distance: i32 = (i as i32 - i32::from(line.0.0)).abs();
                            let j_distance: i32 = (j as i32 - i32::from(line.0.1)).abs();
                            if (i_distance == j_distance) {
                                let score = grid[i][j] + 1;

                                if (score == 2) {
                                    score_of_2 +=1;
                                }
                                grid[i][j] = score;
                        }
                        } 
                }
            
                
            }
    }
    
}
score_of_2
}

fn main() {
    let string = include_str!("../input.txt");
    let lines: Vec<((u16, u16), (u16, u16))> = get_lines(string);
    let mut grid = make_grid(1000);
    let mut score_of_2 = 0;
    for line in lines.into_iter() {
        score_of_2 = score_of_2 + score_line(line, &mut grid[..], 1000);
    }

    // for row in grid.into_iter() {
    //     println!("{:?}", row);
    // }
    println!("number of points with score 2 is: {}", score_of_2);


    println!("Hello, world!");
}
