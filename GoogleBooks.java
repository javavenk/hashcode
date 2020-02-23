import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GoogleBooks {
    public static int B, L, D;
    public static int S[];
    public static int libraries[][];
    public static ArrayList<Integer[]> books;

    public static ArrayList<Integer[]> dlv;
    public static long mlv[];

    public static void main(String[] args) throws Throwable {
        Scanner scanner = new Scanner(new File("/Users/venkateshc/Downloads/a_example.txt"));
        B = scanner.nextInt();
        L = scanner.nextInt();
        D = scanner.nextInt();
        S=new int[B];
        for(int i=0;i<B;i++) {
            S[i]=scanner.nextInt();
        }
        libraries = new int [L][3];
        books = new ArrayList<>();

        mlv = new long[L];

        Integer bookArray[];
        for(int i = 0;i<L;i++) {
            libraries[i][0] = scanner.nextInt();
            libraries[i][1] = scanner.nextInt();
            libraries[i][2] = scanner.nextInt();
            bookArray = new Integer[libraries[i][0]];
            for(int j=0;j<libraries[i][0];j++) {
                bookArray[j]=scanner.nextInt();
                mlv[i]+=S[bookArray[j]];
            }
            books.add(bookArray);
        }
        scanner.close();

        /*bID = new int[B];
        for(int i = 0;i<B;i++) {
            bID[i] = i+1;
        }

        int t;
        for(int i = ;i<B;i++) {
            for (int j=i+1;j<B;j++) {
                if(S[i]<S[j]) {
                    t = S[i];
                    S[i] = S[j];
                    S[j] = t;
                    t = bID[i];
                    bID[i] = bID[j];
                    bID[j] = t;
                }
            }
        }*/

        int t;
        Integer booksOfLibraryI[];
        for(int i = 0;i<L;i++) {
            booksOfLibraryI=books.get(i);
            for(int j= 0;j<booksOfLibraryI.length;j++) {
                for(int k = j+1;k<booksOfLibraryI.length;k++) {
                    if(S[booksOfLibraryI[j]] < S[booksOfLibraryI[k]]) {
                        t = booksOfLibraryI[j];
                        booksOfLibraryI[j]=booksOfLibraryI[k];
                        booksOfLibraryI[k]=t;
                    }
                }
            }
        }

/*        dlv = new ArrayList<>();
        Integer libraryValue[];
        for(int i = 0;i<L;i++) {
            libraryValue = new Integer[(int) (libraries[i][1]+Math.ceil((libraries[i][0]*1.0)/libraries[i][2]))];
            for(int j=0;j<libraries[i][1];j++) {
                libraryValue[j]=0;
            }
            for(int j=0;j<libraries[i][0];j++) {
                libraryValue[libraries[i][1]+j/libraries[i][2]-1]+=S[books.get(i)[j]];
            }
            dlv.add(libraryValue);
        }
*/

        int LID[]=new int[L];
        for(int i =0;i<L;i++) {
            LID[i]=i;
        }

        long lt;
        for(int i = 0;i<L;i++) {
            for(int j=i+1;j<L;j++) {
                if(mlv[i]<mlv[j]) {
                    lt = mlv[i];
                    mlv[i]=mlv[j];
                    mlv[j]=lt;

                    t = LID[i];
                    LID[i]=LID[j];
                    LID[j]=t;
                }
            }
        }

        int lc = 0,durclid, tD = D;
        while(tD>0 && lc<L) {
            //durclid= (int) (libraries[LID[clid]][1]+Math.ceil((libraries[LID[clid]][1]*1.0)/libraries[LID[clid]][2]));
            durclid=libraries[LID[lc]][1];
            if(durclid<D) {
                tD-=durclid;
            }
            lc++;
        }


        FileWriter fos = new FileWriter(new File("solutionhashcode.txt"));
        fos.write(lc+"\n");

        for(int i = 0;i<tD;i++) {
            fos.write(""+(LID[i]+1)+ " " + libraries[LID[i]][0]+"\n");
            for(int j = 0;j< libraries[LID[i]][0];j++) {
                fos.write(""+books.get(i)[j]+" ");
            }
            fos.write("\n");
        }

        fos.close();



        System.out.println("yay!");
    }
}
