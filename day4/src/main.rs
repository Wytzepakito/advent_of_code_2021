

fn load_score(input: &str) -> Vec<u8> {
    let scores = input.split(",").map(|x| x.parse::<u8>().unwrap()).collect();
    return(scores);
}

fn load_grid(input: Vec<&str>) -> Vec<Vec<Vec<u8>>> {
    let mut grids: Vec<Vec<Vec<u8>>> = Vec::new();
    let mut grid: Vec<Vec<u8>> = Vec::new();
    for line in input {
       if (line == "") {
        grids.push(grid.to_owned());
        grid = Vec::new();
       } else {
        grid.push(line.split_whitespace().map(|x| x.parse::<u8>().unwrap()).collect());
       }
    }
    grids.push(grid);


    return(grids);

}  

fn make_score_grids(req_amount: usize) -> Vec<Vec<Vec<bool>>> {

    let mut score_grids: Vec<Vec<Vec<bool>>> = vec![];
    for i in 0..req_amount {
        let mut score_grid: Vec<Vec<bool>> = vec![]; 
        for i in 0..5 {
            let mut row: Vec<bool> = vec![];
            for i in 0..5 {
                row.push(false);
            }
            score_grid.push(row);
        }
        score_grids.push(score_grid);
    }
    return(score_grids);
}

fn do_score_grid<'a>(score: u8, cards: &'a [Vec<Vec<u8>>], score_grids: &'a mut [Vec<Vec<bool>>]) -> &'a [Vec<Vec<bool>>] {

    for (i, card) in cards.into_iter().enumerate() {
        for (j, row ) in card.into_iter().enumerate() {
            for (k, num) in row.into_iter().enumerate() {
                if (num == &score) {
                    score_grids[i][j][k] = true;
                }
            }
        }
        
    }
    return(score_grids);
}

fn is_winner(score_grid: &[Vec<bool>]) -> bool {
    
    for i in 0..score_grid.len() {
        let mut row: Vec<bool> = Vec::new();
        let mut col: Vec<bool> = Vec::new();
        for j in 0..score_grid.len() {
            row.push(score_grid[i][j].clone());
            col.push(score_grid[j][i].clone());
        }
        
        if (col.into_iter().all(|x| x ==  true)) {
            return true;
        } 
        if (row.into_iter().all(|x| x == true)) {
            return true;
        }
    }
    return false;
}

fn get_sum_uncrossed(card: &[Vec<u8>], score_grid: &[Vec<bool>]) -> i32 {

    let mut sum: i32 = 0;
    for i in 0..score_grid.len() {
        for j in 0..score_grid.len() {
            if (score_grid[i][j] == false) {
                sum += i32::from(card[i][j]);
            }  
        }
    }
    return(sum);

}


fn main() {
    println!("Hello, world!");   
    let input = include_str!("../input.txt");

    let lines = input.lines().collect::<Vec<_>>();
    let scores = load_score(lines[0]);

    let cards = load_grid(lines[2..].to_vec());
    let mut score_grids = make_score_grids(cards.len());


    let mut won = false;
    let mut index = 0;
    let mut indices: Vec<usize> = (0..score_grids.len()).collect();
    while(won == false) {
        let num = scores[index];
        let mut score_grids = do_score_grid(num, &cards, &mut score_grids);
        let mut removals = Vec::new();

        for score_index in &indices {
            let score_grid = &score_grids[*score_index];
            if is_winner(score_grid) && indices.len() == 1 {
                println!("The winner won in {} draws", index);
                let sum_uncrossed = get_sum_uncrossed(&cards[*score_index], score_grid);
                println!("The final score is {} * {} {} ", sum_uncrossed, num, sum_uncrossed * i32::from(num)); 
                won = true;
            } else if is_winner(score_grid) {
                println!("There was a winner with: {} draws", index);
                removals.push(score_index);

            }
        } 

        let mut new_indices: Vec<usize> = indices.clone();   
        for remove_index in removals.into_iter() {
            new_indices.retain(|&x| x != *remove_index);
        }


        indices = new_indices;
        index += 1;
    }
}
