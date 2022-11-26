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
            Class.forName("repository.RepositoryImp");
//            Class.forName("repository.Drive");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

                else if(parts[0].equals("makefile")){
                    sm.createFile(parts[1],0);
                }

                else if(parts[0].equals("makefiles")){
                    sm.createFile(parts[1],1);
                }

                else if(parts[0].equals("makefolder")){
                    sm.createStorage(parts[1],0);
                }

                else if(parts[0].equals("makefolders")){
                    sm.createStorage(parts[1],1);
                }

                //delete folder1/file.txt ili ako brisemo ceo folder delete folder1
                else if(parts[0].equals("delete")){
                    sm.deleteFile(parts[1],1);
                }

//                "deletefiles folder1/f1.txt,folder1/f2.txt"
                else if(parts[0].equals("deletefiles")){
                    sm.deleteFile(parts[1],2);
                }

//               "move folder1/mm.txt folder2"
                else if(parts[0].equals("move")){
                    sm.moveFile(parts[1],parts[2]);
                }


                else if(parts[0].equals("download")){
                    sm.downloadFile(parts[1]);
                }

                else if(parts[0].equals("makeconfig")){

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

                else if(parts[0].equals("list")){

                    int param = Integer.valueOf(parts[1]);

                    switch (param){
                        case 1:
                            //vrati sve falove iz direktorijuma i poddirektorijuma
                            // primer: list 1 folder1
                            System.out.println(sm.viewFile(parts[2],param,null));
                            break;
                        case 2:
                            //vrati sve sa odredjenom ekstenzijom
                            // primer: list 2 jpeg
                            System.out.println(sm.viewFile(null,param,parts[2]));
                            break;
                        case 3:
                            // vrati sve koji pocinju ili se zavrsavaju nekim podstringom
                            // primer: list 3 pp
                            System.out.println(sm.viewFile(null,param,parts[2]));
                            break;
                        case 4:
                            //vratiti da li određeni direktorijum sadrži fajl sa određenim imenom, ili više
                            //fajlova sa zadatom listom imena
                            //primer:  list 4 folder1 app.txt
                            String[] params = parts[3].split(",");
                            System.out.println(sm.viewFile(parts[2],param,Arrays.asList(params)));
                            break;
                        case 5:
                            //vrati u kom folderu se nalazi fajl sa odredjenim imenom
                            //primer: list 5 app.txt
                            System.out.println(sm.viewFile(null,param,parts[2]));
                            break;
                        case 6:
                            //obezbediti zadavanje različitih kriterijuma sortiranja, na primer po nazivu,
                            //datumu kreiranje ili modifikacije, rastuće ili opadajuće,
                            // primer: list 6 created desc
                            System.out.println(sm.viewFile(parts[2],param,parts[3]));
                            break;
                        case 7:
                           // vrati fajlove koji su kreirani modifikovani u nekom periodu
                           // primer: list 7 folder1 25/11/2022 26/11/2022
                            System.out.println(sm.viewFile(parts[2],param,Arrays.asList(parts[3],parts[4])));
                            break;
                    }

                }

                else if(parts[0].equals("filter")){
                    // filtriranje podataka koji se prikazuju za fajlove koji su rezultati pretraga
                    // primer: filter fileSize
                    System.out.println(sm.filterFiles(parts[1]));
                }

                else{
                    System.out.println("wrong syntax");
                }



            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println("wrong syntax");
            }

        }

    }
}
