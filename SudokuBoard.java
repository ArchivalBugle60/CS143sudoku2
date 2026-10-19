import java.util.*;
import java.io.*;

public class SudokuBoard {
   private String[][] board;
   
   public SudokuBoard(String fileName) {
      board = new String[9][9];
      try {
         Scanner file = new Scanner(new File(fileName));
         
         for(int r = 0; r < board.length; r++) {
            String line = file.nextLine();
            for(int c = 0; c < board[0].length; c++) {
               board[r][c] = "" + line.charAt(c);
            }
         }
      } catch (FileNotFoundException e) {
         System.out.println("File not found.");
      }
   }
   
      
   public String toString() {
      String output = "";
      for (int r = 0; r < 9; r++) {
        if (r % 3 == 0) {
            output += "  ---------------------\n";
        }
        for (int c = 0; c < 9; c++) {
            if (c % 3 == 0) {
                output += "| ";
            }
            String value = board[r][c];
            if (value.equals(".")) {
                output += "  ";
            } else {
                output += value + " ";
            }
        }
        output += "|\n";
    }
    output += "  ---------------------\n";
    return output;
   }
   public boolean isValid() {
      if (!isValidData()) {
         return false;
      }
      if (!isValidRow()) {
         return false;
      }
      if (!isValidC()) {
         return false;
      }
      if (!isValidMS()) {
         return false;
      }
      return true;
   }
   
   private boolean isValidData() {
      ArrayList<String> store = new ArrayList<>();
      store.add(".");
      store.add("1");
      store.add("2");
      store.add("3");
      store.add("4");
      store.add("5");
      store.add("6");
      store.add("7");
      store.add("8");
      store.add("9");
      for (int r = 0; r < 9; r++) {
         for (int c = 0; c < 9; c++) {
             String strVal = board[r][c];
            
            if (!store.contains(strVal)) {
               return false;
            }
         }
      }
      return true;
      
   }
   
   private boolean isValidRow() {
      for (int r = 0; r< 9; r++) {
         Set<String> store2 = new HashSet<String>();
         for (int c = 0; c < 9; c++) {
            String strVal = board[r][c];
            if (!strVal.equals(".")) {
               if (store2.contains(strVal)) {
                  return false;
               } else {
                  store2.add(strVal);
            }
         }
      }
   }
   return true;
}

   private boolean isValidC() {
      for (int c = 0; c< 9; c++) {
         Set<String> store3 = new HashSet<String>();
         for (int r = 0; r < 9; r++) {
            String strVal = board[r][c];
            if (!strVal.equals(".")) {
               if (store3.contains(strVal)) {
                  return false;
               } else {
                  store3.add(strVal);
            }
         }
      }
   }
   return true;
}   

      private String[][] miniSquare(int spot) {
      String[][] mini = new String[3][3];
      for(int r = 0; r < 3; r++) {
         for(int c = 0; c < 3; c++) {
            mini[r][c] = board[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }
      }
      return mini;
   }
   private boolean isValidMS() {
      for (int spot = 1; spot<= 9; spot++) {
         Set<String> store4 = new HashSet<>();
         String[][] minVal = miniSquare(spot);
         
         for (int r=0; r < 3; r++) {
            
            for (int c= 0; c< 3; c++) {
               String strVal = minVal[r][c];
               if (!strVal.equals(".")) {
                  if (store4.contains(strVal)) {
                     return false;
                  } else {
                     store4.add(strVal);
            }
            }
         }
         
      }
      
   }
   return true;
}

   public boolean isSolved() {
      Map<String,Integer> counter = new HashMap<>();
      for (int i = 1; i <= 9; i++) {
         counter.put(""+i, 0);
      }
      for (int r = 0; r < 9; r++) {
         for (int c = 0; c< 9; c++) {
            String strVal = board[r][c];
            if (counter.containsKey(strVal)) {
               int counts = counter.get(strVal);
               counts++;
               counter.put(strVal,counts);
            }
         }
      }
      
      return true;
      
    
   }
}


