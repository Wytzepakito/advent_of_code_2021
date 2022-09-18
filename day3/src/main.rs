





fn main() {
    let input = "00100
    11110
    10110
    10111
    10101
    01111
    00111
    11100
    10000
    11001
    00010
    01010";

    let mut res_vec: Vec<i32> = vec![0;6];
    let mut input_len = 0;

    for line in input.lines() {
        
        for (i,c) in line.trim().chars().enumerate() {
            match c {
                '1' => res_vec[i] += 1,
                '0' => (),
                _ => (),
            }
        }
        input_len += 1;
    }
    let mut gamma_bits: Vec<i32> = vec![];
    let mut epsilon_bits: Vec<i32> = vec![];
    
    for res in &res_vec {
        if res > &((input_len / 2)) {
            gamma_bits.push(1);
            epsilon_bits.push(0);
        } else {
            gamma_bits.push(0);
            epsilon_bits.push(1);
        }
    }
}
