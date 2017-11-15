/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 *
 * Subsequência comum mais longa (LCS)
 * Página 285 Cormen 3 ed
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal {

    static final int DIAGONAL=1;
    static final int PARACIMA=2;
    static final int VOLTAR=3;
    
    //Matrizes auxiliares
    static int[][] b;
    static int[][] c;

    /**
     * Imprime a tabela calculada.
     *
     * @param X Subsequência 1
     * @param Y Subsequência 1
     */
    public static void imprimirTabelas(String X, String Y) {
        int m = X.length()+1; //Colunas
        int n = Y.length()+1; //Linhas        
        
        System.out.printf("\t \t");
        System.out.printf("\t yi \t");
        for ( int j = 0; j < n-1; j++ ) {
            System.out.printf("\t %s \t", Y.charAt(j));             
        }               
        System.out.println();
        
        for (int i = 0; i < m; i++) {
            if (i==0){            
                System.out.printf("\t xi \t");
            } else {
                System.out.printf("\t %s \t", X.charAt(i-1));
            }
            //System.out.printf("\t 0 \t");
            for (int j = 0; j < n; j++) {                
                char letra = ' ';                
                switch ( b[i][j] ) {
                    case DIAGONAL:
                        letra = 'D';
                        break;
                    case PARACIMA:
                        letra = 'C';
                        break;
                    case VOLTAR:
                        letra = 'V';
                        break;
                }                 
                System.out.printf("\t %d %s \t", c[i][j], letra);                                
            }
            System.out.println();
        }
        System.out.println("Legenda: D=Diagonal, C=Cima e V=Voltar");
    }

    /**
     * Encontra a subsequência comum mais longa.
     * @param X Subsequência 1
     * @param Y Subsequência 1
     */
    public static void subsequenciaComunMaisLonga(String X, String Y) {
        int m = X.length()+1; //Colunas
        int n = X.length()+1; //Linhas
        b = new int[m][n];
        c = new int[m][n];
        for (int i = 0; i < m; i++) {
            c[i][0] = 0;
        }
        for (int j = 0; j < n; j++) {
            c[0][j] = 0;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {               
                if (X.charAt(i-1) == Y.charAt(j-1)) {                    
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = DIAGONAL;
                } else {
                    if (c[i - 1][j] >= c[i][j - 1]) {
                        c[i][j] = c[i - 1][j];
                        b[i][j] = PARACIMA;
                    } else {                        
                       c[i][j] = c[i][j - 1];                        
                       b[i][j] = VOLTAR;
                    }
                }
            }
        }        
    }

    /**
     * Imprime a subsequencia comum mais longa.
     * @param b Matriz da solução ótima
     * @param X Sequência a ser verificada
     * @param i Posição em sequência X
     * @param j Posição na sequência Y
     */
    public static void printLCS(int[][] b, String X, int i, int j) {
        if ( (i == 0) || (j == 0) ) {
            return;
        }
        if ( b[i][j] == DIAGONAL) {
            printLCS(b, X, i - 1, j - 1);
            System.out.print(X.charAt(i - 1));
        } else {
            if (b[i][j] == PARACIMA ) {
                printLCS(b, X, i - 1, j);
            } else {
                printLCS(b, X, i, j - 1);        
            }
        }               
    }   
    
    public static void main(String args[]) {
        //Subsequências a serem analisadas
        String X = "ABCDAB";
        String Y = "BDCABA";

        System.out.println("Subsequencia Comun mais longa:");
        
        //Calcula a subsequência
        subsequenciaComunMaisLonga(X, Y);
        
        //Mostra as tabelas
        imprimirTabelas(X,Y);
        
        //Mostra o resultado
        System.out.println();
        System.out.println("Resultado LCS:");
        printLCS(b,X,X.length(),Y.length());
        System.out.println();
    }
}
