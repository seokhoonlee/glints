import java.util.*;

public class TalentHunt {
  private int[][] jobMatrix;
  private int[][] candidateMatrix;

  public class Pair {
    private Integer candidateIndex;
    private Integer jobIndex;

    public Pair(Integer candidateIndex, Integer jobIndex) {
      this.candidateIndex = candidateIndex;
      this.jobIndex = jobIndex;
    }

    @Override
    public String toString() {
      return "c" + (candidateIndex + 1) + " j" + (jobIndex + 1);
    }
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

  public void generateMatrix(List<String> inputLines) {
    jobMatrix = new int[inputLines.size() / 2][inputLines.size() / 2];
    candidateMatrix = new int[inputLines.size() / 2][inputLines.size() / 2];

    int lineCount = 0;

    for (String line: inputLines) {
      line = line.replaceAll("[jc:]","");
      String[] lineWords = line.split(" ");

      if (lineCount < inputLines.size() / 2) {
        int jobIndex = Integer.valueOf(lineWords[0]);
        for (int i = 1; i < lineWords.length; i++) {
          int candidateIndex = Integer.valueOf(lineWords[i]);
          jobMatrix[jobIndex - 1][i - 1] = candidateIndex - 1; 
        }
      } else {
        int candidateIndex = Integer.valueOf(lineWords[0]);
        for (int i = 1; i < lineWords.length; i++) {
          int jobIndex = Integer.valueOf(lineWords[i]);
          candidateMatrix[candidateIndex - 1][i - 1] = jobIndex - 1;
        }
      }

      lineCount++;
    }
  }

  public List<Pair> matchJobAndCadidate() {
    int[] candidateChoices = new int[candidateMatrix.length];
    Set<Integer> chosenJob = new HashSet<>();
    int numChosenJob = 0;

    for (int i = 0; i < candidateMatrix.length; i++) {
      int firstChoiceJob = candidateMatrix[i][0];

      if (!chosenJob.contains(firstChoiceJob)) {
        chosenJob.add(firstChoiceJob);
        numChosenJob++;
      }

      candidateChoices[i] = firstChoiceJob;
    }

    while (numChosenJob != jobMatrix.length) {
      for (int i = 0; i < candidateChoices.length; i++) {
        for (int j = i + 1; j < candidateChoices.length; j++) {
          if (candidateChoices[i] == candidateChoices[j]) {
            int jobsRankingForI = getRankingForCandidate(jobMatrix[candidateChoices[i]], i);
            int jobsRankingForJ = getRankingForCandidate(jobMatrix[candidateChoices[j]], j);

            int newChoiceJob;

            if (jobsRankingForI < jobsRankingForJ) {
              newChoiceJob = getNextChoiceJob(candidateMatrix[j], candidateChoices[j]);
              candidateChoices[j] = newChoiceJob;
            } else {
              newChoiceJob = getNextChoiceJob(candidateMatrix[i], candidateChoices[i]);
              candidateChoices[i] = newChoiceJob;
            }

            if (!chosenJob.contains(newChoiceJob)) {
              chosenJob.add(newChoiceJob);
              numChosenJob++;
            }
          }
        }
      }
    }

    List<Pair> pairs = new ArrayList<>();

    for (int i = 0; i < candidateChoices.length; i++) {
      pairs.add(new Pair(i, candidateChoices[i]));
    }

    return pairs;
  }

  public int getNextChoiceJob(int[] candidateRankingForJob, int candidateOldChoice) {
    for (int i = 0; i < candidateRankingForJob.length - 1; i++) {
      if (candidateRankingForJob[i] == candidateOldChoice) {
        return candidateRankingForJob[i + 1];
      }
    }

    return candidateRankingForJob[candidateRankingForJob.length - 1];
  }

  public int getRankingForCandidate(int[] jobRankingForCandidate, int candidateIndex) {
    for(int i = 0; i < jobRankingForCandidate.length; i++) {
      if (jobRankingForCandidate[i] == candidateIndex) {
        return i;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    TalentHunt talentHunt = new TalentHunt();

    List<String> inputLines = talentHunt.readInputLines();

    talentHunt.generateMatrix(inputLines);

    List<Pair> pairs = talentHunt.matchJobAndCadidate();
    
    talentHunt.printPairs(pairs);
  }  
}