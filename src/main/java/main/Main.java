package main;

import repository.RepositoryImp;
import specification.ProjectSpecification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        ProjectSpecification repositoryImp = new RepositoryImp();

        String str;
        Scanner in = new Scanner(System.in);

        while (true){

            str = in.nextLine();

            if(str.equals("quit")) break;
            String[] parts = str.split("\\s+");


            try {
                if(parts[0].equals("makedir")){
                    repositoryImp.initialiseDirectory(parts[1],parts[2],parts[3]);
                }

                if(parts[0].equals("changedir")){
                    repositoryImp.changeDir(parts[1],parts[2],parts[3]);
                }

                if(parts[0].equals("login")){
                    repositoryImp.logIn(parts[1],parts[2]);
                }

                if(parts[0].equals("logout")){
                    repositoryImp.logOut();
                }

                if(parts[0].equals("makefile")){
                    repositoryImp.createFile(parts[1],0);
                }
                if(parts[0].equals("makefiles")){
                    repositoryImp.createFile(parts[1],1);
                }

                if(parts[0].equals("makefolder")){
                    repositoryImp.createStorage(parts[1],0);
                }

                if(parts[0].equals("makefolders")){
                    repositoryImp.createStorage(parts[1],1);
                }

                if(parts[0].equals("deletefile")){
                    repositoryImp.deleteFile(parts[1],0);
                }

                if(parts[0].equals("deletefiles")){
                    repositoryImp.deleteFile(parts[1],1);
                }

                if(parts[0].equals("deleteall")){
                    repositoryImp.deleteFile(parts[1],2);
                }

                if(parts[0].equals("move")){
                    repositoryImp.moveFile(parts[1],parts[2]);
                }

                if(parts[0].equals("makeuser")){
                    String[] tokens = parts[4].split(",");
                    repositoryImp.createUser(parts[1],parts[2],parts[3], Arrays.asList(tokens));
                }

                if(parts[0].equals("download")){
                    repositoryImp.downloadFile(parts[1]);
                }

                if(parts[0].equals("makeconfig")){
                    String[] folders = parts[2].split(",");
                    String[] nums = parts[3].split(",");
                    List<Integer> ints = new ArrayList<>();
                    for(String num:nums){
                        ints.add(Integer.valueOf(num));
                    }
                    String[] filetypes = parts[4].split(",");
                    repositoryImp.createConfig(Integer.valueOf(parts[1]),Arrays.asList(folders),ints,Arrays.asList(filetypes));
                }

                if(parts[0].equals("list")){
                    if(parts[1].equals("filesdir"))
                        repositoryImp.viewFile(parts[2],1);
                    if(parts[1].equals("filesdirsubdir"))
                        repositoryImp.viewFile(parts[2],2);
                    if(parts[1].equals("foldersdirsubdir"))
                        repositoryImp.viewFile(parts[2],3);
                    if(parts[1].equals("foldersdir"))
                        repositoryImp.viewFile(parts[2],4);
                    if(parts[1].equals("sortedfiles"))
                        repositoryImp.viewFile(parts[2],5);
                    if(parts[1].equals("sortedfolders"))
                        repositoryImp.viewFile(parts[2],6);
                    if(parts[1].equals("filesbydatecreated"))
                        repositoryImp.viewFile(parts[2],7);
                    if(parts[1].equals("filesbydatemodified"))
                        repositoryImp.viewFile(parts[2],8);
                }

                if(parts[0].equals("upload")){

                }


            }catch (IndexOutOfBoundsException e){
                System.out.println("wrong syntax");
            }

        }

    }
}
