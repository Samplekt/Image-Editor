/**
 * Created by Keaton Sample on 11/30/2016.
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class Image {
    protected String file;
    int[][] pixels;
    protected String magic;
    protected int rows;
    protected int cols;
    protected int depth;

    public Image(String file) throws FileNotFoundException {
        this.file = file;

        Scanner sc = new Scanner(new File(file));
        magic = sc.nextLine();

        //String dimension = sc.nextLine();
        rows = sc.nextInt();
        cols = sc.nextInt();
        //System.out.println(rows+","+cols);
        //String[] parts = dimension.split(" ");
       // rows = Integer.parseInt(parts[0]);
       // cols = Integer.parseInt(parts[1]);

        depth = sc.nextInt();

        pixels = new int[rows][cols*3];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols*3; j++){
                pixels[i][j]=sc.nextInt();
                //System.out.print(pixels[i][j]+" ");
            }
            //System.out.println();
        }
}
public void printfile(String file) throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(file);
    writer.println(magic);
    writer.println(rows+" "+cols);
    writer.println(depth);
    for(int i = 0; i < rows; i++){
        for(int j = 0; j < cols*3; j++){
            writer.print(pixels[i][j]+" ");
        }
        writer.println();
    }
    writer.close();
}
public void negate_red(){
    for(int i = 0; i < rows; i++){
        for(int j = 0; j < cols*3; j+=3){
            pixels[i][j] = Math.abs((pixels[i][j] - depth));
            //System.out.print(pixels[i][j]+" ");
        }
        //System.out.println();
    }
}
    public void negate_green(){
        for(int i = 0; i < rows; i++){
            for(int j = 1; j < cols*3; j+=3){
                pixels[i][j] = Math.abs((pixels[i][j] - depth));
                //System.out.print(pixels[i][j]+" ");
            }
            //System.out.println();
        }
    }
    public void negate_blue(){
        for(int i = 0; i < rows; i++){
            for(int j = 2; j < cols*3; j+=3){
                pixels[i][j] = Math.abs((pixels[i][j] - depth));
                //System.out.print(pixels[i][j]+" ");
            }
            //System.out.println();
        }
    }

    public void flatten_red(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols*3; j+=3){
                pixels[i][j] = 0;
                //System.out.print(pixels[i][j]+" ");
            }
            //System.out.println();
        }
    }
    public void flatten_green(){
        for(int i = 0; i < rows; i++){
            for(int j = 1; j < cols*3; j+=3){
                pixels[i][j] = 0;
                //System.out.print(pixels[i][j]+" ");
            }
            //System.out.println();
        }
    }
    public void flatten_blue(){
        for(int i = 0; i < rows; i++){
            for(int j = 2; j < cols*3; j+=3){
                pixels[i][j] = 0;
                //System.out.print(pixels[i][j]+" ");
            }
            //System.out.println();
        }
    }
    public void grey_scale(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols*3; j+=3){
                int r = pixels[i][j];
                int g = pixels[i][j+1];
                int b = pixels[1][j+2];
                int avg = ((r+g+b)/3);
                pixels[i][j]   = avg;
                pixels[i][j+1] = avg;
                pixels[i][j+2] = avg;
                //System.out.print(pixels[i][j]+" ");
            }
            //System.out.println();
        }
    }
    public void flip_horizontally(String output) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter(output);
        writer.println(magic);
        writer.println(rows +" "+ cols);
        writer.println(depth);

        int [][] horiz = new int[rows][cols*3];
        for(int i = 0; i < rows;i++){

            for(int j = cols*3 - 1;j > -1; j--) {
                int count = 0;

                horiz[i][count] = pixels[i][j];
                horiz[i][j] = horiz[i][count];

                writer.print(horiz[i][count]+" ");
                count -= 1;
            }
            writer.println();
        }
        writer.close();
    }


public static void main(String [] args) throws FileNotFoundException {
    System.out.println("Portable Pixmap (PPM) Image Editor!");
    System.out.println();
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter name of image file: ");
    String path = sc.nextLine();
    try{
        Scanner test = new Scanner(new File(path));
    }catch (FileNotFoundException a){
        System.out.println("File Not Found");
        System.exit(0);
    }

    System.out.print("Enter name of output file: ");
    String output = sc.nextLine();
    System.out.println();

    System.out.println("Here are your choices:");
    System.out.println("[1]  convert to greyscale [2]  flip horizontally");
    System.out.println("[3]  negative of red [4]  negative of green");
    System.out.println("[5]  negative of blue [6]  just the reds");
    System.out.println("7]  just the greens   [8]  just the blues");
    System.out.println();

    Image ppm = new Image(path);

    System.out.print("Do you want [1]? (y/n)  ");
    String one = sc.nextLine();
    System.out.print("Do you want [2]? (y/n)  ");
    String two = sc.nextLine();
    System.out.print("Do you want [3]? (y/n)  ");
    String three = sc.nextLine();
    System.out.print("Do you want [4]? (y/n)  ");
    String four = sc.nextLine();
    System.out.print("Do you want [5]? (y/n)  ");
    String five = sc.nextLine();
    System.out.print("Do you want [6]? (y/n)  ");
    String six = sc.nextLine();
    System.out.print("Do you want [7]? (y/n)  ");
    String seven = sc.nextLine();
    System.out.print("Do you want [8]? (y/n)  ");
    String eight = sc.nextLine();
    if (one.equals("y")){
        ppm.grey_scale();
    }
    if (three.equals("y")){
        ppm.negate_red();
    }
    if (four.equals("y")){
        ppm.negate_green();
    }
    if (five.equals("y")){
        ppm.negate_blue();
    }
    if (six.equals("y")){
        ppm.flatten_green();
        ppm.flatten_blue();
    }
    if (seven.equals("y")){
        ppm.flatten_blue();
        ppm.flatten_red();
    }
    if (eight.equals("y")){
        ppm.flatten_green();
        ppm.flatten_red();
    }
    switch (two){
        case "y":
            ppm.flip_horizontally(output);
            break;
        case "n":
            ppm.printfile(output);
            break;
    }


    System.out.println();
    System.out.println(output+" created.");
}
}
