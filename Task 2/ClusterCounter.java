import java.util.*;

public class ClusterCounter {
  public int countCluster(int[][] flatDiagram, int row, int col) {
    int count = 0;

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        System.out.print(flatDiagram[i][j]);
      }
      System.out.println();
    }

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

    if (flatDiagram[i - 1][j - 1] == 1) {
      removeConnectedCluster(flatDiagram, i - 1, j - 1);
    }

    if (flatDiagram[i - 1][j + 0] == 1) {
      removeConnectedCluster(flatDiagram, i - 1, j + 0);
    }

    if (flatDiagram[i - 1][j + 1] == 1) {
      removeConnectedCluster(flatDiagram, i - 1, j + 1);
    }

    if (flatDiagram[i + 0][j - 1] == 1) {
      removeConnectedCluster(flatDiagram, i + 0, j - 1);
    }

    if (flatDiagram[i + 0][j + 1] == 1) {
      removeConnectedCluster(flatDiagram, i + 0, j + 1);
    }

    if (flatDiagram[i + 1][j - 1] == 1) {
      removeConnectedCluster(flatDiagram, i + 1, j - 1);
    }

    if (flatDiagram[i + 1][j + 0] == 1) {
      removeConnectedCluster(flatDiagram, i + 1, j + 0);
    }

    if (flatDiagram[i + 1][j + 1] == 1) {
      removeConnectedCluster(flatDiagram, i + 1, j + 1);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    List<String> inputLines = new ArrayList<>();
    
    while (sc.hasNextLine()) {
        inputLines.add(sc.nextLine());
    }

    if (inputLines.size() == 0) {
      System.out.println("DIAGRAM HAS 0 ROWS");
      return;
    }

    if (inputLines.get(0).length() == 0) {
      System.out.println("DIAGRAM HAS 0 COLS");
      return;
    }

    int row = inputLines.size();
    int col = inputLines.get(0).length();

    int[][] flatDiagram = new int[row + 2][col + 2];

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

    ClusterCounter counter = new ClusterCounter();

    int clusterCount = counter.countCluster(flatDiagram, row + 2, col + 2);

    System.out.println(clusterCount);
  }
}