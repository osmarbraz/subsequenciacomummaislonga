/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 *
 * Subsequência comum mais longa 
 * Longest Comum Subsequence(LCS)
 *
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
     * Mostra na tela tabela calculada.
     * 
     * Utiliza as matrizes c e b calculadas pela operação subsequenciaComunMaisLonga.
     * Conforme exemplo página 288 Livro Cormen 3 ed.
     *
     * @param X Subsequência 1
     * @param Y Subsequência 2
     */
    public static void imprimirTabelas(String X, String Y) {
        int m = X.length()+1; //Linhas
        int n = Y.length()+1; //Colunas        
        
        System.out.println("Tabela Calculada para o LCS de X e Y ");
        System.out.printf("\t j \t");
        for ( int j = 0; j < n; j++ ) {            
            System.out.printf(" %d \t", j);             
        }               
        System.out.println(); //Pula a linha       
        
        System.out.printf("i\t");        
        System.out.printf("\t y[j] \t");
        for ( int j = 0; j < n-1; j++ ) {            
            System.out.printf(" %s \t", Y.charAt(j));             
        }               
        System.out.println();//Pula a linha
        
        for (int i = 0; i < m; i++) {
            System.out.printf("%d \t",i);
            if (i==0){            
                System.out.printf("x[i] \t");
            } else {
                System.out.printf("%s \t", X.charAt(i-1));
            }            
            for (int j = 0; j < n; j++) {                                
                char seta = ' ';
                switch ( b[i][j] ) {
                    case DIAGONAL:                        
                        seta = 8598;
                        break;
                    case PARACIMA:
                        seta = 8593;
                        break;
                    case VOLTAR:
                        seta = 8592;                        
                        break;
                }                 
                System.out.printf(" %d %s \t", c[i][j], seta);                                
            }
            System.out.println();
        }        
    }  
    
    /**
     * Encontra a subsequência comum mais longa.
     * 
     * Utilizando programação dinâmica para encontrar a subsequência comum mais longa.
     * 
     * Complexidade O(m*n)
     * 
     * @param X Subsequência 1
     * @param Y Subsequência 2
     */
    public static void subsequenciaComunMaisLonga(String X, String Y) {
        int m = X.length()+1; //Linhas
        int n = Y.length()+1; //Colunas
        //Inicializa os vetores do resultado
        b = new int[m][n];
        c = new int[m][n];
        for (int i = 1; i < m; i++) {
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
     * 
     * Complexidade de tempo O(m+n)
     * 
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
        //Problema Livro Cormen pag 285
        String X = "ABCBDAB";
        String Y = "BDCABA";
        
        //Problema Prova Prof Meidanis
        //String X = "ATGCGTACT";
        //String Y = "CTGATAGAT";

        System.out.println("Subsequência Comun mais longa");
        
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
