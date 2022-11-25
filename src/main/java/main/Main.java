package main;

import repository.RepositoryImp;
import specification.ProjectSpecification;
import specification.StorageManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        try {
//            Class.forName("repository.LocalRepositoryImp");
            Class.forName("repository.Drive");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ProjectSpecification sm = StorageManager.getStorageManager();

        System.out.println("Welcome");

        String str;
        Scanner in = new Scanner(System.in);

        while (true){

            str = in.nextLine();

            if(str.equals("quit")) break;
            String[] parts = str.split("\\s+");


            try {
                if(parts[0].equals("makedir")){
                    sm.initialiseDirectory(parts[1]);
                }


                if(parts[0].equals("makefile")){
                    sm.createFile(parts[1],0);
                }

                if(parts[0].equals("makefiles")){
                    sm.createFile(parts[1],1);
                }

                if(parts[0].equals("makefolder")){
                    sm.createStorage(parts[1],0);
                }

                if(parts[0].equals("makefolders")){
                    sm.createStorage(parts[1],1);
                }

                //delete folder1/file.txt ili ako brisemo ceo folder delete folder1
                if(parts[0].equals("delete")){
                    sm.deleteFile(parts[1],1);
                }

//                "deletefiles folder1/f1.txt,folder1/f2.txt"
                if(parts[0].equals("deletefiles")){
                    sm.deleteFile(parts[1],2);
                }

//               "move folder1/mm.txt folder2"
                if(parts[0].equals("move")){
                    sm.moveFile(parts[1],parts[2]);
                }


                if(parts[0].equals("download")){
                    sm.downloadFile(parts[1]);
                }

                if(parts[0].equals("makeconfig")){

                    String[] folders = parts[1].split(",");

                    String[] nums = parts[2].split(",");
                    List<Integer> maxLens = new ArrayList<>();
                    for(String num:nums){
                        maxLens.add(Integer.valueOf(num));
                    }


                    String[] filetypes = parts[3].split(",");

                    List<Integer> intMaxSizes = new ArrayList<>();
                    String[] maxSize = parts[4].split(",");
                    for(String s:maxSize){
                        intMaxSizes.add(Integer.valueOf(s));
                    }

                    sm.createConfig(Arrays.asList(folders),maxLens,Arrays.asList(filetypes),intMaxSizes);

                }

                if(parts[0].equals("list")){
                    System.out.println("prikazi");
//
                }


            }catch (IndexOutOfBoundsException e){
                System.out.println(e);
                System.out.println("wrong syntax");
            }

        }

    }
}
