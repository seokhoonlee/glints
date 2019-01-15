import java.util.*;

public class ClusterCounter {
  public int countCluster(int[][] flatDiagram, int row, int col) {
    int count = 0;

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (flatDiagram[i][j] == 1) {
          count++;
          removeConnectedCluster(flatDiagram, i, j);
        }
      }
    }

    return count;
  }

  public void removeConnectedCluster(int[][] flatDiagram, int i, int j) {
    flatDiagram[i][j] = 0;

    for (int k = -1; k <= 1; k++) {
      for (int l = - 1; l <= 1; l++) {
        if (flatDiagram[i + k][j + l] == 1) {
          removeConnectedCluster(flatDiagram, i + k, j + l);
        }
      }
    }
  }

  public List<String> readInputLines() {
    List<String> inputLines = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    if (!sc.hasNextLine()) {
      System.out.println("NO INPUT LINE");
      return inputLines;
    }
    
    while (sc.hasNextLine()) {
        inputLines.add(sc.nextLine());
    }

    if (inputLines.size() == 0) {
      System.out.println("DIAGRAM HAS 0 ROWS");
      return inputLines;
    }

    if (inputLines.get(0).length() == 0) {
      System.out.println("DIAGRAM HAS 0 COLS");
    }

    return inputLines;
  }

  public int[][] getFlatDiagram(List<String> inputLines) {
    int row = inputLines.size();
    int col = inputLines.get(0).length();

    int[][] flatDiagram = new int[row + 2][col + 2]; // pad with 0s around the orginal diagram

    for (int i = 0; i <= row + 1; i++) {
      for (int j = 0; j <= col + 1; j++) {
        if (i == 0 || j == 0 || i == row + 1 || j == col + 1) {
          flatDiagram[i][j] = 0;
          continue;
        }

        char c = inputLines.get(i - 1).charAt(j - 1);

        if (c == '0') {
          flatDiagram[i][j] = 0;
        } else if (c == '+') {
          flatDiagram[i][j] = 1;
        } else {
          System.out.println("INPUT TYPE IS OUT OF RANGE");
          flatDiagram[i][j] = 0;
        }
      }
    }

    return flatDiagram;
  }

  public static void main(String[] args) {
    ClusterCounter counter = new ClusterCounter();

    List<String> inputLines = counter.readInputLines();

    int row = inputLines.size();
    int col = inputLines.size() == 0 ? 0 : inputLines.get(0).length();

    if (row == 0 || col == 0) {
      System.out.println(0);
      return;
    }

    int[][] flatDiagram = counter.getFlatDiagram(inputLines);

    int clusterCount = counter.countCluster(flatDiagram, row + 2, col + 2); // row and col increased by 2 due to 0 padding

    System.out.println(clusterCount);
  }
}