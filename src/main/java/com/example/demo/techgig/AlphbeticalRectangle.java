package com.example.demo.techgig;


import java.util.Arrays;

public class AlphbeticalRectangle {
    public enum alphabets {A(1),B(2),C(3),D(4),E(5),F(6),G(7),H(8),I(9),J(10),K(11),L(12),M(13),N(14),O(15),P(16),Q(17),R(18),S(19),T(20),U(21),V(22),W(23),X(24),Y(25),Z(26);
    private int value ;
        private alphabets(int value){
            this.value=value;
        }
    };
    public static void main (String[] args){
        int limit =26;


              Arrays.stream(alphabets.values()).forEach(alphabets -> {
                  int i =1;

                  while (i<limit+1) {

                      for (int k = 0; k <limit+1 ; k++) {
                          if (alphabets.value ==i)
                              System.out.print(alphabets.name());
                      }

                    i++;
                  }
                  System.out.println();
              });


    }
}
