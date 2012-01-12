
// Copyright (c) 2000 living systems AG
package ro.cuzma.tools;

/**
 * A Class class.
 * <P>
 * @author Laurian Cuzma
 */
public class OracleUtils {

    public static String stringToOracle(String init){
      String temp = "";
      char curent;
      for(int i= 0; i < init.length();i++){
        curent = init.charAt(i);
        temp += curent;
        if (curent == '\''){
          temp += curent;
        }

      }
      return temp;
    }
}

 