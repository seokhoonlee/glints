import java.util.*;

public class TalentHunt {
  private int[][] jobMatrix;
  private int[][] candidateMatrix;

  public class Pair { // implements Comparable<Pair> {
    private Integer candidateIndex;
    private Integer jobIndex;

    public Pair(Integer candidateIndex, Integer jobIndex) {
      this.candidateIndex = candidateIndex + 1; // account for difference between zero index and one idnex
      this.jobIndex = jobIndex + 1; // account for difference between zero index and one idnex
    }

    @Override
    public String toString() {
      return "c" + candidateIndex + " j" + jobIndex;
    }

    // @Override
    // public int compareTo(Pair pair) {
    //   return this.candidateIndex.compareTo(pair.candidateIndex);
    // }
  }

  public void printPairs(List<Pair> pairs) {
    for (Pair pair: pairs) {
      System.out.println(pair.toString());
    }
  }

  public List<String> readInputLines() {
    List<String> inputLines = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    while (sc.hasNextLine()) {
      String nextLine = sc.nextLine();

      if (!nextLine.equals("")) {
        inputLines.add(nextLine);
      }
    }

    return inputLines;
  }

  public void generateMatrix() {

  }

  public static void main(String[] args) {
    TalentHunt talentHunt = new TalentHunt();

    List<String> inputLines = talentHunt.readInputLines();

    // for (String inputLine: inputLines) {
    //   System.out.println(inputLine);
    // }

    // System.out.println(inputLines.size());

    talentHunt.generateMatrix(inputLines);

    // List<Pair> pairs = talentHunt.match();

    // Collections.sort(pairs);

    // talentHunt.printPairs(pairs);
  }  
}