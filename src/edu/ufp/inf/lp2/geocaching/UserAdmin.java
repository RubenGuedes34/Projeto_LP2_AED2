package edu.ufp.inf.lp2.geocaching;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.ST;

import java.util.ArrayList;


public class UserAdmin extends UserPremium {

    public static ST<String, UserBasic> userST = new ST<>();

    public static ST<String, Cache> cacheST = new ST<>();

    public UserAdmin(String name, String id) {
        super(name, id);
    }



   public void now() {
       System.out.println("\nFunção now: Localizacao dos TravelBugs do user " + this.name);
       for (String key : meusTravelBugs.keys()) {
           TravelBugs travelBugs = meusTravelBugs.get(key);
           System.out.println("\tTravel bug -> " + travelBugs.nameItem + " ja esteve nas seguintes localizacoes:");
           int size = travelBugs.historicoTravelBugsLogs.size() - 1;
           if (travelBugs.historicoTravelBugsLogs.size() > 0) {
               while (size >= 0) {
                   if (size == travelBugs.historicoTravelBugsLogs.size() - 1) {
                       TravelBugsLogs tblog = travelBugs.historicoTravelBugsLogs.get(size);
                       if (tblog.p == null) {
                           System.out.println("\t\tEncontra se neste moemnto na cache " + tblog.c.serialNumber + " e foi la deixada pelo user "
                                   + userST.get(tblog.user).name + " no dia " + tblog.date.print());
                       } else {
                           System.out.println("\t\tEncontra se neste moemnto com o o user  " + userST.get(tblog.user).name + " e retirado da cache "
                                   + tblog.cache + " no dia " + tblog.date.print());
                       }

                   } else {
                       if (size == travelBugs.historicoTravelBugsLogs.size() - 1) {
                           TravelBugsLogs tblog = travelBugs.historicoTravelBugsLogs.get(size);
                           if (tblog.p == null) {
                               System.out.println("\t\tEsteve na cache " + tblog.c.serialNumber + " e foi la deixada pelo user "
                                       + userST.get(tblog.user).name + " no dia " + tblog.date.print());
                           } else {
                               System.out.println("\t\tEsteve com o user  " + userST.get(tblog.user).name + " e retirado da cache "
                                       + tblog.cache + " no dia " + tblog.date.print());
                           }
                       }

                   }
                   size--;
               }
           }
       }
   }

    public void editUser(UserBasic user, String name) {
        user.name = name;
        userST.put(user.id, user);
    }


    public void printALLTravelBug() {
        System.out.println("Lista todos Travel Bugs\n");
        for (String name : userST.keys()) {
            UserBasic userBasic = userST.get(name);
            if (!userBasic.getClass().equals(UserBasic.class)) {
                UserPremium userPremium = (UserPremium) userBasic;
                if (userPremium.meusTravelBugs.size() > 0) {
                    System.out.println("Utilizador " + userPremium.name + " tem os TravelBugs :");
                    for (String id : userPremium.meusTravelBugs.keys()) {
                        TravelBugs travelBugs = userPremium.meusTravelBugs.get(id);
                        System.out.println(travelBugs.toString());
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("Utilizador " + userPremium.name + " nao tem TravelBugs\n");
                }

            }
        }


    }


    public void printALLTravelBugHCaches() {
        System.out.println("Lista todos Travel Bugs e o seu Historico de Caches\n");
        for (String name : userST.keys()) {
            UserBasic userBasic = userST.get(name);
            if (!userBasic.getClass().equals(UserBasic.class)) {
                UserPremium userPremium = (UserPremium) userBasic;
                if (userPremium.meusTravelBugs.size() > 0) {
                    System.out.println("Utilizador " + userPremium.name + " tem os TravelBugs :");
                    for (String id : userPremium.meusTravelBugs.keys()) {
                        TravelBugs travelBugs = userPremium.meusTravelBugs.get(id);
                        System.out.println( "\t" + travelBugs.toString());
                        for (String key :travelBugs.historicoCaches.keys()){
                            Cache cache= cacheST.get(key);
                            System.out.println("\t\t Este travelbug ja esteve na cache -> " + cache.serialNumber);

                        }
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("Utilizador " + userPremium.name + " nao tem TravelBugs\n");
                }

            }
        }


    }

    public void printALLTravelBugHUsers() {
        System.out.println("Lista todos Travel Bugs e o seu Historico de Users\n");
        for (String name : userST.keys()) {
            UserBasic userBasic = userST.get(name);
            if (!userBasic.getClass().equals(UserBasic.class)) {
                UserPremium userPremium = (UserPremium) userBasic;
                if (userPremium.meusTravelBugs.size() > 0) {
                    System.out.println("Utilizador " + userPremium.name + " tem os TravelBugs :");
                    for (String id : userPremium.meusTravelBugs.keys()) {
                        TravelBugs travelBugs = userPremium.meusTravelBugs.get(id);
                        System.out.println( "\t" + travelBugs.toString());
                        for (String key :travelBugs.historicoUsers.keys()){
                            UserPremium puser= (UserPremium) userST.get(key);
                            System.out.println("\t\t Este travelbug ja esteve com o user -> " + puser.name);

                        }
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("Utilizador " + userPremium.name + " nao tem TravelBugs\n");
                }

            }
        }


    }

    public void insertCache(Cache cache) {
        cacheST.put(cache.serialNumber, cache);
    }

    public void removeCache(Cache cache) {
        cacheST.remove(cache.serialNumber);
    }


    public void printCache() {
        System.out.println("Lista Caches: ");
        for (String cacheName : cacheST.keys()) {
            System.out.println(cacheST.get(cacheName));
        }
        System.out.println("\n");
    }

    public void printUser() {

        System.out.println("Lista Utilizadores: ");
        for (String name : userST.keys()) {
            System.out.println(userST.get(name));
        }
        System.out.println("\n");
    }

    public void print_r8a_global(UserBasic userBasic) {
        System.out.println();
        userBasic.printhCaches();
    }

    public void print_r8a_regiao(UserBasic userBasic, String regiao) {
        if (userBasic.hCaches.size() > 0) {
            System.out.println("\nUtilizador " + userBasic.name + " visitou as seguintes caches em " + regiao + "\n");
            for (Date d : userBasic.hCaches.keys()) {
                Cache c = userBasic.hCaches.get(d);
                if (c.regiao.equals(regiao)) System.out.println(c.toString() + " no dia " + d.print());
            }
            return;
        }
        System.out.println("\nUtilizador " + userBasic.name + " ainda nao visitou nenhuma cache em " + regiao + "\n");
    }

    public void printr8b(UserBasic userBasic) {
        System.out.println("O user " + userBasic.name + " nao visitou as seguintes caches:");
        Cache visitada = new Cache();
        for (String key : cacheST.keys()) {
            visitada = cacheST.get(key);
            boolean visitado = false;
            for (Date date : userBasic.hCaches.keys()) {
                Cache cache = userBasic.hCaches.get(date);

                if (cache.serialNumber.equals(visitada.serialNumber)) {
                    visitado = true;
                    break;
                }
            }
            if (!visitado) System.out.println("-> " + visitada.serialNumber + "\n");
        }
        System.out.println();

    }

    public void print_r8b_regiao(UserBasic userBasic, String regiao) {
        System.out.println("O user " + userBasic.name + " nao visitou as seguintes caches na regiao " + regiao);
        Cache cache = new Cache();
        for (String key : cacheST.keys()) {
            cache = cacheST.get(key);
            boolean visitado = false;
            for (Date date : userBasic.hCaches.keys()) {
                Cache visitada = userBasic.hCaches.get(date);

                if (cache.serialNumber.equals(visitada.serialNumber)) {
                    visitado = true;
                    break;
                }
            }
            if (!visitado && cache.regiao.equals(regiao)) System.out.println("-> " + cache.serialNumber + "\n");
        }
        System.out.println();

    }


    public void printr8c(Cache cache) {
        System.out.println(cache.serialNumber + " -> Utilizadores que visitaram esta cache:");
        for (Date date : cache.hUsers.keys()) {
            UserBasic userBasic = cache.hUsers.get(date);
            System.out.println("\t" + userBasic.name + " visitou esta cache no dia no dia " + date.print() + ".");
        }
        System.out.println();
    }

    public void printr8d() {//Todas as caches premium que têm pelo menos um objecto;
        System.out.println("Caches primium que tem objetos:");
        for (String key : cacheST) {
            Cache cache = cacheST.get(key);
            if (cache.type.equals(CacheType.Premium) && cache.meusObjetos.size() > 0) {
                System.out.println("\t" + cache.serialNumber);
            }

        }
    }

    public void printr8e(Date dinicial, Date dfinal) {//Top-5 de utilizadores que visitaram maior nº de caches num dado períodotemporal;
        ST<String, Integer> totalvisitas = new ST<>();
        for (String key : cacheST) {
            Cache cache = cacheST.get(key);
            for (CacheLogs clogs : cache.cacheLogs) {
                //se visitou entre as datas
                if (clogs.data.afterDate(dinicial) && clogs.data.beforeDate(dfinal) || clogs.data.equals(dinicial) || clogs.data.equals(dfinal)) {
                    String userid = clogs.userID;
                    if (totalvisitas.contains(userid)) {//se ser ja estiver na bst
                        totalvisitas.put(userid, totalvisitas.get(userid) + 1);//incremento 1 visita
                    } else totalvisitas.put(userid, 1);//se nao visitas = 1
                }
            }
        }

        int top5 = 0, topvisiter = 0, lastvisiter = 0;
        String user = "", lastusername = "";
        while (top5 < 5 && totalvisitas.size() > 0) {//caso nao haja mais que 5
            for (String id : totalvisitas.keys()) {

                if (top5 == 0 && totalvisitas.get(id) >= topvisiter) {
                    topvisiter = totalvisitas.get(id);
                    user = id;
                } else if (top5 > 0 && totalvisitas.get(id) > topvisiter && totalvisitas.get(id) <= lastvisiter && !lastusername.equals(id)) {
                    topvisiter = totalvisitas.get(id);
                    user = id;
                }

            }
            if (top5 == 0) System.out.println("Top 5 utilizadores mais ativos:");
            System.out.println("Top " + (top5 + 1) + "-> " + userST.get(user).name + " com um total de visitas de: " + topvisiter);
            lastusername = user;
            lastvisiter = topvisiter;
            topvisiter = 0;
            top5++;
            totalvisitas.delete(user);
        }


    }

    public void printr8f() {
        ArrayList<TravelBugs> travelBugs = new ArrayList<>();
        for (String id : userST) {
            if (userST.get(id).getClass().equals(UserPremium.class) || userST.get(id).getClass().equals(UserAdmin.class)) {
                UserPremium userPremium = (UserPremium) userST.get(id);
                for (String key : userPremium.meusTravelBugs.keys()) {
                    if (userPremium.meusTravelBugs.get(key).historicoCaches.size() > 0)
                        travelBugs.add(userPremium.meusTravelBugs.get(key));

                }
            }
        }
        if (travelBugs.size() == 0) {
            System.out.println("\nNao existem TravelBugs que tenham historico de cache.");
            return;
        }
        System.out.println("\nTop TravelBugs mais ativos:");

        int max = 0, top = 0;
        while (travelBugs.size() > 0) {
            TravelBugs auxiliar = new TravelBugs();
            for (TravelBugs travelBugs1 : travelBugs) {
                if (travelBugs1.historicoCaches.size() > max) {
                    auxiliar = travelBugs1;
                    max = travelBugs1.historicoCaches.size();
                }
            }
            System.out.println("\tTop" + (top + 1) + ": Travel Bug " + auxiliar.nameItem + " ja percorreu um total de locais de " + auxiliar.historicoCaches.size() + ".\n");
            max = 0;
            travelBugs.remove(auxiliar);
            top++;
        }

    }

    public void printObjetosAllCaches() {
        System.out.println("Lista todos Objetos das Caches\n");
        for (String key : cacheST.keys()) {
            Cache c = cacheST.get(key);
            if (c.meusObjetos.size() > 0) {
                System.out.println("Objetos da cache " + c.serialNumber + ":");
                for (String obj : c.meusObjetos.keys()) {
                    System.out.println("\t" + c.meusObjetos.get(obj).toString() + "\n");
                }
            }

        }
        System.out.println("\n");
    }

    public void printObjetosAllUsers() {
        System.out.println("Lista todos Objetos dos Users\n");
        for (String key : userST.keys()) {
            UserBasic user = userST.get(key);
            if (user.myObjetos.size() > 0) {
                System.out.println("Objetos do User " + user.name + ":");
                for (String obj : user.myObjetos.keys()) {
                    System.out.println("\t" + user.myObjetos.get(obj).toString());
                }
            }
            System.out.println("\n");
        }
    }

    public void printUsers_historicoCaches() {
        System.out.println("Historico de Caches do Utilizadores:\n");
        for (String key : userST.keys()) {
            UserBasic userBasic = userST.get(key);
            if (userBasic.hCaches.size() > 0) {
                System.out.println("\tUtilizador " + userBasic.name + " visitou as seguintes caches:\n");
                for (Date d : userBasic.hCaches.keys()) {
                    Cache cache = userBasic.hCaches.get(d);
                    System.out.println("\t\tCache: " + cache.serialNumber + " , no dia -> " + d.print() + "\n");
                }
            } else System.out.println("\tUtilizador " + userBasic.name + " nao visitou caches.\n");
        }

    }

    public void printCaches_historicoUsers() {
        System.out.println("Historico de Utilizadores das Caches:\n");
        for (String key : cacheST.keys()) {
            Cache c = cacheST.get(key);
            if (c.hUsers.size() > 0) {
                System.out.println("\tCache " + c.serialNumber + " recebeu as visitas dos seguintes utilizadores:\n");
                for (Date d : c.hUsers.keys()) {
                    UserBasic user = c.hUsers.get(d);
                    System.out.println("\t\tUser: " + user.name + " , no dia -> " + d.print() + "\n");
                }
            } else System.out.println("\tCache " + c.serialNumber + " nao recebeu as visitas.\n");

        }
    }

    public void printCacheLogs() {
        System.out.println("\n\nLogs das Caches:\n");
        for (String serial : cacheST.keys()) {
            Cache cache = cacheST.get(serial);
            if (cache.cacheLogs.size() > 0) {
                System.out.println("\tCache ->" + cache.serialNumber + " Logs:");
                for (CacheLogs clogs : cache.cacheLogs) {
                    System.out.println("\t\t->" + clogs.toString());
                }
            }

        }
    }

    public void printUserLogs() {
        System.out.println("\n\nLogs das Users:\n");
        for (String id : userST.keys()) {
            UserBasic userBasic = userST.get(id);
            if (userBasic.userLogs.size() > 0) {
                System.out.println("\tUser -> " + userBasic.name + " Logs:");
                for (Date date : userBasic.userLogs.keys()) {
                    UserLogs userLogs = userBasic.userLogs.get(date);
                    System.out.println("\t\t->" + userLogs.toString());
                }
            }

        }
    }

    public void printMessageLogs(){
        System.out.println("\nMessage Logs de todas as caches:\n");
        for (String key : cacheST.keys()){
            Cache cache= cacheST.get(key);
            if(cache.messageLogs.size()>0) System.out.println("Cache " + cache.serialNumber + ":\n");
            for (MessageLog messageLog: cache.messageLogs){
                System.out.println("\t-> " + messageLog.mensagem + ".\n");
            }
        }
    }



    private static TravelBugs findTravelBug(String idTB) {
        for (String name : userST.keys()) {
            UserBasic userBasic = userST.get(name);
            if (!userBasic.getClass().equals(UserBasic.class)) {
                UserPremium userPremium = (UserPremium) userBasic;
                if (userPremium.meusTravelBugs.size() > 0) {
                    for (String id : userPremium.meusTravelBugs.keys()) {
                        TravelBugs travelBugs = userPremium.meusTravelBugs.get(id);
                        if(travelBugs.id.equals(idTB))return travelBugs;

                    }
                }
            }
        }
        return null;
    }


    public void editCache(Cache cache, String serialNumber, CacheDiff type, UserPremium usercreator, int x, int y, String regiao) {
        cache.serialNumber = serialNumber;
        cache.diff = type;
        cache.userCreator = usercreator;
        cache.x = x;
        cache.y = y;
        cache.regiao = regiao;
    }


    public void printAllTravelBugsLogs(){
        System.out.println("\nLogs dos Travel Bugs:");
        for (String id : userST.keys()) {
            UserBasic userBasic = userST.get(id);
            if (!userBasic.getClass().equals(UserBasic.class)) {
                UserPremium userPremium = (UserPremium) userBasic;
                if (userPremium.meusTravelBugs.size() > 0) {
                    System.out.println("User "+ userPremium.name +" tem os seguintos travelBugs:");
                    for (String id2 : userPremium.meusTravelBugs.keys()) {
                        TravelBugs travelBugs = userPremium.meusTravelBugs.get(id2);
                        System.out.println("\t-> " + travelBugs.nameItem);
                        for (TravelBugsLogs travelBugsLogs : travelBugs.historicoTravelBugsLogs){
                            if(travelBugsLogs.p==null){
                                System.out.println("\t\t O user " + travelBugsLogs.user + " colocou me na cache " + travelBugsLogs.cache + " no dia " +
                                        travelBugsLogs.date.print() + " Cache destino: " + travelBugsLogs.destinoConcluido);
                            }
                            else if (travelBugsLogs.c==null){
                                System.out.println("\t\t O user " + travelBugsLogs.user + " retirou me me na cache " + travelBugsLogs.cache + " no dia " +
                                        travelBugsLogs.date.print() + " Cache destino: " + travelBugsLogs.destinoConcluido);
                            }
                        }
                    }
                }
            }
        }

    }


    ///////////////////////////////////////////                   FICHEIROS                 //////////////////////////////////////////


    public static void saveUsers() {
        Out out = new Out(".//data//user.txt");
        for (String id : userST) {
            UserBasic user = userST.get(id);
            if (user.getClass().equals(UserBasic.class))
                out.print(user.name + "," + user.id + "," + user.cachesVisitadas + "," + "BASIC\n");
            else if (user.getClass().equals(UserPremium.class))
                out.print(user.name + "," + user.id + "," + user.cachesVisitadas + "," + "PREMIUM\n");
            else out.print(user.name + "," + user.id + "," + user.cachesVisitadas + "," + "ADMIN\n");
        }
        out.close();

    }

    public static void readUsers() {
        In in = new In(".//data//user.txt");

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] words = line.split(",");
            switch (words[3]) {
                case "BASIC":
                    UserBasic user = new UserBasic(words[0], words[1]);
                    user.cachesVisitadas = Double.parseDouble(words[2]);
                    user.insertUser();
                    break;
                case "PREMIUM":
                    UserPremium puser = new UserPremium(words[0], words[1]);
                    puser.cachesVisitadas = Double.parseDouble(words[2]);
                    puser.insertUser();
                    break;
                case "ADMIN":
                    UserAdmin auser = new UserAdmin(words[0], words[1]);
                    auser.cachesVisitadas = Double.parseDouble(words[2]);
                    auser.insertUser();
                    break;
            }

        }
        in.close();

    }

    public static void saveCaches(){
        Out out = new Out(".//data//cache.txt");
        for (String serialNumber: cacheST){
            Cache cache= cacheST.get(serialNumber);
            out.print(cache.userCreator.id + "," + cache.type + "," + cache.diff+ "," + cache.serialNumber + "," + cache.x + "," + cache.y + "," + cache.regiao + "\n");
        }
        out.close();
    }


    public static void readCaches(){
        In in = new In(".//data//cache.txt");
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] words = line.split(",");
            CacheDiff cdif = CacheDiff.Easy;
            CacheType ctype = CacheType.Basic;
            if(words[1].equals("Premium"))ctype=CacheType.Premium;
            if(words[2].equals("Medium"))cdif=CacheDiff.Medium;
            else if (words[2].equals("Hard"))cdif=CacheDiff.Hard;

            UserPremium creator = (UserPremium) userST.get(words[0]);
            int x = Integer.parseInt(words[4]);
            int y = Integer.parseInt(words[5]);
            Cache cache = new Cache(words[3],cdif,ctype,creator,x,y,words[6]);

            cacheST.put(cache.serialNumber,cache);


        }
    }

    public static void saveObjetosCache(){
        Out out = new Out(".//data//objetosCaches.txt");
        for (String key : cacheST.keys()){
            Cache c = cacheST.get(key);
            if (c.meusObjetos.size() > 0) {
                for (String key2 : c.meusObjetos.keys()) {
                    Objeto  obj = c.meusObjetos.get(key2);
                    if(obj.getClass().equals(Objeto.class)){
                            out.print("objeto" + ","+ obj.nameItem + "," + obj.id + "," +obj.criadorObjeto.id  + "," + obj.cacheAtual.serialNumber+ "\n");
                    }else{
                        TravelBugs tb = (TravelBugs) c.meusObjetos.get(key2);
                        out.print("travelbug" + ","+ tb.nameItem + "," + tb.id + "," +tb.criadorObjeto.id + ","+  tb.cacheAtual.serialNumber+","+ tb.cacheDestino.serialNumber+ "\n");

                    }

                }
            }
        }
        out.close();
    }


    public static void readObjetosCache(){
        In in = new In(".//data//objetosCaches.txt");
        while(in.hasNextLine()){
            String line = in.readLine();
            String [] words = line.split(",");
            if(words[0].equals("objeto")){
                UserBasic userCeator = userST.get(words[3]);
                Cache cacheAtual = cacheST.get(words[4]);
                Objeto objeto = new Objeto(words[2],words[1], userCeator);
                objeto.cacheAtual=cacheAtual;
                objeto.userAtual=null;
                cacheAtual.meusObjetos.put(objeto.id,objeto);

            }else{
                UserPremium userCeator = (UserPremium) userST.get(words[3]);
                Cache cacheAtual = cacheST.get(words[4]);
                Cache cacheDestino = cacheST.get(words[5]);
                TravelBugs travelBugs = new TravelBugs(words[2],words[1],userCeator,cacheDestino );
                travelBugs.cacheAtual=cacheAtual;
                travelBugs.userAtual=null;
                cacheAtual.meusObjetos.put(travelBugs.id,travelBugs);
                userCeator.meusTravelBugs.put(travelBugs.id,travelBugs);

            }
        }
        in.close();
    }


    public static void saveObjetosUsers(){
        Out out = new Out(".//data//objetosUsers.txt");
        for (String key : userST.keys()){
            UserBasic user = userST.get(key);
            if (user.myObjetos.size() > 0) {
                for (String key2 : user.myObjetos.keys()) {
                    Objeto  obj = user.myObjetos.get(key2);
                    if(obj.getClass().equals(Objeto.class)){
                        out.print("objeto" + ","+ obj.nameItem + "," + obj.id + "," +obj.criadorObjeto.id  + "," + obj.userAtual.id+ "\n");
                    }else{
                        TravelBugs tb = (TravelBugs) user.myObjetos.get(key2);
                        out.print("travelbug" + ","+ tb.nameItem + "," + tb.id + "," +tb.criadorObjeto.id + ","+  tb.userAtual.id+","+ tb.cacheDestino.serialNumber+ "\n");

                    }

                }
            }
        }
        out.close();
    }


    public static void readObjetosUsers(){
        In in = new In(".//data//objetosUsers.txt");
        while(in.hasNextLine()){
            String line = in.readLine();
            String [] words = line.split(",");
            if(words[0].equals("objeto")){
                UserBasic userCeator = userST.get(words[3]);
                UserBasic userAtual = userST.get(words[4]);
                Objeto objeto = new Objeto(words[2],words[1], userCeator);
                objeto.cacheAtual=null;
                objeto.userAtual=userAtual;
                userAtual.myObjetos.put(objeto.id,objeto);

            }else{
                UserPremium userCeator = (UserPremium) userST.get(words[3]);
                UserPremium userAtual = (UserPremium) userST.get(words[4]);
                Cache cacheDestino = cacheST.get(words[5]);
                TravelBugs travelBugs = new TravelBugs(words[2],words[1],userCeator,cacheDestino );
                travelBugs.cacheAtual=null;
                travelBugs.userAtual=userAtual;
                userAtual.myObjetos.put(travelBugs.id,travelBugs);
                userCeator.meusTravelBugs.put(travelBugs.id,travelBugs);

            }
        }
        in.close();
    }


    public static void savehCachesehUsers(){
        Out out = new Out(".//data//hCacheshUsers.txt");
        for (String key: userST.keys()){
            UserBasic userBasic = userST.get(key);
            if (userBasic.hCaches.size() > 0) {
                for (Date d : userBasic.hCaches.keys()) {
                    Cache cache = userBasic.hCaches.get(d);
                    out.print(userBasic.id + "," + cache.serialNumber + "," + d.day + "," + d.month +"," + d.year + "\n");
                }
            }
        }
        out.close();
    }

    public static void readhCacheshUsers(){
        In in = new In(".//data//hCacheshUsers.txt");
        while(in.hasNextLine()){
            String line = in.readLine();
            String []words = line.split(",");
            Cache cache = cacheST.get(words[1]);
            UserBasic user = userST.get(words[0]);
            int day = Integer.parseInt(words[2]);
            int month = Integer.parseInt(words[3]);
            int year = Integer.parseInt(words[4]);
            Date date = new Date(day,month,year);
            cache.hUsers.put(date,user);
            user.hCaches.put(date,cache);
        }
    }

    public static void saveLogsCache(){
        Out out = new Out(".//data//LogsCache.txt");
        for (String serial : cacheST.keys()) {
            Cache cache = cacheST.get(serial);
            if (cache.cacheLogs.size() > 0) {
                for (CacheLogs clogs : cache.cacheLogs) {
                    out.print(cache.serialNumber + "," +clogs.userID + "," + clogs.objetocolocado + "," + clogs.objetoretirado +
                            "," + clogs.data.day + "," + clogs.data.month + "," + clogs.data.year+ "\n");
                }
            }
        }
        out.close();
    }

    public static void readLogsCache(){
        In in = new In(".//data//LogsCache.txt");
        while (in.hasNextLine()){
            String line = in.readLine();
            String []words = line.split(",");
            Cache cache= cacheST.get(words[0]);
            int dia=Integer.parseInt(words[4]), mes=Integer.parseInt(words[5]),ano=Integer.parseInt(words[6]);
            Date date = new Date(dia,mes,ano);
            CacheLogs clogs = new CacheLogs(date,words[1],words[2],words[3]);
            if(clogs.objetoretirado.equals("null"))clogs.objetoretirado=null;
            if(clogs.objetocolocado.equals("null"))clogs.objetocolocado=null;
            cache.cacheLogs.add(clogs);
        }
        in.close();
    }



    public static void saveLogsUser() {
        Out out = new Out(".//data//LogsUser.txt");
        for (String id : userST.keys()) {
            UserBasic userBasic = userST.get(id);
            if (userBasic.userLogs.size() > 0) {
                for (Date date : userBasic.userLogs.keys()) {
                    UserLogs userLogs = userBasic.userLogs.get(date);
                    out.print(userBasic.id + "," + userLogs.serialNumber+ "," +userLogs.objetocolocado+ "," + userLogs.objetoretirado
                            + "," +date.day + "," + date.month +"," + date.year+"\n");
                }
            }
        }
        out.close();
    }


    public static void readLogsUsers(){
        In in = new In(".//data//LogsUser.txt");
        while (in.hasNextLine()){
            String line = in.readLine();
            String []words = line.split(",");
            UserBasic user= userST.get(words[0]);
            int dia=Integer.parseInt(words[4]), mes=Integer.parseInt(words[5]),ano=Integer.parseInt(words[6]);
            Date date = new Date(dia,mes,ano);
            UserLogs userLogs = new UserLogs(date,words[1],words[2],words[3]);
            if(userLogs.objetoretirado.equals("null"))userLogs.objetoretirado=null;
            if(userLogs.objetocolocado.equals("null"))userLogs.objetocolocado=null;
            user.userLogs.put(date,userLogs);
        }
        in.close();
    }


    public static void saveMessageLogs(){
        Out out = new Out(".//data//LogsMessage.txt");
        for (String key : cacheST.keys()){
            Cache cache= cacheST.get(key);
            for (MessageLog messageLog: cache.messageLogs){
                out.print(cache.serialNumber + "," + messageLog.mensagem + "\n");
            }
        }
        out.close();
    }

    public static void readMessageLogs(){
        In in = new In(".//data//LogsMessage.txt");
        while(in.hasNextLine()){
            String line = in.readLine();
            String [] words = line.split(",");
            Cache cache = cacheST.get(words[0]);
            MessageLog messageLog= new MessageLog(words[1]);
            cache.messageLogs.add(messageLog);
        }
        in.close();
    }


    public static void saveTravelBugsHCaches(){
        Out out = new Out(".//data//TravelBugsHCaches.txt");
        for (String name : userST.keys()) {
            UserBasic userBasic = userST.get(name);
            if (!userBasic.getClass().equals(UserBasic.class)) {
                UserPremium userPremium = (UserPremium) userBasic;
                if (userPremium.meusTravelBugs.size() > 0) {
                    for (String id : userPremium.meusTravelBugs.keys()) {
                        TravelBugs travelBugs = userPremium.meusTravelBugs.get(id);
                        for (String key :travelBugs.historicoCaches.keys()){
                            Cache cache= cacheST.get(key);
                            out.print(travelBugs.id + "," + cache.serialNumber + "\n");

                        }
                    }
                }
            }
        }
        out.close();

    }

    public static void readTravelBugsHCaches(){
        In in = new In(".//data//TravelBugsHCaches.txt");
        while(in.hasNextLine()){
            String line = in.readLine();
            String []words = line.split(",");
            TravelBugs travelBugs = UserAdmin.findTravelBug(words[0]);
            Cache cache = cacheST.get(words[1]);
            assert travelBugs != null;
            travelBugs.historicoCaches.put(cache.serialNumber,cache);
        }
        in.close();
    }



    public static void saveTravelBugsHCUsers(){
        Out out = new Out(".//data//TravelBugsHUsers.txt");
        for (String id : userST.keys()) {
            UserBasic userBasic = userST.get(id);
            if (!userBasic.getClass().equals(UserBasic.class)) {
                UserPremium userPremium = (UserPremium) userBasic;
                if (userPremium.meusTravelBugs.size() > 0) {
                    for (String id2 : userPremium.meusTravelBugs.keys()) {
                        TravelBugs travelBugs = userPremium.meusTravelBugs.get(id2);
                        for (String key :travelBugs.historicoUsers.keys()){
                            UserPremium puser= (UserPremium) userST.get(key);
                            out.print(travelBugs.id + "," + puser.id + "\n");

                        }
                    }
                }
            }
        }
        out.close();
    }

    public static void readTravelBugsHUsers(){
        In in = new In(".//data//TravelBugsHUsers.txt");
        while(in.hasNextLine()){
            String line = in.readLine();
            String []words = line.split(",");
            TravelBugs travelBugs = UserAdmin.findTravelBug(words[0]);
            UserPremium puser = (UserPremium) userST.get(words[1]);
            assert travelBugs != null;
            travelBugs.historicoUsers.put(puser.id, puser);
        }
        in.close();
    }


    public static void saveTravelBugsLogs(){
        Out out = new Out(".//data//TravelBugsLogs.txt");
        for (String id : userST.keys()) {
            UserBasic userBasic = userST.get(id);
            if (!userBasic.getClass().equals(UserBasic.class)) {
                UserPremium userPremium = (UserPremium) userBasic;
                if (userPremium.meusTravelBugs.size() > 0) {
                    for (String id2 : userPremium.meusTravelBugs.keys()) {
                        TravelBugs travelBugs = userPremium.meusTravelBugs.get(id2);
                       for (TravelBugsLogs tblogs : travelBugs.historicoTravelBugsLogs){
                           if(tblogs.p==null){
                               out.print(travelBugs.criadorObjeto.id + "," + travelBugs.id + "," + tblogs.user + "," + tblogs.cache + "," +
                                       tblogs.date.day  + "," +tblogs.date.month  + "," +tblogs.date.year  + "," + tblogs.c.serialNumber + "," + tblogs.destinoConcluido + ",CACHE\n");
                           }else{
                               out.print(travelBugs.criadorObjeto.id + "," + travelBugs.id + "," + tblogs.user + "," + tblogs.cache + "," +
                                       tblogs.date.day  + "," +tblogs.date.month  + "," +tblogs.date.year  + "," + tblogs.p.id + "," + tblogs.destinoConcluido + ",USER\n");
                           }
                       }
                    }
                }
            }
        }
        out.close();
    }


    public static void readTravelBugsLogs(){
        In in = new In(".//data//TravelBugsLogs.txt");
        while(in.hasNextLine()){
            String line = in.readLine();
            String []words = line.split(",");
            UserPremium puser = (UserPremium) userST.get(words[0]);
            TravelBugs tb = puser.meusTravelBugs.get(words[1]);
            if(words[9].equals("CACHE")){//logs esta numa cache
                Cache cache = cacheST.get(words[7]);
                int dia = Integer.parseInt(words[4]),mes = Integer.parseInt(words[5]),ano = Integer.parseInt(words[6]);
                Date date = new Date(dia,mes,ano);
                TravelBugsLogs tblogs = new TravelBugsLogs(words[3], words[2],cache,null,date );
                if(words[8].equals("true"))tblogs.destinoConcluido=true;
                else tblogs.destinoConcluido=false;
                tb.historicoTravelBugsLogs.add(tblogs);

            }else{//logs esta num user
                UserPremium user = (UserPremium) userST.get(words[2]);
                int dia = Integer.parseInt(words[4]),mes = Integer.parseInt(words[5]),ano = Integer.parseInt(words[6]);
                Date date = new Date(dia,mes,ano);
                TravelBugsLogs tblogs = new TravelBugsLogs(words[3], words[2],null,user,date );
                tblogs.destinoConcluido=false;
                tb.historicoTravelBugsLogs.add(tblogs);
            }
        }
        in.close();
    }


    @Override
    public String toString() {
        return "UserAdmin{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", cachesVisitadas=" + cachesVisitadas +
                '}';
    }
}
