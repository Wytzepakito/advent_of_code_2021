import program.Simulation;

public class App {
    public static void main(String[] args) throws Exception {
        int cycles = 256;
        Simulation sim = new Simulation();
        sim.initList();
        sim.runSimulation(cycles);
    }
}
