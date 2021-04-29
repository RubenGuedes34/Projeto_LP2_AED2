package edu.ufp.inf.lp2.geocaching;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import static edu.ufp.inf.lp2.geocaching.UserAdmin.cacheST;
import static edu.ufp.inf.lp2.geocaching.UserAdmin.userST;

public class Main {

    private static final String FILE_DELIMETER = ";";

    public static void main(String[] args) {

        //---------[Criação de utilizadores]---------
        UserBasic u1= new UserBasic("40097", "Tomas", 1,2);
        UserBasic u2= new UserBasic("39917", "Ruben", 3,4);
        UserPremium u3= new UserPremium("1904", "Leticia", 5,6);
        UserPremium u4= new UserPremium("5555", "Leonor", 7,8);
        UserAdmin u5= new UserAdmin("1001", "Ivone", 9, 10 );
        UserAdmin u6= new UserAdmin("7004", "Arriscado", 11,12);

        //---------[Inserção de Utilizadores na ST]---------


        u5.insertUser(u1);
        u6.insertUser(u2);
        u5.insertUser(u3);
        u6.insertUser(u4);
        u5.insertUser(u5);
        u6.insertUser(u6);

        //---------[Print Utilizadores]---------
        printUsers();

/*
        //---------[Editar Utilizador da ST]---------
        u1.editUser("TomasEditado",12,55);
        u2.editUser("RubenEditado", 66,77);
        u3.editUser("LeticiaEditada", 343, 314);
        u4.editUser("LeonorEditada", 22, 55);
        u5.editUser("IvoneEditada", 66,88);
        u6.editUser("ArriscadoEditada", 78, 32);
        u1.printUser();

        //---------[Remover Utilizador]---------
        u6.removeUser("7004");
        u1.printUser();

        */

        //---------[Escrever e Ler Utilizadores do ficheiro]---------
        //writeUser_txt(".//data/user.txt");
        //readUser_txt(userST, ".//data/user.txt");
        //printUsers();


        //---------[Caches de objetos e Caches]---------
        Objeto rato = new Objeto("44","rato");
        Objeto teclado= new Objeto("55", "teclado");
        Objeto monitor = new Objeto("66", "monitor");

        Cache c1= new Cache("11",CacheDiff.Easy,u1, rato ,1,3,"Lisboa");
        Cache c2= new Cache("21",CacheDiff.Medium,u2, teclado ,6,8,"Porto");
        Cache c3= new Cache("31",CacheDiff.Hard,u3, monitor ,7,4,"Cardielos");

        //---------[Inserção de Cache na ST]---------
        u5.insertCache(c1);
        u6.insertCache(c2);
/*
        //---------[Print Caches]---------
        u5.printCache();

        //---------[Editar Caches da ST]---------
        u5.editCache(c1,"33",CacheDiff.Hard,u1,8,9,"Viana");
        u5.printCache();

        //---------[Remover Caches da ST]---------
        u5.removeCache(c1);
        u5.removeCache(c2);
        u5.printCache();
*/

        //---------[Escrever e Ler Caches e Objetos do ficheiro]---------
        writeCache_txt(".//data/cache.txt");
        writeObjeto_txt(".//data/objeto.txt");
        //falta ler da cache e do objeto
        u5.printCache();


    }

    private static void readUser_txt(String path){
        In in = new In(path);
        in.readLine();

        while (!in.isEmpty()){
            String line = in.readLine();
            String [] fields = line. split(FILE_DELIMETER);

            UserBasic u = new UserBasic(fields[0], fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]));
            userST.put(u.getName(),u);
        }
    }

    private static void printUsers(){
        for (String name : userST.keys()){
            UserBasic u = userST.get(name);
            System.out.println(userST.get(name));
        }
        System.out.println("\n");
    }

    private static void writeUser_txt(String path){
        Out out = new Out(path);

        for (String u: userST.keys()){
            out.println( userST.get(u).getId() + " " + userST.get(u).getName() + " " + userST.get(u).getX() + " " +userST.get(u).getY());
        }
        System.out.println("\n");
    }

    private static void writeCache_txt(String path){
        Out out = new Out(path);

        for (String u: cacheST.keys()){
            out.println(cacheST.get(u).getSerialNumber() + " " + cacheST.get(u).type + " " + cacheST.get(u).obj.nameItem + " " + cacheST.get(u).getX() + " " + cacheST.get(u).getY() + " " + cacheST.get(u).getRegiao() );
        }
        System.out.println("\n");
    }

    private static void writeObjeto_txt(String path){
        Out out = new Out(path);

        for (String u: cacheST.keys()){
            out.println(cacheST.get(u).getSerialNumber() + " " + cacheST.get(u).obj.nameItem );
        }
        System.out.println("\n");
    }

}

